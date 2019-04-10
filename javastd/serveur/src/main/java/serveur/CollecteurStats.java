package serveur;

import java.util.HashMap;
import java.util.Map;

import commun.Identification;
import commun.jeux.ResultCTR;
import commun.stats.Stats;

public class CollecteurStats {
	private Map<String, Stats> stats;
	
	public CollecteurStats() {
		stats = new HashMap<>();
	}
	
	private void makePresent(Identification id) {
		if (!stats.containsKey(id.getNom())) {
			stats.put(id.getNom(), new Stats());
		}
	}
	
	public Stats getStats(Identification id) {
		makePresent(id);
		return stats.get(id.getNom());
	}
	
	public void addPartieTCR(Identification id, ResultCTR result) {
		makePresent(id);
		stats.get(id.getNom()).addPartieTCR(result);
	}
	
	public void newEnigme(Identification id) {
		makePresent(id);
		stats.get(id.getNom()).newEnigme();
	}
	public void bonneReponseEnigme(Identification id) {
		makePresent(id);
		stats.get(id.getNom()).bonneReponseEnigme();
	}
	public void mauvaiseReponseEnigme(Identification id) {
		makePresent(id);
		stats.get(id.getNom()).mauvaiseReponseEnigme();
	}
	
	public void newPartieReflex(Identification id) {
		makePresent(id);
		stats.get(id.getNom()).newPartieReflex();
	}
	public void reponseReflex(Identification id, boolean bonne) {
		makePresent(id);
		stats.get(id.getNom()).reponseReflex(bonne);
	}
	public void newRecordReflex(Identification id, int score) {
		makePresent(id);
		stats.get(id.getNom()).newRecordReflex(score);
	}
}
