// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import java.util.Collections;
import java.util.List;

public class BackGammonView {

    // GameDisplay function displays a big backgammon name intro display
    public static void GameDisplay() {
        System.out.println("===================================================================================================================================================================");
        System.out.println(" ________  ________  ________  ___  __    ________  ________  _____ ______   _____ ______   ________  ________      ");
        System.out.println("|\\   __  \\|\\   __  \\|\\   ____\\|\\  \\|\\  \\ |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\   _ \\  _   \\|\\   __  \\|\\   ___  \\    ");
        System.out.println("\\ \\  \\|\\ /\\ \\  \\|\\  \\ \\  \\___|\\ \\  \\/  /|\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\  \\\\\\__\\ \\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\   ");
        System.out.println(" \\ \\   __  \\ \\   __  \\ \\  \\    \\ \\   ___  \\ \\  \\  __\\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\\\|__| \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\  ");
        System.out.println("  \\ \\  \\|\\  \\ \\  \\ \\  \\ \\  \\____\\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\    \\ \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ ");
        System.out.println("   \\ \\_______\\ \\__\\ \\__\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\__\\    \\ \\__\\ \\_______\\ \\__\\\\ \\__\\");
        System.out.println("    \\|_______|\\|__|\\|__|\\|_______|\\|__| \\|__|\\|_______|\\|__|\\|__|\\|__|     \\|__|\\|__|     \\|__|\\|_______|\\|__| \\|__|");
        System.out.println("\n=====================================================================================================================================================================");
    }

    // matchStart function displays welcome and requests player names
    public static void matchStart() {
        System.out.println("Welcome to Backgammon!");
        System.out.println("Enter the names of the two players: ");
    }

    // displayBoard function displays the board with checker and updates every move
    public static void displayBoard(Board board, activePlayer[] players) {
        List<Integer> bPointsSize = board.calculateBPointsSize();   // Dynamically calculate checker stack sizes
        int maxCheckers = Collections.max(bPointsSize);             // Recalculate maxCheckers

        List<Checker> bar = board.getBar();      // Bar
        Point[] bPoints = board.getbPoints();    // Points

        // Print the top row
        System.out.println("=====================================================");
        System.out.println("Player 1: " + players[0].getPlayerName() + " (Red's Home). Pieces off the Board: " + board.bOffRed.size());
        System.out.println("_____________________________________________________");
        System.out.println("| 13  14  15  16  17 18 | B | 19  20  21  22  23  24| OFF");

        for (int i = 0; i < maxCheckers; i++) {
            for (int j = 12; j < bPoints.length; j++) {
                printRows(i, j, bPoints, bar, (bPoints.length / 2) + 6, Colour.WHITE);
            }
            System.out.println("|");
        }

        // Print the bottom row
        System.out.println("|-----------------------| B |-----------------------|");
        for (int i = 0; i < maxCheckers; i++) {
            for (int j = bPoints.length / 2 - 1; j >= 0; j--) {
                printRows(i, j, bPoints, bar, (bPoints.length / 2) - 7, Colour.RED);
            }
            System.out.println("|");
        }
        System.out.println("| 12  11  10  9   8   7 | B | 6   5   4   3   2   1 | OFF");
        System.out.println("_____________________________________________________");
        System.out.println("Player 2: " + players[1].getPlayerName() + " (White's Home). Pieces off the Board: " + board.bOffWhite.size());
        System.out.println("=====================================================");
    }

    // printRows function prints board row by row of each checker column
    public static void printRows(int i, int j, Point[] bPoints, List<Checker> bar, int colMid, Colour zoneColour) {
        if (j == colMid){
            if (bar.size() > i){               // Print checkers on the bar
                if (bar.get(i).getChkColour() == Colour.RED && zoneColour != bar.get(i).getChkColour()){
                    System.out.print("| R ");  // Print red checker
                } else if ((bar.get(i).getChkColour() == Colour.WHITE && zoneColour != bar.get(i).getChkColour())){
                    System.out.print("| W ");  // Print white checker
                } else {
                    System.out.print("|   ");  // Print blank
                }
            } else {
                System.out.print("|   ");      // Print blank
            }
        }
        if (bPoints[j].getCheckerCount() > i) {   // Print column
            System.out.print("| " + bPoints[j].getpCheckers().getLast().getChkColour().toString().charAt(0) + " ");
        } else {
            System.out.print("|   ");          // Print blank
        }
    }

    // showHint function displays all input options
    public static void showHint() {
        //System.out.println("=====================================================");
        System.out.println("Available Commands:");
        System.out.println("1. quit - Quit the current game.");
        System.out.println("2. pip - Display the current pip score for each player.");
        System.out.println("3. roll - Roll the dice to begin your turn.");
        System.out.println("4. custom roll - Roll a custom dice" );
        System.out.println("5. double - Double the current bet.");
        System.out.println("6. test - Use a test file to simulate inputs.");
        System.out.println("7. quit match - Quit the entire match.");
        System.out.println("=====================================================");
    }

    // displayMove function displays the available move options on a given turn
    public static void displayMoves(activePlayer player, Store_Moves moveoptions) {
        System.out.println(player.getPlayerName() + " has the following move options:");
        int size = moveoptions.getCount().size();

        // Loop to display each move option at a time
        for (int i = 0; i < size; i++) {
            // Assign each value to an individual integer
            int count = moveoptions.getCount().get(i);
            int start = moveoptions.getStart().get(i) + 1;
            int roll = moveoptions.getRoll().get(i);
            int affect = moveoptions.getAffect().get(i);
            int end = (player.getpColour() == Colour.RED) ? roll+start : start-roll;

            if (start == 0 || start == 25){ // If the checker is starting from the bar
                System.out.println(count + ") Roll: " +roll+ " will place you back on the board in box: " +(end));
            } else if (affect == 0) {       // If the checker is landing on an empty square
                System.out.println(count + ") Roll: " +roll+ " which goes from " +(start)+ " to " +(end)+ ", you will be in danger leaving a single checker.");
            } else if (affect == 1) {       // If the checker is landing on a square with pieces of the same colour
                System.out.println(count + ") Roll: " +roll+ " which goes from " +(start)+ " to " +(end)+ ", you will be safe with other checker.");
            } else if (affect == 2) {       // If the checker is landing on an opponents checker and knocking it off
                System.out.println(count + ") Roll: " +roll+ " which goes from " +(start)+ " to " +(end)+ ", you can knock your opponents checker!");
            } else if (affect == 4) {       // If the checker is moving off of the board
                System.out.println(count + ") Roll: " +roll+ " which goes from " +(start)+ " to off the board!");
            }
        }
        System.out.println("=====================================================");
    }

    // displayAfterMove function displays the move chosen
    public static void displayAfterMove(activePlayer player, Store_Moves moveoptions, int move) {
        System.out.println("=====================================================");

        // Assign each value to an individual integer
        int start = moveoptions.getStart().get(move) + 1;
        int roll = moveoptions.getRoll().get(move);
        int affect = moveoptions.getAffect().get(move);
        int end = (player.getpColour() == Colour.RED) ? roll+start : start-roll;


        if (start == 0 || start == 25){ // If the checker is starting from the bar
            System.out.println("You choose to move back onto the board to " +roll);
        } else if (affect == 0) {       // If the checker is landing on an empty square
            System.out.println("You choose to move " +roll+ " from " +(start)+ " to " +(end)+ ", you are now be danger as you leave a checker on its own.");
        } else if (affect == 1) {       // If the checker is landing on a square with pieces of the same colour
            System.out.println("You choose to move " +roll+ " from " +(start)+ " to " +(end)+ ", you are safe with other checker.");
        } else if (affect == 2) {       // If the checker is landing on an opponents checker and knocking it off
            System.out.println("You choose to move " +roll+ " from " +(start)+ " to " +(end)+ ", you knocked your opponents checker!");
        } else if (affect == 4) {       // If the checker is moving off of the board
            System.out.println("You choose to move " +roll+ " from " +(start)+ " to take it off the board!");
        }
        System.out.println("=====================================================");
    }

    // matchDetails function displays the match score, game by game
    public static void matchDetails(String[] playerNames, int matchNumber, activePlayer[] players) {
        System.out.printf("=======================GAME %d========================\n", matchNumber);
        System.out.printf(playerNames[0] + " score: %d\n", players[0].getPlayerScore());
        System.out.printf(playerNames[1] + " score: %d\n", players[1].getPlayerScore());
        System.out.println("=====================================================");
    }

    // updateMatchScore function updates the match score and displays it
    private static void updateMatchScore(int winnerIndex, int loserIndex, Board board, activePlayer[] pNames, int isDouble) {
        // Check if the loser has borne off any checkers
        boolean hasLoserBorneOff = board.hasPlayerWon(pNames[loserIndex].getpColour());

        // Check if the loser has checkers on the bar
        boolean hasLoserOnBar = board.hasCheckersOnBar(pNames[loserIndex].getpColour());

        // Check if the loser has checkers in the winner's home board
        boolean hasLoserInWinnerHomeBoard = board.hasCheckersInHomeBoard(pNames[loserIndex].getpColour());

        // Determine the outcome
        String outcome;
        int points;

        // Allocating correct number of points
        if (!hasLoserBorneOff && (hasLoserOnBar || hasLoserInWinnerHomeBoard)) {
            outcome = "Backgammon";
            points = 3;
        } else if (!hasLoserBorneOff) {
            outcome = "Gammon";
            points = 2;
        } else {
            outcome = "Single";
            points = 1;
        }

        // Update the winner's score with the multiplier for doubling
        pNames[winnerIndex].setPlayerScore(pNames[winnerIndex].getPlayerScore() + (points * isDouble));

        // Announce the result
        System.out.println("The game ends in a " + outcome + "!");
        System.out.println(pNames[winnerIndex].getPlayerName() + " scores " + (points * isDouble) + " points.");
        System.out.println("Updated Scores: " + pNames[0].getPlayerName() + " = " + pNames[0].getPlayerScore() +
                ", " + pNames[1].getPlayerName() + " = " + pNames[1].getPlayerScore());
    }

    // checkGameOver function uses pip score to display game over
    public static boolean checkGameOver(activePlayer[] pNames, Board board, Boolean forfeit, int isDouble) {
        if (forfeit) {
            // Calculate pip scores and declare the player with the lower pip score as the winner
            int player1PipScore = Pip.getPlayer1Score(board);
            int player2PipScore = Pip.getPlayer2Score(board);

            // If player wins by forfeit
            if (player1PipScore < player2PipScore) {
                System.out.println("Game ended: " + pNames[0].getPlayerName() + " wins with a lower pip score of " + player1PipScore);
                pNames[0].setPlayerScore(isDouble * (pNames[0].getPlayerScore() + 1));
            } else if (player2PipScore < player1PipScore) {
                System.out.println("Game ended: " + pNames[1].getPlayerName() + " wins with a lower pip score of " + player2PipScore);
                pNames[1].setPlayerScore(isDouble * (pNames[1].getPlayerScore() + 1));
            } else {
                System.out.println("Game ended. It's a tie with both players having the same pip score.");
                pNames[0].setPlayerScore(pNames[0].getPlayerScore() + 1);
                pNames[1].setPlayerScore(pNames[1].getPlayerScore() + 1);
            }
            return true;

        // If player wins by clearing the board
        } else if (board.hasPlayerWon(Colour.RED)) {
            System.out.println("Congratulations " + pNames[0].getPlayerName() + "! You have won the game.");
            pNames[0].setPlayerScore(isDouble * (pNames[0].getPlayerScore() + 1));
            updateMatchScore(0, 1, board, pNames, isDouble);
            return true;
        } else if (board.hasPlayerWon(Colour.WHITE)) {
            System.out.println("Congratulations " + pNames[1].getPlayerName() + "! You have won the game.");
            pNames[1].setPlayerScore(isDouble * (pNames[1].getPlayerScore() + 1));
            updateMatchScore(1, 0, board, pNames, isDouble);
            return true;
        }
        return false;
    }

    // matchOver function checks if the match is over and displays the result
    public static void matchOver(activePlayer[] players) {
        System.out.println("Match Over!");
        System.out.printf(players[0].getPlayerName() + " score: %d\n", players[0].getPlayerScore());
        System.out.printf(players[1].getPlayerName() + " score: %d\n", players[1].getPlayerScore());
        if (players[0].getPlayerScore() > players[1].getPlayerScore()) {
            System.out.println(players[0].getPlayerName() + " wins the match");
        } else if (players[1].getPlayerScore() > players[0].getPlayerScore()) {
            System.out.println(players[1].getPlayerName() + " wins the match");
        } else {
            System.out.println("The match is a tie");
        }
        System.out.println("=====================================================");
    }
}

