/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import ia.Aetoile;
import ia.GrilleAvecValeur;
import ia.HeuristiqueSokoban;
import ia.ProblemeSokoban;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.GestionNiveau;
import modele.Grille;

/**
 * Permet le demarage de l'algorithme a etoile
 * @author Lakradi - Drouart
 */
public class ClientAetoile {
    /**
     * Instancie l'algorithme A* qui va tout gerer lui meme.
     * @param args argument a passer a notre classe (ici aucun)
     */
    public static void main(String[] args)
    {
        //charger le niveau
        GestionNiveau niveau = new GestionNiveau();
        
        //niveau.allerAuNiveau(4);
        
        //recuperer l'EtatInitial
        Grille etatInit = new Grille(niveau); 
        //etatInit.afficherGrille();
        
        ProblemeSokoban pbSoko = new ProblemeSokoban(etatInit);
        HeuristiqueSokoban h = new HeuristiqueSokoban(); 
        Aetoile algo = new Aetoile<>(etatInit, pbSoko.lireActions(etatInit));
        LinkedList<GrilleAvecValeur> a = (LinkedList<GrilleAvecValeur>) algo.resoudre(pbSoko, h);
        
        int nbDeplacement =0;
        for (int i = 0; i < a.size(); i++) {
            try {
                System.out.println(i);
                a.get(i).lireGrille().afficherGrille();
                sleep(5000);
                nbDeplacement++;
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientAetoile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Nombre de deplacement : "+ nbDeplacement);
    }
}
