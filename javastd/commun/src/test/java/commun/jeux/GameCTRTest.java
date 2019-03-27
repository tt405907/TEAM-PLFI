package commun.jeux;

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
		assertTrue(game.isValidForm(Forme.CARRE));
		assertTrue(game.isValidForm(Forme.TRIANGLE));
		assertTrue(game.isValidForm(Forme.ROND));
		
		assertFalse(game.isValidForm(Forme.POINT));
		assertFalse(game.isValidForm(Forme.SEGMENT));
		assertFalse(game.isValidForm(Forme.POLYGONE));
		assertFalse(game.isValidForm(Forme.UNKNOWN));
	}

	@Test
	public void bat() {
		//Carré bat Rond
		assertTrue(game.bat(Forme.CARRE, Forme.ROND));
		assertFalse(game.bat(Forme.ROND, Forme.CARRE));
		//Triangle bat Carré
		assertTrue(game.bat(Forme.TRIANGLE, Forme.CARRE));
		assertFalse(game.bat(Forme.CARRE, Forme.TRIANGLE));
		//Rond bat Triangle
		assertTrue(game.bat(Forme.ROND, Forme.TRIANGLE));
		assertFalse(game.bat(Forme.TRIANGLE, Forme.ROND));

	}

}
