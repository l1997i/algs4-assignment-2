import org.junit.Test;
import edu.princeton.cs.algs4.StdOut;

public class utest {

    @Test
    public void test() {
        WordNet test = new WordNet("data/synsets.txt", "data/hypernyms.txt");
    }

    @Test
    public void testSAP() {
        StdOut.print("Hello!");
        // String file = "data/digraph25.txt";
        // In in = new In(file);
        // Digraph G = new Digraph(in);
        // SAP sap = new SAP(G);
        // int v = StdIn.readInt();
        // int w = StdIn.readInt();
    }
}