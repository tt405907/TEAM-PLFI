package serveur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commun.Dessin;
import commun.Forme;
import commun.Point;

/**
 * Un objet qui traduit un Dessin en Forme
 */
public class FormeMatcher {
	private List<Point> points;

	public Forme identify(Dessin d) {
		// TODO: vraie reconnaissance
		// "Reconnaissance" TEMPORAIRE très aléatoire juste pour que ça fasse des choses
		points = new ArrayList<Point>(Arrays.asList(d.getPoints()));
		Point barycentre = barycentre(points);
		for (Forme f : Forme.FORMES) {

		}
		// Aucune forme reconnue
		return Forme.UNKNOWN;
	}

	private Point barycentre(List<Point> points) {
		float xg = moment(points, 1, 0) / points.size();
		float yg = moment(points, 0, 1) / points.size();
		return new Point(xg, yg);
	}

	private float moment(List<Point> points, int p, int q) {
		float m = 0;
		for (Point po : points) {
			m += Math.pow(po.getX(), p) * Math.pow(po.getY(), q);
		}
		return m;
	}

	private float momentCentre(List<Point> points, int p, int q) {
		float m = 0;
		Point g = barycentre(points);
		for (Point po : points) {
			m += Math.pow(po.getX() - g.getX(), p) * Math.pow(po.getY() - g.getY(), q);
		}
		return m;
	}

	private float huInvariant(List<Point> points, int i) {
		switch (i) {
		case 1:
			return momentCentre(points, 2, 0) + momentCentre(points, 0, 2);
			
		case 2:
			return huInvariant(points, 1) * huInvariant(points, 1)
					+ 4 * momentCentre(points, 1, 1) * momentCentre(points, 1, 1);
			
		case 3:
			float a = momentCentre(points, 3, 0) - 3 * momentCentre(points, 1, 2);
			float b = 3 * momentCentre(points, 2, 1) - momentCentre(points, 0, 3);
			return a * a + b * b;
		case 4:
			float c = momentCentre(points, 3, 0) + momentCentre(points, 1, 2);
			float d = momentCentre(points, 2, 1) + momentCentre(points, 0, 3);
			return c * c + d * d;
		default:
			return huInvariant(points,1);
		}
	}
}
