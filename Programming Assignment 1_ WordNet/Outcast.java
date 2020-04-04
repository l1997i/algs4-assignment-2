import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 * <p>
 * Given a {@code list} of {@code WordNet} nouns x1, x2, ..., xn, which noun is
 * the least related to the others? To identify an outcast, compute the sum of
 * the distances between each noun and every other one:
 * </p>
 * <p>
 * di = distance(xi, x1) + distance(xi, x2) + ... + distance(xi, xn)
 * </p>
 * <p>
 * and return a noun xt for which dt is maximum. Note that distance(xi, xi) = 0,
 * so it will not contribute to the sum.
 * </p>
 * 
 * @author Li Li
 * @since Apr. 4, 2020
 * @version v0.1
 */
public class Outcast {

    private WordNet wordnet;

    /**
     * constructor takes a WordNet object
     * 
     * @param wordnet
     */
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    /**
     * given an array of WordNet nouns, return an outcast
     * 
     * @param nouns an {@code array} of WordNet nouns
     * @return an outcast
     */
    public String outcast(String[] nouns) {
        String outcast = "";
        int d = -1;
        for (String noun : nouns) {
            int curr_distance = 0;
            for (String cmp_noun : nouns) {
                if (cmp_noun!=noun){
                    curr_distance += wordnet.distance(noun, cmp_noun);
                }
            }

            if (curr_distance > d) {
                d = curr_distance;
                outcast = noun;
            }
        }
        return outcast;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}