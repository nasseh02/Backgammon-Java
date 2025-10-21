// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DiceRollTest {

    // Checks that dice can be rolled on instantiation
    @Test
    public void testDiceRollInitialization() {
        DiceRoll dice = new DiceRoll();
        assertNotNull(dice.getMoves());
    }

    // Checks that dice can be assigned custom roll values
    @Test
    public void testAssignDice() {
        DiceRoll dice = new DiceRoll();
        dice.assign_dice(4, 4);
        assertEquals(4, dice.getMoves().size());
        assertTrue(dice.getMoves().contains(4));
    }
}
