import java.util.Iterator;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Shortest ancestral path({@code SAP}). An ancestral path between two vertices
 * v and w in a digraph is a directed path from v to a common ancestor x,
 * together with a directed path from w to the same ancestor x. A shortest
 * ancestral path is an ancestral path of minimum total length. We refer to the
 * common ancestor in a shortest ancestral path as a shortest common ancestor.
 * Note also that an ancestral path is a path, but not a directed path.
 * 
 * @author Li Li
 * @since Apr. 4
 * @version v0.2
 */
public class SAP {

    private static final int INFINITY = Integer.MAX_VALUE;
    private Digraph graph;
    private Graph bigraph;
    private Iterable<Integer> sap;
    private int destination = -1;

    /**
     * constructor
     * 
     * @param G a digraph (not necessarily a {@code DAG})
     */
    public SAP(Digraph G) {

        if (G == null) {
            throw new IllegalArgumentException("Argument is null");
        }

        graph = G;
        bigraph = toBigraph(graph);

    }

    /**
     * Returns the bigraph of the digraph.
     *
     * @return the bigraph of the digraph
     */
    private Graph toBigraph(Digraph G) {
        Graph bigraph = new Graph(G.V());
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                bigraph.addEdge(v, w);
                bigraph.addEdge(w, v);
            }
        }
        return bigraph;
    }

    /**
     * length of shortest ancestral path between v and w; -1 if no such path
     * 
     * @param v
     * @param w
     * @throws IllegalArgumentException Vertex argument is outside its prescribed
     *                                  range
     * @return length of shortest ancestral path between v and w; -1 if no such path
     */
    public int length(int v, int w) {

        int numV = graph.V();
        validateVertex(v, numV);
        validateVertex(w, numV);

        BreadthFirstPaths bfgraph = new BreadthFirstPaths(bigraph, v);

        if (bfgraph.hasPathTo(w)) {
            return bfgraph.distTo(w);
        }

        return -1;

    }

    /**
     * a common ancestor of v and w that participates in a shortest ancestral path;
     * -1 if no such path
     * 
     * @param v
     * @param w
     * @throws IllegalArgumentException Vertex argument is outside its prescribed
     *                                  range
     * @return a common ancestor of v and w that participates in a shortest
     *         ancestral path
     */
    public int ancestor(int v, int w) {
        int numV = graph.V();
        validateVertex(v, numV);
        validateVertex(w, numV);

        BreadthFirstPaths bfgraph = new BreadthFirstPaths(bigraph, v);
        BreadthFirstDirectedPaths bfdgraph = new BreadthFirstDirectedPaths(graph, v);
        int vNext = 0;
        int vthis = 0;

        if (!bfgraph.hasPathTo(w)) {
            return -1;
        }

        Iterator<Integer> pathIterator = bfgraph.pathTo(w).iterator();
        while (pathIterator.hasNext()) {
            vNext = pathIterator.next();
            if (!bfdgraph.hasPathTo(vNext)) {
                return vthis;
            }
            vthis = vNext;
        }
        return vNext;

    }

    /**
     * length of shortest ancestral path between any vertex in v and any vertex in
     * w; -1 if no such path
     * 
     * @param v
     * @param w
     * @throws IllegalArgumentException unless {@code 0 <= v < V} for each vertex in
     *                                  v
     * @return length of shortest ancestral path between any vertex in v and any
     *         vertex in w; -1 if no such path
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        int numV = graph.V();
        int min = INFINITY;
        validateVertices(v, numV);

        for (int vitem : v) {
            BreadthFirstPaths bfgraph = new BreadthFirstPaths(bigraph, vitem);

            for (int witem : w) {
                int curr = bfgraph.distTo(witem);
                if (curr < min) {
                    min = curr;
                    sap = bfgraph.pathTo(witem);
                    destination = witem;
                }
            }
        }

        return (min == INFINITY) ? -1 : min;

    }

    /**
     * a common ancestor that participates in shortest ancestral path; -1 if no such
     * path
     * 
     * @param v
     * @param w
     * @throws IllegalArgumentException unless {@code 0 <= v < V} for each vertex in
     *                                  v
     * @return a common ancestor that participates in shortest ancestral path; -1 if
     *         no such path
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {

        int numV = graph.V();
        validateVertices(v, numV);
        if (sap == null) {
            length(v, w);
        }

        int source = sap.iterator().next();

        return ancestor(source, destination);

    }

    /**
     * Check if any vertex argument is outside its prescribed range
     * 
     * @param v
     * @param V
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    private void validateVertex(int v, int V) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * Check if any vertices arguments are outside its prescribed range
     * 
     * @param vertices
     * @param V
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    private void validateVertices(Iterable<Integer> vertices, int V) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
            }
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        // while (!StdIn.isEmpty()) {
        // int v = StdIn.readInt();
        // int w = StdIn.readInt();
        // int length = sap.length(v, w);
        // int ancestor = sap.ancestor(v, w);
        // StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        // }

        Bag<Integer> vBag = new Bag<>();
        Bag<Integer> wBag = new Bag<>();
        vBag.add(13);
        vBag.add(23);
        vBag.add(24);
        wBag.add(6);
        wBag.add(16);
        wBag.add(17);
        int length = sap.length(vBag, wBag);
        int ancestor = sap.ancestor(vBag, wBag);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
}