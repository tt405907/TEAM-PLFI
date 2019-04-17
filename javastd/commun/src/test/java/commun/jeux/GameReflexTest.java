package commun.jeux;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import commun.Forme;

public class GameReflexTest {
	private GameReflex game;

	@Before
	public void setUp() {
		game = new GameReflex(new Random());
	}

	@Test
	public void partieCarre() {
		game.start(Forme.CARRE);
		
		assertEquals(Forme.CARRE, game.getFormeAttendue());
		assertEquals(0, game.getScore());
		
		assertTrue(game.valider(Forme.CARRE));
		assertEquals(1, game.getScore());
		
		assertFalse(game.valider(Forme.TRIANGLE));
		assertEquals(1, game.getScore());
		assertFalse(game.valider(Forme.ROND));
		assertEquals(1, game.getScore());
		assertFalse(game.valider(Forme.UNKNOWN));
		assertEquals(1, game.getScore());
		
		assertTrue(game.valider(Forme.CARRE));
		assertEquals(2, game.getScore());
	}

	@Test
	public void partieTriangle() {
		game.start(Forme.TRIANGLE);
		
		assertEquals(Forme.TRIANGLE, game.getFormeAttendue());
		assertEquals(0, game.getScore());
		
		assertTrue(game.valider(Forme.TRIANGLE));
		assertEquals(1, game.getScore());
		
		assertFalse(game.valider(Forme.CARRE));
		assertEquals(1, game.getScore());
		assertFalse(game.valider(Forme.ROND));
		assertEquals(1, game.getScore());
		assertFalse(game.valider(Forme.UNKNOWN));
		assertEquals(1, game.getScore());
		
		assertTrue(game.valider(Forme.TRIANGLE));
		assertEquals(2, game.getScore());
	}

	@Test
	public void partieRond() {
		game.start(Forme.ROND);
		
		assertEquals(Forme.ROND, game.getFormeAttendue());
		assertEquals(0, game.getScore());
		
		assertTrue(game.valider(Forme.ROND));
		assertEquals(1, game.getScore());
		
		assertFalse(game.valider(Forme.CARRE));
		assertEquals(1, game.getScore());
		assertFalse(game.valider(Forme.TRIANGLE));
		assertEquals(1, game.getScore());
		assertFalse(game.valider(Forme.UNKNOWN));
		assertEquals(1, game.getScore());
		
		assertTrue(game.valider(Forme.ROND));
		assertEquals(2, game.getScore());
	}
	
	@Test
	public void partieAleatoire() {
		game.start();
		
		assertTrue(game.getFormeAttendue() == Forme.CARRE 
				|| game.getFormeAttendue() == Forme.TRIANGLE 
				|| game.getFormeAttendue() == Forme.ROND);
		assertEquals(0, game.getScore());
		assertTrue(game.valider(game.getFormeAttendue()));
		assertEquals(1, game.getScore());
	}

}
