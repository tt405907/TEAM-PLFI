package serveur;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import commun.Dessin;
import commun.Forme;
import commun.Point;

import static serveur.DessinUtils.*;

public class FormeMatcherCercleTest {
	private FormeMatcher fm;
	
	@Before
	public void setup() {
		fm = new FormeMatcher();
	}

	@Test
	public void cercleParfait() {
		Dessin cercle;
		Point[] pointsCercle = new Point[180];
		for (int i = 0; i < 180; i++) {
			pointsCercle[i] = new Point((float) Math.cos(i * 2 * Math.PI / 180.0) * 50 + 125,
					(float) Math.sin(i * 2 * Math.PI / 180.0) * 50 + 125);
		}
		cercle = new Dessin(pointsCercle);
		assertEquals(Forme.ROND, fm.identify(cercle));
	}

	@Test
	public void translationCercle() {
		Dessin cercle;
		Point[] pointsCercle = new Point[180];
		for (int i = 0; i < 180; i++) {
			pointsCercle[i] = new Point((float) Math.cos(i * 2 * Math.PI / 180.0) * 50 + 125,
					(float) Math.sin(i * 2 * Math.PI / 180.0) * 50 + 125);
		}
		cercle = new Dessin(pointsCercle);
		//Translate dans pleins de directions
		for (int x = -200; x <= 200; x += 200) {
			for (int y = -200; y <= 200; y += 200) {
				assertEquals(Forme.ROND, fm.identify(translate(cercle, x, y)));
			}
		}
		
	}

}
