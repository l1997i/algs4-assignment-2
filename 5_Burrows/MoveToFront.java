import edu.princeton.cs.algs4.BinaryStdIn;

/**
 * Move-to-front encoding. Given a text file in which sequences of the same
 * character occur near each other many times, convert it into a text file in
 * which certain characters appear much more frequently than others.
 * 
 * @author Li Li
 * @since Apr. 29, 2020
 */
public class MoveToFront {

    /**
     * apply move-to-front encoding, reading from standard input and writing to
     * standard output
     */
    public static void encode() {

        while (!BinaryStdIn.isEmpty()) {
            BinaryStdIn.readChar();
        }

    }

    /**
     * apply move-to-front decoding, reading from standard input and writing to
     * standard output
     */
    public static void decode() {

    }

    /**
     * if args[0] is "-", apply move-to-front encoding; if args[0] is "+", apply
     * move-to-front decoding
     * 
     * @param args
     */
    public static void main(String[] args) {

        if (args[0].charAt(0) == '-') {
            encode();
        }
        if (args[0].charAt(0) == '+') {
            decode();
        }
    }

}