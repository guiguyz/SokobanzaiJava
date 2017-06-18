/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Classe du bouton "Resoudre par l'ia" du menu permettant de resoudre le niveau actuel par l'algorithme A*.
 * @author Lakradi - Drouart
 */
public class MenuResoudre extends AbstractAction {
    /**
     * Constructeur logique.
     * @param vuePrincipale vue principale de l'application
     * @param texte texte a affiche
     */
    public MenuResoudre(VuePrincipale vuePrincipale, String texte) {
        super(texte);
        this.vuePrincipale = vuePrincipale;
    }

    /**
     * Action appele lors de l'appuie sur le bouton "Recommencer "du menu.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        vuePrincipale.resoudre();
    }
    
    /**
     * Represente la vue principale de l'application.
     */
    private final VuePrincipale vuePrincipale;
}
