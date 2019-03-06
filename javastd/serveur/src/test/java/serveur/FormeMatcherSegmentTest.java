package serveur;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import commun.Dessin;
import commun.Forme;
import commun.Point;

import static serveur.DessinUtils.*;

public class FormeMatcherSegmentTest {
	private FormeMatcher fm;
	
	@Before
	public void setup() {
		fm = new FormeMatcher();
	}

	@Test
	public void segementParfait() {
		Dessin segment = new Dessin(new Point[] {new Point(100, 100), new Point(300, 100), new Point(300, 300), new Point(100, 300)});
		
		assertEquals(Forme.CARRE, fm.identify(segment));
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
