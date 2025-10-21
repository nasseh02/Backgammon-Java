// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColourTest {

    @Test
    public void testEnumValues() {
        // Arrange: Get all Colour values
        Colour[] colours = Colour.values();

        // Assert: Verify the enum contains the expected values
        assertEquals(3, colours.length, "Incorrect number of Colour enum values");
        assertEquals(Colour.RED, colours[0], "First Colour value should be RED");
        assertEquals(Colour.WHITE, colours[1], "Second Colour value should be WHITE");
        assertEquals(Colour.NONE, colours[2], "Third Colour value should be NONE");
    }

    @Test
    public void testToString() {
        // Assert: Verify the toString output of each Colour
        assertEquals("RED", Colour.RED.toString(), "Incorrect toString for RED");
        assertEquals("WHITE", Colour.WHITE.toString(), "Incorrect toString for WHITE");
        assertEquals("NONE", Colour.NONE.toString(), "Incorrect toString for NONE");
    }
}
