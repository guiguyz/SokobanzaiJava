/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.deplacements;

/**
 *
 * @author marwan
 */
public interface DeplacementAbstrait {
    /**
     * Methode permettant le deplacement sur la grille du personnage 
     * en fonction des entier passe en parametre.
     * @param deltaX -1, 0 ou 1
     * @param deltaY -1, 0 ou 1
     */
    void deplacementVers(int deltaX, int deltaY);
    
    /**
     *  Permet un deplacement vers le haut du personnage.
     */
    void enHaut();
    
    /**
     * Permet un deplacement vers le bas du personnage.
     */
    void enBas();
    
    /**
     * Permet un deplacement vers la gauche du personnage.
     */
    void aGauche();
    
    /**
     * Permet un deplacement vers le droite du personnage.
     */
    void aDroite();
}
