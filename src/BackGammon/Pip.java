// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

public class Pip {

    // getPlayer1Score function gets the pip score for player 1
    public static Integer getPlayer1Score(Board board) {
        int player1Score = 0;
        for (int i = 0; i < board.getbPoints().length; i++) {
            Point aPoint = board.getbPoints()[i];
            if (aPoint.getColour() == Colour.RED) {
                for (Checker checker : aPoint.getpCheckers()) {
                    // Check if the checker is on the bar
                    if (checker.getOnBar()) {
                        player1Score += 24; // Add 24 pips for each checker on the bar
                    } else if (checker.getOnBoard()) {
                        player1Score += (25 - i - 1);
                    }
                }
            }
        }
        // Add pip score for checkers on the bar
        for (Checker checker : board.getBar()) {
            if (checker.getChkColour() == Colour.RED) {
                player1Score += 24;
            }
        }
        return player1Score;
    }

    // getPlayer2Score function gets the pip score for player 2
    public static Integer getPlayer2Score(Board board) {
        int player2Score = 0;
        for (int i = 0; i < board.getbPoints().length; i++) {
            Point aPoint = board.getbPoints()[i];
            if (aPoint.getColour() == Colour.WHITE) {
                for (Checker checker : aPoint.getpCheckers()) {
                    // Check if the checker is on the bar
                    if (checker.getOnBar()) {
                        player2Score += 24; // Add 24 pips for each checker on the bar
                    } else if (checker.getOnBoard()) {
                        player2Score += (i + 1);
                    }
                }
            }
        }
        // Add pip score for checkers on the bar
        for (Checker checker : board.getBar()) {
            if (checker.getChkColour() == Colour.WHITE) {
                player2Score += 24;
            }
        }
        return player2Score;
    }

    // getPipScores function outputs the players pip scores
    public static void getPipScores(String player1, String player2, Board board) {
        System.out.printf("Pip Scores \n%s: %d \n%s: %d \n=====================================================\n", player1, getPlayer1Score(board), player2, getPlayer2Score(board));
    }
}