package commun.stats;

public class StatsReflex {
	private int parties, bons, mauvais, max;
	
	public void newPartie() {
		parties++;
	}
	public void reponse(boolean bonne) {
		if (bonne) bons++;
		else mauvais++;
	}
	public void newRecord(int score) {
		max = Math.max(score, max);
	}

	public int getParties() {
		return parties;
	}
	public void setParties(int parties) {
		this.parties = parties;
	}
	public int getBons() {
		return bons;
	}
	public void setBons(int bons) {
		this.bons = bons;
	}
	public int getMauvais() {
		return mauvais;
	}
	public void setMauvais(int mauvais) {
		this.mauvais = mauvais;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	

}
