/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.Objects;
import modele.Grille;

/**
 * Classe representant une grille avec une valeur 
 * implemente l'interface comparable permettant de comparer 
 * ses differentes valeurs et les classer en consequence
 * @author Lakradi - Drouart
 */
public class GrilleAvecValeur implements Comparable
{
    /**
     * Constructeur logique.
     * @param grille grille du jeu.
     * @param g cout de l'etat en question
     * @param h fonction heuristique
     */
    public GrilleAvecValeur(Grille grille, int g, int h) {
        this.grille = grille;
        this.g = g;
        this.h = h;
        this.parent = this;
    }

    /**
     * Permet de comparer la valeur d'une grille avec une autre.
     * @param obj autre grille
     * @return -1, 0 ou 1 en fonction de la comparaison des entier g et h des 2 grilles
     */
    @Override
    public int compareTo(Object obj) {
        GrilleAvecValeur otherGrilleVal = (GrilleAvecValeur)obj;
        if( this.h == otherGrilleVal.h )
            return 0;
        if( this.h > otherGrilleVal.h )
            return 1;
        return -1;
    }
    
    /**
     * Redefinition de la methode equals() 
     * de la classe Object de l'API Java,
     * Permet ainsi de reelement compare si 2 Grille sont equivalente
     * l'une a l'autre.
     * @param obj Objet a comparer avec la grille.
     * @return un boolean true ou false selon que la grille est equivalente au paramettre
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof GrilleAvecValeur))
            return false;
        return this.grille.equals( ((GrilleAvecValeur) obj).grille );
    }
    
    /**
     * Redefinition de la methode hashCode()
     * de la classe Object de l'API Java,
     * @return l'entier correspondant a l'objet grille hashe
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.grille);
        return hash;
    }
    
    //--------------------- ACCESSEUR ---------------------//
    /**
     * Renvoie la grille du niveau.
     * @return grille.
     */
    public Grille lireGrille(){
        return grille;
    }
    
    public int lireF(){
        return g+h;
    }
    
    /**
     * Renvoie la valeur de la fonction "g"
     * @return g.
     */
    public int lireG(){
        return g;
    }
    
    /**
     * Renvoie la valeur de la distance estimée.
     * @return h
     */
    public int lireH(){
        return h;
    }
    //----------------------------------------------------//
    //--------------------- MUTATEUR ---------------------//
    /**
     * Permet de changer le parent de la grille actuel.
     * @param nParent nouveau parent de cette grille
     */
    public void setParent(GrilleAvecValeur nParent){
        this.parent = nParent;
    }
    //----------------------------------------------------//
    /**
     * Represente le parent de la grille.
     */
    protected GrilleAvecValeur parent;
    
    /**
     * Represente la grille de jeu.
     */
    protected Grille grille;
    
    /**
     * Represente les valeurs 
     * g, h et f de l'algorithme A*.
     */
    protected int g, h, f = 0;
}
