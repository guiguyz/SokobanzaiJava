/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import modele.Case.TypeCases;
import modele.Grille;

/**
 * 1 Instance de probleme de sokoban represente 1 niveau.
 * @author Lakradi - Drouart
 */
public class ProblemeSokoban implements ProblemeAbstrait<Grille, ActionSokoban>
{
    /**
     * Constructeur logique.
     * @param grille etat du probleme
     */
    public ProblemeSokoban(Grille grille){
        this.grille = grille;
        
        EnumSet<ActionSokoban> actions = EnumSet.allOf( ActionSokoban.class );
        ProblemeSokoban.listeActions = new ArrayList(actions);
    }
    
    /**
     * Permet de retourner une copie de la grille passe en argument,
     * suivant l'action de deplacement effectue par le personnage. 
     * @param grille sur laquelle on effectue l'action
     * @param action a effectuer sur la grille
     * @return un successeur de la grille passe en argument avec l'action associe.
     */
    @Override
    public Grille successeur(Grille grille, ActionSokoban action) {
        Grille copieGrille = grille.copie();
        switch(action){
            case EnHaut:
                copieGrille.lirePerso().enHaut();
                break;
            case EnBas:
                copieGrille.lirePerso().enBas();
                break;
            case ADroite:
                copieGrille.lirePerso().aDroite();
                break;
            case AGauche:
                copieGrille.lirePerso().aGauche();
                break;
            default:
                copieGrille.lirePerso();
                break;
        }
        return copieGrille;
    }

    /**
     * Permet de savoir si le probleme est terminal.
     * @param grille sur laquelle on test si un probleme est terminal ou non
     * @return victoire, un booleen
     */
    @Override
    public boolean estTerminal(Grille grille) {
        return grille.testVictoire();
    }

    
    //--------------------- ACCESSEUR ---------------------//
    /**
     * Renvoie la grille du probleme.
     * @return grille du probleme
     */
    @Override
    public Grille lireEtat() {
        return grille;
    }

    /**
     * Renvoie la liste des actions possible sur une grille.
     * @param grille du probleme courant
     * @return liste d'action (ici les deplacements du personnage)
     */
    @Override
    public List<ActionSokoban> lireActions(Grille grille) {
        return listeActions;
    }
    //----------------------------------------------------//

    /**
     * Represente notre grille de jeu (associe au probleme).
     */
    protected Grille grille;
    
    /**
     * Represente la liste des actions d'un personnage.
     */
    protected static List<ActionSokoban> listeActions;
    //----------------------------------------------------//
}
