package serveur;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import commun.Dessin;
import commun.Forme;
import commun.Point;

import static serveur.DessinUtils.*;

public class FormeMatcherTriangleTest {
	private FormeMatcher fm;
	
	@Before
	public void setup() {
		fm = new FormeMatcher();
	}

	@Test
	public void triangleParfait() {
		Dessin triangle = new Dessin(new Point[] {new Point(100, 150), new Point(150, 150),
				new Point(125, (float) (100 + 25 * Math.sqrt(3)))});
		
		assertEquals(Forme.TRIANGLE, fm.identify(triangle));
	}

	@Test
	public void translationTriangle() {
		Dessin triangle = new Dessin(new Point[] {new Point(300, 350), new Point(350, 350), 
				new Point(325, (float) (300 + 25 * Math.sqrt(3)))});
		
		//Translate
		for (int x = -200; x <= 200; x += 200) {
			for (int y = -200; y <= 200; y += 200) {
				assertEquals(Forme.TRIANGLE, fm.identify(translate(triangle, x, y)));
			}
			
		}	
	}

}
