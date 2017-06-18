package presentateur;

import ia.Aetoile;
import ia.GrilleAvecValeur;
import ia.HeuristiqueSokoban;
import ia.ProblemeSokoban;
import java.util.ArrayList;
import java.util.LinkedList;
import modele.GestionNiveau;
import modele.Grille;
import vue.VuePrincipale;

/**
 * Classe representant le presentateur de l'application.
 * @author Lakradi - Drouart
 */
public final class Presentateur
{
    /**
     * Constructeur logique, instancie le niveau, la grille et la vue.
     */
    public Presentateur(){
        controleurEvent = new ControleurEvent(this);
        niveau = new GestionNiveau();
        modele = new Grille(this.niveau);
        vue = new VuePrincipale(this);
    }
    
    /**
     * Demarre ce presentateur en affichant la vue (utile pour rajout du menu JPanel).
     */
    public void demarrer() {
        vue.pack();
        vue.setResizable(false);
        vue.setVisible(true);
        jouer();
    }
    
    /**
     * Si la partie n'est pas terminer alors on fait avancer le presentateur.
     */
    public void jouer(){
        if(modele.lireFlagPartieFinie())
            fin();
        else
            suivant();		  
    }
    
    /**
     * Fait jouer le personnage.
     */
    public void suivant(){
        while(!modele.lireFlagPartieFinie()){
            vue.lirePanneauGrille().getKeyListeners();  
        }
        fin();
    }
    
    /**
     * Reinitialise le niveau courant 
     * et met a jour la vue.
     */
    public void recommencer(){
        modele.recommencer();
        vue.updateGrille(modele);
    }
    
    /**
     * Permet de resoudre le niveau 
     * a l'aide de l'algorithme A*.
     */
    public void resoudre(){
        ProblemeSokoban pbSoko = new ProblemeSokoban(modele);
        HeuristiqueSokoban h = new HeuristiqueSokoban(); 
        Aetoile aEtoile = new Aetoile<>(modele, pbSoko.lireActions(modele));
        LinkedList<GrilleAvecValeur> chemin = (LinkedList<GrilleAvecValeur>) aEtoile.resoudre(pbSoko, h);
        int nbDeplacement = 0;
        for (int i = 0; i < chemin.size(); i++) {
            modele = chemin.get(i).lireGrille();
            nbDeplacement++;
        }
        System.out.println("Nombre de deplacement : "+ nbDeplacement);
    }
    
    /**
     * Permet d'aller a un niveau choisi
     * et met a jour la grille et la vue.
     * @param numNiveau numero du niveau choisi
     */
    public void allerAuNiveau(int numNiveau){
        this.lireNiveau().allerAuNiveau(numNiveau);
        modele.initialiserGrille(this.lireNiveau().lireNiveauCourant());
        vue.updateGrille(modele);
    }
    
    /**
     * Lance la fin de la partie.
     */
    public void fin(){
        modele.partieTerminee();
        vue.partieTerminee();
    }
    
    /**
     * Instancie un nouveau modele avec son parametre
     * et met a jour la vue.
     * @param niveau instance du niveau charge
     */
    public void nouveauModele(GestionNiveau niveau) {
        this.niveau = niveau;
        Grille grille = new Grille(niveau);
        this.modele = grille;
        vue.updateGrille(modele);
    }
    
    /**
     * Instancie un nouveau modele de base
     * et met a jour la vue.
     */
    public void nouveauModele() {
        Grille grille = new Grille(niveau);
        this.modele = grille;
        vue.updateGrille(modele);
        demarrer();
    }
    
    //--------------------- ACCESSEUR ---------------------//
    /**
     * Renvoie la grille du jeu.
     * @return modele notre grille de jeu
     */
    public Grille lireModele(){
        return modele;
    }
    
    /**
     * Renvoie le niveau charge
     * @return niveau instance du niveau charge
     */
    public GestionNiveau lireNiveau(){
        return niveau;
    }
    
    /**
     * Renvoie le controleur d'evenement.
     * @return controleur d'evenement de notre jeu
     */
    public ControleurEvent lireControleurEvent(){
        return controleurEvent;
    }
    //------------------------------------------------------//
    
    //--------------- VARIABLE DE CLASSE ---------------//
    /**
     * Instance du controleur d'evenement.
     */
    protected ControleurEvent controleurEvent;
    
    /**
     * Instance du singleton niveau.
     */
    protected GestionNiveau niveau;
    
    /**
     * Instance de la grille.
     */
    protected Grille modele;
    
    /**
     * Instance de la vue.
     */
    protected VuePrincipale vue; 
}
