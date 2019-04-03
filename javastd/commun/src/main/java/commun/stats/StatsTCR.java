package commun.stats;

import commun.jeux.ResultCTR;

public class StatsTCR {
	private int triangles, carres, ronds;
	private int victoires, defaites, egalites;
	private int total;
	
	public void addPartie(ResultCTR result) {
		if (result.getResult().equals(ResultCTR.ERREUR)) return;
		
		total++;
		
		switch (result.getFormeClient()) {
		case TRIANGLE:
			triangles++;
			break;
		case CARRE:
			carres++;
			break;
		case ROND:
			ronds++;
			break;
		default:
			break;
		}
		
		if (result.getResult().equals(ResultCTR.CLIENT_GAGNE)) {
			victoires++;
		}
		else if (result.getResult().equals(ResultCTR.SERVEUR_GAGNE)) {
			defaites++;
		}
		else if (result.getResult().equals(ResultCTR.EGALITE)) {
			egalites++;
		}
	}
	
	public int getTriangles() {
		return triangles;
	}
	public void setTriangles(int triangles) {
		this.triangles = triangles;
	}
	public int getCarres() {
		return carres;
	}
	public void setCarres(int carres) {
		this.carres = carres;
	}
	public int getRonds() {
		return ronds;
	}
	public void setRonds(int ronds) {
		this.ronds = ronds;
	}
	public int getVictoires() {
		return victoires;
	}
	public void setVictoires(int victoires) {
		this.victoires = victoires;
	}
	public int getDefaites() {
		return defaites;
	}
	public void setDefaites(int defaites) {
		this.defaites = defaites;
	}
	public int getEgalites() {
		return egalites;
	}
	public void setEgalites(int egalites) {
		this.egalites = egalites;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	

}
