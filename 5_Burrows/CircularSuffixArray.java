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
     * returns index of ith sorted suffix
     * 
     * @param i
     * @throws IllegalArgumentException if the argument {@code i} is outside its
     *                                  prescribed range (between 0 and n − 1).
     * @return index of ith sorted suffix
     */
    public int index(int i) {
        // TODO:

        int n = length();
        if (i < 0 || i > n - 1) {
            throw new IllegalArgumentException("argument outside prescribed range");
        }

        return 0;
    }

    // unit testing (required)
    public static void main(String[] args) {
        // TODO:

    }
}