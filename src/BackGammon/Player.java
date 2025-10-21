// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

public class Player {

    private String name;  // Player name
    private int score;    // Player score

    // Player function initiates player name and score
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // getPlayerName function outputs the player name
    public String getPlayerName() {
        return name;
    }

    // setPlayerScore function sets the player score to an inputted value
    public void setPlayerScore(int score) {
        this.score = score;
    }

    // getPlayerScore function outputs the current player score
    public Integer getPlayerScore() {
        return score;
    }
}