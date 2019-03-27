package commun;

import static commun.DessinUtils.translate;

import java.util.ArrayList;
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
        // Copie la liste
        points = new ArrayList<>();
        for (Point p : d.asList())
            points.add(p.copy());
        this.parcoursGraham(points);
        float perimetre = this.perimetre(points);
        float aire = this.area(points);
        if (points.size() <= 2)
            return Forme.SEGMENT;
        if (!this.fixRatio(points))
            return Forme.POINT;

        Point barycentre = barycentre(points);
        float m22 = momentCentre(points, 2, 2);
        float m04m40 = momentCentre(points, 0, 4) * momentCentre(points, 4, 0);
        float m51m40 = momentCentre(points, 5, 1) * momentCentre(points, 4, 0);
        float m33 = momentCentre(points, 3, 3);
        float m80m042 = momentCentre(points, 8, 0) * momentCentre(points, 0, 4) * momentCentre(points, 0, 4);
        float m62m04 = momentCentre(points, 6, 2) * momentCentre(points, 0, 4);
        float m44 = momentCentre(points, 4, 4);
        float iHu1 = huInvariant(points, 1);
        float iHu2 = huInvariant(points, 2);
        float iHu3 = huInvariant(points, 3);
        float iHu4 = huInvariant(points, 4);
//		System.out.println("perimetre : " + perimetre + " aire : " + aire + " m22 : " + m22 + " m04m40 : " + m04m40
//				+ " m51m40 : " + m51m40 + " m33 : " + m33 + " m80m042 : " + m80m042 + " m62m04 : " + m62m04 + " m44 : "
//				+ m44 + " iHu1 : " + iHu1 + "iHu2 : " + iHu2 + " iHu3 : " + iHu3 + " iHu4 : " + iHu4);

        float c = perimetre / 4;
        if (c * c > 0.9 * aire && c * c < 1.1 * aire) {
            return Forme.CARRE;
        }
        if (points.size() < 4)
            return Forme.TRIANGLE;
        /*
         * c = perimetre / 3; float h = (float) (c * Math.sqrt(3) / 2); if ((c * h / 2)
         * > 0.9 * aire && (c * h / 2) < 1.1 * aire) { return Forme.TRIANGLE; }
         */
        if (circleTester(points, barycentre, 0.15))
            return Forme.ROND;


        return Forme.TRIANGLE;
    }

    private boolean fixRatio(List<Point> points) {
        float xmax = points.stream().max(Comparator.comparingDouble(Point::getX)).get().getX();
        float xmin = points.stream().min(Comparator.comparingDouble(Point::getX)).get().getX();
        float ymax = points.stream().max(Comparator.comparingDouble(Point::getY)).get().getY();
        float ymin = points.stream().min(Comparator.comparingDouble(Point::getY)).get().getY();

        float ratio = 100 / ((xmax - xmin + ymax - ymin) / 2);
        if ((xmax - xmin) < 10) {
            if ((ymax - ymin) < 10)
                return false;
            else
                ratio = ymax - ymin;

        }
        if ((ymax - ymin) < 10)
            ratio = xmax - xmin;
        final float ratiolambda = ratio;
        points.forEach(p -> {
            p.setX((float) (p.getX() * ratiolambda));
            p.setY((float) (p.getY() * ratiolambda));
        });
        return true;
    }

    private Point barycentre(List<Point> points) {
        float xg = moment(points, 1, 0) / points.size();
        float yg = moment(points, 0, 1) / points.size();
        return new Point(xg, yg);
    }

    private boolean circleTester(List<Point> points, Point barycentre, double errorMarge) {
        float distanceMoyenne = 0;
        for (Point p : points)
            distanceMoyenne += distancePoint(barycentre, p);
        distanceMoyenne = distanceMoyenne / points.size();

        int errorCount = 0;
        for (Point p : points) {
            float d = distancePoint(barycentre, p);
            if (d < (1 - errorMarge) * distanceMoyenne || d > (1 + errorMarge) * distanceMoyenne)
                errorCount += 1;
        }

        if (errorCount > points.size() / 5)
            return false;
        return true;
    }

    private float area(List<Point> points) {
        float a = lambda(points.get(points.size() - 1), points.get(0));
        for (int i = 0; i < points.size() - 1; i++) {
            a += lambda(points.get(i), points.get(i + 1));
        }
        return Math.abs(a / 2);
    }

    private float lambda(Point p1, Point p2) {
        return (p1.getX() * p2.getY()) - (p1.getY() * p2.getX());
    }

    // on suppose les points triés par graham
    private float perimetre(List<Point> points) {
        float per = distancePoint(points.get(0), points.get(points.size() - 1));
        for (int i = 0; i < points.size() - 1; i++) {
            per += distancePoint(points.get(i), points.get(i + 1));
        }
        return per;
    }

    private float distancePoint(Point p1, Point p2) {
        return (float) Math.sqrt((double) (p1.getX() - p2.getX()) * (p1.getX() - p2.getX())
                + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
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

    private void parcoursGraham(List<Point> points) {
        List<Point> sorted = new ArrayList<>();
        float ymin = points.stream().min(Comparator.comparingDouble(Point::getY)).get().getY();

        Point pivot = new Point(10000, 0);
        Point max = new Point(-10000, 0);
        for (Point p : points) {
            if (p.getY() == ymin) {
                if (p.getX() < pivot.getX())
                    pivot = p;
                if (p.getX() > max.getX())
                    max = p;
            }
        }
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if (p.getY() == ymin && p.getX() < max.getX())
                points.remove(p);
        }

        points.remove(pivot);
        while (!points.isEmpty()) {
            Point nextPoint = points.get(0);
            double theta = 10E12;
            for (Point p : points) {
                double thetap = Math.atan2(p.getY() - pivot.getY(), p.getX() - pivot.getX());
                if (thetap < theta) {
                    nextPoint = p;
                    theta = thetap;
                }
            }
            sorted.add(nextPoint);
            points.remove(nextPoint);
        }

        points.add(pivot);
        points.add(sorted.get(0));
        sorted.remove(0);
        sorted.add(pivot);
        while (!sorted.isEmpty()) {
            Point currentPoint = sorted.get(0);
            Point lastPoint = points.get(points.size() - 1);
            Point beforeLastPoint = points.get(points.size() - 2);

            float t = (lastPoint.getX() - beforeLastPoint.getX()) * (currentPoint.getY() - beforeLastPoint.getY())
                    - (lastPoint.getY() - beforeLastPoint.getY()) * (currentPoint.getX() - beforeLastPoint.getX());
            if (t == 0) {
                points.remove(lastPoint);
                points.add(currentPoint);
                sorted.remove(0);
            } else if (t < 0) {
                points.remove(lastPoint);
            } else {
                points.add(currentPoint);
                sorted.remove(0);
            }
        }
        points.remove(points.size() - 1);
        //System.out.println(points.size());
    }

    public static void main(String[] args) {
        FormeMatcher fm = new FormeMatcher();
        Dessin carreParfait = new Dessin(
                new Point[]{new Point(200, 100), new Point(400, 100), new Point(400, 300), new Point(200, 300)});
        Point[] pointsCercle = new Point[360];
        for (int i = 0; i < 360; i++) {
            pointsCercle[i] = new Point((float) Math.cos(i * 2 * Math.PI / 360.0) * 100 + 250,
                    (float) Math.sin(i * 2 * Math.PI / 360.0) * 100 + 250);
        }
        Dessin cercleParfait = new Dessin(pointsCercle);
        Point[] pointsTriangle = new Point[]{new Point(200, 300), new Point(300, 300),
                new Point(250, (float) (200 + 50 * Math.sqrt(3)))};
        Dessin triangleParfait = new Dessin(pointsTriangle);
        Dessin segmentParfait = new Dessin(
                new Point[]{new Point(300, 200), new Point(300, 400), new Point(300, 300)});

        List<Point> points = new ArrayList<>();

        float xStart = 500, yStart = 150, steplen = 9, steps = 14;

        // Haut gauche -> haut droit
        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart + steplen * i, yStart));
        }
        // Haut droit -> bas droit
        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart + steplen * steps, yStart + steplen * i));
        }
        // Bas droit -> bas gauche
        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart + steplen * (steps - i), yStart + steplen * steps));
        }
        // Bas gauche -> haut gauche
        for (int i = 0; i < steps; i++) {
            points.add(new Point(xStart, yStart + steplen * (steps - i)));
        }
        Dessin CarreCompose = Dessin.fromList(points);

        points = CarreCompose.asList();
        for (int i = 0; i < 180; i++) {
            pointsCercle[i] = new Point((float) Math.cos(i * 2 * Math.PI / 180.0) * 100 + 625,
                    (float) Math.sin(i * 2 * Math.PI / 180.0) * 100 + 625);
        }
        Dessin cercle = new Dessin(pointsCercle);
        // Translate dans pleins de directions
        for (int x = -200; x <= 200; x += 200) {
            for (int y = -200; y <= 200; y += 200) {
                fm.identify(translate(cercle, x, y));
            }
        }
        System.out.print("Carre parfait : ");
        fm.identify(carreParfait);
        System.out.print("Cercle parfait : ");
        fm.identify(cercleParfait);
        System.out.print("Triangle parfait : ");
        fm.identify(triangleParfait);
        System.out.println("Segment parfait : ");
        fm.identify(segmentParfait);
        System.out.println("Carre compose : ");
        fm.identify(CarreCompose);
    }
}
