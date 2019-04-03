package commun.stats;

import commun.jeux.ResultCTR;

public class Stats {
	private StatsTCR tcr;
	private StatsEnigme enigme;
	
	public Stats() {
		tcr = new StatsTCR();
		enigme = new StatsEnigme();
	}

	public StatsTCR getStatsTCR() {
		return tcr;
	}

	public StatsEnigme getStatsEnigme() {
		return enigme;
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

}
