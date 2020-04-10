import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;

/**
 * <p>
 * Seam-carving is a content-aware image resizing technique where the image is
 * reduced in size by one pixel of height (or width) at a time. A vertical seam
 * in an image is a path of pixels connected from the top to the bottom with one
 * pixel in each row; a horizontal seam is a path of pixels connected from the
 * left to the right with one pixel in each column.
 * </p>
 * 
 * <p>
 * Unlike standard content-agnostic resizing techniques (such as cropping and
 * scaling), seam carving preserves the most interest features (aspect ratio,
 * set of objects present, etc.) of the image.
 * </p>
 * 
 * <p>
 * Although the underlying algorithm is simple and elegant, it was not
 * discovered until 2007. Now, it is now a core feature in Adobe Photoshop and
 * other computer graphics applications.
 * </p>
 * 
 * @author Li Li
 * @since Apr. 9, 2020
 * @version 0.0.2
 */
public class SeamCarver {
    private Picture picture;

    /**
     * create a seam carver object based on the given picture
     * 
     * @param picture
     */
    public SeamCarver(Picture picture) {

        /** Corner cases **/
        if (picture == null) {
            throw new IllegalArgumentException("called with a null argument");
        }

        this.picture = new Picture(picture);
    }

    /**
     * return current picture
     * 
     * @return current picture
     */
    public Picture picture() {
        return picture;
    }

    /**
     * return width of current picture
     * 
     * @return width of current picture
     */
    public int width() {
        return picture.width();

    }

    /**
     * return height of current picture
     * 
     * @return height of current picture
     */
    public int height() {

        return picture.height();
    }

    /**
     * return energy of pixel at column x and row y
     * 
     * @param x
     * @param y
     * @throws IllegalArgumentException IFF either x or y is outside its prescribed
     *                                  range
     * @return energy of pixel at column x and row y
     */
    public double energy(int x, int y) {

        int width = width();
        int height = height();

        /** Corner cases **/
        if (x < 0 || x > width - 1 || y < 0 || y > height - 1) {
            throw new IllegalArgumentException("Either x or y is outside its prescribed range");
        }

        // the energy of a pixel at the border of the image to be 1000
        if (x * (x - width + 1) * y * (y - height + 1) == 0)
            return 1000;

        return pixelEnergy(x, y);

    }

    // get the RGB value of the given pixel
    private double pixelEnergy(int x, int y) {
        int rgbW = picture.getRGB(x, y - 1);
        int rW = (rgbW >> 16) & 0xFF;
        int gW = (rgbW >> 8) & 0xFF;
        int bW = (rgbW >> 0) & 0xFF;

        int rgbE = picture.getRGB(x, y + 1);
        int rE = (rgbE >> 16) & 0xFF;
        int gE = (rgbE >> 8) & 0xFF;
        int bE = (rgbE >> 0) & 0xFF;

        int rgbS = picture.getRGB(x - 1, y);
        int rS = (rgbS >> 16) & 0xFF;
        int gS = (rgbS >> 8) & 0xFF;
        int bS = (rgbS >> 0) & 0xFF;

        int rgbN = picture.getRGB(x + 1, y);
        int rN = (rgbN >> 16) & 0xFF;
        int gN = (rgbN >> 8) & 0xFF;
        int bN = (rgbN >> 0) & 0xFF;

        double deltaWE = Math.pow(rW - rE, 2) + Math.pow(gW - gE, 2) + Math.pow(bW - bE, 2);
        double deltaSN = Math.pow(rS - rN, 2) + Math.pow(gS - gN, 2) + Math.pow(bS - bN, 2);

        return Math.sqrt(deltaWE + deltaSN);
    }

    /**
     * find the sequence of indices for horizontal seam
     * 
     * @return sequence of indices for horizontal seam
     */
    public int[] findHorizontalSeam() {

        transpose();

        int[] VerticalSeam = findVerticalSeam();
        transpose();

        return VerticalSeam;
    }

    /**
     * find the sequence of indices for vertical seam
     * 
     * @return sequence of indices for vertical seam
     */
    public int[] findVerticalSeam() {

        Digraph energyDigraph = pic2Digraph(true);
        int width = width();
        int height = height();
        double[][] energy = new double[width][height];
        int[] from = new int[width * height];
        Topological energyTopological = new Topological(energyDigraph);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                energy[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        for (int v : energyTopological.order()) {
            int[] index_v = getxy(v);
            double energy_v = energy(index_v[0], index_v[1]);
            double curr_energy = energy_v;
            for (Integer w : findParents(v, width, height)) {
                if (w == -1) {
                    energy[index_v[0]][index_v[1]] = curr_energy;
                    break;
                }
                int[] index_w = getxy(w);
                if (curr_energy + energy[index_w[0]][index_w[1]] < energy[index_v[0]][index_v[1]]) {
                    // StdOut.println("v: " + v + "(" + curr_energy + ") \t\t\t\t<- w: " + w + "("
                    // + energy[index_w[0]][index_w[1]] + ")\t\t\t\t = "
                    // + (curr_energy + energy[index_w[0]][index_w[1]]));
                    energy[index_v[0]][index_v[1]] = curr_energy + energy[index_w[0]][index_w[1]];
                    from[v] = w;
                }
            }
        }

        double energyMin = Double.POSITIVE_INFINITY;
        int vertexMin = 0;
        int[] seam = new int[height];
        int[] aux = new int[height];
        for (int j = 0; j < width; j++) {
            if (energy[j][height - 1] < energyMin) {
                energyMin = energy[j][height - 1];
                vertexMin = getIndex(j, height - 1);
            }
        }

        aux[height - 1] = vertexMin;
        seam[height - 1] = vertexMin % width;
        for (int j = height - 2; j >= 0; j--) {
            aux[j] = from[aux[j + 1]];
            seam[j] = aux[j] % width;
        }

        return seam;

    }

    private Iterable<Integer> findParents(int v, int width, int height) {
        int index[] = getxy(v);
        ArrayList<Integer> parents = new ArrayList<>();
        if (index[1] == 0) {
            parents.add(-1);
            return parents;
        }
        parents.add(v - width);
        if (index[0] == 0) {
            parents.add(v - width + 1);
            return parents;
        }
        if (index[0] == width - 1) {
            parents.add(v - width - 1);
            return parents;
        }
        parents.add(v - width + 1);
        parents.add(v - width - 1);
        return parents;
    }

    private Digraph pic2Digraph(boolean isVertical) {

        int vWeight = isVertical ? 1 : 0;
        int hWeight = isVertical ? 0 : 1;
        int width = picture.width();
        int height = picture.height();
        int size = width * height;
        int maxLoop = width * hWeight + height * vWeight;
        int endIndex = isVertical ? width - 1 : width * (height - 1);
        Digraph energyGraph = new Digraph(size);
        int nextIndexA = isVertical ? width : 1;
        int nextIndexB;

        nextIndexB = width + 1;
        for (int i = 0, j = 0; j < maxLoop - 1; i += nextIndexA) {
            energyGraph.addEdge(i, i + nextIndexA);
            energyGraph.addEdge(i, i + nextIndexB);
            j++;
        }

        nextIndexB = isVertical ? nextIndexA - 1 : -width + 1;
        for (int i = endIndex, j = 0; j < maxLoop - 1; i += nextIndexA) {
            energyGraph.addEdge(i, i + nextIndexA);
            energyGraph.addEdge(i, i + nextIndexB);
            j++;
        }

        if (isVertical) {
            for (int i = 0; i < height - 1; i++) {
                for (int j = 1; j < width - 1; j++) {
                    int curr_index = getIndex(j, i);
                    int next_index = getIndex(j, i + 1);
                    energyGraph.addEdge(curr_index, next_index - 1);
                    energyGraph.addEdge(curr_index, next_index);
                    energyGraph.addEdge(curr_index, next_index + 1);
                }
            }
        } else {
            for (int i = 1; i < height - 1; i++) {
                for (int j = 0; j < width - 1; j++) {
                    int curr_index = getIndex(j, i);
                    int next_indexA = getIndex(j + 1, i - 1);
                    int next_indexB = getIndex(j + 1, i + 1);
                    energyGraph.addEdge(curr_index, next_indexA);
                    energyGraph.addEdge(curr_index, curr_index + 1);
                    energyGraph.addEdge(curr_index, next_indexB);
                }
            }
        }

        return energyGraph;
    }

    /**
     * remove horizontal seam from current picture
     * 
     * @param seam
     * @throws IllegalArgumentException if called with a null argument or called
     *                                  when the width of the picture is less than
     *                                  or equal to 1
     */
    public void removeHorizontalSeam(int[] seam) {

        /** Corner cases **/
        if (seam == null || height() <= 1)
            throw new IllegalArgumentException(
                    "called with a null argument or called when the height of the picture is less than or equal to 1");
        if (!isValidSeam(seam, false))
            throw new IllegalArgumentException("seam illegal");

        // TODO:

    }

    /**
     * remove vertical seam from current picture
     * 
     * @param seam
     * @throws IllegalArgumentException if called with a null argument or called
     *                                  when the width of the picture is less than
     *                                  or equal to 1
     */
    public void removeVerticalSeam(int[] seam) {

        /** Corner cases **/
        if (seam == null || width() <= 1) {
            throw new IllegalArgumentException(
                    "called with a null argument or called when the width of the picture is less than or equal to 1");
        }
        if (!isValidSeam(seam, true))
            throw new IllegalArgumentException("seam illegal");

        int width = width();
        int height = height();
        for (int i = 0; i < height; i++) {
            int seam_i = seam[i];
            for (int j = 0; j < width - 1; j++) {
                if (j != seam_i)
                    picture.set(i, j, picture.get(i, j));
                else
                    j--;
            }
        }

    }

    /**
     * test wheter the given seam is a valid array. The illegal array of seam is the
     * one which has the wrong length (an entry is outside its prescribed range) or
     * two adjacent entries differ by more than 1
     * 
     * @param seam       the given seam to verify
     * @param isVertical {@code true} if vertical, {@code false} if horizontal
     * @return {@code true} if valid, {@code false} if not valid
     */
    private boolean isValidSeam(int[] seam, boolean isVertical) {

        int range;
        if (isVertical)
            range = width() - 1;
        else
            range = height() - 1;

        int item_old = -1;
        for (int item : seam) {

            // an entry is outside its prescribed range
            if (item < 0 || item > range)
                return false;

            // two adjacent entries differ by more than 1
            if (Math.abs(item - item_old) > 1 && item_old != -1)
                return false;
            item_old = item;
        }

        return true;
    }

    private int[] getxy(int v) {
        int width = width();
        int[] xy = new int[2];
        xy[1] = Math.floorDiv(v, width());
        xy[0] = v - xy[1] * width;
        return xy;
    }

    private int getIndex(int x, int y) {
        int width = width();
        return y * width + x;
    }

    private void transpose() {
        Picture transposedPicture = new Picture(picture.height(), picture.width());
        for (int i = 0; i < picture.width(); i++)
            for (int k = 0; k < picture.height(); k++) {
                transposedPicture.set(k, i, picture.get(i, k));
            }
        picture = transposedPicture;
    }

    // unit testing
    public static void main(String[] args) {

        // TODO:

        Picture picture = new Picture(
                "/Users/l1997i/Desktop/algs4-assignment-2/2_Seam_Carving/data/3x7.png");
        SeamCarver seamPicture = new SeamCarver(picture);
        StdOut.println(picture.width());
        StdOut.println(picture.height());
        for (int y = 0; y < picture.height(); y++) {
            for (int x = 0; x < picture.width(); x++) {

                StdOut.print(seamPicture.energy(x, y) + "\t\t");
            }
            StdOut.print("\n");
        }
        StdOut.print("\n");

        Digraph A = seamPicture.pic2Digraph(true);
        Topological tl = new Topological(A);
        for (int i : tl.order()) {
            StdOut.print(i + "\t");
        }

        int[] a = seamPicture.findVerticalSeam();
        int[] b = seamPicture.findHorizontalSeam();
        StdOut.print("\n");
    }
}