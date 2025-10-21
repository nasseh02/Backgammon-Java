// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class storeMovesTest {

    @Test
    public void testAddAndRetrieveMoves() {
        // Arrange: Create a Store_Moves instance
        Store_Moves store = new Store_Moves();

        // Act: Add moves to the Store_Moves instance
        store.addMove(0, 1, 5, 0); // Move 0: start at 1, dice roll 5, empty target
        store.addMove(1, 3, 2, 1); // Move 1: start at 3, dice roll 2, own piece target
        store.addMove(2, 4, 6, 2); // Move 2: start at 4, dice roll 6, hit opponent

        // Assert: Verify the stored data
        assertEquals(3, store.getCount().size(), "Incorrect move count");
        assertEquals(1, store.getStart().get(0), "Start position mismatch for move 0");
        assertEquals(5, store.getRoll().get(0), "Dice roll mismatch for move 0");
        assertEquals(0, store.getAffect().get(0), "Affect mismatch for move 0");

        assertEquals(3, store.getStart().get(1), "Start position mismatch for move 1");
        assertEquals(2, store.getRoll().get(1), "Dice roll mismatch for move 1");
        assertEquals(1, store.getAffect().get(1), "Affect mismatch for move 1");

        assertEquals(4, store.getStart().get(2), "Start position mismatch for move 2");
        assertEquals(6, store.getRoll().get(2), "Dice roll mismatch for move 2");
        assertEquals(2, store.getAffect().get(2), "Affect mismatch for move 2");
    }

    @Test
    public void testEmptyStore() {
        // Arrange: Create an empty Store_Moves instance
        Store_Moves store = new Store_Moves();

        // Assert: Verify all lists are empty
        assertTrue(store.getCount().isEmpty(), "Count list should be empty");
        assertTrue(store.getStart().isEmpty(), "Start list should be empty");
        assertTrue(store.getRoll().isEmpty(), "Roll list should be empty");
        assertTrue(store.getAffect().isEmpty(), "Affect list should be empty");
    }

    @Test
    public void testSetAndRetrieveData() {
        // Arrange: Create a Store_Moves instance with predefined data
        Store_Moves store = new Store_Moves();
        store.setCount(List.of(0, 1, 2));
        store.setStart(List.of(1, 3, 4));
        store.setRoll(List.of(5, 2, 6));
        store.setAffect(List.of(0, 1, 2));

        // Assert: Verify the set data
        assertEquals(3, store.getCount().size(), "Incorrect count size");
        assertEquals(1, store.getStart().get(0), "Start position mismatch for first move");
        assertEquals(5, store.getRoll().get(0), "Dice roll mismatch for first move");
        assertEquals(0, store.getAffect().get(0), "Affect mismatch for first move");

        assertEquals(3, store.getStart().get(1), "Start position mismatch for second move");
        assertEquals(2, store.getRoll().get(1), "Dice roll mismatch for second move");
        assertEquals(1, store.getAffect().get(1), "Affect mismatch for second move");

        assertEquals(4, store.getStart().get(2), "Start position mismatch for third move");
        assertEquals(6, store.getRoll().get(2), "Dice roll mismatch for third move");
        assertEquals(2, store.getAffect().get(2), "Affect mismatch for third move");
    }
}
