import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

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

        final int R = 256; /* Extended-ASCII */
        char[] asciiTable = new char[R];

        /* init: asciiTable */
        for (int i = 0; i < R; i++) {
            asciiTable[i] = (char) i;
        }

        /* Read char in binary form */
        while (!BinaryStdIn.isEmpty()) {
            char currChar = BinaryStdIn.readChar();
            char currCharNo = asciiTable[currChar];
            BinaryStdOut.write(currCharNo, 8);
            /* move all the char in front of current char 1 place back */
            for (int i = 0; i < R; i++) {
                char iNo = asciiTable[i];
                if (iNo < currCharNo) {
                    asciiTable[i]++;
                }
            }
            asciiTable[currChar] = 0; /* move current char to the front */
        }

        BinaryStdOut.close();
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