/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * Classe du bouton "A propos" du menu permettant l'affichage du message lie a 'a propos'
 * @author Lakradi - Drouart
 */
public class MenuAProposAction extends AbstractAction 
{
    /**
     * Constructeur logique.
     * @param vuePrincipale vue principale de l'application
     * @param texte texte a affiche
     */
    public MenuAProposAction(VuePrincipale vuePrincipale, String texte) {
        super(texte);
        this.vuePrincipale = vuePrincipale;
    }

    /**
     * Action appele lors de l'appuie sur le bouton "a propos" du menu.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(vuePrincipale, "Ce programme a ete developpe pour le projet "
                + "annuel du L3 Informatique de la Fac de Sciences CAEN \n"
				+ "Projet Tuteure", "A propos", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Represente la vue principale de l'application.
     */
    private final VuePrincipale vuePrincipale;
}
