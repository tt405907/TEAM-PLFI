package commun;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import commun.Dessin;
import commun.Point;

/**
 * Fonctions utilitaires pour les tests de reconnaissance de forme
 */
public final class DessinUtils {
	private DessinUtils() {}
	
	/**
	 * Créé un nouveau Dessin basé sur le dessin donné en appliquant la fonction donnée
	 * à chacun de ses points
	 * @param d Dessin à transformer
	 * @param f fonction à appliquer à chaque Point
	 * @return un nouveau Dessin avec des nouveaux Points
	 */
	public static Dessin transform(Dessin d, Function<Point, Point> f) {
		List<Point> points = d.asList();
		points = points.stream().map(f).collect(Collectors.toList());
		return Dessin.fromList(points);
	}

	/**
	 * Fait une copie translatée d'un Dessin
	 * @param d Dessin à translater
	 * @param x translation en x
	 * @param y translation en y
	 * @return copie du Dessin mais translatée
	 */
	public static Dessin translate(Dessin d, double x, double y) {
		return transform(d, p -> translate(p, x, y));
	}
	
	/**
	 * Fait une copie translatée d'un Point
	 * @param p Point à translater
	 * @param x translation en x
	 * @param y translation en y
	 * @return copie du Point translatée
	 */
	public static Point translate(Point p, double x, double y) {
		return new Point(p.getX() + x, p.getY() + y);
	}

	/**
	 * Fait une copie pivotée d'un Dessin
	 * @param d Dessin à pivoter
	 * @param centre Point centre de rotation
	 * @param angle angle de rotation en radians
	 * @return copie du Dessin pivotée
	 */
	public static Dessin rotate(Dessin d, Point centre, double angle) {
		return transform(d, p -> rotate(p, centre, angle));
	}
	
	/**
	 * Fait une copie pivotée d'un Point
	 * @param p Point à pivoter
	 * @param centre Point centre de rotation
	 * @param angle angle de rotation en radians
	 * @return copie du Point pivotée
	 */
	public static Point rotate(Point p, Point centre, double angle) {
		double diffX = p.getX() - centre.getX();
		double diffY = p.getY() - centre.getY();
		return new Point(
				diffX * Math.cos(angle) + diffY * Math.sin(angle) + centre.getX(),
				- diffX * Math.sin(angle) + diffY * Math.cos(angle) + centre.getY());
	}
	
	/**
	 * Fait une copie translatée aléatoirement d'un Dessin
	 * @param d Dessin à translater
	 * @param rand source d'aléatoire
	 * @param max translation maximale en x et y (dans les 2 sens)
	 * @return copie du Dessin translatée
	 */
	public static Dessin randomTranslate(Dessin d, Random rand, double max) {
		return transform(d, p -> randomTranslate(p, rand, max));
	}
	
	/**
	 * Fait une copie translatée aléatoirement d'un Point
	 * @param p Point à translater
	 * @param rand source d'aléatoire
	 * @param max translation maximale en x et y (dans les 2 sens)
	 * @return copie du Point translatée
	 */
	public static Point randomTranslate(Point p, Random rand, double max) {
		return translate(p, (rand.nextDouble()-0.5)*2*max, (rand.nextDouble()-0.5)*2*max);
	}
}
