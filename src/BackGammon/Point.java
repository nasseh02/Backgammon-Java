// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import java.util.ArrayList;

public class Point {

    private ArrayList<Checker> pCheckers = new ArrayList<>();   // Declare checker pieces
    private Colour colour;                                      // Colour of the checkers on this point

    // Point function instantiates a backgammon point
    public Point(Colour colour, int chkCount){
        this.colour = colour; // Set the initial colour of the point

            // Initiate checker piece
            for (int i = 0; i < chkCount; i++) {
                Checker aChecker;
                aChecker = new Checker(colour, false, true);
                pCheckers.add(aChecker);
        }
    }

    // setColour function sets the colour of the checker
    public void setColour(Colour colour) {
        this.colour = colour;
    }

    // getColour function gets the colour of the checker
    public Colour getColour() {
        return colour;
    }

    // getCheckerCount gets the number of checkers on a point
    public int getCheckerCount() {
        return pCheckers.size();
    }

    // getpChecker function gets list of checker pieces on a point
    public ArrayList<Checker> getpCheckers() {
        return pCheckers;
    }

    // addChecker function adds a checker to the point
    public void addChecker(Checker c) {
        if (pCheckers.isEmpty()) {
            setColour(c.getChkColour());
        }
        pCheckers.add(c);
    }

    // removeChecker function removes a checker from the point
    public void removeChecker(Checker c) {
        pCheckers.remove(c);
        if (pCheckers.isEmpty()) {
            setColour(Colour.NONE);
        }
    }

    @Override   // toString function outputs a string of the checker values for the test display
    public String toString() {
        return String.format("This Point has %s" + " %s checkers", getCheckerCount(), !pCheckers.isEmpty() ? pCheckers.getLast().getChkColour() : " ");
    }
}