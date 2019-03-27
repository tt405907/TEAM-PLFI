package commun.jeux;

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
    private static final String[] EN_CARRE = {
    		"On me dit de quelqu'un d'organisé.",
    		"Je ne suis pas un druide mais une racine porte mon nom.",
    		"Quand un nombre se multiplie avec lui même, on lui ajoute mon nom.",
    		"Mon premier est un bus qui relie les villes. Mon second est une note de musique et un poisson. Mon tout est la forme à dessiner."    
    		};
    private static final String[] EN_TRIANGLE = {
    		"La somme de mes angles est égale à la température idéale de cuisson d'une tarte aux pommes.",
    		"Pour me définir, il suffit de trouver 3 points non alignés", 
    		"Je peux avoir au maximum un angle grave."
    		};
    private static final String[] EN_ROND = {
    		"Mon périmètre vaut deux pierres.",
    		"Mon aire est aussi une pierre carrée.",
    		"Je suis la forme de la calvitie du Père Fouras.",
    		"J'ai une infinité de tangente.",
    		"Je ne suis pas un archer mais je possède des arcs et des cordes."
    		};
    private static final String ERREUR = "Je suis une erreur qui est survenue sur le serveur";
}
