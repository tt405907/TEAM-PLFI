package commun.jeux;


import java.util.Random;

import commun.Forme;

public class GameEnigme {
    private Forme attente;
    private Random rand;

    public GameEnigme(Random rand) {
        this.rand = rand;
    }

    public String makeEnigme(Forme forme) {
        attente = forme;
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

    public String makeEnigme() {
        return makeEnigme(randomForme());
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
            "Mon premier est un bus qui relie les villes. Mon second est une note de musique et un poisson. Mon tout est la forme à dessiner.",
            "Je suis le fils d'un rectangle et d'un losange.",
            "J'ai quatre axes de symétrie, qui se croisent tous en un centre de symétrie.",
            "Me faire pivoter d'un quart de tour ne me fait pas changer.",
            "Je suis toujours équilatéral, mais je ne suis pas un triangle.",
            "On peut faire un solide régulier à 6 faces avec moi."
    };
    private static final String[] EN_TRIANGLE = {
            "La somme de mes angles est égale à la température idéale de cuisson d'une tarte aux pommes.",
            "Pour me définir, il suffit de trouver 3 points non alignés.",
            "Je peux avoir au maximum un angle grave.",
            "On ne peut pas construire de polygone avec moins de côtés que moi.",
            "Je peut être rectangle, mais je n'en suis pas un.",
            "Si mes côtés sont égaux, on peut paver un plan avec moi.",
            "Si mes côtés sont égaux, on peut faire un solide régulier à 4 faces avec moi.",
            "Si mes côtés sont égaux, on peut faire un solide régulier à 8 faces avec moi.",
            "Si mes côtés sont égaux, on peut faire un solide régulier à 20 faces avec moi."
    };
    private static final String[] EN_ROND = {
            "Mon périmètre vaut deux pierres.",
            "Mon aire est aussi une pierre carrée.",
            "Je suis la forme de la calvitie du Père Fouras.",
            "J'ai une infinité de tangente.",
            "Je ne suis pas un archer mais je possède des arcs et des cordes.",
            "Me faire pivoter de n'importe quel angle ne me fait pas changer.",
            "Plus on rajoute de côtés à un polygone régulier, plus il me ressemble.",
            "Je peux rouler sur une route plate.",
            "Si je suis la base d'un prisme, on l'appelle un cylindre."
    };
    private static final String ERREUR = "Je suis une erreur qui est survenue sur le serveur";

}
