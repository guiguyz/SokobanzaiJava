/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

/**
 * Interface representant une heuristique 
 * des differentes distance manhattan.
 * @author Lakradi - Drouart
 * @param <E> represente un etat E
 */
public interface HeuristiqueAbstrait <E> 
{
    /**
     * Somme sur toutes les caisses
     * de la distance manhattan de cette caisse a 
     * l'objectif le plus proche non encore place. 
     * @param grille sur laquelle on evalue les distances manhattan
     * @return un entier representant l'evaluation de distance
     */
    public int distanceManhattanCaisseObjectif(E grille);
    
    /**
     * Distance manhattan entre le personnage et les differentes caisses
     * presente sur la grille.
     * @param grille sur laquelle on evalue les distances manhattan
     * @return un entier representant l'evaluation de distance
     */
    public int distanceManhattanPersonnageCaisse(E grille);
}
