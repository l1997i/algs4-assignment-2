import java.util.LinkedList;
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
        LinkedList<Character> asciiTable = new LinkedList<>();

        /* init: asciiTable */
        for (int i = 0; i < R; i++) {
            asciiTable.add((char) i);
        }

        /* Read char in binary form */
        /* asciiTable.indexOf(input_char) = encode_int */
        /* asciiTable.indexOf('A') = 6 means
        /* when you read 'A' from input, then encode to 6 */
        while (!BinaryStdIn.isEmpty()) {
            char currChar = BinaryStdIn.readChar();
            int currCharNo = asciiTable.indexOf(currChar);
            BinaryStdOut.write(currCharNo, 8);
            Character firstChar = asciiTable.remove(currCharNo);
            asciiTable.addFirst(firstChar); /* move current char to the front */
        }

        BinaryStdOut.close();
    }

    /**
     * apply move-to-front decoding, reading from standard input and writing to
     * standard output
     */
    public static void decode() {
        final int R = 256; /* Extended-ASCII */
        char[] asciiTable = new char[R];

        /* init: asciiTable */
        for (int i = 0; i < R; i++) {
            asciiTable[i] = (char) i;
        }

        /* Read char in binary form */
        /* asciiTable[input_code] = decode_char */
        /* asciiTable[0] = 'A' means when you read 0 from input, then decode to 'A' */
        while (!BinaryStdIn.isEmpty()) {
            char currChar = BinaryStdIn.readChar();
            char currCharNo = asciiTable[currChar];
            BinaryStdOut.write(currCharNo, 8);
            /* move all the char in front of current char 1 place back */
            for (int i = currChar; i > 0; i--) {
                asciiTable[i] = asciiTable[i - 1];
            }
            asciiTable[0] = currCharNo; /* move current char to the front */
        }

        BinaryStdOut.close();

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