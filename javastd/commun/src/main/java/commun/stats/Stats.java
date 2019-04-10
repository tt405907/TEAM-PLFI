package commun.stats;

import commun.jeux.ResultCTR;

public class Stats {
	private StatsTCR tcr;
	private StatsEnigme enigme;
	private StatsReflex reflex;
	
	public Stats() {
		tcr = new StatsTCR();
		enigme = new StatsEnigme();
		reflex = new StatsReflex();
	}

	public StatsTCR getStatsTCR() {
		return tcr;
	}

	public StatsEnigme getStatsEnigme() {
		return enigme;
	}
	
	public StatsReflex getStatsReflex() {
		return reflex;
	}
	
	public void addPartieTCR(ResultCTR result) {
		tcr.addPartie(result);
	}
	
	public void newEnigme() {
		enigme.newEnigme();
	}
	public void bonneReponseEnigme() {
		enigme.bonneReponse();
	}
	public void mauvaiseReponseEnigme() {
		enigme.mauvaiseReponse();
	}
	
	public void newPartieReflex() {
		reflex.newPartie();
	}
	public void reponseReflex(boolean bonne) {
		reflex.reponse(bonne);
	}
	public void newRecordReflex(int score) {
		reflex.newRecord(score);
	}

}
