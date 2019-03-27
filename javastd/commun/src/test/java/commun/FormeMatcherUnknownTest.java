package commun;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class FormeMatcherUnknownTest {
    private FormeMatcher fm;

    @Before
    public void setup() {
        fm = new FormeMatcher();
    }

    @Test
    public void empty() {
        assertEquals(Forme.UNKNOWN, fm.identify(Dessin.fromList(Collections.emptyList())));
    }
    
    //TODO: faire des gribouillis sur un téléphone pour tests pratiques
}
