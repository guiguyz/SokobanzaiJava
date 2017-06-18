/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Classe du bouton permettant de quitter le jeu.
 * @author Lakradi - Drouart
 */
public class MenuQuitter extends AbstractAction
{
    /**
     * Constructeur logique.
     * @param texte texte a affiche
     */
    public MenuQuitter(String texte){
        super(texte);
    }
    
    /**
     * Action appele lors de l'appuie sur le bouton du menu "quitter".
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
