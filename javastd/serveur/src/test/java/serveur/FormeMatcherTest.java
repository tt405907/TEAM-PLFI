package serveur;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import commun.Dessin;
import commun.Forme;

public class FormeMatcherTest {

	private Dessin dessin1;
	private FormeMatcher fm;

	@Test
	public void identify() {
		fm = new FormeMatcher();

		dessin1 = new Dessin(Forme.CARRE.ordinal());
		assertEquals(fm.identify(dessin1), Forme.CARRE);
		dessin1 = new Dessin(Forme.ROND.ordinal());
		assertEquals(fm.identify(dessin1), Forme.ROND);
		dessin1 = new Dessin(Forme.TRIANGLE.ordinal());
		assertEquals(fm.identify(dessin1), Forme.TRIANGLE);
		dessin1 = new Dessin(12);
		assertEquals(fm.identify(dessin1), Forme.UNKNOWN);
	}

}
