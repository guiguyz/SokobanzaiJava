/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;

/**
 * Class du bouton "Choix du niveau" du menu permettant d'aller a un niveau choisi.
 * @author Lakradi - Drouart
 */
public class MenuAllerAuNiveau extends AbstractAction 
{
    /**
     * Constructeur logique.
     * @param vuePrincipale vue principale de l'application
     * @param texte texte a afficher
     */
    public MenuAllerAuNiveau(VuePrincipale vuePrincipale, String texte){
        super(texte);
        this.vuePrincipale = vuePrincipale;
    }

    /**
     * Action appele lors de l'appuie sur le bouton "Choix du niveau" du menu.
     * @param e action evenement
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String numNiveau = showInputDialog(vuePrincipale, "Numero du niveau voulu : ", "Taper un nombre entre 1 et 100");
        try{
            this.numeroNiveau = Integer.parseInt(numNiveau);
            if(numeroNiveau < 1 || numeroNiveau > 100){
                    throw new NumberFormatException();
            }
            vuePrincipale.allerAuNiveau(numeroNiveau); 
        }
        catch(NumberFormatException error){
            JOptionPane.showMessageDialog(vuePrincipale,"ERREUR le numero du niveau doit etre compris entre 1 et 100 !", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Represente le niveau choisi par l'utilisateur.
     */
    public int numeroNiveau;
    
    /**
     * Represente la vue principale de l'application.
     */
    private final VuePrincipale vuePrincipale;
}
