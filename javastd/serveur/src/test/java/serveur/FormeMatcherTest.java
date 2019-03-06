package serveur;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import commun.Dessin;
import commun.Forme;
import commun.Point;

public class FormeMatcherTest {
	private FormeMatcher fm;
	
	//Fonctions utilitaires
	private Dessin translate(Dessin d, double x, double y) {
		List<Point> points = d.asList();
		points.forEach(p -> {p.setX((float) (p.getX() + x)); p.setY((float) (p.getY() + y));});
		return Dessin.fromList(points);
	}
	
	@Before
	public void setup() {
		fm = new FormeMatcher();
	}

	@Test
	public void carreParfait() {
		Dessin carre = new Dessin(new Point[] {new Point(100, 100), new Point(300, 100), new Point(300, 300), new Point(100, 300)});
		
		assertEquals(Forme.CARRE, fm.identify(carre));
	}

	@Test
	public void translationCarre() {
		Dessin carre = new Dessin(new Point[] {new Point(100, 100), new Point(300, 100), new Point(300, 300), new Point(100, 300)});
		
		//Translate dans pleins de directions
		for (int x = -100; x <= 100; x += 100) {
			for (int y = -100; y <= 100; y += 100) {
				assertEquals(Forme.CARRE, fm.identify(translate(carre, x, y)));
			}
		}
		
	}

}
