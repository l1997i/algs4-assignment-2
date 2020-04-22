/**
 * The Boggle game. Boggle is a word game designed by Allan Turoff and
 * distributed by Hasbro. It involves a board made up of 16 cubic dice, where
 * each die has a letter printed on each of its 6 sides. At the beginning of the
 * game, the 16 dice are shaken and randomly distributed into a 4-by-4 tray,
 * with only the top sides of the dice visible. The players compete to
 * accumulate points by building valid words from the dice.
 * 
 * {@code BoggleSolver} class is a Boggle solver that finds all valid words in a
 * given Boggle board, using a given dictionary.
 * 
 * @author Li Li
 * @since Apr. 21, 2020
 */
public class BoggleSolver {

    private BoggleDict dict;

    /**
     * Initializes the data structure using the given array of strings as the
     * dictionary. Assume each word in the dictionary contains only the uppercase
     * letters A through Z.
     * 
     * @param dictionary
     */
    public BoggleSolver(String[] dictionary) {
        dict = new BoggleDict(dictionary);
    }

    /**
     * Returns the set of all valid words in the given Boggle board, as an Iterable.
     * 
     * @param board
     * @return
     */
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        // TODO:

        return dict.keysWithPrefix("ABAND");
    }

    /**
     * Returns the score of the given word if it is in the dictionary, zero
     * otherwise. Assume the word contains only the uppercase letters A through Z.)
     * 
     * @param word
     * @return
     */
    public int scoreOf(String word) {
        // TODO:

        return 0;

    }

}
