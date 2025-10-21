// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

public class Checker {

    private Colour chkcolour;  // Checker colour
    private Boolean onBoard;   // Is the checker on the board?
    private Boolean onBar;     // Is the checker on the bar?

    // Checker function for tracking checker colour and location
    public Checker(Colour chkcolour, Boolean onBar, Boolean onBoard) {
        this.chkcolour = chkcolour;
        this.onBoard = onBoard;
        this.onBar = onBar;
    }

    // Get checker colour
    public Colour getChkColour() {
        return chkcolour;
    }

    // Check if checker is on bar
    public Boolean getOnBar() {
        return onBar;
    }

    // Check if checker is on backgammon board
    public Boolean getOnBoard() {
        return onBoard;
    }
}
