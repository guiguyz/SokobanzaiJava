/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.deplacements;

import modele.Grille;

/**
 * Classe qui permettant de faire "glisser" les caisses d'une case de plus
 * avec une certaine probabilite.
 * @author Lakradi - Drouart
 */
public class DeplacementGlissant implements DeplacementAbstrait
{
    /**
     * Constructeur logique.
     * @param grille du jeu
     */
    public DeplacementGlissant(Grille grille){
        this.grille = grille;
    }
    
    /**
     *  Methode effectuant les tests et deplacement sur la grille du personnage 
     * en fonction des valeurs representant les coordonnees x,y
     * et actualise le type de case apres deplacement, et une fois sur 
     * dix fait glisser les caisses d'une case de plus
     * TODO.
     * @param deltaX coordonnee x de deplacement
     * @param deltaY coordonnee y de deplacement
     */
    @Override
    public void deplacementVers(int deltaX, int deltaY){}

    /**
     * Permet un deplacement vers le haut du personnage.
     */
    @Override
    public void enHaut(){
        deplacementVers(-1, 0);
    }

    /**
     * Permet un deplacement vers le bas du personnage.
     */
    @Override
    public void enBas(){
        deplacementVers(+1, 0);
    }

    /**
     * Permet un deplacement vers la gauche du personnage.
     */
    @Override
    public void aGauche(){
        deplacementVers(0, -1);
    }

    /**
     * Permet un deplacement vers la droite du personnage.
     */
    @Override
    public void aDroite(){
        deplacementVers(0, +1);
    }
    
    /**
     * Represente notre grille de jeu.
     */
    protected Grille grille;
}
