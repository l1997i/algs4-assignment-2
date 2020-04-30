import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * Burrows–Wheeler inverse transform. Now, we describe how to invert the
 * Burrows–Wheeler transform and recover the original input string. If the jth
 * original suffix (original string, shifted j characters to the left) is the
 * ith row in the sorted order, we define next[i] to be the row in the sorted
 * order where the (j + 1)st original suffix appears. For example, if first is
 * the row in which the original input string appears, then next[first] is the
 * row in the sorted order where the 1st original suffix (the original string
 * left-shifted by 1) appears; next[next[first]] is the row in the sorted order
 * where the 2nd original suffix appears; next[next[next[first]]] is the row
 * where the 3rd original suffix appears; and so forth.
 * 
 * @author Li Li
 * @since Apr. 30, 2020
 */
public class BurrowsWheeler {

    /**
     * apply Burrows-Wheeler transform, reading from standard input and writing to
     * standard output
     */
    public static void transform() {

        String textString = BinaryStdIn.readString();
        StringBuilder rst = new StringBuilder(); /* store output result */
        CircularSuffixArray suffix = new CircularSuffixArray(textString);
        int length = suffix.length();

        /* find the first and construct the rst */
        int first = 0;
        for (int i = 0; i < length; i++) {
            int index_raw = suffix.index(i);
            int index = (length + index_raw - 1) % length;
            rst.append(textString.charAt(index));
            if (index_raw == 0) {
                first = i;
            }
        }

        /* write back rst to StdOut */
        BinaryStdOut.write(first, 32);

        for (int i = 0; i < rst.length(); i++) {
            BinaryStdOut.write(rst.charAt(i), 8);
        }
        BinaryStdOut.close();

    }

    /**
     * apply Burrows-Wheeler inverse transform, reading from standard input and
     * writing to standard output. This is a Least-Significant-Digit First (LSD)
     * implement.
     */
    public static void inverseTransform() {

        final int R = 256; /* Extended-ASCII */
        int first = BinaryStdIn.readInt();
        String textString = BinaryStdIn.readString();
        int n = textString.length();
        int[] next = new int[n];

        /* compute frequency counts */
        int[] count = new int[R + 1];
        for (int i = 0; i < n; i++)
            count[textString.charAt(i) + 1]++;

        /* compute cumulates */
        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];

        /* move data */
        for (int i = 0; i < n; i++)
            next[count[textString.charAt(i)]++] = i;

        /* writing to StdOut */
        for (int j = 0, k = next[first]; j < n; j++, k = next[k])
            BinaryStdOut.write(textString.charAt(k));
        BinaryStdOut.close();

    }

    /**
     * if args[0] is "-", apply Burrows-Wheeler transform if args[0] is "+", apply
     * Burrows-Wheeler inverse transform
     * 
     * @param args
     */
    public static void main(String[] args) {
        if (args[0].charAt(0) == '-') {
            transform();
        }
        if (args[0].charAt(0) == '+') {
            inverseTransform();
        }
    }
}