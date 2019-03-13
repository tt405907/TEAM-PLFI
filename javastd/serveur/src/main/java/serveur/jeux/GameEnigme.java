package serveur.jeux;

import java.util.Random;

import commun.Forme;

public class GameEnigme {
	private Forme attente;
	private Random rand;
	
	public GameEnigme(Random rand) {
		this.rand = rand;
	}
	
	public String makeEnigme() {
		attente = randomForme();
		switch (attente) {
		case CARRE:
			return EN_CARRE[rand.nextInt(EN_CARRE.length)];
		case TRIANGLE:
			return EN_TRIANGLE[rand.nextInt(EN_TRIANGLE.length)];
		case ROND:
			return EN_ROND[rand.nextInt(EN_ROND.length)];
		default:
			return ERREUR;
		}
	}
	
	public boolean estFormeAttendue(Forme f) {
		return attente == f;
	}
	
	public Forme getFormeAttendue() {
		return attente;
	}

    private Forme randomForme() {
        int num = rand.nextInt(3);
        switch (num) {
        case 0:
            return Forme.CARRE;
        case 1:
            return Forme.TRIANGLE;
        case 2:
        default:
            return Forme.ROND;
        }
    }

    //Enigmes
    private static final String[] EN_CARRE = {"Je suis un carré"};
    private static final String[] EN_TRIANGLE = {"Je suis un triangle"};
    private static final String[] EN_ROND = {"Je suis un rond", "Je suis un cercle"};
    private static final String ERREUR = "Je suis une erreur qui est survenue sur le serveur";
}
