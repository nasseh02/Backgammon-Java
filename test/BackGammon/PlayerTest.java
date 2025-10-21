// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    // Checks that player can be given a name
    @Test
    public void testGetPlayerName() {
        Player player = new Player("John", 0);
        assertEquals("John", player.getPlayerName());
    }

    // Checks that player can be given a score
    @Test
    public void testSetAndGetPlayerScore() {
        Player player = new Player("Jane", 0);
        player.setPlayerScore(10);
        assertEquals(10, player.getPlayerScore());
    }

    // Checks that player can be given a score on instantiation.
    @Test
    public void testPlayerScoreInitialization() {
        Player player = new Player("Alice", 5);
        assertEquals(5, player.getPlayerScore());
    }
}
