package serveur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
		points = d.asList();
		float xmax = points.stream().max(Comparator.comparingDouble(Point::getX)).get().getX();
		float xmin = points.stream().min(Comparator.comparingDouble(Point::getX)).get().getX();
		float ymax = points.stream().max(Comparator.comparingDouble(Point::getX)).get().getY();
		float ymin = points.stream().min(Comparator.comparingDouble(Point::getX)).get().getY();
		float ratio = 100/((xmax - xmin + ymax - ymin) / 2);
		points.forEach(p -> {p.setX((float) (p.getX() *ratio)); p.setY((float) (p.getY() *ratio));});
		Point barycentre = barycentre(points);
		float iHu1 = huInvariant(points, 1);
		float iHu2 = huInvariant(points, 2);
		float iHu3 = huInvariant(points, 3);
		float iHu4 = huInvariant(points, 4);
		if (iHu3 < 0.1 && iHu4 < 0.1) {
			if (iHu2 < 50000)
				return Forme.SEGMENT;
			return Forme.CARRE;
		}
		if (iHu3 > 10E6 || iHu4 > 10E6) {
			return Forme.TRIANGLE;
		}

		// Aucune forme reconnue
		return Forme.ROND;
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
			return huInvariant(points, 1);
		}
	}

	public static void main(String[] args) {
		FormeMatcher fm = new FormeMatcher();
		Dessin carreParfait = new Dessin(
				new Point[] { new Point(200, 100), new Point(400, 100), new Point(400, 300), new Point(200, 300) });

		Dessin cercleParfait;
		Point[] pointsCercle = new Point[360];
		for (int i = 0; i < 360; i++) {
			pointsCercle[i] = new Point((float) Math.cos(i * 2 * Math.PI / 360.0) * 100 + 250,
					(float) Math.sin(i * 2 * Math.PI / 360.0) * 100 + 250);
		}
		cercleParfait = new Dessin(pointsCercle);
		Point[] pointsTriangle = new Point[] { new Point(200, 300), new Point(300, 300),
				new Point(250, (float) (200 + 50 * Math.sqrt(3))) };
		Dessin triangleParfait = new Dessin(pointsTriangle);
		Dessin segmentParfait = new Dessin(
				new Point[] { new Point(300, 200), new Point(300, 400), new Point(300, 300) });
		List<Point> pointsc = carreParfait.asList();
		List<Point> pointsr = cercleParfait.asList();
		List<Point> pointst = triangleParfait.asList();
		List<Point> pointss = segmentParfait.asList();

		System.out.println("Carre parfait :" + " " + fm.barycentre(pointsc).getX() + " " + fm.barycentre(pointsc).getY()
				+ " " + fm.huInvariant(pointsc, 1) + " " + fm.huInvariant(pointsc, 2) + " " + fm.huInvariant(pointsc, 3)
				+ " " + fm.huInvariant(pointsc, 4));
		System.out.println("Cercle parfait :" + " " + fm.barycentre(pointsr).getX() + " "
				+ fm.barycentre(pointsr).getY() + " " + fm.huInvariant(pointsr, 1) + " " + fm.huInvariant(pointsr, 2)
				+ " " + fm.huInvariant(pointsr, 3) + " " + fm.huInvariant(pointsr, 4));
		System.out.println("Triangle parfait :" + " " + fm.barycentre(pointst).getX() + " "
				+ fm.barycentre(pointst).getY() + " " + fm.huInvariant(pointst, 1) + " " + fm.huInvariant(pointst, 2)
				+ " " + fm.huInvariant(pointst, 3) + " " + fm.huInvariant(pointst, 4));
		System.out.println("Segment parfait :" + " " + fm.barycentre(pointss).getX() + " "
				+ fm.barycentre(pointss).getY() + " " + fm.huInvariant(pointss, 1) + " " + fm.huInvariant(pointss, 2)
				+ " " + fm.huInvariant(pointss, 3) + " " + fm.huInvariant(pointss, 4));
	}
}
