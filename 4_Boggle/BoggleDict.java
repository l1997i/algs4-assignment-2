import java.util.HashMap;
import edu.princeton.cs.algs4.TST;

public class BoggleDict {

    private final HashMap<String, TST<Integer>> dictionary;

    /*
     * Initializes an empty string symbol table.
     */
    public BoggleDict(String[] dictionary) {
        this.dictionary = new HashMap<>();

        for (String w : dictionary) {
            if (w.length() > 2) {
                String key = word2key(w);
                if (!this.dictionary.containsKey(key)) {
                    // Create TST for the key
                    this.dictionary.put(key, new TST<Integer>());
                }
                this.dictionary.get(key).put(w.substring(2), w.length());
            }
        }
    }

    /**
     * Returns all of the keys in the set that start with {@code prefix}.
     * 
     * @param prefix the prefix
     * @return all of the keys in the set that start with {@code prefix}, as an
     *         iterable
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        String key = word2key(prefix);
        if (!dictionary.containsKey(key)) {
            return null;
        }
        return dictionary.get(key).keysWithPrefix(prefix.substring(2));
    }

    public boolean containsPrefix(String prefix) {
        if (prefix.length() == 1) {
            char[] c = { prefix.charAt(0), 'A' };
            while (c[1] <= 'Z') {
                if (dictionary.containsKey(String.valueOf(c))) {
                    return true;
                }
                c[1]++;
            }
            return false;
        }
        if (prefix.length() == 2) {
            return dictionary.containsKey(prefix);
        }

        else {
            String key = word2key(prefix);
            if (!dictionary.containsKey(key)) {
                return false;
            }
            return dictionary.get(key).keysWithPrefix(prefix.substring(2)).iterator().hasNext();
        }

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
        if (word.length() < 2) {
            return false;
        }
        if (word.length() == 2) {
            return dictionary.containsKey(word);
        }
        String key = word2key(word);
        if (!dictionary.containsKey(key)) {
            return false;
        }
        return dictionary.get(key).contains(word.substring(2));
    }

    private String word2key(String word) {
        return String.valueOf(word.charAt(0)) + String.valueOf(word.charAt(1));
    }
}