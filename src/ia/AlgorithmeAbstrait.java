/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.List;

/**
 * Interface representant un algorithme abstrait a 
 * appliquer sur un probleme abstrait 
 * avec une heuristique abstraite
 * @author Lakradi - Drouart
 * @param <E> represente dans notre cas, un etat du jeu Sokoban.
 * @param <A> represente dans notre cas, une liste d'action sur le jeu.
 */
public interface AlgorithmeAbstrait <E, A>
{
    /**
     * Methode permettant de resoudre un probleme
     * a l'aide d'un algorithme de resolution 
     * et une heuristique correspondante.
     * @param probleme celui sur lequel il faut executer l'algorithme
     * @param heuristique celle qu'il faut appliquer a l'algorithme, a condition qu'elle soit admissible
     * @return Une arraylist contenant la resolution du probleme
     */
    List<E> resoudre (
            ProblemeAbstrait <E, A> probleme, 
            HeuristiqueAbstrait<E> heuristique
    );
}
