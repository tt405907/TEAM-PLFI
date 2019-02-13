package commun.jeux;

import commun.Forme;

/**
 * Le résultat d'un match de Carré-Triangle-Rond
 */
public class ResultCTR {
	public static final String
		CLIENT_GAGNE = "client gagne",
		SERVEUR_GAGNE = "serveur gagne",
		EGALITE = "egalite",
		ERREUR = "erreur";
	
	private Forme client, serveur;
	private String result;
	
	public ResultCTR() {}

	public ResultCTR(Forme client, Forme serveur, String result) {
		this.client = client;
		this.serveur = serveur;
		this.result = result;
	}

	public Forme getFormeClient() {
		return client;
	}

	public Forme getFormeServeur() {
		return serveur;
	}

	public String getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "ResultCTR [client=" + client + ", serveur=" + serveur + ", result=" + result + "]";
	}
}
