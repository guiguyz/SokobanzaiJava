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
 *  Classe du bouton "Aide" du menu permettant l'affichage du message lie a 'aide'
 * @author Lakradi - Drouart
 */
public class MenuAide extends AbstractAction
{
    /**
     * Constructeur logique.
     * @param vuePrincipale vue principale de l'application
     * @param texte texte a affiche
     */
    public MenuAide(VuePrincipale vuePrincipale, String texte) {
        super(texte);
        this.vuePrincipale = vuePrincipale;
    }
    
    /**
     * Action appele lors de l'appuie sur le bouton "aide" du menu.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(vuePrincipale,"Le joueur doit ranger des caisses sur des cases cibles.\n"
                + "Il peut se déplacer dans les quatre directions, a l’aide des flèches directionnelles.\n"
                + "Le joueur peut pousser (mais pas tirer) une seule caisse à la fois.\n"
                + "Une fois toutes les caisses rangées, le niveau est réussi et le joueur passe au niveau suivant, plus difficile en général.\n"
                + "L'idéal est de réussir avec le moins de coups possibles (déplacements et poussées).","Aide",JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Represente la vue principale de l'application.
     */
    private final VuePrincipale vuePrincipale;
}
