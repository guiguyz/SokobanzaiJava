/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.objets;

import modele.deplacements.DeplacementAbstrait;
import java.util.Observable;
import modele.Case.TypeCases;
import modele.Grille;

/**
 * Classe observable representant un personnage du jeu.
 * @author Lakradi - Drouart
 */
public class Personnage extends Observable implements DeplacementAbstrait
{   
    /**
     * Contructeur logique.
     * @param x coordonne x voulue
     * @param y coordonne y voulue
     * @param grille grille sur laquelle le personnage est instanciee
     */
    public Personnage(int x, int y, Grille grille){
        this.x = x;
        this.y = y;
        this.grille = grille;
        this.nbPas = 0;
        this.nbPousse = 0;
    }

    /**
     * Methode effectuant les tests et deplacement sur la grille du personnage 
     * en fonction des valeurs representant les coordonnees x,y
     * et actualise le type de case apres deplacement.
     * @param deltaX coordonnee x pouvant avoir comme valeur -1, 0 ou +1.
     * @param deltaY coordonnee y pouvant avoir comme valeur -1, 0 ou +1.
     */
    @Override
    public void deplacementVers(int deltaX, int deltaY){
        //------------------------------------------------------------------------------------------------------------//
        //booleens utilisee pour tester la validite d'un deplacement
        boolean mur = this.grille.lireCases()[this.x+deltaX][this.y+deltaY].lireStatut() == TypeCases.Mur;
        
	boolean but = (this.grille.lireCases()[this.x+deltaX][this.y+deltaY].lireStatut() == TypeCases.Objectif) 
                || (this.grille.lireCases()[this.x+deltaX][this.y+deltaY].lireStatut() == TypeCases.CaisseSurObj);
        
	boolean caisse = (this.grille.lireCases()[this.x+deltaX][this.y+deltaY].lireStatut() == TypeCases.CaisseSurObj)
                || (this.grille.lireCases()[this.x+deltaX][this.y+deltaY].lireStatut() == TypeCases.Caisse);
        
        boolean persoSurBut = ( this.grille.lireCases()[this.x][this.y].lireStatut() == TypeCases.PersoSurObj );
        //------------------------------------------------------------------------------------------------------------//
        if (!mur) {
            if(!caisse){
                //Si le perso est sur un objectif, alors apres deplacement 
                //on change le type de la case à objectif, 
                //sinon une case vide.
                this.grille.lireCases()[this.x][this.y].setStatut((persoSurBut) ? TypeCases.Objectif : TypeCases.CaseVide);
                //Si la case au dessus du perso est sur un objectif, alors apres deplacement 
                //on change le type de la case à Personnage sur objectif, 
                //sinon simplement Personnage.
                this.grille.lireCases()[this.x+deltaX][this.y+deltaY].setStatut((but) ? TypeCases.PersoSurObj : orientationPerso);
                System.out.println("ORIENTATION : " + orientationPerso);
                this.x += deltaX;
                this.y += deltaY;
                this.nbPas++;
            }
            else{
                int delta2X = deltaX*2;
                int delta2Y = deltaY*2;
                //Variable permettant de tester la deuxieme case a proximite du Personnage.
		TypeCases auDessusDeLaCaisse = this.grille.lireCases()[this.x+delta2X][this.y+delta2Y].lireStatut();
                System.out.println("A UNE CASE DE LA CAISSE : " + auDessusDeLaCaisse);
		switch(auDessusDeLaCaisse) {
                    case CaseVide :
                        this.grille.lireCases()[this.x][this.y].setStatut((persoSurBut) ? TypeCases.Objectif : TypeCases.CaseVide);
                        this.grille.lireCases()[this.x+deltaX][this.y+deltaY].setStatut((but) ? TypeCases.PersoSurObj : orientationPerso);
                        //Si on a une case vide a 1 case de la caisse, 
                        //alors on change le type de la case en Caisse;
                        this.grille.lireCases()[this.x+delta2X][this.y+delta2Y].setStatut(TypeCases.Caisse);
                        this.x += deltaX;
                        this.y += deltaY;
                        this.nbPas++;
                        this.nbPousse++;
                        break;  
                    case Objectif :
                        this.grille.lireCases()[this.x][this.y].setStatut((persoSurBut) ? TypeCases.Objectif : TypeCases.CaseVide);                     
                        this.grille.lireCases()[this.x+deltaX][this.y+deltaY].setStatut((but) ? TypeCases.PersoSurObj : orientationPerso);
                        //Si on a un objectif a 1 cases de la caisse, 
                        //alors on change le type de la case en CaisseSurObj;
                        this.grille.lireCases()[this.x+(deltaX*2)][this.y+(deltaY*2)].setStatut(TypeCases.CaisseSurObj);
                        this.x += deltaX;
                        this.y += deltaY;
                        this.nbPas++;
                        this.nbPousse++;
                        break;   
                    default:
                        break;
		}//switch
            }//else
	}//if
    }
    
    /**
     * Permet de notifier son obesrveur et
     * teste la victoire du joueur a sa nouvelle position.
     */
    public void notifier(){
        setChanged();
        notifyObservers();
        this.grille.testVictoire();
    }
    
    /**
     * Permet un deplacement vers le haut du personnage.
     */
    @Override
    public void enHaut(){
        orientationPerso = TypeCases.PersoHaut;
        deplacementVers(-1,0);
    }
    
    /**
     * Permet un deplacement vers le bas du personnage.
     */
    @Override
    public void enBas(){
        orientationPerso = TypeCases.PersoBas;
        deplacementVers(+1,0);
    }
    
    /**
     * Permet un deplacement vers la gauche du personnage.
     */
    @Override
    public void aGauche(){
        orientationPerso = TypeCases.PersoGauche;
        deplacementVers(0,-1);
    }
    
    /**
     * Permet un deplacement vers la droite du personnage.
     */
    @Override
    public void aDroite(){
        orientationPerso = TypeCases.PersoDroite;
        deplacementVers(0,+1);
    }
    
    //--------------------- MUTATEUR ---------------------//
    public void setX(int newX){
        this.x = newX;
    }
    
    public void setY(int newY){
        this.y = newY;
    }
    /**
     * Permet de modifier l'orientation du personnage.
     * @param nOrientation nouvelle orientation
     */
    public void setOrientationPerso(TypeCases nOrientation){
        this.orientationPerso = nOrientation;  
    }
    //----------------------------------------------------//
    
    //--------------------- ACCESSEUR ---------------------//
    /**
     * Renvoie l'enum permettant de savoir l'orientation du personnage.
     * @return orientationPerso
     */
    public TypeCases lireOrientationPerso(){
        return orientationPerso;
    }
    
    /**
     * Renvoie le nombre de pas effectues 
     * par le personnage dans la grille.
     * @return nbPas nombre de pas du personnage
     */
    public int lireNbPas(){
        return nbPas;
    }
    
    /**
     * Renvoie le nombre de pousse de caisse effectues 
     * par le personnage dans la grille.
     * @return nbPousse nombre de pousse de caisse du personnage
     */
    public int lireNbPousses(){
        return nbPousse;
    }
    
    /**
     * Renvoie la coordonnee correspondant a la ligne du personnage.
     * @return x
     */
    public int lireX(){
        return x;
    }
    
    /**
     * Renvoie la coordonnee correspondant a la colonne du personnage.
     * @return y
     */
    public int lireY(){
        return y;
    }
    //------------------------------------------------------//
    
    /**
     * Represente notre grille de jeu.
     */
    protected Grille grille;
    
    /**
     * Permet de savoir l'orientation du Personnage.
     */
    protected TypeCases orientationPerso;

    /**
     * Coordonnee X, Y, nombre de deplacement 
     * et nombre de pousse de caisse du Personnage.
     */
    protected int x, y, nbPas, nbPousse;
   
}
