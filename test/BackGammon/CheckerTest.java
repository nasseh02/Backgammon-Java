// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckerTest {

    // Check checker can be initialised
    @Test
    public void testCheckerInitialization() {
        Checker checker = new Checker(Colour.RED, false, true);
        assertEquals(Colour.RED, checker.getChkColour());
        assertTrue(checker.getOnBoard());
        assertFalse(checker.getOnBar());
    }
}
