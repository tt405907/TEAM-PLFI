package commun;

/**
 * Représente une forme dessinée
 */
public class Dessin {
	//Pour l'instant, on ne fait pas de dessin à proprement parler
	//On se contente d'entiers pour identifier des formes prédéfinies
	
	private int forme;
	
	//Pour json
	public Dessin() {}
	public Dessin(int valeur) {
		forme = valeur;
	}
	
	public int getValeur() {
		return forme;
	}
}
