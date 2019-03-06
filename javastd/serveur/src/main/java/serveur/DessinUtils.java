package serveur;

import java.util.List;

import commun.Dessin;
import commun.Point;

/**
 * Fonctions utilitaires pour les tests de reconnaissance de forme
 */
public final class DessinUtils {
	private DessinUtils() {}

	public static Dessin translate(Dessin d, double x, double y) {
		List<Point> points = d.asList();
		points.forEach(p -> {p.setX((float) (p.getX() + x)); p.setY((float) (p.getY() + y));});
		return Dessin.fromList(points);
	}
}
