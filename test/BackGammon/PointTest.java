// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    private Point myPoint;
    private Checker myRedChecker;
    private Checker myWhiteChecker;

    // Sets up the tests for the Point class
    @BeforeEach
    public void setupPoint() {
        myPoint = new Point(Colour.RED,5);
        myRedChecker = new Checker(Colour.RED, false, true);
        myWhiteChecker = new Checker(Colour.WHITE, false, true);
    }

    // Check point initialisation
    @Test
    public void testPointInitialization() {
        myPoint = new Point(Colour.WHITE, 2);
        assertEquals(2, myPoint.getCheckerCount());
        assertEquals(Colour.WHITE, myPoint.getColour());
    }

    // Check can add and remove red checkers
    @Test
    public void testAddRemoveRedChecker() {
        myPoint.addChecker(myRedChecker);
        assertEquals(6, myPoint.getCheckerCount());
        myPoint.removeChecker(myPoint.getpCheckers().removeLast());
        assertEquals(5, myPoint.getCheckerCount());
        myPoint.removeChecker(myPoint.getpCheckers().removeLast());
        assertEquals(4, myPoint.getCheckerCount());
    }

    // Check can add and remove white checkers
    @Test
    public void testAddRemoveWhiteChecker() {
        myPoint.addChecker(myWhiteChecker);
        assertEquals(6, myPoint.getCheckerCount());
        myPoint.removeChecker(myPoint.getpCheckers().removeLast());
        assertEquals(5, myPoint.getCheckerCount());
        myPoint.removeChecker(myPoint.getpCheckers().removeLast());
        assertEquals(4, myPoint.getCheckerCount());
    }

    // Give status of point
    @Test
    public void getPointStatus() {
        assertEquals(5, myPoint.getCheckerCount());
        System.out.println(myPoint.toString());
    }
}
