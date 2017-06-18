/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.GridLayout;

import javax.swing.JPanel;

import modele.Case;
import modele.Grille;

/**
 * Classe permettant l'affichage de la grille du jeu.
 * @author Lakradi - Drouart
 */
public class PanneauGrille extends JPanel
{
    /**
     * Constructeur logique.
     * @param grille du jeu
     */
    public PanneauGrille (Grille grille) {
        super();
        this.grille = grille;
        
        GridLayout layout = new GridLayout(16, 19);
        setLayout(layout);                       
        
        dessiner(grille);
    }
    
    /**
     * Permet l'affichage de la grille passe en parametre associe a chacune de ses cases.
     * @param grille nouvelle grille.
     */
    public final void dessiner(Grille grille){
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 19; j++) {
                Case nCase = grille.contenuCaseEn(i, j);
                VueCases vueCases = new VueCases(nCase);
  
                this.cases[i][j] = vueCases;
                add(vueCases);
            }
        }
    }
    
    /**
     * Met a jour l'affichage de la grille.
     * @param grille de jeu
     */
    public void update(Grille grille){
        this.removeAll();
        this.grille = grille;
        dessiner(this.grille);
        this.repaint();
        this.revalidate();
    }
    
    //--------------- VARIABLE DE CLASSE ---------------//
    /**
     * Grille du jeu.
     */
    protected Grille grille;
    
    /**
     * Tableau des images des cases du jeu.
     */
    private final VueCases[][] cases = new VueCases[16][19];
}