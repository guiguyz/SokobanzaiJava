/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.List;

/**
 * Interface representant un probleme abstrait avec des types generique
 * E (pour nous : grille) et A (pour nous : action).
 * @author Lakradi - Drouart
 * @param <E> represente un etat E
 * @param <A>represente une action A
 */
public interface ProblemeAbstrait <E, A>
{
    /**
     * Permet de retourner une copie de la grille passe en argument,
     * suivant l'action de deplacement effectue par le personnage. 
     * @param etat represente une grille a un instant donne
     * @param action represente l'action a effectue sur l'etat
     * @return E, la grille suivante de l'etat passe en argument avec l'action effectue dessus
     */
    E successeur(E etat, A action);
    
    /**
     * Permet de savoir si un etat (une grille) est terminale,
     * cet-a-dire si le personnage est victorieux ou non.
     * @param etat represente une grille a un instant donne.
     * @return true ou false selon si le probleme est resolu.
     */
    boolean estTerminal(E etat);
    
    //--------------------- ACCESSEUR ---------------------//
    /**
     * Renvoie l'etat de type E
     * @return E
     */
    E lireEtat();
    
    /**
     * Renvoie la liste des actions possible sur l'etat.
     * @param etat de type E (pour nous : une grille)
     * @return une liste de type A, une liste d'action plus precisement
     */
    List<A> lireActions(E etat);
    //-----------------------------------------------------//
}
