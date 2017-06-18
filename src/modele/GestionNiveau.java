/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Observable;

/**
 * Classe permettant de gerer les niveaux.
 * @author Lakradi - Drouart
 */
public final class GestionNiveau extends Observable
{
    /**
     * Constructeur logique.
     */
    public GestionNiveau(){
        try {
            BufferedReader niveau = new BufferedReader(new FileReader(fichierNiveau));
            niveauMaxAtteint = this.lireNiveauCourant();
            niveau.close();
        }catch(IOException e) {
            niveauMaxAtteint = 1;
            e.printStackTrace();
        }
        niveauCourant = niveauMaxAtteint;
    }

    /**
     * Permet d'aller au niveau suivant si celui-ci 
     * est inferieur au niveau maximal existant.
     */
    public void niveauSuivant(){
        //changer 100 par le nombre de fichier dans le repertoire niveau.
        this.niveauCourant += niveauCourant != 100 ? 1 : 0;
        this.niveauMaxAtteint = niveauCourant > niveauMaxAtteint ? niveauCourant : niveauMaxAtteint;
        try {
            FileWriter niveauSuivant = new FileWriter(fichierTemporaire);
            niveauSuivant.write(niveauMaxAtteint); //fichierNiveau
            niveauSuivant.close();
        }catch(IOException e) {
            System.err.println("Le fichier " + fichierTemporaire + " n'existe pas.");
        }//catch
        setChanged(); 
        notifyObservers();
    }
    
    /**
     * Permet d'aller au niveau precedent 
     * si celui-ci est superieur a un.
     */
    public void niveauPrecedent(){
        if (!(this.niveauCourant == 1))
            niveauCourant--;
        this.niveauCourant = 1;
        setChanged(); 
        notifyObservers();
    }
    
    /**
     * Permet de savoir le nombre total de niveau 
     * de jeu de Sokoban
     * @return nombreNiveau, le nombre de niveau
     * repertorie dans "/niveaux/"
     */
    public int nbTotalNiveau(){
        File repertoireNiveau = new File("src/ressources/niveaux/");
        FilenameFilter fichierFiltre = new LvlFichierFiltre();
        String[] res = repertoireNiveau.list(fichierFiltre);
        for (int i = 1; i <= res.length; i++) {
            this.nombreNiveau = i;
        }
        return nombreNiveau;
    }

    /**
     * Permet d'aller a un niveau voulu.
     * @param nbNiveau numero du niveau voulu
     */
    public void allerAuNiveau(int nbNiveau){
        nbTotalNiveau();
        if (nbNiveau >= 1 || nbNiveau <= this.nombreNiveau){
            this.niveauCourant = nbNiveau;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    
    //------------------ ACCESSEUR ------------------//
    /**
     * Renvoie le numero du niveau actuel de l'utilisateur. 
     * @return niveauCourant
     */
    public int lireNiveauCourant(){
        return niveauCourant;
    }
    
    /**
     * Renvoie le numero du niveau maximal 
     * atteint par l'utilisateur.
     * @return niveauMaxAtteint
     */
    public int lireNiveauMaxAtteint(){
        return niveauMaxAtteint;
    }
    //-----------------------------------------------//
    
    /**
     * Nombre de niveau "lvlXXX.sok" dans 
     * le repertoire niveau.
     */
    private int nombreNiveau;
    
    /**
     * Niveau courant de l'utilisateur, 
     * initialise au niveau un.
     */
    protected int niveauCourant = 1;
    
    /**
     * Represente le niveau maximum 
     * atteint par l'utilisateur.
     */
    protected int niveauMaxAtteint;
    
    /**
     * Chemin relatif du fichier du niveau courant.
     */
    protected String fichierNiveau = "src/ressources/niveaux/lvl"+ niveauCourant +".sok";
    
    /**
     * Chemin relatif du fichier temporaire, 
     * permet lors du passage au niveau suivant 
     * d'ecrire sur celui-ci, au lieu d'ecraser 
     * le fichier du niveau actuel.
     */
    protected String fichierTemporaire = "src/ressources/niveaux/levelTemp.sok";
}
