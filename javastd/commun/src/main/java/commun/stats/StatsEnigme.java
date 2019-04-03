package commun.stats;

public class StatsEnigme {
	private int bonnes, mauvaises;
	private int total;
	
	public void newEnigme() {
		total++;
	}
	public void bonneReponse() {
		bonnes++;
	}
	public void mauvaiseReponse() {
		mauvaises++;
	}
	
	public int getBonnes() {
		return bonnes;
	}
	public void setBonnes(int bonnes) {
		this.bonnes = bonnes;
	}
	public int getMauvaises() {
		return mauvaises;
	}
	public void setMauvaises(int mauvaises) {
		this.mauvaises = mauvaises;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

}
