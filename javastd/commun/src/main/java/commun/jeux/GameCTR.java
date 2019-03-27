package commun.jeux;

import commun.Forme;
import commun.jeux.ResultCTR;

/**
 * Effectue des parties de Carré-Triangle-Rond en générant le résultat
 */
public class GameCTR {
	public ResultCTR play(Forme client, Forme serveur) {
		//Si une des formes ne convient pas, erreur
		if (!isValidForm(client) || !isValidForm(serveur))
			return new ResultCTR(client, serveur, ResultCTR.ERREUR);
		
		//Si les formes sont les mêmes, égalité
		if (client == serveur) return new ResultCTR(client, serveur, ResultCTR.EGALITE);
		
		//Sinon on regarde si le client a battu le serveur
		if (bat(client, serveur)) return new ResultCTR(client, serveur, ResultCTR.CLIENT_GAGNE);
		else return new ResultCTR(client, serveur, ResultCTR.SERVEUR_GAGNE);
	}
	
	public boolean isValidForm(Forme f) {
		return f == Forme.CARRE || f == Forme.TRIANGLE || f == Forme.ROND;
	}
	
	/**
	 * Dit si la Forme a bat la Forme b, supposé différents.
	 * @return true si a bat b, false sinon
	 */
	public boolean bat(Forme a, Forme b) {
		//Carré bat Rond
		//Triangle bat Carré
		//Rond bat Triangle
		switch (a) {
		case CARRE:
			return b == Forme.ROND;
		case TRIANGLE:
			return b == Forme.CARRE;
		case ROND:
			return b == Forme.TRIANGLE;
		default:
			return false;
		}
	}
}
