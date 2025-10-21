// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceRoll {

    private int Roll_1;           // First dice roll
    private int Roll_2;           // Second dice roll
    private List<Integer> moves;  // List of all available rolls

    // DiceRoll function to hold and collect dice rolls
    public DiceRoll(){
        moves = new ArrayList<>();
        rollDice();
    }

    // rollDice function used to roll 2 dice and if there's a double it gives 4 move options
    public void rollDice() {
        Random random = new Random();
        Roll_1 = random.nextInt(6) + 1;  // Generating dice 1
        Roll_2 = random.nextInt(6) + 1;  // Generating dice 2

        moves.clear();   // Clearing previous moves

        // Allocating the dice to the move array if was a double roll or 2 signals
        if (Roll_1 == Roll_2) {
            // For doubles, add four moves
            for (int i = 0; i < 4; i++) {
                moves.add(Roll_1);
            }
        } else {
            moves.add(Roll_1);
            moves.add(Roll_2);
        }
    }

    // assign_dice function takes 2 custom dice inputs
    public void assign_dice(int Roll_1, int Roll_2){
        moves.clear();   // Clearing previous moves

        // Allocating the dice to the move array if was a double roll or 2 signals
        if (Roll_1 == Roll_2) {
            // For doubles, add four moves
            for (int i = 0; i < 4; i++) {
                moves.add(Roll_1);
            }
        } else {
            moves.add(Roll_1);
            moves.add(Roll_2);
        }
    }

    // getMoves function outputs the list of all available dice moves
    public List<Integer> getMoves() {
        return moves;
    }

    // getRoll1 function outputs the value of the first dice rolled
    public int getRoll1() {
        return Roll_1;
    }

    // getRoll2 function outputs the value of the second dice rolled
    public int getRoll2() {
        return Roll_2;
    }

    // removeMove function removes a move from the list
    public boolean removeMove(int usedRoll) {
        return moves.remove((Integer) usedRoll);
    } 
}
