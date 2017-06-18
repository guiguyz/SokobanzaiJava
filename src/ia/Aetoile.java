/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import modele.Grille;

/**
 * Classe representant l'algorithme A etoile.
 * @author Lakradi - Drouart
 * @param <E> etat representant ici notre grille
 * @param <A> liste des actions possible du personnage
 */
public class Aetoile <E,A> implements AlgorithmeAbstrait <E,A> 
{
    /**
     * Constructeur logique.
     * @param grille grille sur laquelle on applique l'algorithme
     * @param actions liste d'actions possible du personnage sur la grille
     */
    public Aetoile(E grille, List<A> actions){
        super();
        this.grille = grille;
        this.actions = actions;
    }
    
    /**
     * Permet de resoudre le probleme du Sokoban avec une certaine heuristique.
     * @param probleme probleme representant la resolution d'un niveau de sokoban
     * @param heuristique heuristique a appliquer sur le probleme
     * @return la liste contenant le chemin de l'etat initial a l'etat final
     */
    @Override
    public List<E> resoudre(ProblemeAbstrait<E, A> probleme, 
            HeuristiqueAbstrait<E> heuristique) 
    {
        List<E> chemin = new LinkedList();
        
        LinkedList<GrilleAvecValeur> listeEtatVisite = new LinkedList();
        PriorityQueue<GrilleAvecValeur> queueEtatAExplorer = new PriorityQueue<>();
        
        Grille etatInitial = (Grille) probleme.lireEtat();
        int h = ( heuristique.distanceManhattanPersonnageCaisse((E) etatInitial) ) + ( heuristique.distanceManhattanCaisseObjectif((E) etatInitial) ) ;
        
        GrilleAvecValeur grilleDepart = new GrilleAvecValeur((Grille)etatInitial,0,h);
        queueEtatAExplorer.add(grilleDepart);

        while(!queueEtatAExplorer.isEmpty())
        {
            GrilleAvecValeur etatCourant = queueEtatAExplorer.poll();
            listeEtatVisite.add(etatCourant);

            if(probleme.estTerminal((E) etatCourant.lireGrille())){
                GrilleAvecValeur noeudCourant = etatCourant;
                while(noeudCourant != grilleDepart){  
                    chemin.add((E) noeudCourant);
                    noeudCourant = noeudCourant.parent;
                }
                System.out.println("VICTOIRE !!");
                return chemin;
            }
            for (int i = 0; i < probleme.lireActions((E) etatCourant.lireGrille()).size(); i++) 
            {
                Grille grilleSuccesseur = (Grille) probleme.successeur((E) etatCourant.lireGrille(), this.actions.get(i));
                h = (heuristique.distanceManhattanPersonnageCaisse((E) grilleSuccesseur) 
                        + heuristique.distanceManhattanCaisseObjectif((E) grilleSuccesseur));
                
                int newG = etatCourant.parent.g + 1;
                GrilleAvecValeur grilleSuccValeur = new GrilleAvecValeur((Grille)grilleSuccesseur,newG,h);

                if(!queueEtatAExplorer.contains(grilleSuccValeur) 
                        && !listeEtatVisite.contains(grilleSuccValeur))
                {
                    grilleSuccValeur.setParent(etatCourant);
                    grilleSuccValeur.g = newG;
                    queueEtatAExplorer.add(grilleSuccValeur);
                }
                
                else if(grilleSuccValeur.g > newG)
                {
                    grilleSuccValeur.setParent(etatCourant);
                    grilleSuccValeur.g = newG;
                    if(listeEtatVisite.contains(grilleSuccValeur)){
                        listeEtatVisite.remove(grilleSuccValeur);
                        queueEtatAExplorer.add(grilleSuccValeur);
                    }
                }

            }
        }
        //Pas de solution.
        System.out.println("Pas de solution...");
        return chemin;
    }//RESOUDRE 
    
    //--------------------- ACCESSEUR ---------------------//
    
    /**
     * Renvoie la grille ou l'algorithme est applique.
     * @return grille
     */
    public E lireGrille(){
        return grille;
    }
    
    /**
     * Renvoie la liste des actions possible.
     * @return actions, toutes les actions 
     * existante et executable par un personnage
     */
    public List<A> lireListeActions(){
        return actions;
    }
    //----------------------------------------------------//
    
    /**
     * Represente la grille de jeu.
     */
    protected E grille;
    
    /**
     * Represente la liste des actions possibles du personnage.
     */
    protected List<A> actions;
}
