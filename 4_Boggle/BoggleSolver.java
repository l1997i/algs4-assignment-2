import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.Bag;

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
    private boolean[] marked;
    private int[] edgeTo;
    private Bag<String> validWords = new Bag<>();

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

    private static class Die {
        public final int row;
        public final int col;

        public Die(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public String toString() {
            return "(" + row + ", " + col + ")";
        }

        public boolean isValid(int boardRows, int boardCols) {
            if (row >= boardRows || row < 0 || col >= boardCols || col < 0) {
                return false;
            }
            return true;
        }

    }

    /**
     * Returns the set of all valid words in the given Boggle board, as an Iterable.
     * 
     * @param board
     * @return
     */
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        // TODO:

        final int rows = board.rows();
        final int cols = board.cols();
        final int SIZE = cols * rows;
        marked = new boolean[SIZE];
        edgeTo = new int[SIZE];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Die root = new Die(i, j);
                char l = board.getLetter(i, j);
                String word = new String();
                word += l;
                getAllValidWords(board, root, word);
            }
        }

        return validWords;
    }

    private boolean isInDict(String word) {
        return dict.contains(word);
    }

    private void getAllValidWords(BoggleBoard board, Die d, String word) {

        if (word.length() > 2 && isInDict(word)) {
            validWords.add(word);
        }

        int idx = toIndex(board, d.row, d.col);
        marked[idx] = true;
        for (Die nbr : neighbor(board, d.row, d.col)) {
            int idx_nbr = toIndex(board, nbr.row, nbr.col);
            if (!marked[idx_nbr]) {
                word += board.getLetter(nbr.row, nbr.row);
                getAllValidWords(board, nbr, word);
            }
        }
    }

    private int toIndex(BoggleBoard board, int row, int col) {
        int boardCols = board.cols();
        return row * boardCols + col;
    }

    private Iterable<Die> neighbor(BoggleBoard board, int r, int c) {
        Bag<Die> retBag = new Bag<>();
        int boardCols = board.cols();
        int boardRows = board.rows();

        for (int offset_r = -1; offset_r <= 1; offset_r++) {
            for (int offset_c = -1; offset_c <= 1; offset_c++) {
                Die d = new Die(r + offset_r, c + offset_c);
                if (!(offset_r == 0 && offset_c == 0) && d.isValid(boardRows, boardCols)) {
                    retBag.add(d);
                }
            }
        }

        return retBag;
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
