import edu.princeton.cs.algs4.StdOut;

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
     * circular suffix array of s
     * 
     * @param s
     * @throws IllegalArgumentException if the argument {@code s} is null
     */
    public CircularSuffixArray(String s) {
        // DEBUG:

        if (s == null) {
            throw new IllegalArgumentException("argument s is null");
        }

        this.textString = s;
        this.length = textString.length();
        this.index = new int[length];

        for (int i = 0; i < length; i++) {
            this.index[i] = i;
        }

        sort(index);

    }

    private void sort(int[] index) {
        sortCore(index, 0, index.length - 1);
    }

    // quicksort the subarray from index[lo] to index[hi]
    private void sortCore(int[] index, int lo, int hi) {

        if (hi <= lo)
            return;

        // cutoff to insertion sort for small subarrays
        final int CUTOFF = 8;
        if (hi <= lo + CUTOFF) {
            insertion(index, lo, hi);
            return;
        }

        int j = partition(index, lo, hi);
        sortCore(index, lo, j - 1);
        sortCore(index, j + 1, hi);
    }

    // insertion sort from index[lo] to index[hi]
    private void insertion(int[] index, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(index[j], index[j - 1]); j--)
                exch(index, j, j - 1);
    }

    // partition the subarray index[lo..hi]
    // so that index[lo..j-1] <= index[j] <= index[j+1..hi]
    // and return the index j.
    private int partition(int[] index, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = index[lo];
        while (true) {

            // find item on lo to swap
            while (less(index[++i], v)) {
                if (i == hi)
                    break;
            }

            // find item on hi to swap
            while (less(v, index[--j])) {
                if (j == lo)
                    break; // redundant since index[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j)
                break;

            exch(index, i, j);
        }

        // put partitioning item v at index[j]
        exch(index, lo, j);

        // now, index[lo .. j-1] <= index[j] <= index[j+1 .. hi]
        return j;
    }

    // is v < w ?
    private boolean less(int v, int w) {
        int v1, w1;
        for (int k = 0; k < length; k++) {
            v1 = (v + k) % length;
            w1 = (w + k) % length;
            if (textString.charAt(v1) > textString.charAt(w1)) {
                return false;
            }
            if (textString.charAt(v1) < textString.charAt(w1)) {
                return true;
            }
        }
        return false;
    }

    // exchange index[i] and index[j]
    private void exch(int[] index, int i, int j) {
        int swap = index[i];
        index[i] = index[j];
        index[j] = swap;
    }

    /**
     * return the length of s
     * 
     * @return the length of s
     */
    public int length() {
        // DEBUG:

        return this.length;
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
        // DEBUG:

        isValid(i);

        return this.index[i];
    }

    private void isValid(int i) {
        int n = length();
        if (i < 0 || i > n - 1) {
            throw new IllegalArgumentException("argument outside prescribed range");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // DEBUG:

        CircularSuffixArray array = new CircularSuffixArray("ABRACADABRA!");
        StdOut.println("ABRACADABRA!");
        for (int i = 0; i < array.length(); i++) {
            StdOut.println(array.index(i));
        }
    }
}