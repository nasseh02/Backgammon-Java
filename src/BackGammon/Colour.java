// Group 17
// Manasseh Ekundayo ------- 21407956 -- https://github.com/Morpheus36
// Oluwadamilola Bayadere -- 24258005 -- https://github.com/DammyAA7
// Ciaran Conway ----------- 21314733 -- https://github.com/Ciaran374

package BackGammon;

import java.util.Arrays;

enum Colour {

    // The board can be occupied with either red, white or no colour
    RED, WHITE, NONE;

    // Print abbreviations
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Colour.values()));
    }
}
