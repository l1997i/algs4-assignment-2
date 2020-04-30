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
public class BurrowsWheele {

    /**
     * apply Burrows-Wheeler transform, reading from standard input and writing to
     * standard output
     */
    public static void transform() {

    }

    /**
     * apply Burrows-Wheeler inverse transform, reading from standard input and
     * writing to standard output
     */
    public static void inverseTransform() {

    }

    /**
     * if args[0] is "-", apply Burrows-Wheeler transform if args[0] is "+", apply
     * Burrows-Wheeler inverse transform
     * 
     * @param args
     */
    public static void main(String[] args) {

    }
}