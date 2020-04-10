import org.junit.Test;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class SeamTest {

    @Test
    public void SeamTest(){
        Picture picture = new Picture("/Users/l1997i/Desktop/algs4-assignment-2/Programming Assignment 2_ Seam Carving/data/3x4.png");
        SeamCarver seamPicture = new SeamCarver(picture);
        for (int x = 0; x<4; x++){
            for (int y = 0; y<3; y++){
                StdOut.print(seamPicture.energy(x, y));
            }
            StdOut.print("\n");
        }
        StdOut.print("\n");
    }
}