import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TrieST;

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

    private TrieAZ dict = new TrieAZ();;

    /**
     * Initializes the data structure using the given array of strings as the
     * dictionary. Assume each word in the dictionary contains only the uppercase
     * letters A through Z.
     * 
     * @param dictionary
     */
    public BoggleSolver(String[] dictionary) {
        for (int i = 0; i < dictionary.length; i++) {
            dict.put(dictionary[i], i);
        }

        StdOut.print("Test");

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

    private class TrieAZ {
        private static final int R = 26; // contains only the uppercase letters A through Z
        private Node root; // root of trie
        private int n; // number of keys in trie
        private Alphabet az;

        // R-way trie node
        private class Node {
            private int val;
            private Node[] next = new Node[R];
        }

        /*
         * Initializes an empty string symbol table.
         */
        public TrieAZ() {
            String azSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            az = new Alphabet(azSet);
        }

        /**
         * Inserts the key-value pair into the symbol table, overwriting the old value
         * with the new value if the key is already in the symbol table. If the value is
         * {@code null}, this effectively deletes the key from the symbol table.
         * 
         * @param key the key
         * @param val the value
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void put(String key, int val) {
            if (key == null)
                throw new IllegalArgumentException("first argument to put() is null");
            else
                root = put(root, key, val, 0);
        }

        private Node put(Node x, String key, int val, int d) {
            if (x == null)
                x = new Node();
            if (d == key.length()) {
                n++;
                x.val = val;
                return x;
            }
            int c = az.toIndex(key.charAt(d));
            x.next[c] = put(x.next[c], key, val, d + 1);
            return x;
        }

        /**
         * Returns all of the keys in the set that start with {@code prefix}.
         * 
         * @param prefix the prefix
         * @return all of the keys in the set that start with {@code prefix}, as an
         *         iterable
         */
        public Iterable<String> keysWithPrefix(String prefix) {
            Queue<String> results = new Queue<String>();
            Node x = get(root, prefix, 0);
            collect(x, new StringBuilder(prefix), results);
            return results;
        }

        private void collect(Node x, StringBuilder prefix, Queue<String> results) {
            if (x == null)
                return;
            results.enqueue(prefix.toString());
            for (char c = 0; c < R; c++) {
                prefix.append(c);
                collect(x.next[c], prefix, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

        /**
         * Returns the value associated with the given key.
         * 
         * @param key the key
         * @return the value associated with the given key if the key is in the symbol
         *         table and {@code -1} if the key is not in the symbol table
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public int get(String key) {
            if (key == null)
                throw new IllegalArgumentException("argument to get() is null");
            Node x = get(root, key, 0);
            if (x == null)
                return -1;
            return x.val;
        }

        /**
         * Does this symbol table contain the given key?
         * 
         * @param key the key
         * @return {@code true} if this symbol table contains {@code key} and
         *         {@code false} otherwise
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public boolean contains(String key) {
            if (key == null)
                throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != -1;
        }

        private Node get(Node x, String key, int d) {
            if (x == null)
                return null;
            if (d == key.length())
                return x;
            int c = az.toIndex(key.charAt(d));
            return get(x.next[c], key, d + 1);
        }
    }
}
