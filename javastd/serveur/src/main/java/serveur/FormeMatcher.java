package serveur;

import commun.Dessin;
import commun.Forme;

/**
 * Un objet qui traduit un Dessin en Forme
 */
public class FormeMatcher {
	public Forme identify(Dessin d) {
		for (Forme f : Forme.FORMES) {
			//"Reconnaissance" temporaire tant que l'on n'a pas de dessin
			if (d.getValeur() == f.ordinal()) return f;
		}
		//Aucune forme reconnue
		return Forme.UNKNOWN;
	}
}
