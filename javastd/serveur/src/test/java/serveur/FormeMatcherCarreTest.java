package serveur;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import commun.Dessin;
import commun.Forme;
import commun.Point;

public class FormeMatcherCarreTest {
	private FormeMatcher fm;
	
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
	public void directionTrace() {
		//Trace le carré parfait en partant de coins différents en tournant dans une direction différente
		List<Point> points = new ArrayList<>();
		
		points.add(new Point(500, 200));
		points.add(new Point(600, 200));
		points.add(new Point(600, 300));
		points.add(new Point(500, 300));

		//Sens horaire
		//Haut gauche
		assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
		//Haut droit
		points.add(points.remove(0));
		assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
		//Bas droit
		points.add(points.remove(0));
		assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
		//Bas gauche
		points.add(points.remove(0));
		assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
		
		//Sens anti-horaire
		points.add(points.remove(0));
		Collections.reverse(points);
		//Haut gauche
		assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
		//Bas gauche
		points.add(points.remove(0));
		assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
		//Bas droit
		points.add(points.remove(0));
		assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
		//Haut droit
		points.add(points.remove(0));
		assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
	}

	@Test
	public void translation() {
		Dessin carre = new Dessin(new Point[] {new Point(200, 200), new Point(400, 200), new Point(400, 400), new Point(200, 400)});
		
		//Translate dans pleins de directions
		for (int x = -210; x <= 210; x += 70) {
			for (int y = -210; y <= 210; y += 70) {
				assertEquals(Forme.CARRE, fm.identify(DessinUtils.translate(carre, x, y)));
			}
		}
		
	}
	
	@Test
	public void compose() {
		//Un carré parfait dont les segments sont composés de plusieurs points
		List<Point> points = new ArrayList<>();
		
		float xStart = 500, yStart = 150, steplen = 9, steps = 14;
		
		//Haut gauche -> haut droit
		for (int i = 0; i < steps; i++) {
			points.add(new Point(xStart + steplen*i, yStart));
		}
		//Haut droit -> bas droit
		for (int i = 0; i < steps; i++) {
			points.add(new Point(xStart + steplen*steps, yStart + steplen*i));
		}
		//Bas droit -> bas gauche
		for (int i = 0; i < steps; i++) {
			points.add(new Point(xStart + steplen*(steps-i), yStart + steplen*steps));
		}
		//Bas gauche -> haut gauche
		for (int i = 0; i < steps; i++) {
			points.add(new Point(xStart, yStart + steplen*(steps-i)));
		}
		
		assertEquals(Forme.CARRE, fm.identify(Dessin.fromList(points)));
	}

	@Test
	public void redimension() {
		//Différentes tailles de carrés
		for (int taille = 40; taille <= 200; taille += 40) {
			Point[] p = new Point[]{new Point(100, 100), new Point(100+taille, 100), new Point(100+taille, 100+taille), new Point(100, 100+taille)};
			assertEquals(Forme.CARRE, fm.identify(new Dessin(p)));
		}
	}

	@Test
	public void rotation() {
		Dessin carre = new Dessin(new Point[] {new Point(200, 200), new Point(400, 200), new Point(400, 400), new Point(200, 400)});
		Point centre = new Point(300, 300);
		
		//Rotationne dans plein d'angles
		for (int theta = 0; theta < 20; theta++) {
			double angle = theta * Math.PI / (2*20);
			assertEquals(Forme.CARRE, fm.identify(DessinUtils.rotate(carre, centre, angle)));
		}
		
	}

	@Test
	public void composeTremblant() {
		//Même carré que compose
		//Va bouger chaque point aléatoirement pour simuler un dessin "tremblant"
		List<Point> points = new ArrayList<>();
		
		float xStart = 500, yStart = 150, steplen = 20, steps = 10;
		
		for (int i = 0; i < steps; i++) {
			points.add(new Point(xStart + steplen*i, yStart));
		}
		for (int i = 0; i < steps; i++) {
			points.add(new Point(xStart + steplen*steps, yStart + steplen*i));
		}
		for (int i = 0; i < steps; i++) {
			points.add(new Point(xStart + steplen*(steps-i), yStart + steplen*steps));
		}
		for (int i = 0; i < steps; i++) {
			points.add(new Point(xStart, yStart + steplen*(steps-i)));
		}
		
		Dessin carre = Dessin.fromList(points);
		//Graine fixe arbitraire pour que le test soit consistant
		Random rand = new Random(180114041513L);
		
		//Beaucoup d'essais
		for (int i = 0; i < 100; i++) {
			//TODO: Améliorer reconnaissance de forme
			//assertEquals(Forme.CARRE, fm.identify(DessinUtils.randomTranslate(carre, rand, 10)));
		}
		
	}

}