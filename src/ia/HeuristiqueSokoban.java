/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import modele.Case.TypeCases;
import modele.Grille;
import modele.objets.Personnage;

/**
 * Classe concrete representant notre heuristique du probleme de sokoban.
 * @author Lakradi - Drouart
 */
public class HeuristiqueSokoban implements HeuristiqueAbstrait<Grille>
{
 
    /**
     * Calcule de distance Manhattan entre les coordoonees du personnage 
     * et les caisses sur la grille passee en parametre
     * @param grille sur laquelle on evalue la distance
     * @return distance
     */
    @Override
    public int distanceManhattanPersonnageCaisse(Grille grille){
        Personnage personnage = grille.lirePerso();
        int distance = 0;
        int x0 = personnage.lireX();
        int y0 = personnage.lireY();
        for (int i = 0; i < grille.lireNbLignes(); i++) {
            for (int j = 0; j < grille.lireNbColonnes(); j++) {
                if(grille.contenuCaseEn(i, j).lireStatut() == TypeCases.Caisse ||
                        grille.contenuCaseEn(i, j).lireStatut() == TypeCases.CaisseSurObj)
                {
                    xCaisse = i;
                    yCaisse = j;
                } 
                if(grille.contenuCaseEn(i, j).lireStatut() == TypeCases.Objectif ||
                        grille.contenuCaseEn(i, j).lireStatut() == TypeCases.CaisseSurObj || 
                            grille.contenuCaseEn(i, j).lireStatut() == TypeCases.PersoSurObj )
                {
                    xObj = i;
                    yObj = j;
                }   
            }
        }
        distance = (Math.abs(xCaisse - x0) + Math.abs(yCaisse - y0));
        return distance;
    }
    
    /**
     * Calcul de distance Manhattan entre les caisses et les objectifs
     * sur la grille passe en parametre.
     * @param grille sur laquelle on evalue la distance
     * @return distance
     */
    @Override
    public int distanceManhattanCaisseObjectif(Grille grille)
    {
        int distance = 0;
        distance = (Math.abs(xObj - xCaisse) + Math.abs(yObj - yCaisse));
        return distance;    
    }
    
    /**
     * Variable de classe representant les coordoonees des x et y
     * des cases et objectifs d'une grille.
     */
    protected int xCaisse = 0, yCaisse = 0, xObj = 0, yObj = 0;
}
