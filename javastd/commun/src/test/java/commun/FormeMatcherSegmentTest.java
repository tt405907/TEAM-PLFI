package commun;

import static commun.DessinUtils.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import commun.Dessin;
import commun.Forme;
import commun.Point;

public class FormeMatcherSegmentTest {
	private FormeMatcher fm;
	
	@Before
	public void setup() {
		fm = new FormeMatcher();
	}

	@Test
	public void segementParfait() {
		Dessin segment = new Dessin(new Point[] {new Point(400, 300), new Point(400, 500), new Point(400, 400)});
		
		assertEquals(Forme.SEGMENT, fm.identify(segment));
	}

	@Test
	public void translationSegment() {
		Dessin segment = new Dessin(new Point[] {new Point(200, 100), new Point(200, 300), new Point(200, 200)});
		
		//Translate dans pleins de directions
		for (int x = -200; x <= 200; x += 200) {
			for (int y = -200; y <= 200; y += 200) {
				assertEquals(Forme.SEGMENT, fm.identify(translate(segment, x, y)));
			}
		}
		
	}

}
