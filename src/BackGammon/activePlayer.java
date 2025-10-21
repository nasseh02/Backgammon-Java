// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

public class activePlayer extends Player {

    private Colour pColour; // Player Colour
    public String name;     // Player Name

    // activePlayer function for tracking player name, colour and score
    public activePlayer(String name, Colour pColour, int score) {
        super(name, score);
        this.pColour = pColour;
    }

    // getpColour function to output the players colour
    public Colour getpColour() {
        return pColour;
    }
}
