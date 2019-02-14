package serveur.jeux;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import commun.Forme;
import commun.jeux.ResultCTR;

public class GameCTRTest {
	GameCTR game;
	ResultCTR result1;
	ResultCTR result2;
	Forme formeClient;
	Forme formeServeur;

	@Before
	public void setBefore() {
		game = new GameCTR();
	}

	@Test
	public void play() {

		formeClient = Forme.ROND;
		formeServeur = Forme.UNKNOWN;
		assertEquals(game.play(formeClient, formeServeur).getResult(), ResultCTR.ERREUR);
		formeClient = Forme.TRIANGLE;
		formeServeur = Forme.TRIANGLE;
		assertEquals(game.play(formeClient, formeServeur).getResult(), ResultCTR.EGALITE);
		formeClient = Forme.TRIANGLE;
		formeServeur = Forme.ROND;
		assertEquals(game.play(formeClient, formeServeur).getResult(), ResultCTR.SERVEUR_GAGNE);
		formeClient = Forme.CARRE;
		assertEquals(game.play(formeClient, formeServeur).getResult(), ResultCTR.CLIENT_GAGNE);
	}

	@Test
	public void isValidForm() {

		formeClient = Forme.UNKNOWN;
		assertFalse(game.isValidForm(formeClient));
		formeClient = Forme.ROND;
		assertTrue(game.isValidForm(formeClient));
	}

	@Test
	public void bat() {
		formeClient = Forme.ROND;
		formeServeur = Forme.TRIANGLE;
		assertTrue(game.bat(formeClient, formeServeur));
		formeClient = Forme.CARRE;

		assertFalse(game.bat(formeClient, formeServeur));
		formeClient = Forme.TRIANGLE;
		formeServeur = Forme.CARRE;
		assertTrue(game.bat(formeClient, formeServeur));

	}

}
