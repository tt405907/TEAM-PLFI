package commun;

/**
 * Représente une forme reconnue.
 */
public enum Forme {
	POINT,
	SEGMENT,
	TRIANGLE,
	CARRE,
	ROND,
	/**
	 * Une forme inconnue, donc une erreur
	 */
	UNKNOWN,
	POLYGONE;
	
	/**
	 * Toutes les formes non erreurs
	 */
	public static final Forme[] FORMES = {POINT, SEGMENT, TRIANGLE, CARRE, ROND, POLYGONE};
}
