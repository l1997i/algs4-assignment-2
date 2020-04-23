import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

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
    private boolean[][] marked;
    private SET<String> validWords;
    private BoggleBoard board;

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

        this.board = board;
        this.validWords = new SET<>();

        final int rows = board.rows();
        final int cols = board.cols();
        marked = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Die root = new Die(i, j);
                char first_l = board.getLetter(i, j);
                if (first_l == 'Q') {
                    String word = new String();
                    word += "QU";
                    getAllValidWords(root, word);
                } else {
                    String word = new String();
                    word += first_l;
                    getAllValidWords(root, word);
                }

            }
        }

        return validWords;
    }

    private void getAllValidWords(Die d, String word) {

        // add valid word
        if (word.length() > 2 && isInDict(word)) {
            validWords.add(word);
        }

        // backtracking optimization: when the current path corresponds to a string that
        // is not a prefix of any word in the dictionary, there is no need to expand the
        // path further
        if (dict.containsPrefix(word)) {
            // dfs
            marked[d.row][d.col] = true;
            for (Die nbr : neighbor(d.row, d.col)) {

                if (!marked[nbr.row][nbr.col]) {
                    char next = board.getLetter(nbr.row, nbr.col);

                    if (next == 'Q') {
                        getAllValidWords(nbr, word + "QU");
                    } else {
                        getAllValidWords(nbr, word + next);
                    }

                }
            }
            marked[d.row][d.col] = false; // unmark the die as unvisited
        }

    }

    private boolean isInDict(String word) {
        return dict.contains(word);
    }

    private Iterable<Die> neighbor(int r, int c) {
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
        // invalid word
        if (word.length() < 3 || !isInDict(word)) {
            return 0;
        }

        // valid word
        return scoreWord(word);

    }

    /**
     * Score a word based off typical Boggle scoring.
     * 
     * @param s Word to score
     * @return Score of the word passed in
     */
    private int scoreWord(String s) {
        int pointValue;
        int length = s.length();
        if (length < 5)
            pointValue = 1;
        else if (length == 5)
            pointValue = 2;
        else if (length == 6)
            pointValue = 3;
        else if (length == 7)
            pointValue = 5;
        else
            pointValue = 11;
        return pointValue;
    }

    // unit test
    public static void main(String[] args) {
        In in = new In("data/dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard("data/board4x4.txt");
        for (Die d : solver.neighbor(2, 2)) {
            StdOut.println(d);
        }

        StdOut.println("ABANDONED ? " + solver.isInDict("ABANDONED"));
        StdOut.println("EQUATIONS ? " + solver.isInDict("EQUATIONS"));
        StdOut.println("NOTINIT ? " + solver.isInDict("NOTINIT"));
    }

}
