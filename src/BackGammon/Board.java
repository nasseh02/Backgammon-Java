// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public ArrayList<Checker> bOffRed = new ArrayList<>(); // Red checkers off the board
    public ArrayList<Checker> bOffWhite = new ArrayList<>(); // White checkers off the board
    private Point[] bPoints = new Point[24]; // Declare Backgammon Points
    private Point[] bPointsRed = new Point[24]; // Declare Backgammon Points for Red
    private Point[] bPointsWhite = new Point[24]; // Declare Backgammon Points for White
    private List<Checker> bar = new ArrayList<>();
    private List<Integer> bPointsSize = new ArrayList<>(); // Declare Backgammon Points
    public Integer[] pointLocationsRed = {0, 11, 16, 18}; // Declare Backgammon Point Locations
    public Integer[] pointLocationsWhite = {5, 7, 12, 23}; // Declare Backgammon Point Locations
    private Integer[] checkers = {2, 5, 3, 5}; // Declare Backgammon Checker Counts

    // Board function instantiates the backgammon board
    public Board() {
        for (int i = 0; i < pointLocationsRed.length; i++) {
            // Putting checkers and their quantity in the correct locations
            bPointsRed[pointLocationsRed[i]] = new Point(Colour.RED, checkers[i]);
            bPointsWhite[pointLocationsWhite[i]] = new Point(Colour.WHITE, checkers[3 - i]);
        }

        // Assigning the coloured checkers to the coloured bPoint Point[]'s
        for (int i = 0; i < bPoints.length; i++) {
            if (bPointsRed[i] != null) {
                bPoints[i] = bPointsRed[i];
            } else if (bPointsWhite[i] != null) {
                bPoints[i] = bPointsWhite[i];
            } else {
                bPoints[i] = new Point(Colour.NONE, 0); // Empty point
            }
        }
        for (Point point : bPoints) {
            bPointsSize.add(point.getCheckerCount());
        }
    }

    // getbPoints function gets the bPoints
    public Point[] getbPoints() {
        return bPoints;
    }

    // calculatedBPointsSize function calculates the bPoint size each turn to decide the board column heights
    public List<Integer> calculateBPointsSize() {
        List<Integer> sizes = new ArrayList<>();
        for (Point point : bPoints) {
            sizes.add(point.getCheckerCount());
        }
        return sizes;
    }

    // getBar function gets the checkers on the bar
    public List<Checker> getBar() {
        return bar;
    }

    // hasPlayerWon function checkers if all the players pieces are off the board
    public boolean hasPlayerWon(Colour colour) {
        for (Point point : bPoints) {   // Are pieces on the board?
            if (point.getColour() == colour && point.getCheckerCount() > 0) {
                return false;
            }
        }
        for (Checker checker : bar) {   // Are pieces on the bar?
            if (checker.getChkColour() == colour) {
                return false;
            }
        }
        return true; // Player has won if all checkers are off the board and bar
    }

    // hasCheckersOnBar functions checkers if the current player has a checker on the bar
    public boolean hasCheckersOnBar(Colour colour) {
        for (Checker checker : bar) {
            if (checker.getChkColour() == colour) {
                return true;
            }
        }
        return false; // Player has checkers on the bar
    }

    // hasCheckersInHomeBoard function checks if all the players checkers are in the final 6 squares
    public boolean hasCheckersInHomeBoard(Colour colour) {
        int start = (colour == Colour.RED) ? 0 : 18;
        int end = (colour == Colour.RED) ? 5 : 23;
        for (int i = start; i <= end; i++) {
            if (bPoints[i].getColour() == colour) {
                return true;
            }
        }
        return false; // Player has checkers in the home board
    }

    // moveOffBoard function moves the players checker off the board
    public void moveOffBoard(Checker c) {
        if (c.getChkColour() == Colour.RED) {
            bOffRed.add(c);
        }else if (c.getChkColour() == Colour.WHITE) {
            bOffWhite.add(c);
        }
    }

    // board_state function checks whether the current player a piece on the bar, end zone or board
    public int board_state(activePlayer player) {
        Colour playerColour = player.getpColour();

        // Check if the player's piece was knocked off
        for (Checker checker : bar) {
            if (checker.getChkColour() == playerColour) {
                return 0; // A piece is on the bar
            }
        }

        // Check if all pieces are in the end game zone (final 6 spaces)
        int startZone = (playerColour == Colour.RED) ? 18 : 0;
        int endZone = (playerColour == Colour.RED) ? 23 : 5;
        int Total_Checkers = 0;
        int EndZone_Checkers = 0;

        for (int i = 0; i < bPoints.length; i++) {
            Point point = bPoints[i];
            if (point.getColour() == playerColour) {
                Total_Checkers += point.getCheckerCount(); // Count total player's checkers
                if (i >= startZone && i <= endZone) {
                    EndZone_Checkers += point.getCheckerCount(); // Count checkers in end zone
                }
            }
        }

        if (EndZone_Checkers == Total_Checkers && Total_Checkers > 0) {
            return 1; // All checkers are in the end zone
        } else if (Total_Checkers == 0) {
            return 3; // Board is empty of a coloured piece
        } else {
            return 2; // Board is in normal state
        }
    }

    // Move_Options function is used to work out all available checker moves
    public Store_Moves Move_Options(activePlayer player, DiceRoll dice) {

        Colour colour = player.getpColour();
        int state = board_state(player);        // Checking board state (where players checkers are located)
        Store_Moves store = new Store_Moves();  // Initialising the store moves array
        List<Integer> dice_roll = dice.getMoves();

        // Assigning the dice differently in case of a double roll
        if (dice_roll.size() > 1 && List.of(dice_roll.get(0)).equals(List.of(dice_roll.get(1)))){
            dice_roll = List.of(dice_roll.getFirst());
        }

        boolean myChecker;
        int start_position;
        int end_position;
        int count = 0;

        if (state == 0) {       // Player checker is on the bar

            if (colour == Colour.RED){
                start_position =  -1;
                for (int i = 0; i < dice_roll.size(); i++) {

                    int diceMove = dice.getMoves().get(i);      // Dice roll
                    int idx = diceMove + start_position;        // Land location
                    int check = checkLanding(bPoints[idx], colour);     // Check if player can land

                    if (check != 3) {       // If player can land the option is stored
                        store.addMove(count, start_position, diceMove, check);
                        count++;
                    }
                }
            }
            if (colour == Colour.WHITE) {
                start_position = 24;
                for (int i = 0; i < dice_roll.size(); i++) {

                    int diceMove = dice.getMoves().get(i);      // Dice roll
                    int idx = start_position - diceMove;        // Land location
                    int check = checkLanding(bPoints[idx], colour);     // Check if player can land

                    if (check != 3) {       // If player can land the option is stored
                        store.addMove(count, start_position, diceMove, check);
                        count++;
                    }
                }
            }
        }

        if (state == 1) {       // Player has all pieces in the end zone

            boolean diceroll1used = false;      // If a checker over shoots the end of the board and
            boolean diceroll2used = false;      // can move within the board it is not an option

            if (colour == Colour.RED){
                start_position =  18;
                end_position = 23;
                for (int j = start_position; j <= end_position; j++) {  // Possible start locations

                    myChecker = Start_Point(bPoints[j], colour);
                    if(myChecker == true){
                        for (int i = 0; i < dice_roll.size(); i++) {    // Possible land locations

                            int diceMove = dice.getMoves().get(i);      // Dice roll
                            int idx = diceMove + j;                     // Land location
                            int check;
                            if (idx <= 23 && idx >= 0){  // checkLanding breaks if it is outside the board
                                check = checkLanding(bPoints[idx], colour);
                            } else {
                                check = 4;
                            }

                            // If checker can move on the board
                            if (check != 3 && check != 4) {
                                store.addMove(count, j, diceMove, check);
                                if (i == 0){        // Dice can't over shoot
                                    diceroll1used = true;
                                } else if (i == 1){
                                    diceroll2used = true;
                                }
                                count++;
                            }
                            if (idx == 24){  // If checker lands perfectly off the board
                                store.addMove(count, j, diceMove, check);
                                count++;
                            } else if (check == 4 && !diceroll1used && i == 0){  // If dice 1 overshoots
                                store.addMove(count, j, diceMove, check);
                                count++;
                            } else if (check == 4 && !diceroll2used && i == 1){  // If dice 2 overshoots
                                store.addMove(count, j, diceMove, check);
                                count++;
                            }
                        }
                    }
                }
            }
            if (colour == Colour.WHITE) {
                start_position =  5;
                end_position = 0;
                for (int j = start_position; end_position <= j; j--) {  // Possible start locations

                    myChecker = Start_Point(bPoints[j], colour);
                    if(myChecker == true) {
                        for (int i = 0; i < dice_roll.size(); i++) {    // Possible land locations

                            int diceMove = dice.getMoves().get(i);      // Dice roll
                            int idx = j - diceMove;                     // Land location
                            int check;
                            if (idx <= 23 && idx >= 0){  // checkLanding breaks if it is outside the board
                                check = checkLanding(bPoints[idx], colour);
                            } else {
                                check = 4;
                            }

                            // If checker can move on the board
                            if (check != 3 && check != 4) {
                                store.addMove(count, j, diceMove, check);
                                if (i == 0){        // Dice can't over shoot
                                    diceroll1used = true;
                                } else if (i == 1){
                                    diceroll2used = true;
                                }
                                count++;
                            }
                            if (idx == -1){  // If checker lands perfectly off the board
                                store.addMove(count, j, diceMove, check);
                                count++;
                            } else if (check == 4 && !diceroll1used && i == 0){  // If dice 1 overshoots
                                store.addMove(count, j, diceMove, check);
                                count++;
                            } else if (check == 4 && !diceroll2used && i == 1){  // If dice 2 overshoots
                                store.addMove(count, j, diceMove, check);
                                count++;
                            }
                        }
                    }
                }
            }
        }

        if (state == 2) {       // All player checkers in play are on the board

            if (colour == Colour.RED){
                start_position =  0;
                end_position = 23;
                for (int j = start_position; j <= end_position; j++) {  // Possible start locations
                    myChecker = Start_Point(bPoints[j], colour);
                    if(myChecker) {
                        for (int i = 0; i < dice_roll.size(); i++) {
                            int diceMove = dice.getMoves().get(i);      // Dice roll
                            int idx = j + diceMove;                     // Land location
                            if (idx >= 0 && idx < 24){
                                int check = checkLanding(bPoints[idx], colour);     // Check if player can land
                                if (check != 3 && check != 4) {
                                    store.addMove(count, j, diceMove, check);   // Add move option
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
            if (colour == Colour.WHITE) {
                start_position =  23;
                end_position = 0;
                for (int j = start_position; j >= end_position; j--) {  // Possible start locations
                    myChecker = Start_Point(bPoints[j], colour);
                    if(myChecker) {
                        for (int i = 0; i < dice_roll.size(); i++) {
                            int diceMove = dice.getMoves().get(i);      // Dice roll
                            int idx = j - diceMove;                     // Land location
                            if (idx >= 0 && idx < 24){
                                int check = checkLanding(bPoints[idx], colour);     // Check if player can land
                                if (check != 3 && check != 4) {
                                    store.addMove(count, j, diceMove, check);   // Add move option
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (state == 3){   // Affect is set to 10 when all players pieces are cleared from the board
            store.addMove(0, 0, 0, 10); // Game over
        }
        return store;
    }

    // checkLanding function checks the state of a location that a checker could move to
    public int checkLanding(Point targetPoint, Colour playerColour) {
        int opponentCheckers = targetPoint.getCheckerCount();
        Colour pointColour = targetPoint.getColour();

        if (opponentCheckers == 0) {
            // Point is empty
            return 0;
        } else if (pointColour == playerColour || pointColour == Colour.NONE) {
            // Point is occupied by the player's own checkers
            return 1;
        } else if (opponentCheckers == 1) {
            // Point has exactly one opponent checker
            return 2;
        } else if (opponentCheckers > 1){
            // Point is occupied by multiple opponent checkers
            return 3;
        } else {
            // Point is off the board
            return 4;
        }
    }

    // Start_Point function checks if the current player has checker in the specific point
    public boolean Start_Point(Point point, Colour playerColour) {
        int myCheckers = point.getCheckerCount();
        Colour pointColour = point.getColour();
        
        if (myCheckers >= 1 && pointColour == playerColour) {
            // My checker
            return true;
        } else {
            return false;
        }
    }

    // setbPoints0 function is used to reset all bPoints to 0 so there are no checkers on the board for testing
    public void setbPoints0() {
        for (int i = 0; i < bPoints.length; i++) {
            bPoints[i] = new Point(Colour.RED, 0); // Set each Point to (0, 0)
        }
    }
}
