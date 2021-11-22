package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}
		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage 
         * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?
                if(this.estDansUnGarage()){
                    throw new Exception();
                }
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/**
	 * Fait sortir la voiture du garage 
         * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
                //on verifie que la voiture est dans un garage
                if(!this.estDansUnGarage()){
                    throw new Exception("Une voiture ne peut sortir d'un garage si elle n'est pas dans un garage");
                }
                // Trouver le dernier stationnement de la voiture
		Stationnement dernierStationnement = myStationnements.get(myStationnements.size()-1);
		// Terminer ce stationnement
                dernierStationnement.terminer();
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		// TODO: Implémenter cette méthode
                Set<Garage> res = new HashSet<Garage>();
		for(Stationnement s : myStationnements){
                    res.add(s.getGarage());
                }
                return res;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
            boolean res = true;
            if(myStationnements.isEmpty()){
                res = false;
            }
            else{
                Stationnement dernierStationnement = myStationnements.get(myStationnements.size()-1);
                if(!dernierStationnement.estEnCours()){
                    res = false;
                }
            }
            return res;
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entrée / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
                Garage garage;
		for(Garage g : this.garagesVisites()){
                    out.println(g.toString());
                    garage = g;
                    for(Stationnement s : myStationnements){
                        if(s.getGarage().equals(garage)){
                            out.println(s);
                        }
                    }
                }
	}
        
        /*system.out.println
         system = classe
         out = propriété static -> sortie standard stdout sur Unix (endroit ou on écrit par defaut)
         System.out = PrintStream ->
        */

}
