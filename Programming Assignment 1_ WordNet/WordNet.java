import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Topological;

/**
 * 
 * WordNet is a semantic lexicon for the English language that computational
 * linguists and cognitive scientists use extensively. For example, WordNet was
 * a key component in IBM’s Jeopardy-playing Watson computer system. WordNet
 * groups words into sets of synonyms called synsets. For example, { AND
 * circuit, AND gate } is a synset that represent a logical gate that fires only
 * when all of its inputs fire. WordNet also describes semantic relationships
 * between synsets. One such relationship is the is-a relationship, which
 * connects a hyponym (more specific synset) to a hypernym (more general
 * synset). For example, the synset { gate, logic gate } is a hypernym of { AND
 * circuit, AND gate } because an AND gate is a kind of logic gate.
 * 
 * @author Li Li
 * @since Apr. 2
 * @version v0.2
 * 
 */
public class WordNet {

    private ST<String, SET<Integer>> synsets;
    private ST<Integer, String> id_nouns;
    private Digraph wordGraph;
    private int outSum = 0; // the totoal number of out edges
    private int num = 0;

    /**
     * constructor takes the name of the two input files
     * 
     * @param synsets
     * @param hypernyms
     * @throws IllegalArgumentException if any argument to the constructor or an
     *                                  instance method is null
     */
    public WordNet(String synsets, String hypernyms) {

        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException("Any argument to the constructor or an instance method is null");
        }

        getSynsets(synsets);
        getHypernyms(hypernyms);
    }

    private void getSynsets(String synsets) {
        this.synsets = new ST<String, SET<Integer>>();
        id_nouns = new ST<Integer, String>();
        In synset = new In(synsets);
        while (synset.hasNextLine()) {
            String fields[] = synset.readLine().split(",");
            String nounSET[] = fields[1].split(" ");
            Integer subID;
            num++;
            subID = Integer.parseInt(fields[0]);
            id_nouns.put(subID, fields[1]);
            for (String noun : nounSET) {
                if (this.synsets.contains(noun)) {
                    this.synsets.get(noun).add(subID);
                } else {
                    SET<Integer> ids = new SET<Integer>();
                    ids.add(subID);
                    this.synsets.put(noun, ids);
                }
            }
        }
    }

    private void getHypernyms(String hypernyms) {
        In hypernym = new In(hypernyms);
        wordGraph = new Digraph(num);
        boolean vIsOut[] = new boolean[num]; // does this vertice has an edge out?
        while (hypernym.hasNextLine()) {
            String fields[] = hypernym.readLine().split(",");
            Integer hyponymId = Integer.parseInt(fields[0]);

            for (int i = 1; i < fields.length - 1; i++) {
                Integer hypernymId = Integer.parseInt(fields[i]);
                wordGraph.addEdge(hyponymId, hypernymId);
            }

            if (!vIsOut[hyponymId] && fields.length != 1) {
                outSum++;
            }
            vIsOut[hyponymId] = true;
        }
        isRootedDAG();
    }

    private void isRootedDAG() {
        if (num - outSum != 1) {
            throw new IllegalArgumentException("The input to the constructor does not correspond to a rooted graph");
        }

        Topological testDAG = new Topological(wordGraph);
        if (!testDAG.hasOrder()) {
            throw new IllegalArgumentException("The input to the constructor does not correspond to a DAG");
        }
    }

    /**
     * returns all WordNet nouns
     * 
     * @return all WordNet nouns
     */
    public Iterable<String> nouns() {
        return synsets.keys();
    }

    /**
     * is the word a WordNet noun?
     * 
     * @param word
     * @return
     */
    public boolean isNoun(String word) {
        return synsets.contains(word);

    }

    /**
     * distance between nounA and nounB
     *
     * @param nounA
     * @param nounB
     * @return distance between nounA and nounB
     */
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Any of the noun arguments in distance() is not a WordNet noun.");
        }
        SAP sap = new SAP(wordGraph);
        SET<Integer> idA = synsets.get(nounA);
        SET<Integer> idB = synsets.get(nounB);

        return sap.length(idA, idB);

    }

    /**
     * a synset (second field of synsets.txt) that is the common ancestor of nounA
     * and nounB in a shortest ancestral path
     *
     * @param nounA
     * @param nounB
     * @return the common ancestor of nounA and nounB in a shortest ancestral
     *         path(SAP)
     */
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Any of the noun arguments in sap() is not a WordNet noun.");
        }
        SAP sap = new SAP(wordGraph);
        SET<Integer> idA = synsets.get(nounA);
        SET<Integer> idB = synsets.get(nounB);
        int ancestorId = sap.ancestor(idA, idB);

        return id_nouns.get(ancestorId);
    }

    /**
     * do unit testing of this class
     * 
     * @param args
     */
    public static void main(String[] args) {
        WordNet test = new WordNet("data/synsets.txt", "data/hypernyms.txt");
    }
}