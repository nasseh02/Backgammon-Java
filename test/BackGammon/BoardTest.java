// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    // Initialising board
    private Board board = new Board();

    // Set up tests
    @BeforeEach
    public void setupAll() {}

    // Test BoardInitialization to make sure it is the correct size
    @Test
    public void testBoardInitialization() {
        board = new Board();
        assertNotNull(board.getbPoints());
        assertEquals(24, board.getbPoints().length);
    }

    // Test HasPlayerWon if their pieces are no longer on the board or bar
    @Test
    public void testHasPlayerWon() { // Players haven't won yet
        assertFalse(board.hasPlayerWon(Colour.RED));
        assertFalse(board.hasPlayerWon(Colour.WHITE));
    }

    // Test MoveOffBoard to see if the pieces leave the board
    @Test
    public void testMoveOffBoard() {
        Checker checker = new Checker(Colour.RED, false, true);
        board.moveOffBoard(checker);
        // Check that checker is now off the board
    }

    // Test CheckLanding to see the status of a location a checker could move
    @Test
    public void testCheckLanding() {
        Point point = new Point(Colour.RED, 1);
        int result = board.checkLanding(point, Colour.RED);
        assertEquals(1, result);
    }

    // Test CheckLanding to see the status of a location a checker could move
    @Test
    public void testLandCheck() {
        int landChk;
        landChk = board.checkLanding(board.getbPoints()[5], Colour.WHITE); //Check if white piece can occupy point 6 with red pieces
        assertEquals(1, landChk);
        landChk = board.checkLanding(board.getbPoints()[5], Colour.RED); //Check if red piece can occupy point 6 with many white pieces
        assertEquals(3, landChk);
        landChk = board.checkLanding(board.getbPoints()[4], Colour.RED); //Check if red piece can occupy empty point
        assertEquals(0, landChk);

    }
}
