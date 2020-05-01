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
    private int length;
    private CircularSuffix[] cs;

    private class CircularSuffix {
        private String orgString; /* the original text string */
        private int ptr; /* the pointer to the string */

        public CircularSuffix(int index) {
            orgString = textString;
            ptr = index;
        }

        /**
         * return the d(th) char of ptr(th) string in original suffixes
         * 
         * @param d the d(th) char
         * @return the char in original suffixes
         */
        public char charAt(int d) {
            return orgString.charAt((d + ptr) % length);
        }
    }

    /**
     * circular suffix array of s
     * 
     * @param s
     * @throws IllegalArgumentException if the argument {@code s} is null
     */
    public CircularSuffixArray(String s) {

        if (s == null) {
            throw new IllegalArgumentException("argument s is null");
        }

        this.textString = s;
        this.length = textString.length();
        this.cs = new CircularSuffix[length];

        /* init: the circular suffix array */
        for (int i = 0; i < s.length(); i++) {
            cs[i] = new CircularSuffix(i);
        }

        quick3string(0, length - 1, 0);

    }

    // 3-way string quicksort cs[lo..hi] starting at d(th) character
    private void quick3string(int lo, int hi, int d) {

        // cutoff to insertion sort for small subarrays
        final int CUTOFF = 20;
        if (hi <= lo + CUTOFF) {
            insertion(lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        int v = cs[lo].charAt(d);
        int i = lo + 1;
        while (i <= gt) {
            int t = cs[i].charAt(d);
            if (t < v)
                exch(lt++, i++);
            else if (t > v)
                exch(i, gt--);
            else
                i++;
        }

        // cs[lo..lt-1] < v = cs[lt..gt] < cs[gt+1..hi].
        quick3string(lo, lt - 1, d);
        if (v >= 0)
            quick3string(lt, gt, d + 1);
        quick3string(gt + 1, hi, d);
    }

    // sort from cs[lo] to cs[hi], starting at the dth character
    private void insertion(int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(j, j - 1, d); j--)
                exch(j, j - 1);
    }

    // is cs[v] < cs[w] at d(th) char?
    private boolean less(int v, int w, int d) {
        for (int i = d; i < length; i++) {
            if (cs[v].charAt(i) < cs[w].charAt(i))
                return true;
            if (cs[v].charAt(i) > cs[w].charAt(i))
                return false;
        }
        return false;
    }

    // exchange cs[i] and cs[j]
    private void exch(int i, int j) {
        CircularSuffix swap = cs[i];
        cs[i] = cs[j];
        cs[j] = swap;
    }

    /**
     * return the length of s
     * 
     * @return the length of s
     */
    public int length() {

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

        isValid(i);

        return this.cs[i].ptr;
    }

    private void isValid(int i) {
        int n = length();
        if (i < 0 || i > n - 1) {
            throw new IllegalArgumentException("argument outside prescribed range");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray array = new CircularSuffixArray("ABRACADABRA!");
        StdOut.println("ABRACADABRA!");
        for (int i = 0; i < array.length(); i++) {
            StdOut.println(array.index(i));
        }
    }
}