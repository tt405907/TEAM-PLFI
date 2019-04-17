package commun.jeux;


import java.util.Random;

import commun.Forme;

public class GameReflex {
    private Forme attente;
    private Random rand;
    private int score;

    public GameReflex(Random rand) {
        this.rand = rand;
    }

    public Forme start() {
        return start(randomForme());
    }

    public Forme start(Forme forme) {
        attente = forme;
        score = 0;
        return attente;
    }

    public boolean valider(Forme f) {
    	if (attente == f) {
    		score++;
    		return true;
    	}
    	else return false;
    }

    public Forme getFormeAttendue() {
        return attente;
    }
    
    public int getScore() {
    	return score;
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

}
