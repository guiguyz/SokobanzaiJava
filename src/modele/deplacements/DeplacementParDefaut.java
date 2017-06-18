/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.deplacements;

import modele.Grille;

/**
 * Interface possedant les methodes de deplacement d'un personnage.
 * @author Lakradi - Drouart
 */
public class DeplacementParDefaut implements DeplacementAbstrait
{    
    /**
     * Constructeur logique.
     * @param grille du jeu
     */
    public DeplacementParDefaut(Grille grille){
        this.grille = grille;
    }

    @Override
    public void deplacementVers(int deltaX, int deltaY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enHaut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enBas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void aGauche() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void aDroite() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private final Grille grille;
}
