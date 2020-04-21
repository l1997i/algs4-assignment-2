import org.junit.Test;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestSolver {

    @Test
    public void testBoggleSolver() {
        In in = new In("data/dictionary-shakespeare.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard();
        for (String s:solver.getAllValidWords(board)){
            StdOut.println(s);
        }
        StdOut.print("test success!");
    }
}