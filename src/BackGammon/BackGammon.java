// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import java.io.IOException;
import java.util.*;

public class BackGammon {

    public static void main(String[] args) {

        BackGammonView.GameDisplay();   // Display backgammon title
        BackGammonView.matchStart();    // Prompt for player names

        activePlayer[] players = BackGammon.getNames(); // Get Player names

        String[] pNames = new String[2];
        pNames[0] = players[0].getPlayerName();     // Assign player 1
        pNames[1] = players[1].getPlayerName();     // Assign player 2

        DiceRoll dice = new DiceRoll();
        dice.rollDice();                    // Retrieve and roll dice

        int matchLength = getMatchLength(); // Get input match length

        int turn = 0;
        turn = assignPlayers(pNames[0], pNames[1], dice);   // Decide who goes first

        if (turn == 0){     // Displaying who goes first with their associated dice roll
            System.out.println(pNames[0] + " will start with the following dice: " + dice.getMoves());
        } else if (turn == 1){
            System.out.println(pNames[1]+ " will start with the following dice: " + dice.getMoves());
        }

        Board myBoard = new Board();    // Initiating the board
        System.out.println("=====================================================");
        BackGammonView.showHint();      // Displaying input options
        System.out.println("=======================GAME 1========================");   // Displaying game 1 header
        BackGammonView.displayBoard(myBoard, players);      // Display board
        moveChecker(myBoard, players, turn, dice);          // Move Checkers

        if(turn == 0) turn++;
        else turn--;                // Change player turn

        int doubleStake = 1;        // Initialising doubles stakes
        boolean endMatch = false;   // Initialising endMatch in case of quit match input

        for (int i = 0; i < matchLength; i++) {     // For loop to contain the match

            // Initialising a new board. This isn't used in the first game as the initial role decides it
            if (i > 0){
                // Display match results after a game
                BackGammonView.matchDetails(pNames, i + 1, players);
                BackGammonView.showHint();          // Display input options
                myBoard = new Board();              // Initiating a new board
                BackGammonView.displayBoard(myBoard, players);      // Display board
            }

            while (true) {      // While loop containing each individual game
                // Checking has player forfeited
                boolean Game_over = BackGammonView.checkGameOver(players, myBoard, false, doubleStake);

                if (Game_over){
                    Game_over = false;      // Ending game if player has forfeit
                    break;
                }

                if (turn == 0){     // Display player turn
                    System.out.println("Its now " +pNames[0]+ "'s turn");
                } else if (turn == 1){
                    System.out.println("Its now " +pNames[1]+ "'s turn");
                }

                System.out.print("Enter command: ");        // Requesting input command
                Scanner sc = new Scanner(System.in);        // Initiating scanner
                String input = sc.nextLine();               // Scanning following line
                System.out.println("=====================================================");

                // If function for possible test file input option
                if(input.equalsIgnoreCase("test")){
                    BGtest(input, dice, myBoard, players, turn, pNames, doubleStake);
                } else {
                    // If player input is roll to roll a new dice
                    if (input.equalsIgnoreCase("roll")) {
                        dice.rollDice();                            // Roll dice
                        moveChecker(myBoard, players, turn, dice);  // Move checker
                        if(turn == 0) turn++;
                        else turn--;                // End of go, change turns

                    // If input is hint it displays input options
                    } else if (input.equalsIgnoreCase("hint")) {
                        BackGammonView.showHint();

                    // If input is pip it displays the pip score of both players
                    } else if (input.equalsIgnoreCase("pip")) {
                        Pip.getPipScores(pNames[0], pNames[1], myBoard);

                    // If input is double it displays an option to play the next game for double the stakes
                    } else if (input.equalsIgnoreCase("double")) {
                        boolean value = doubleStake(doubleStake, players, turn);
                        doubleStake = value ? doubleStake * 2 : doubleStake;
                        if (!value) {
                            break;
                        }

                    // If input is quit it ends the current game
                    } else if (input.equalsIgnoreCase("quit")) {
                        BackGammonView.checkGameOver(players, myBoard, true, doubleStake);
                        break;

                    // If input is quit match it ends the entire match
                    } else if (input.equalsIgnoreCase("quit match")) {
                        endMatch = true;
                        break;

                    // If input is custom roll it allows the player to input custom dice rolls
                    } else if (input.toLowerCase().startsWith("custom roll ")) {
                        String params = input.substring(12).trim();
                        String[] rolls = params.split(",");
                        if (rolls.length == 2) {
                            int roll1 = Integer.parseInt(rolls[0].trim());
                            int roll2 = Integer.parseInt(rolls[1].trim());
                            if (roll1 > 0 && roll2 > 0 && roll1 <= 6 && roll2 <= 6) {
                                dice.assign_dice(roll1, roll2);
                                System.out.println("Dice rolls set to: " + roll1 + ", " + roll2);

                                moveChecker(myBoard, players, turn, dice);  // Move checker
                                if(turn == 0) turn++;
                                else turn--;                // End of go, change turns
                            } else {
                                System.out.println("Invalid numbers! Use the format: custom roll X,Y where X and Y are integers between 1 and 6.");
                            }
                        } else {
                            System.out.println("Invalid format! Use the format: custom roll X,Y.");
                        }
                    // If input was incorrect the following error message shows
                    } else {
                        System.out.println("Unknown command! Type 'hint' for available commands.");
                        System.out.println("=====================================================");
                    }
                }
            }
            if (endMatch){
                break;          // Ends Match if a player forfeits the match
            }
        }
        if (!endMatch){         // Display final game result if not for a match forfeit
            BackGammonView.matchDetails(pNames, matchLength, players);
        }
        BackGammonView.matchOver(players);      // Display final match results
    }

    // getNames function is used to get player names
    public static activePlayer[] getNames() {
        //Hold player names and assign and instantiate objects
        String[] names = new String[2];
        activePlayer[] players = new activePlayer[2];

        Scanner namePrompter = new Scanner(System.in);

        // Prompt and assigning player 1
        System.out.print("Player 1: ");
        names[0] = namePrompter.nextLine();

        // Prompt and assigning player 2
        System.out.print("Player 2: ");
        names[1] = namePrompter.nextLine();

        // Assigning players to activePlayer
        players[0] = new activePlayer(names[0], Colour.RED, 0);
        players[1] = new activePlayer(names[1], Colour.WHITE, 0);

        return players;
    }

    // assignPlayer function is used get each user to roll a die and assign who goes first
    public static int assignPlayers(String Player1, String Player2, DiceRoll dice) {

        int turn;       // Initialising turn
        System.out.println("=====================================================");
        System.out.println("Both players will roll a die to determine the starting order.");;

        while(true) {       // Loop used to go again if both players roll the same dice
            int firstRoll = dice.getRoll1();        // Rolling first dice and assigning it to player 1
            System.out.println(Player1 + " rolled a " + firstRoll + ".");
            int secondRoll = dice.getRoll2();       // Rolling second dice and assigning it to player 2
            System.out.println(Player2 + " rolled a " + secondRoll + ".");

            if (firstRoll > secondRoll) {           // If dice 1 is higher player 1 will go first
                System.out.println(Player1 + " will go first and play as Red.");
                System.out.println(Player2 + " will go second and play as White.");
                turn = 0;
                break;
            } else if (firstRoll < secondRoll) {    // If dice 2 is higher player 2 will go first
                System.out.println(Player2 + " will go first and play as White.");
                System.out.println(Player1 + " will go second and play as Red.");
                turn = 1;
                break;
            } else {        // If both roll the same then the while loop repeats and both get new rolls
                System.out.println("Both players rolled the same die, it's a tie! Roll again.");
                dice.rollDice();
            }
        }
        System.out.println("=====================================================");

        return turn;
    }

    // getMatchLength function is used to get the length of the match
    public static int getMatchLength() {
        int length = 0;

        while (true){
            System.out.print("Enter match length: ");
            Scanner matchLength = new Scanner(System.in);

            if(matchLength.hasNextInt()) {
                length = matchLength.nextInt();
                break;
            } else {
                System.out.println("Invalid input! Please try again.");
            }
        }
        return length;
    }

    // moveChecker function used to display checker move options then move them and repeat till the players turn is over
    public static void moveChecker(Board myBoard, activePlayer[] players, int turn, DiceRoll dice){

        System.out.println(players[turn].getPlayerName() + " rolls: " + dice.getMoves());   // Display players dice roll
        int numOfMoves = dice.getMoves().size();    // Number of turns depending on dice roll

        // For loop to use all player turns
        for (int i = 0; i < numOfMoves; i++){

            // Initiating Store_Moves array function to hold move options
            Store_Moves moveoptions = myBoard.Move_Options(players[turn], dice);

            while(true){
                // Check if all piece are off the board
                if (moveoptions.getAffect().get(0) == 10){
                    return; // Player has won
                }

                BackGammonView.displayMoves(players[turn], moveoptions);   // Display available moves

                // Function to break the loop if there are no moves available
                if (moveoptions.getCount().isEmpty()){
                    System.out.println("There are no available moves, your turn is now over");
                    break;
                }

                int move = 0;       // Initialising move for the using to input an integer for it

                while (true){
                    System.out.print("Enter move: ");           // Prompt for player to choose a move
                    Scanner sc_move = new Scanner(System.in);   // Initialising scanner for the inputted move

                    if(sc_move.hasNextInt()) {
                        move = sc_move.nextInt();       // Move checker
                        break;

                    // Display if input is invalid
                    } else {
                        System.out.println("Invalid input! Please try again.");
                    }
                }

                // Checking that the value inputted in within the available moves
                if (move <= Collections.max(moveoptions.getCount())){
                    Move(move, myBoard, players[turn], dice);
                    BackGammonView.displayAfterMove(players[turn], moveoptions, move);
                    break;
                } else{
                    System.out.println("Invalid move! Please try again.");
                }
            }
            // Display board with the updated checker position
            BackGammonView.displayBoard(myBoard, players);
        }
    }

    // Move function is used to move a checker once it has been chosen
    public static void Move(int move, Board myBoard, activePlayer player, DiceRoll dice){

        // Collecting move options
        Store_Moves store = myBoard.Move_Options(player, dice);

        // Assigning the chosen move to individual integers
        int count = store.getCount().get(move);
        int start = store.getStart().get(count);
        int roll = store.getRoll().get(count);
        int affect = store.getAffect().get(count);

        Colour colour = player.getpColour();        // Get player colour
        int end = (colour == Colour.RED) ? start + roll : start - roll;     // Calculating the landing position

        // If the checker is coming from the bar back onto the board
        if (!myBoard.getBar().isEmpty() && myBoard.getBar().getLast().getChkColour() == colour){
            Point Pend = myBoard.getbPoints()[end];         // Assigning landing point
            Checker c = myBoard.getBar().getLast();     // Assigning checker

            if (affect == 0 || affect == 1){            // If landing on a blank square or own piece
                barToP(Pend, myBoard, c);
            } else if (affect == 2) {                   // If landing on an opponents singular piece
                pToBar(Pend, myBoard, Pend.getpCheckers().getLast());
                barToP(Pend, myBoard, c);
            }

        // If the checker is starting from on the board
        } else{
            Point Pstart = myBoard.getbPoints()[start];         // Assigning start point
            Checker cStart = Pstart.getpCheckers().getLast();   // Assigning checker
            if (affect == 4){                           // If checker is moving off the board
                offBoard(myBoard, Pstart, cStart);
            } else if (affect == 0 || affect == 1){     // If landing on a blank square or own piece
                Point Pend = myBoard.getbPoints()[end];         // Assigning landing point
                pToP(Pstart, Pend, cStart);
            } else if (affect == 2){                    // If landing on an opponents singular piece
                Point Pend = myBoard.getbPoints()[end];         // Assigning landing point
                pToBar(Pend, myBoard, Pend.getpCheckers().getLast());
                pToP(Pstart, Pend, cStart);
            }
        }
        dice.removeMove(roll);      // Removing dice roll that was used from the move options
    }

    // pToP function moves checkers on the board
    public static void pToP(Point p1, Point p2, Checker c) {
        p1.removeChecker(c);
        p2.addChecker(c);
    }

    // offBoard function moves checkers off the board to attempt to win
    public static void offBoard(Board b1, Point p1, Checker c) {
        b1.moveOffBoard(c);
        p1.removeChecker(c);
    }

    // pToBar function moves opponents checker to the bar when knocked off
    public static void pToBar(Point p1, Board b1, Checker c) {
        p1.removeChecker(c);
        b1.getBar().add(c);
    }

    // barToP function moves the users pieces back on the board from the bar
    public static void barToP(Point p1, Board b1, Checker c) {
        b1.getBar().remove(c);
        p1.addChecker(c);
    }

    // showCurrentPlayerScore function shows the pip score of the player currently playing
    public static void showCurrentPlayerScore(String player, Colour colour, Board board) {
        int pipScore;

        if (colour == Colour.RED) {     // If red its player 1
            pipScore = Pip.getPlayer1Score(board);
        } else {                        // If white its player 2
            pipScore = Pip.getPlayer2Score(board);
        }

        // Display player and pip score
        System.out.println("Current Player: " + player);
        System.out.println("Pip Score: " + pipScore);
    }

    // doubleStake function to offer double points in the following game
    public static boolean doubleStake(int stake, activePlayer[] pNames, int turn) {
        stake = stake * 2;         // Double the stakes
        Scanner sc = new Scanner(System.in);
        String playerName1 = (turn != 0) ? pNames[0].getPlayerName() : pNames[1].getPlayerName();

        System.out.println(playerName1 + ", would you like to double the stake? (Accept/Decline)");
        String playerName = pNames[turn].getPlayerName();
        while (true) {
            String input = sc.nextLine();
            // Handle the "Accept" input
            if (input.equalsIgnoreCase("Accept")) {
                System.out.println("Stake has been doubled!");
                return true; // Breaks out of the loop and exits the method
            }

            // Handle the "Decline" input
            else if (input.equalsIgnoreCase("Decline")) {
                System.out.println("Stake has been declined!");
                System.out.println(playerName + " wins the match!");

                // Increment the player's score
                pNames[turn].setPlayerScore(pNames[turn].getPlayerScore() + 1);
                return false; // Breaks out of the loop and exits the method
            }

            // Handle invalid input
            else {
                System.out.println("Invalid input! Please type 'Accept' or 'Decline'.");
            }
        }
    }

    // BGtest function is used to test inputted test .txt files
    public static void BGtest(String input, DiceRoll dice, Board myBoard, activePlayer[] players, int turn, String[] pNames, int doubleStake) {

        // New input bank
        String filePath = "Group17_BackGammon/test/BackGammon/commands.txt"; // Replace with your file path

        readCommand fileReader = null;   // Initialising read file

        try {
            fileReader = new readCommand(filePath);   // Read file
            String line;                              // Initialising String

            // External while loop for reading the file
            while ((line = fileReader.readNextLine()) != null) {

                // If player input is roll to roll a new dice
                if (line.equalsIgnoreCase("roll")) {
                    dice.rollDice();                            // Roll dice
                    moveChecker(myBoard, players, turn, dice);  // Move checker
                    if (turn == 0) turn++;
                    else turn--;                // End of go, change turns
                }
                // If input is hint it displays input options
                else if (line.equalsIgnoreCase("hint")) {
                    BackGammonView.showHint();
                }
                // If input is pip it displays the pip score of both players
                else if (line.equalsIgnoreCase("pip")) {
                    Pip.getPipScores(pNames[0], pNames[1], myBoard);
                }
                // If input is double it displays an option to play the next game for double the stakes
                else if (line.equalsIgnoreCase("double")) {
                    boolean value = doubleStake(doubleStake, players, turn);
                    doubleStake = value ? doubleStake * 2 : doubleStake;
                    if (!value) {
                        break;
                    }
                }
                // If input is quit it ends the current game
                else if (line.equalsIgnoreCase("quit")) {
                    BackGammonView.checkGameOver(players, myBoard, true, doubleStake);
                    break;
                }
                // If input is custom roll it allows the player to input custom dice rolls
                else if (input.toLowerCase().startsWith("custom roll ")) {
                    String params = input.substring(12).trim();
                    String[] rolls = params.split(",");
                    if (rolls.length == 2) {

                        int roll1 = Integer.parseInt(rolls[0].trim());
                        int roll2 = Integer.parseInt(rolls[1].trim());
                        if (roll1 > 0 && roll2 > 0 && roll1 <= 6 && roll2 <= 6) {
                            dice.assign_dice(roll1, roll2);
                            System.out.println("Dice rolls set to: " + roll1 + ", " + roll2);

                            moveChecker(myBoard, players, turn, dice);  // Move checker
                            if (turn == 0) turn++;
                            else turn--;                // End of go, change turns
                        } else {
                            System.out.println("Invalid numbers! Use the format: custom roll X,Y where X and Y are integers between 1 and 6.");
                        }
                    } else {
                        System.out.println("Invalid format! Use the format: custom roll X,Y.");
                    }
                    // If input was incorrect the following error message shows
                } else {
                    System.out.println("Unknown command! Type 'hint' for available commands.");
                    System.out.println("=====================================================");
                }
            }
        // Find errors
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close(); // Ensure the reader is closed
                }
            } catch (IOException e) {
                System.err.println("Error closing file: " + e.getMessage());
            }
        }
    }

    // moveChecker function used to test the process of moving checkers
    public static void moveCheckerTest(Board myBoard, activePlayer[] players, int turn, DiceRoll dice) {

        System.out.println(players[turn].getPlayerName() + " rolls: " + dice.getMoves());   // Display players dice roll
        int numOfMoves = dice.getMoves().size();    // Number of turns depending on dice roll

        // For loop to use all player turns
        for (int i = 0; i < numOfMoves; i++){

            // Initiating Store_Moves array function to hold move options
            Store_Moves moveoptions = myBoard.Move_Options(players[turn], dice);

            while(true){
                // Check if all piece are off the board
                if (moveoptions.getAffect().getFirst() == 10){
                    return; // Player has won
                }

                BackGammonView.displayMoves(players[turn], moveoptions);   // Display available moves

                // Function to break the loop if there are no moves available
                if (moveoptions.getCount().isEmpty()){
                    System.out.println("There are no available moves, your turn is now over");
                    break;
                }

                int move = 0;       // Initialising move for the using to input an integer for it

                while (true){
                    System.out.print("Enter move: ");           // Prompt for player to choose a move
                    int sc_move = 0;       // Set to 0 for the test

                    if (false) {           // False for the test
                        move = sc_move;    // Move checker
                        break;

                    // Display if input is invalid
                    } else {
                        System.out.println("Invalid input! Please try again.");
                        break;      // For the test
                    }
                }

                // Checking that the value inputted in within the available moves
                if (move <= Collections.max(moveoptions.getCount())) {
                    Move(move, myBoard, players[turn], dice);
                    BackGammonView.displayAfterMove(players[turn], moveoptions, move);
                    break;
                } else{
                    System.out.println("Invalid move! Please try again.");
                    break;      // For the test
                }
            }
            // Display board with the updated checker position
            BackGammonView.displayBoard(myBoard, players);
        }
    }
}