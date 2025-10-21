// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PipTest {

    @Test
    public void testGetPlayer1Score() {
        // Arrange: Set up the board with known configurations for Player 1 (Red)
        Board board = new Board();
        Point[] points = board.getbPoints();

        // Clear the board and add specific checkers for Player 1
        for (Point point : points) {
            point.getpCheckers().clear();
        }

        points[0].addChecker(new Checker(Colour.RED, false, true)); // Position 0
        points[5].addChecker(new Checker(Colour.RED, false, true)); // Position 5
        board.getBar().add(new Checker(Colour.RED, true, false));   // On the bar

        // Act: Calculate the pip score for Player 1
        int player1Score = Pip.getPlayer1Score(board);

        // Assert: Verify the calculated pip score
        int expectedScore = (25 - 0 - 1) + (25 - 5 - 1) + 24; // 24 for bar, plus board positions
        assertEquals(expectedScore, player1Score);
    }

    @Test
    public void testGetPlayer2Score() {
        // Arrange: Set up the board with known configurations for Player 2 (White)
        Board board = new Board();
        Point[] points = board.getbPoints();

        // Clear the board and add specific checkers for Player 2
        for (Point point : points) {
            point.getpCheckers().clear();
        }

        points[23].addChecker(new Checker(Colour.WHITE, false, true)); // Position 23
        points[18].addChecker(new Checker(Colour.WHITE, false, true)); // Position 18
        board.getBar().add(new Checker(Colour.WHITE, true, false));    // On the bar

        // Act: Calculate the pip score for Player 2
        int player2Score = Pip.getPlayer2Score(board);

        // Assert: Verify the calculated pip score
        int expectedScore = (23 + 1) + (18 + 1) + 24; // 24 for bar, plus board positions
        assertEquals(expectedScore, player2Score);
    }

    @Test
    public void testGetPipScoresOutput() {
        // Arrange: Create a mock board with known pip scores
        Board board = new Board();
        Point[] points = board.getbPoints();

        // Clear the board and set up checkers for both players
        for (Point point : points) {
            point.getpCheckers().clear();
        }

        points[0].addChecker(new Checker(Colour.RED, false, true)); // Player 1
        points[23].addChecker(new Checker(Colour.WHITE, false, true)); // Player 2

        // Act & Assert: Ensure the output format is correct (visual verification if necessary)
        // Note: In practice, redirecting and testing console output is needed for `System.out` calls
        Pip.getPipScores("Player1", "Player2", board);
    }
}
