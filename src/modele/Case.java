/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Observable;

/**
 * Classe representant une case d'une grille.
 * @author Lakradi - Drouart
 */
public final class Case extends Observable
{
    /**
     * Enum permettant de differencier les divers types de cases.
     */
    public enum TypeCases
    {
        CaseVide,
        Mur,
        Caisse,
        PersoBas,
        PersoHaut,
        PersoGauche,
        PersoDroite,
        Objectif,
        PersoSurObj,
        CaisseSurObj;
    }
    
    /**
     * Constructeur logique.
     * @param statut type de la case
     */
    public Case(TypeCases statut)
    {
        this.statut = statut;
    }
    
    /**
     * Constructeur logique.
     * @param cases case associe a son statut
     */
    public Case(Case cases){
        setStatut(cases.lireStatut());
    }
    
    /**
     * Permet de modifier le type de la case, et le notifie a son observateur.
     * @param nouveauStatut enum du nouveau type de la case
     */
    public void setStatut(TypeCases nouveauStatut){
        this.statut = nouveauStatut;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Accesseur pour le type de la case.
     * @return enum du type de la case.
     */
    public TypeCases lireStatut(){
        return statut;
    }
    
    /**
     * Enum representant le statut de la case actuelle.
     */
    protected TypeCases statut;
}
