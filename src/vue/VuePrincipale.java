/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import modele.Grille;
import presentateur.Presentateur;

/**
 * Classe representant la vue principale
 * @author Lakradi - Drouart
 */
public class VuePrincipale extends JFrame
{
    /**
     * Constructeur logique.
     * @param presentateur presentateur actuel
     */
    public VuePrincipale(Presentateur presentateur){
        this.presentateur = presentateur;
        addKeyListener(this.presentateur.lireControleurEvent());
        
        this.setTitle("Soko'Banzai");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
        panneauGrille = new PanneauGrille(presentateur.lireModele());
        this.getContentPane().add(panneauGrille, BorderLayout.CENTER);
        
        labelsPerso = new LabelsPerso(presentateur.lireModele());
        this.getContentPane().add(labelsPerso, BorderLayout.SOUTH);
        
        //Bar de menu
        JMenuBar menuBar = new JMenuBar();
        
        //-------------- Menu 1 ---------------//
        JMenu menuPartie = new JMenu("Partie");     
        JMenuItem quitter = new JMenuItem(new MenuQuitter("Quitter"));
        JMenuItem recommencer = new JMenuItem(new MenuRecommencer(this, "Recommencer (R)"));
        JMenuItem allerAuNiveau = new JMenuItem(new MenuAllerAuNiveau(this, "Choix du niveau"));
        JMenuItem resoudre = new JMenuItem(new MenuResoudre(this, "Resoudre par l'IA"));
        
        menuPartie.add(resoudre);
        menuPartie.add(allerAuNiveau);
        menuPartie.add(recommencer);
        menuPartie.add(quitter);
        //-------------------------------------//
        //-------------- Menu 2 ---------------//
        JMenu menuAPropos = new JMenu("?");
        JMenuItem aPropos = new JMenuItem(new MenuAProposAction(this, "A propos"));
        JMenuItem aide = new JMenuItem(new MenuAide(this, "Aide"));
        
        menuAPropos.add(aide);
        menuAPropos.add(aPropos);
        //-------------------------------------//
        menuBar.add(menuPartie);
        menuBar.add(menuAPropos);
        
        this.getContentPane().add(menuBar, BorderLayout.NORTH); 
    }
    
    /**
     * Met a jour l'affichage.
     * @param grille la grille du jeu
     */
    public void updateGrille(Grille grille){
        panneauGrille.update(grille);
        labelsPerso.miseAJour(grille);
    }
    
    /**
     * Fait avancer le presentateur.
     */
    public void suivant(){
        presentateur.suivant();
    }
    
    /**
     * Permet d'aller a un niveau choisi
     * @param numNiveau numero du niveau choisi
     */
    public void allerAuNiveau(int numNiveau){
        presentateur.allerAuNiveau(numNiveau);
    }
    
    /**
     * Permet de recommencer le niveau courant.
     */
    public void recommencer(){
        presentateur.recommencer();
    }
    
    /**
     * Permet de resoudre le niveau courant a l'aide 
     * d'un algorithme de resolution.
     */
    public void resoudre(){
        presentateur.resoudre();
    }
    
    /**
     * Affiche un message signifiant la fin de la partie, et reinitialise
     * le presentateur.
     */
    public void partieTerminee(){
        JOptionPane.showMessageDialog(this,  "Bravo, tu as réussi","Niveau termine", JOptionPane.INFORMATION_MESSAGE);
        presentateur.nouveauModele();
    }
    
    //--------------------- ACCESSEUR ---------------------//
    /**
     * Renvoie le panneau de la grille.
     * @return panneauGrille panneau de la grille
     */
    public PanneauGrille lirePanneauGrille(){
        return panneauGrille;
    }
    
    /**
     * Renvoie le presentateur de la vue.
     * @return presentateur de la vue
     */
    public Presentateur lirePresentateur() {
        return presentateur;
    }
    //------------------------------------------------------//
    
    /**
     * Represente les differents label du personnage 
     * (nombre de pas et nombre de pousse 
     * de caisse de personnage).
     */
    private final LabelsPerso labelsPerso;
    
    /**
     * Le panneau representant la vue de la grille.
     */
    protected PanneauGrille panneauGrille;
    
    /**
     * Presentateur de la vue.
     */
    protected final Presentateur presentateur;
}
