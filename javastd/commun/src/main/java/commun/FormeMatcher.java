package commun;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Un objet qui traduit un Dessin en Forme
 */
public class FormeMatcher {
    private List<Point> points;

    public Forme identify(Dessin d) {
        if (d.asList().isEmpty())
            return Forme.UNKNOWN;
        // Copie la liste
        points = new ArrayList<>();
        for (Point p : d.asList())
            points.add(p.copy());
        this.parcoursGraham(points);
        double perimetre = this.perimetre(points);
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

        double c = perimetre / 4;
        if (c * c > 0.9 * aire && c * c < 1.1 * aire) {
            return Forme.CARRE;
        }
        if (points.size() < 4)
            return Forme.TRIANGLE;
        /*
         * c = perimetre / 3; float h = (float) (c * Math.sqrt(3) / 2); if ((c * h / 2)
         * > 0.9 * aire && (c * h / 2) < 1.1 * aire) { return Forme.TRIANGLE; }
         **/
        if (circleTester(points, barycentre, 0.15))
            return Forme.ROND;
        if (triangleTester(points, 0.4))
            return Forme.TRIANGLE;
        return Forme.UNKNOWN;
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

    /**
     * Un simple calcul du barycentre d'une figure à base des moments.
     * @param points les points représentant la figure
     * @return un point représenant le barycentre de la figure
     */
    private Point barycentre(List<Point> points) {
        float xg = moment(points, 1, 0) / points.size();
        float yg = moment(points, 0, 1) / points.size();
        return new Point(xg, yg);
    }

    /**
     * on teste si les points passés en entrée correspondent à un cercle.
     * @param points
     * @param barycentre le centre du cercle testé
     * @param errorMarge la marge d'erreur qu'on s'accorde, qui sert pour déterminer si un point appartient au cercle.
     * @return
     */
    private boolean circleTester(List<Point> points, Point barycentre, double errorMarge) {
        float distanceMoyenne = 0;
        for (Point p : points)
            distanceMoyenne += distancePoint(barycentre, p);
        distanceMoyenne = distanceMoyenne / points.size();

        int errorCount = 0;
        for (Point p : points) {
            double d = distancePoint(barycentre, p);
            if (d < (1 - errorMarge) * distanceMoyenne || d > (1 + errorMarge) * distanceMoyenne)
                errorCount += 1;
        }

        if (errorCount > points.size() / 5)
            return false;
        return true;
    }

    /**
     * @param points     Les points dont on va tester si ils représentent un triangle
     * @param errorMarge La marge d'erreur qu'on s'autorise avec cette méthode qui sera assez élevé car cette méthode est assez imprécise (calcul de la somme des angles)
     * @return true si les points représentent un triangle, false sinon.
     */
    private boolean triangleTester(List<Point> points, double errorMarge) {
        int n = points.size();
        if (n < 3)
            return false;
        double sommeAngle = 0;
        for (int i = 0; i < n; i++) {
            double alpha;
            if (i == n - 2)
                alpha = angleTroisPoints(points.get(i), points.get(i + 1), points.get(0));
            else if (i == n - 1)
                alpha = angleTroisPoints(points.get(i), points.get(0), points.get(1));
            else
                alpha = angleTroisPoints(points.get(i), points.get(i + 1), points.get(i + 2));
            if (alpha > 90)
                alpha = 90 - alpha / 2;
            sommeAngle += alpha;
        }
        if ((sommeAngle > 180 * (1 - errorMarge)) && (sommeAngle < 180 * (1 + errorMarge)))
            return true;
        return false;
    }

    /**
     *
     * @param p1 le premier point formant l'angle, calculé dans le sens trigonométrique
     * @param p2 le second point
     * @param p3 le troisième point
     * @return  l'angle entre trois points, exprimé en degrés
     */
    private double angleTroisPoints(Point p1, Point p2, Point p3) {

        double a = distancePoint(p1, p2);
        double b = distancePoint(p2, p3);
        double c = distancePoint(p3, p1);
        if (a < 0.001 || b < 0.001 || c < 0.001) {
            return 0;
        }
        //  System.out.println(a+" " +b+" "+c);
        double dividende = a * a + b * b - c * c;
        double diviseur = 2 * a * b;

        // l'arcos n'est défini que si le quotient est entre -1 et 1

        if (dividende / diviseur <= -1) {

            return 180;
        }
        if (dividende / diviseur >= 1) {
            return 0;
        }
        double angleRad = Math.acos(dividende / diviseur);

        return angleRad * 180 / Math.PI;
    }

    private float area(List<Point> points) {
        float a = lambda(points.get(points.size() - 1), points.get(0));
        for (int i = 0; i < points.size() - 1; i++) {
            a += lambda(points.get(i), points.get(i + 1));
        }
        return Math.abs(a / 2);
    }

    /**
     * Une fonction auxiliaire pour le calcul de l'aire
     * @param p1
     * @param p2
     * @return
     */
    private float lambda(Point p1, Point p2) {
        return (p1.getX() * p2.getY()) - (p1.getY() * p2.getX());
    }

    /**
     * On suppose les points déjà triés par le parcours de Graham.
     * @param points
     * @return
     */
    private double perimetre(List<Point> points) {
        double per = distancePoint(points.get(0), points.get(points.size() - 1));
        for (int i = 0; i < points.size() - 1; i++) {
            per += distancePoint(points.get(i), points.get(i + 1));
        }
        return per;
    }

    /**
     * Une simple distance entre deux points, exprimée en float
     * @param p1
     * @param p2
     * @return
     */
    private double distancePoint(Point p1, Point p2) {
        return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX())
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

    /**
     * Les quatres premiers invariants de Hu (INUTILISES POUR LE MOMENT)
     * @param points
     * @param i le numéro de l'invariant désiré, par défaut on renvoie le premier invariant de Hu.
     * @return
     */
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

    /**
     * Le parcours de Graham modifie une liste de points
     * en ne gardant que les points de l'enveloppe convexe de la figure des points.
     * @param points la liste de points modifiée lors du parcours
     */

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

}
