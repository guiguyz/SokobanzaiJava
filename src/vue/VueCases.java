/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import modele.Case;

/**
 * Classe permettant de visualiser une case de la grille.
 * @author Lakradi - Drouart
 */
public class VueCases extends JLabel implements Observer 
{
    /**
     * Tableau de sprites d'images des divers cases.
     */
    private final ImageIcon[] sprite = {
        new ImageIcon("src/ressources/sprites/vide.gif"),
        new ImageIcon("src/ressources/sprites/mur.gif"),
        new ImageIcon("src/ressources/sprites/caisse.gif"),
        new ImageIcon("src/ressources/sprites/persoBas.gif"),
        new ImageIcon("src/ressources/sprites/persoHaut.gif"),
        new ImageIcon("src/ressources/sprites/persoGauche.gif"),
        new ImageIcon("src/ressources/sprites/persoDroite.gif"),
        new ImageIcon("src/ressources/sprites/zone.gif"),
        new ImageIcon("src/ressources/sprites/persoZone.gif"),
        new ImageIcon("src/ressources/sprites/caisseOK.gif"),
    };
    
    /**
     * Constructeur logique.
     * @param imageCase image d'une case
     */
    public VueCases(Case imageCase) {
        this.imageCase = imageCase;
        this.setIcon(sprite[imageCase.lireStatut().ordinal()]);    
        this.imageCase.addObserver(this);
    }
    
    /**
     * Permet de modifier l'image de la case en fonction de l'enum TypeCase
     * @param cases voulue
     */
    public void setCase(Case cases) {
        this.imageCase = cases;
        this.setIcon(sprite[imageCase.lireStatut().ordinal()]);
        this.imageCase.addObserver(this);
    }
    
    /**
     * Methode appelee quand l'objet observe a change.
     * @param observable personnage ou case
     * @param object objet a modifie
     */
    @Override
    public void update(Observable observable, Object object){
        this.setIcon(sprite[imageCase.lireStatut().ordinal()]);
    }
    
    /**
     * Represente l'image d'une case.
     */
    private Case imageCase;
}
