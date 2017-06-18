/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentateur;

import ia.ActionSokoban;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modele.objets.Personnage;

/**
 * Classe annexe permettant le control des keyCodes et du deplacement
 * du personnage lors de la resolution complete d'un niveau de sokoban.
 * @author Lakradi - Drouart
 */
public class ControleurEvent implements KeyListener
{

    /**
     * Constructeur logique.
     * @param presentateur du jeu
     */
    public ControleurEvent(Presentateur presentateur){
        this.presentateur = presentateur;
    }
    
    /**
     * Appele lors de l'observation du deplacement du personnage 
     * en fonction de l'enum ActionSokoban et le notifie a son observeur.
     * @param direction du personnage
     */
    public void seDeplacer(ActionSokoban direction){
        Personnage personnage = presentateur.lireModele().lirePerso();
        switch(direction)
        {
            case EnHaut:
                personnage.enHaut();
                break;
            case EnBas:
                personnage.enBas();
                break;
            case AGauche:
                personnage.aGauche();
                break;
            case ADroite:
                personnage.aDroite();
                break;
        }
        personnage.notifier();
    }
    
    /**
     * Appelee lors de l'observation du deplacement du personnage
     * en fonction du keyEvent tape et le notifie a son observeur.
     * @param keyPressed integer associe aux touches du clavier
     */
    public void evenement(int keyPressed){
        Personnage personnage = presentateur.lireModele().lirePerso();
        switch(keyPressed)
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_Z:
                personnage.enHaut();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                personnage.enBas();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_Q:
                personnage.aGauche();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                personnage.aDroite();
                break;
            case KeyEvent.VK_R:
                presentateur.recommencer();
                break;
        }
        personnage.notifier();
    }
    
    /**
     * Permet de detecter les evenements lors de l'appui sur une touche de clavier
     * et deplacer le personnage en fonction du keyCode recupere.
     * @param keyEvent evenement clavier
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        evenement(keyEvent.getExtendedKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    
    /**
     * Presentateur du jeu.
     */
    private final Presentateur presentateur;
}
