// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import java.util.ArrayList;
import java.util.List;

public class Store_Moves {
    private List<Integer> count;    // Move number
    private List<Integer> start;    // Start location
    private List<Integer> roll;     // Dice roll to move to end location
    private List<Integer> affect;   // Affect the move has on the board

    // Store_Moves function initialises empty lists
    public Store_Moves() {
        this.count = new ArrayList<>();
        this.start = new ArrayList<>();
        this.roll = new ArrayList<>();
        this.affect = new ArrayList<>();
    }

    // addMove function adds values to count, start, roll, and affect
    public void addMove(int countValue, int startValue, int rollValue, int affectValue) {
        count.add(countValue);
        start.add(startValue);
        roll.add(rollValue);
        affect.add(affectValue);
    }


    // get___ functions are used to get there respective values
    public List<Integer> getCount() {
        return count;
    }

    public List<Integer> getStart() {
        return start;
    }

    public List<Integer> getRoll() {
        return roll;
    }

    public List<Integer> getAffect() {
        return affect;
    }


    // set___ functions are used to set the lists to specific values for testing
    public void setCount(List<Integer> count) {
        this.count = count;
    }

    public void setStart(List<Integer> start) {
        this.start = start;
    }

    public void setRoll(List<Integer> roll) {
        this.roll = roll;
    }

    public void setAffect(List<Integer> affect) {
        this.affect = affect;
    }


    @Override   // toString function outputs all components in a string for the test display
    public String toString() {
        return String.format("Move[count=%d, start=%d, roll=%d, affect=%d]", count, start, roll, affect);
    }
}
