package commun.stats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commun.Forme;
import commun.jeux.ResultCTR;

public class StatsTest {
	private Stats stats;
	private StatsTCR tcr;
	private StatsEnigme enigme;
	private StatsReflex reflex;

	@Before
	public void setUp() {
		stats = new Stats();
		tcr = stats.getStatsTCR();
		enigme = stats.getStatsEnigme();
		reflex = stats.getStatsReflex();
	}

	@Test
	public void TCR() {
		assertEquals(0, tcr.getTotal());
		assertEquals(0, tcr.getVictoires());
		assertEquals(0, tcr.getDefaites());
		assertEquals(0, tcr.getCarres());
		assertEquals(0, tcr.getTriangles());
		assertEquals(0, tcr.getRonds());
		
		stats.addPartieTCR(new ResultCTR(Forme.CARRE, Forme.ROND, ResultCTR.CLIENT_GAGNE));
		assertEquals(1, tcr.getTotal());
		assertEquals(1, tcr.getVictoires());
		assertEquals(0, tcr.getDefaites());
		assertEquals(1, tcr.getCarres());
		assertEquals(0, tcr.getTriangles());
		assertEquals(0, tcr.getRonds());
		
		stats.addPartieTCR(new ResultCTR(Forme.TRIANGLE, Forme.ROND, ResultCTR.SERVEUR_GAGNE));
		assertEquals(2, tcr.getTotal());
		assertEquals(1, tcr.getVictoires());
		assertEquals(1, tcr.getDefaites());
		assertEquals(1, tcr.getCarres());
		assertEquals(1, tcr.getTriangles());
		assertEquals(0, tcr.getRonds());
		
		stats.addPartieTCR(new ResultCTR(Forme.ROND, Forme.ROND, ResultCTR.EGALITE));
		assertEquals(3, tcr.getTotal());
		assertEquals(1, tcr.getVictoires());
		assertEquals(1, tcr.getDefaites());
		assertEquals(1, tcr.getCarres());
		assertEquals(1, tcr.getTriangles());
		assertEquals(1, tcr.getRonds());
		
		stats.addPartieTCR(new ResultCTR(Forme.UNKNOWN, Forme.UNKNOWN, ResultCTR.ERREUR));
		assertEquals(3, tcr.getTotal());
		assertEquals(1, tcr.getVictoires());
		assertEquals(1, tcr.getDefaites());
		assertEquals(1, tcr.getCarres());
		assertEquals(1, tcr.getTriangles());
		assertEquals(1, tcr.getRonds());
	}
	
	@Test
	public void enigme() {
		assertEquals(0, enigme.getTotal());
		assertEquals(0, enigme.getBonnes());
		assertEquals(0, enigme.getMauvaises());
		
		stats.newEnigme();
		assertEquals(1, enigme.getTotal());
		assertEquals(0, enigme.getBonnes());
		assertEquals(0, enigme.getMauvaises());
		
		stats.bonneReponseEnigme();
		assertEquals(1, enigme.getTotal());
		assertEquals(1, enigme.getBonnes());
		assertEquals(0, enigme.getMauvaises());
		
		stats.mauvaiseReponseEnigme();
		assertEquals(1, enigme.getTotal());
		assertEquals(1, enigme.getBonnes());
		assertEquals(1, enigme.getMauvaises());
	}
	
	@Test
	public void reflex() {
		assertEquals(0, reflex.getParties());
		assertEquals(0, reflex.getBons());
		assertEquals(0, reflex.getMauvais());
		assertEquals(0, reflex.getMax());
		
		stats.newPartieReflex();
		assertEquals(1, reflex.getParties());
		assertEquals(0, reflex.getBons());
		assertEquals(0, reflex.getMauvais());
		assertEquals(0, reflex.getMax());
		
		stats.reponseReflex(true);
		assertEquals(1, reflex.getParties());
		assertEquals(1, reflex.getBons());
		assertEquals(0, reflex.getMauvais());
		assertEquals(0, reflex.getMax());
		
		stats.reponseReflex(false);
		assertEquals(1, reflex.getParties());
		assertEquals(1, reflex.getBons());
		assertEquals(1, reflex.getMauvais());
		assertEquals(0, reflex.getMax());
		
		stats.newRecordReflex(10);
		assertEquals(1, reflex.getParties());
		assertEquals(1, reflex.getBons());
		assertEquals(1, reflex.getMauvais());
		assertEquals(10, reflex.getMax());
		
		stats.newRecordReflex(16);
		assertEquals(16, reflex.getMax());
		stats.newRecordReflex(7);
		assertEquals(16, reflex.getMax());
	}

}
