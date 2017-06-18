/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import presentateur.Presentateur;

/**
 * Classe principale du programme.
 * @author Lakradi - Drouart
 */
public class Client 
{
    /**
     * Instancie un presentateur qui va tout gerer lui meme.
     * @param args argument a passer a notre classe (ici aucun)
     */
    public static void main(String[] args)
    {
        final Presentateur presentateur = new Presentateur();
        presentateur.demarrer();
    }
}
