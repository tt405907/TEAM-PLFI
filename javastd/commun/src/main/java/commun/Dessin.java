package commun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Représente une forme dessinée
 */
public class Dessin {
	private Point[] points;
	
	//Pour json
	public Dessin() {}
	public Dessin(Point[] points) {
		this.points = points;
	}
	
	public Point[] getPoints() {
		return points;
	}
	public void setPoints(Point[] points) {
		this.points = points;
	}
	
	public static Dessin fromList(List<Point> points) {
		return new Dessin(points.toArray(new Point[0]));
	}
	
	public List<Point> asList() {
		return new ArrayList<>(Arrays.asList(points));
	}
}
