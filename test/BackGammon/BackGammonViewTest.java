// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class BackGammonViewTest {

    @Test
    void testMatchStart() {
        // Simulate match start and verify the output
        BackGammonView.matchStart();
        // Since this is just a printed output, manual observation is needed or redirect System.out for assertions
    }

    @Test
    void testGameDisplay() {
        // Call GameDisplay to ensure it runs without errors
        BackGammonView.GameDisplay();
        // No assertions for output, as it is visual. Ensure no exceptions occur.
    }

    @Test
    void testDisplayMoves() {
        // Prepare mock activePlayer and Store_Moves objects
        activePlayer mockPlayer = new activePlayer("Player 1", Colour.RED, 0);
        Store_Moves mockMoves = new Store_Moves();
        mockMoves.setCount(Arrays.asList(1, 2, 3));
        mockMoves.setStart(Arrays.asList(3, 5, 7));
        mockMoves.setRoll(Arrays.asList(2, 4, 6));
        mockMoves.setAffect(Arrays.asList(0, 1, 2));

        // Call displayMoves and ensure no exceptions occur
        BackGammonView.displayMoves(mockPlayer, mockMoves);
    }

    @Test
    void testCheckGameOver() {
        // Prepare mock objects
        activePlayer[] mockPlayers = {
            new activePlayer("Player 1", Colour.RED, 0),
            new activePlayer("Player 2", Colour.WHITE, 0)
        };
        Board mockBoard = new Board();
        boolean forfeit = true;
        int isDouble = 2;

        // Simulate game-over conditions
        boolean result = BackGammonView.checkGameOver(mockPlayers, mockBoard, forfeit, isDouble);

        // Assert the result
        assertTrue(result, "Game should be over when forfeit is true.");
    }

    @Test
    void testMatchOver() {
        // Prepare mock players
        activePlayer player1 = new activePlayer("Player 1", Colour.RED, 0);
        activePlayer player2 = new activePlayer("Player 2", Colour.WHITE, 0);
        player1.setPlayerScore(5);
        player2.setPlayerScore(3);

        activePlayer[] mockPlayers = {player1, player2};

        // Call matchOver to verify output
        BackGammonView.matchOver(mockPlayers);
        // Observe printed output or redirect System.out for assertions
    }

    // Additional tests for other methods can follow the same structure
}
