/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import modele.objets.Personnage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import modele.Case.TypeCases;

/**
 * Classe representant la grille du jeu courant.
 * @author Lakradi - Drouart
 */
public final class Grille
{
    /**
     * Constructeur logique.
     * @param niveau Le numero du niveau pour initialiser la grille
     */
    public Grille(int niveau){
        this.initialiserGrille(niveau);
    }
    
    /**
     * Constructeur logique.
     * @param gestionNiveau instance du singleton gestionNiveau
     */
    public Grille(GestionNiveau gestionNiveau)
    {
        this.gestionNiveau = gestionNiveau;
        this.initialiserGrille(this.gestionNiveau.lireNiveauCourant());
    }

    /** Appelle le ChargeurNiveau pour le niveau donne
     * @param niveau Le numero du niveau a charger
     */	
    public void initialiserGrille(int niveau)
    {  
        try
        {
            gestionNiveau.allerAuNiveau(niveau);
            String fichierNiveau = "src/ressources/niveaux/lvl" + niveau + ".sok";
            //recupere l'instance du singleton et instancie le niveau sur la grille
            //avec le niveau associe
            ChargeurNiveau chargeurNiveau = ChargeurNiveau.getInstance();
            chargeurNiveau.initialiserNiveau(this, fichierNiveau);
        }catch(IOException e){}
    }
    
    /**
     * Rappele par le chargeur de niveau du fichier parcouru
     * Instancie nbLignes et nbColonnes pour eviter des cases superflues selon le tableau de cases charge.
     * @param nCases tableau representant les cases de la grille
     */
    public void initialiserGrille(Case[][] nCases)
    {
        nbLignes = 0;
        nbColonnes = 0;
        victoire = false;
        flagPartieFinie = false;
        reinit = true;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 19; j++) {
                if (nCases[i][j].lireStatut() == TypeCases.Mur){
                    nbLignes = i;
                    nbColonnes = (j >= nbColonnes) ? j : nbColonnes;
                }//if
                if ( nCases[i][j].lireStatut() == TypeCases.PersoBas || 
                        nCases[i][j].lireStatut() == TypeCases.PersoSurObj ){
                    this.personnage = new Personnage(i, j, this);
                }//if
                this.cases[i][j] = new Case(nCases[i][j]);
            }//forj
        }//fori
    }

    /**
     * Test la victoire du joueur,
     * le joueur est victorieux lorsqu'il n'y a plus d'objectif
     * et que le personnage n'est pas sur un objectif de la grille.
     * @return true si la partie est gagné, false sinon
     */
    public boolean testVictoire(){
        flagPartieFinie = false;
        victoire = true;
        for(int i = 1; i < nbLignes; i++) {
            for(int j = 1; j < nbColonnes; j++) {
                //Operateur ET binaire.
                victoire = victoire & (this.cases[i][j].lireStatut() != TypeCases.Objectif) && 
                        (this.cases[i][j].lireStatut() != TypeCases.PersoSurObj);
            }
        }
        if(victoire)
            flagPartieFinie = true;
        
        return flagPartieFinie;
    }
    
    /**
     * Appele lorsque le joueur obtient la victoire,
     * permet d'initialiser la grille du niveau suivant.
     */
    public void partieTerminee(){
        if(this.flagPartieFinie){
            gestionNiveau.niveauSuivant();
            initialiserGrille(gestionNiveau.lireNiveauCourant());
        }
    }
    
    /**
     * Permet de reinitialiser la grille 
     * ainsi que toute les variables de classe.
     */
    public void recommencer() {
        initialiserGrille(gestionNiveau.lireNiveauCourant());	
        reinit = true;
    }
    
    /**
     * Retourne une copie de la grille.
     * @return copieGrille
     */
    public Grille copie(){
        Grille copieGrille = new Grille(gestionNiveau);
        for (int i = 1; i < copieGrille.nbLignes; i++) {
            for (int j = 1; j < copieGrille.nbColonnes; j++) {
                TypeCases copieType = this.cases[i][j].lireStatut();
                Personnage copiePerso = this.lirePerso();
                copieGrille.lirePerso().setX(copiePerso.lireX());
                copieGrille.lirePerso().setY(copiePerso.lireY());
                copieGrille.cases[i][j].setStatut(copieType);
            }
        }
        return copieGrille;
    }
    
    /**
     * Redefinition de la methode equals de Object de l'api Java,
     * permettant de comparer l'egalite des statut des cases et
     * de la position du personnage entre la grille actuelle et celle
     * passee en parametre.
     * @param obj de type Grille, a passe en parametre
     * @return true si les deux grille sont egale, false sinon
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Grille)){
            return false;
        }
        Grille uneGrille = (Grille)obj;
        for (int i = 1; i < nbLignes; i++) {
            for (int j = 1; j < nbColonnes; j++) {
                if(this.cases[i][j].lireStatut() != uneGrille.cases[i][j].lireStatut()){
                    return false;
                }
            }
        }
        return ((this.lirePerso().lireX() == uneGrille.lirePerso().lireX()) 
                && (this.lirePerso().lireY() == uneGrille.lirePerso().lireY() ) );
    }

    /**
     * Redefinition de la methode hashCode de la classe
     * Object de l'api Java
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.personnage);
        hash = 37 * hash + Arrays.deepHashCode(this.cases);
        hash = 37 * hash + this.nbLignes;
        hash = 37 * hash + this.nbColonnes;
        return hash;
    }
    
    /**
     * Permet d'afficher la grille en console.
     */
    public void afficherGrille(){
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 19; j++) {
                switch (cases[i][j].lireStatut()) {
                    case CaseVide:
                        System.out.print("▢ ");
                        break;
                    case Caisse:
                        System.out.print("$ ");
                        break;
                    case CaisseSurObj:
                        System.out.print("* ");
                        break;
                    case Mur:
                        System.out.print("# ");
                        break;
                    case PersoBas:
                        System.out.print("B ");
                        break;
                    case PersoHaut:
                        System.out.print("H ");
                        break;
                    case PersoGauche:
                        System.out.print("G ");
                        break;
                    case PersoDroite:
                        System.out.print("D ");
                        break;
                    case Objectif:
                        System.out.print("0 ");
                        break;
                    default:
                        System.out.print(cases[i][j].lireStatut()+" ");
                        break;
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    //****************** ACCESSEURS *********************//
    
    /**
     * Renvoie le niveau associe a la grille.
     * @return instance de gestionNiveau.
     * 
     */
    public GestionNiveau lireGestionNiveau(){
        return gestionNiveau;
    }
    
    /**
     * Renvoie le personnage dans la grille.
     * @return personnage de la grille
     */
    public Personnage lirePerso(){
        return personnage;
    }
    
    /**
     * Renvoie la case située sur la ligne "ligne" et la colonne "colonne"
     * @param ligne La ligne de la case voulue
     * @param colonne La colonne de la case voulue
     * @return emplacement en cases(ligne, colonne)
     */
    public Case contenuCaseEn( int ligne, int colonne ){
        return cases[ligne][colonne];
    }
    
    /**
     * Tableau representant les cases de la grille.
     * @return cases 
     */
    public Case[][] lireCases(){
        return cases;
    }
    
    /**
     * Renvoie le nombre de ligne de la grille. 
     * @return nbLignes
     */
    public int lireNbLignes(){
        return nbLignes;
    }
    
    /**
     * Renvoie le nombre de colonne de la grille. 
     * @return nbColonnes
     */
    public int lireNbColonnes(){
        return nbColonnes;
    }
    
    /**
     * Permet de savoir si la partie doit etre reinitialise.
     * @return le booleen reinit
     */
    public boolean estReinitialise(){
        return reinit;
    }
    

    public boolean lireFlagPartieFinie(){
        return flagPartieFinie;
    }
    
    //--------------------------------------------------//
    
    //--------------- VARIABLE DE CLASSE ---------------//
    
    /**
     * Represente le niveau actuel de la grille.
     */
    protected GestionNiveau gestionNiveau;
    
    /**
     * Represente le personnage de la grille.
     */
    protected Personnage personnage;
    
    /**
     * Represente les cases de la grille courante.
     */
    protected Case[][] cases = new Case[16][19]; 
    
    /**
     * Parametre de la grille representant le nombre de ligne, de colone,
     * du nombre de pas et le nombre de pousse de caisse du personnage.
     */
    protected int nbLignes, nbColonnes;
    
    /**
     * Variable booleene permettant de savoir de
     * tester la victoire du joueur a chaque deplacement.
     */
    protected boolean victoire = false;
    
    /**
     * Variable booleene permettant de savoir si
     * le jeu doit etre reinitialise.
     */
    protected boolean reinit = false;

    /**
     * Variable booleene permettant de savoir 
     * dans le presentateur si la partie est terminee.
     */
    protected boolean flagPartieFinie = false;
}
