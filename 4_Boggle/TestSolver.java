import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestSolver {

    @Test
    public void testBoggleSolver() {
        printHeader("testBoggleSolver");
        In in = new In("data/dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard("data/board4x4.txt");
        for (String s : solver.getAllValidWords(board)) {
            StdOut.println(s);
        }
        printFooter("testBoggleSolver");
    }

    @Test
    public void testBoggleBoard() {
        // Familiarize myself with the BoggleBoard.java data type
        printHeader("testBoggleBoard");
        BoggleBoard myBoard = new BoggleBoard("data/board4x4.txt");
        int cols = myBoard.cols();
        int rows = myBoard.rows();
        StdOut.println("cols: " + cols);
        StdOut.println("rows: " + rows);
        assertEquals(4, cols, "cols not equal!");
        assertEquals(4, rows, "rows not equal!");
        char boardLetter = myBoard.getLetter(2, 2);
        StdOut.println("getLetter(2, 2): " + boardLetter);
        assertEquals('N', boardLetter, "Letter in (2,2) not equal!");
        printFooter("testBoggleBoard");
    }

    private void printHeader(String funcName) {
        StdOut.println("=========== " + funcName + " begins ===========");
    }

    private void printFooter(String funcName) {
        StdOut.println("=> " + funcName + " complete.");
    }
}