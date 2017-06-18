/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modele.Grille;

/**
 * Classe representant les divers labels permettant de savoir :
 * le nombre de pas, 
 * le nombre de pousse de caisse du personnage 
 * et le numero du niveau courant.
 * @author Lakradi - Drouart
 */
public class LabelsPerso extends JPanel implements Observer
{
    /**
     * Constructeur logique.
     * @param grille grille du jeu
     */
    public LabelsPerso(Grille grille){
        this.grille = grille;
        
        nombrePas = new JLabel("Pas : "+grille.lirePerso().lireNbPas()+"    ");   
        nombrePas.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombrePas);
        
        nbPousses = new JLabel("Pousses : "+grille.lirePerso().lireNbPousses()+"    ");
        nbPousses.setHorizontalAlignment(SwingConstants.CENTER);
        add(nbPousses);
        
        numNiveau = new JLabel("Niveau "+grille.lireGestionNiveau().lireNiveauCourant()+" sur 100");
        numNiveau.setHorizontalAlignment(SwingConstants.CENTER);
        add(numNiveau);
        
        this.grille.lirePerso().addObserver(this);
    }
   
    /**
     * Appele lors d'un deplacement du personnage, 
     * ou lors d'un changement de niveau.
     * @param o personnage ou niveau
     * @param arg argument passe a la methode notifyObservers des classes Personnage et GestionNiveau
     */
    @Override
    public void update(Observable o, Object arg) {
        nombrePas.setText("Pas : "+grille.lirePerso().lireNbPas()+"    ");
        nbPousses.setText("Pousses : "+grille.lirePerso().lireNbPousses()+"    ");
        numNiveau.setText("Niveau "+grille.lireGestionNiveau().lireNiveauCourant()+" sur 100");
    }
    
    /**
     * Met a jour les labels lors du changement de la vue principale.
     * @param grille grille du jeu
     */
    public void miseAJour(Grille grille){
        nombrePas.setText("Pas : " + 0+"    ");
        nbPousses.setText("Pousses : "+ 0+"    ");
        numNiveau.setText("Niveau "+grille.lireGestionNiveau().lireNiveauCourant()+" sur 100");
        this.grille = grille;
        this.grille.lireGestionNiveau().addObserver(this);
        this.grille.lirePerso().addObserver(this);
    }
    
    /**
     * Label representant le nombre de pas et de pousse du personnage, 
     * et le numero du niveau actuel.
     */
    private final JLabel nombrePas, nbPousses, numNiveau;
    
    /**
     * Notre grille de jeu.
     */
    private Grille grille;
}
