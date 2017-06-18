/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe qui instancie une grille a partir d'un fichier
 * Une seule méthode statique, qui lit le fichier.
 * @author Lakradi - Drouart
 */
public class ChargeurNiveau 
{ 
    /**
     * Empeche l'instanciation.
     */
    private ChargeurNiveau(){}
    
    /**
     * Classe representant notre singleton.
     */
    private static class ChargeurNiveauSingleton
    {
        /**
         * Instance unique qui ne sera chargée en mémoire 
         * que lorsque l'on y fera référence pour la première fois.
         */
        private final static ChargeurNiveau instance = new ChargeurNiveau();
    }
    
    /**
     * Point d'acces pour la lecture de l'unique singleton.
     * @return instance du singleton de la class ChargeurNiveau.
     */
    public static ChargeurNiveau getInstance(){
        return ChargeurNiveauSingleton.instance;
    }
    
    /**
     * Permet d'initialiser un niveau sur une grille a l'aide du nom du fichier.
     * @param grille grille sur laquelle le niveau va etre instancie.
     * @param niveau string representant le nom de notre fichier niveau
     * @throws IOException renvoie une IOException
     */
    public void initialiserNiveau(Grille grille, String niveau) throws IOException
    {
        Case[][] cases = new Case[16][19];
        BufferedReader fichierNiveau = new BufferedReader(new FileReader(niveau));
        String ligne = fichierNiveau.readLine();
        int ligneCourante = 0;
        while ( ligne.charAt(0) != 'A' )
        {
            int nbColonneNiveau = Math.min(19,ligne.length());
            for (int i = 0; i < nbColonneNiveau; i++) {
                switch(ligne.charAt(i))
                {
                    case '@':
                        cases[ligneCourante][i] = new Case(Case.TypeCases.PersoBas);
                        break;
                    case ' ':
                        cases[ligneCourante][i] = new Case(Case.TypeCases.CaseVide);
                        break;
                    case '#':
                        cases[ligneCourante][i] = new Case(Case.TypeCases.Mur);
                        break;
                    case '*':
                        cases[ligneCourante][i] = new Case(Case.TypeCases.CaisseSurObj);
                        break;
                    case '$':
                        cases[ligneCourante][i] = new Case(Case.TypeCases.Caisse);
                        break;
                    case '.':
                        cases[ligneCourante][i] = new Case(Case.TypeCases.Objectif);
                        break;
                    case '+':
                        cases[ligneCourante][i] = new Case(Case.TypeCases.PersoSurObj);
                        break;
                    default:
                        break;
                }//switch
            }//for
            //On initialise toute les autres cases a Vide.
            for (int i = nbColonneNiveau ; i < 19; i++) {
                cases[ligneCourante][i] = new Case(Case.TypeCases.CaseVide);
            }//for
            ligneCourante++;
            ligne = fichierNiveau.readLine();
        }//while
        for (int i = ligneCourante; i < 16; i++) {
            for (int j = 0; j < 19; j++) {
                cases[i][j] = new Case(Case.TypeCases.CaseVide);
            }//for
        }//for

        grille.initialiserGrille(cases);
        fichierNiveau.close();
    }
}
