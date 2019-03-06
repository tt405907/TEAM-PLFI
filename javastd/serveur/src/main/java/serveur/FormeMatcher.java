package serveur;

import commun.Dessin;
import commun.Forme;

/**
 * Un objet qui traduit un Dessin en Forme
 */
public class FormeMatcher {
	public Forme identify(Dessin d) {
		//TODO: vraie reconnaissance
		//"Reconnaissance" TEMPORAIRE très aléatoire juste pour que ça fasse des choses
		int tmp = d.getPoints().length % Forme.values().length;
		for (Forme f : Forme.FORMES) {
			if (tmp == f.ordinal()) return f;
		}
		//Aucune forme reconnue
		return Forme.UNKNOWN;
	}
}
