package commun.jeux;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import commun.Forme;

public class GameEnigmeTest {
	private GameEnigme game;
	
	@Before
	public void setUp() {
		game = new GameEnigme(new Random());
	}
	
	@Test
	public void makeEnigmeCarre() {
		game.makeEnigme(Forme.CARRE);
		
		assertEquals(Forme.CARRE, game.getFormeAttendue());
		assertTrue(game.estFormeAttendue(Forme.CARRE));
		assertFalse(game.estFormeAttendue(Forme.TRIANGLE));
		assertFalse(game.estFormeAttendue(Forme.ROND));
		assertFalse(game.estFormeAttendue(Forme.UNKNOWN));
	}
	
	@Test
	public void makeEnigmeTriangle() {
		game.makeEnigme(Forme.TRIANGLE);
		
		assertEquals(Forme.TRIANGLE, game.getFormeAttendue());
		assertTrue(game.estFormeAttendue(Forme.TRIANGLE));
		assertFalse(game.estFormeAttendue(Forme.CARRE));
		assertFalse(game.estFormeAttendue(Forme.ROND));
		assertFalse(game.estFormeAttendue(Forme.UNKNOWN));
	}
	
	@Test
	public void makeEnigmeRond() {
		game.makeEnigme(Forme.ROND);
		
		assertEquals(Forme.ROND, game.getFormeAttendue());
		assertTrue(game.estFormeAttendue(Forme.ROND));
		assertFalse(game.estFormeAttendue(Forme.CARRE));
		assertFalse(game.estFormeAttendue(Forme.TRIANGLE));
		assertFalse(game.estFormeAttendue(Forme.UNKNOWN));
	}
	
	@Test
	public void makeEnigmeAleatoire() {
		game.makeEnigme();
		
		assertTrue(game.getFormeAttendue() == Forme.CARRE 
				|| game.getFormeAttendue() == Forme.TRIANGLE 
				|| game.getFormeAttendue() == Forme.ROND);
		assertTrue(game.estFormeAttendue(game.getFormeAttendue()));
	}

}
