// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class BackgammonTest {

    private BackGammon testGammon = new BackGammon();

    @BeforeEach
    void setupAll() {
        BackGammon testGammon = new BackGammon();
    }

    //Test Double Stake methods
    @Test
    public void testDoubleStake() {

        // Setup players
        activePlayer player1 = new activePlayer("Alice", Colour.RED, 0);
        activePlayer player2 = new activePlayer("Bob", Colour.WHITE, 0);
        activePlayer[] players = {player1, player2};

        // Simulate user input for "Accept"
        String simulatedInput = "Accept\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Capture the console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method
        boolean result = BackGammon.doubleStake(1, players, 0);

        // Assertions
        assertTrue(result, "The method should return true when the stake is accepted.");
        assertTrue(outputStream.toString().contains("Stake has been doubled!"),
                "Output should confirm that the stake has been doubled.");
    }

    @Test
    public void testDoubleStakeDecline() {
        // Setup players
        activePlayer player1 = new activePlayer("Alice", Colour.RED, 0);
        activePlayer player2 = new activePlayer("Bob", Colour.WHITE, 0);
        activePlayer[] players = {player1, player2};

        // Simulate user input for "Decline"
        String simulatedInput = "Decline\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Capture the console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method
        boolean result = BackGammon.doubleStake(1, players, 1);

        // Assertions
        assertFalse(result, "The method should return false when the stake is declined.");
        assertEquals(1, player2.getPlayerScore(), "The declining player should have their score incremented.");
        assertTrue(outputStream.toString().contains("Stake has been declined!"),
                "Output should confirm that the stake was declined.");
        assertTrue(outputStream.toString().contains("Bob wins the match!"),
                "Output should announce the declining player as the winner.");
    }

    @Test
    public void testDoubleStakeInvalidInput() {
        // Setup players
        activePlayer player1 = new activePlayer("Alice", Colour.RED, 0);
        activePlayer player2 = new activePlayer("Bob", Colour.WHITE, 0);
        activePlayer[] players = {player1, player2};

        // Simulate user input: Invalid, then "Accept"
        String simulatedInput = "InvalidInput\nAccept\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Capture the console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method
        boolean result = BackGammon.doubleStake(1, players, 0);

        // Assertions
        assertTrue(result, "The method should return true after a valid 'Accept' input.");
        assertTrue(outputStream.toString().contains("Invalid input! Please type 'Accept' or 'Decline'."),
                "Output should notify the user about invalid input.");
        assertTrue(outputStream.toString().contains("Stake has been doubled!"),
                "Output should confirm the stake has been doubled after valid input.");

    }

    // Test move checker for dice rolls 4 and 5
    @Test
    public void testMoveChecker() {

        // Create a real Board
        Board board = new Board();

        // Create active players
        activePlayer player1 = new activePlayer("Alice", Colour.RED, 0);
        activePlayer player2 = new activePlayer("Bob", Colour.WHITE, 0);
        activePlayer[] players = new activePlayer[]{player1, player2};

        // Create a DiceRoll object with fixed rolls
        DiceRoll dice = new DiceRoll();
        dice.assign_dice(4, 5); // Simulate dice rolls of 4 and 5

        // Simulate user input
        String simulatedInput = "1\n"; // User chooses move 1
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Capture the console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method under test
        BackGammon.moveCheckerTest(board, players, 0, dice);

        // Verify the dice rolls were consumed
        assertEquals(0, dice.getMoves().size(), "One move should remain after the first move is used.");

        // Validate output contains expected messages
        String output = outputStream.toString();
        assertTrue(output.contains("Alice rolls: [4, 5]"), "Output should include the player's dice rolls.");
        assertTrue(output.contains("Enter move:"), "Output should prompt the user to enter a move.");
        assertTrue(output.contains("Invalid input! Please try again.") || output.contains("Move executed"),
                "Output should either confirm the move or indicate invalid input.");

        // Ensure the board state has changed (optional depending on game logic)
        // You can assert specific board changes if you have methods to verify the board state.
    }

    //Test moves for Displaying player pip scores
    @Test
    public void testShowCurrentPlayerScoreForRedPlayer() {
        // Create a Board
        Board board = new Board();

        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method for a RED player
        BackGammon.showCurrentPlayerScore("Alice", Colour.RED, board);

        // Capture the output
        String output = outputStream.toString();

        // Validate the output
        assertTrue(output.contains("Current Player: Alice"), "Output should mention the current player.");
        assertTrue(output.contains("Pip Score: " + Pip.getPlayer1Score(board)),
                "Output should display the correct pip score for the RED player.");
    }

    @Test
    public void testShowCurrentPlayerScoreForWhitePlayer() {
        // Create a Board
        Board board = new Board();

        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method for a WHITE player
        BackGammon.showCurrentPlayerScore("Bob", Colour.WHITE, board);

        // Capture the output
        String output = outputStream.toString();

        // Validate the output
        assertTrue(output.contains("Current Player: Bob"), "Output should mention the current player.");
        assertTrue(output.contains("Pip Score: " + Pip.getPlayer2Score(board)),
                "Output should display the correct pip score for the WHITE player.");
    }

    //Test to get names of players
    @Test
    public void testGetNames() {
        // Simulate user input
        String simulatedInput = "Alice\nBob\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Call the method
        activePlayer[] players = BackGammon.getNames();

        // Assertions
        assertNotNull(players, "The players array should not be null.");
        assertEquals(2, players.length, "There should be exactly two players.");

        assertEquals("Alice", players[0].getPlayerName(), "Player 1's name should be Alice.");
        assertEquals(Colour.RED, players[0].getpColour(), "Player 1 should have the RED color.");
        assertEquals(0, players[0].getPlayerScore(), "Player 1's initial score should be 0.");

        assertEquals("Bob", players[1].getPlayerName(), "Player 2's name should be Bob.");
        assertEquals(Colour.WHITE, players[1].getpColour(), "Player 2 should have the WHITE color.");
        assertEquals(0, players[1].getPlayerScore(), "Player 2's initial score should be 0.");
    }

    //Test to move checkers within game board.
    @Test
    public void testMoveCheckerWithinBoard() {
        // Set up a board with predefined points
        Board board = new Board();

        // Set up a player
        activePlayer player = new activePlayer("Alice", Colour.RED, 0);

        // Create a DiceRoll object with a specific roll
        DiceRoll dice = new DiceRoll();
        dice.assign_dice(3, 0); // Single roll of 3 for simplicity

        // Simulate moves available for the player
        Store_Moves moves = board.Move_Options(player, dice);
        moves.addMove(0, 0, 3, 0); // Move from point 0 to point 3

        // Call the Move method
        BackGammon.Move(0, board, player, dice);

        // Validate the state of the board after the move
        Point startPoint = board.getbPoints()[0];
        Point endPoint = board.getbPoints()[3];

        assertEquals(1, startPoint.getCheckerCount(), "Start point should have one checker after the move.");
        assertEquals(1, endPoint.getCheckerCount(), "End point should have one checker after the move.");
        assertEquals(Colour.RED, endPoint.getColour(), "End point should be RED after the move.");
    }

    @Test
    public void testMoveCheckerFromBar() {
        // Set up a board with a checker on the bar
        Board board = new Board();
        Checker redChecker = new Checker(Colour.RED, true, false);
        board.getBar().add(redChecker);

        // Set up a player
        activePlayer player = new activePlayer("Alice", Colour.RED, 0);

        // Create a DiceRoll object with a specific roll
        DiceRoll dice = new DiceRoll();
        dice.assign_dice(4, 0); // Roll of 4 to move off the bar
        dice.removeMove(0);

        // Simulate moves for the player
        Store_Moves moves = board.Move_Options(player, dice);
        moves.addMove(0, -1, 4, 0); // Move from the bar to point 3 (0-based index)

        // Call the Move method
        BackGammon.Move(0, board, player, dice);

        // Validate the state of the board
        assertTrue(board.getBar().isEmpty(), "The bar should be empty after the checker moves off.");
        Point targetPoint = board.getbPoints()[3];
        assertEquals(1, targetPoint.getCheckerCount(), "The target point should have one checker after the move.");
        assertEquals(Colour.RED, targetPoint.getColour(), "The target point should be RED after the move.");
    }

    @Test
    public void testMoveCheckerOffBoard() {
        // Set up a board in the endgame state
        Board board = new Board();
        board.setbPoints0();        // Remove checkers placed on the board
        Point endPoint = board.getbPoints()[20];
        endPoint.addChecker(new Checker(Colour.RED, false, true)); // Add RED checker at position 20

        // Set up a player
        activePlayer player = new activePlayer("Alice", Colour.RED, 0);

        // Create a DiceRoll object with a specific roll
        DiceRoll dice = new DiceRoll();
        dice.assign_dice(5, 0); // Roll of 5 to bear off the checker
        dice.removeMove(0);

        // Simulate moves for the player
        Store_Moves moves = board.Move_Options(player, dice);
        moves.addMove(0, 20, 5, 4); // Move to bear off the checker

        // Call the Move method
        BackGammon.Move(0, board, player, dice);

        // Validate the state of the board
        assertEquals(0, endPoint.getCheckerCount(), "The end point should have no checkers after the move.");
        assertTrue(board.getBar().isEmpty(), "The bar should remain empty during this move.");
    }
}
