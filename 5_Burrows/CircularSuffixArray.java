import edu.princeton.cs.algs4.Quick3string;

/**
 * CircularSuffixArray: To efficiently implement the key component in the
 * Burrows–Wheeler transform, we will use a fundamental data structure known as
 * the circular suffix array, which describes the abstraction of a sorted array
 * of the n circular suffixes of a string of length n. As an example, consider
 * the string "ABRACADABRA!" of length 12. The table below shows its 12 circular
 * suffixes and the result of sorting them.
 * 
 * @author Li Li
 * @since Apr. 30, 2020
 */
public class CircularSuffixArray {

    private String textString;
    private int[] index;
    private int length;

    /**
     * Not to create new String objects when sorting the suffixes. That would take
     * quadratic space. A natural approach is to define a nested class
     * CircularSuffix that represents a circular suffix implicitly (via a reference
     * to the input string and a pointer to the first character in the circular
     * suffix). The constructor of CircularSuffix should take constant time and use
     * constant space.
     */
    private class CircularSuffix {
        String s;
        int index;

        public CircularSuffix(String textString, int index) {
            this.s = textString;
            this.index = index;
        }
    }

    /**
     * circular suffix array of s
     * 
     * @param s
     * @throws IllegalArgumentException if the argument {@code s} is null
     */
    public CircularSuffixArray(String s) {
        // TODO:

        if (s == null) {
            throw new IllegalArgumentException("argument s is null");
        }

        this.textString = new String(s);
        this.length = textString.length();
        this.index = new int[length];

        for (int i = 0; i < length; i++) {
            this.index[i] = i;
        }

    }

    /**
     * return the length of s
     * 
     * @return the length of s
     */
    public int length() {
        // TODO:

        return 0;
    }

    /**
     * Returns index of ith sorted suffix. We define index[i] to be the index of the
     * original suffix that appears ith in the sorted array. For example, index[11]
     * = 2 means that the 2nd original suffix appears 11th in the sorted order.
     * 
     * @param i
     * @throws IllegalArgumentException if the argument {@code i} is outside its
     *                                  prescribed range (between 0 and n − 1).
     * @return index of ith sorted suffix
     */
    public int index(int i) {
        // TODO:

        isValid(i);

        return 0;
    }

    private void isValid(int i) {
        int n = length();
        if (i < 0 || i > n - 1) {
            throw new IllegalArgumentException("argument outside prescribed range");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // TODO:

    }
}