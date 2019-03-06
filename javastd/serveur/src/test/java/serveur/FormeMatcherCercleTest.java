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
		Point[] pointsCercle = new Point[360];
		for (int i = 0; i < 360; i++) {
			pointsCercle[i] = new Point((float) Math.cos(i * 2 * Math.PI / 360.0) * 100 + 250,
					(float) Math.sin(i * 2 * Math.PI / 360.0) * 100 + 250);
		}
		cercle = new Dessin(pointsCercle);
		assertEquals(Forme.ROND, fm.identify(cercle));
	}

	@Test
	public void translationCarre() {
		Dessin carre = new Dessin(new Point[] {new Point(200, 200), new Point(400, 200), new Point(400, 400), new Point(200, 400)});
		
		//Translate dans pleins de directions
		for (int x = -200; x <= 200; x += 200) {
			for (int y = -200; y <= 200; y += 200) {
				assertEquals(Forme.CARRE, fm.identify(translate(carre, x, y)));
			}
		}
		
	}

}
