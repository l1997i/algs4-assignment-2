/**
 * Use a trie to store the dictionary. This is the aux class for
 * {@code BoggleSolver} class.
 * 
 * @author Li Li
 * @since Apr. 23, 2020
 * 
 */
public class BoggleDict {

    private static final int R = 26; // only contains A-Z

    private Node root; // root of trie

    // R-way trie node
    private static class Node {
        private Node[] next = new Node[R];
        private boolean isValid;
    }

    /**
     * initializes a string symbol table with given String[] dictionary
     * 
     * @param dictionary
     */
    public BoggleDict(String[] dictionary) {

        for (String word : dictionary) {
            put(word);
        }
    }

    public boolean containsPrefix(String prefix) {
        Node x = get(root, prefix.toString(), 0);
        if (x != null)
            return true;
        else
            return false;

    }

    /**
     * Does this symbol table contain the given word?
     * 
     * @param word the word
     * @return {@code true} if this symbol table contains {@code word} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code word} is {@code null}
     */

    public boolean contains(String word) {
        Node x = get(root, word, 0);
        if (x == null)
            return false;
        return x.isValid;
    }

    /**
     * Add the given word to the dictionary
     * 
     * @param word
     */
    public void put(String word) {
        root = put(root, word, 0);
    }

    private Node put(Node x, String word, int d) {
        if (x == null)
            x = new Node();
        if (d == word.length()) {
            x.isValid = true;
        } else {
            char c = word.charAt(d);
            x.next[c - 'A'] = put(x.next[c - 'A'], word, d + 1);
        }
        return x;
    }

    /**
     * Get the word begining from {@code Node} x
     * 
     * @param x
     * @param word
     * @param d
     * @return the word you get
     */
    private Node get(Node x, String word, int d) {
        if (x == null)
            return null;
        if (d == word.length())
            return x;
        char c = word.charAt(d);
        return get(x.next[c - 'A'], word, d + 1);
    }
}