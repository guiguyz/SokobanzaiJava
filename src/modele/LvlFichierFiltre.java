/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Classe permettant de filtrer les fichiers commencant par "lvl"
 * @author Lakradi - Drouart
 */
public class LvlFichierFiltre implements FilenameFilter
{
    /**
     * Redefinition de la methode accept de l'interface
     * FilenameFilter de l'API Java, permettant de savoir si un
     * fichier commence par les lettres "lvl".
     * @param dir repertoire contenant les fichiers
     * @param name nom du fichier
     * @return true si le fichier name commence par "lvl" false sinon.
     */
    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().startsWith("lvl");
    }
    
    /**
     * Permet de savoir quel type de fichier on recherche
     * @return "/lvl files"
     */
    public String lireDescritpion(){
        return "/lvl files";
    }
}
