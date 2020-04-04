import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

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
 * @version v1.0
 */
public class SAP {

    private static final int INFINITY = Integer.MAX_VALUE;
    private Digraph graph;
    private int length_cache = -INFINITY;
    private int ancestor_cache = -INFINITY;
    private int v_cache;
    private int w_cache;
    private Iterable<Integer> iv_cache;
    private Iterable<Integer> iw_cache;

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
    }

    /**
     * Returns the ancestor or length of the sap.
     * 
     * @param v
     * @param w
     * @param flag 0 for ancestor, 1 for length
     * @return
     */
    private int bfs_sap(int v, int w, int flag) {

        // check if v and w is the same as cache
        boolean isSame = (v == v_cache && w == w_cache) || (w == v_cache && v == w_cache);

        // use cache for ancestor
        if (isSame && ancestor_cache != -INFINITY && flag == 0) {
            return ancestor_cache;
        }

        // use cache for length
        if (isSame && length_cache != -INFINITY && flag == 1) {
            return length_cache;
        }

        v_cache = v;
        w_cache = w;

        BreadthFirstDirectedPaths dist2w = new BreadthFirstDirectedPaths(graph, w);// reverse source digraph
        Queue<Integer> vQueue = new Queue<Integer>();
        int gV = graph.V();
        Boolean[] marked = new Boolean[gV];
        int[] distTo = new int[gV];
        int minLen = INFINITY;
        int ancestor = -1;

        for (int i = 0; i < gV; i++) {
            marked[i] = false;
            distTo[i] = INFINITY;
        }

        marked[v] = true;
        distTo[v] = 0;
        vQueue.enqueue(v);
        while (!vQueue.isEmpty()) {
            int curr = vQueue.dequeue();
            if (minLen < distTo[curr]) {
                break;
            }
            // if the sum of initial digraph and reverse source digraph is smaller
            if (dist2w.hasPathTo(curr) && (dist2w.distTo(curr) + distTo[curr] < minLen)) {
                minLen = dist2w.distTo(curr) + distTo[curr];
                ancestor = curr;
            }
            for (int next : graph.adj(curr)) {
                // if not visited
                if (!marked[next]) {
                    marked[next] = true;
                    distTo[next] = distTo[curr] + 1;
                    vQueue.enqueue(next);
                }
            }
        }

        if (flag == 1) {
            // return length
            if (minLen < INFINITY) {
                length_cache = minLen;
                return minLen;
            } else {
                length_cache = -1;
                return -1;
            }
        } else {
            // return ancestor
            length_cache = (minLen < INFINITY) ? minLen : -1;
            ancestor_cache = ancestor;
            return ancestor;
        }
    }

    /**
     * Returns the ancestor or length of the sap.
     * 
     * @param v    the vertice set of sources
     * @param w    the vertice set of destination
     * @param flag 0 for ancestor, 1 for length
     * @return
     */
    private int bfs_sap(Iterable<Integer> v, Iterable<Integer> w, int flag) {

        // check if v and w is the same as cache
        boolean isSame = (v == iv_cache && w == iw_cache) || (w == iv_cache && v == iw_cache);

        // use cache for ancestor
        if (isSame && ancestor_cache != -INFINITY && flag == 0) {
            return ancestor_cache;
        }

        // use cache for length
        if (isSame && length_cache != -INFINITY && flag == 1) {
            return length_cache;
        }

        iv_cache = v;
        iw_cache = w;

        BreadthFirstDirectedPaths dist2w = new BreadthFirstDirectedPaths(graph, w);// reverse source digraph
        Queue<Integer> vQueue = new Queue<Integer>();
        int gV = graph.V();
        Boolean[] marked = new Boolean[gV];
        int[] distTo = new int[gV];
        int minLen = INFINITY;
        int ancestor = -1;

        for (int i = 0; i < gV; i++) {
            marked[i] = false;
            distTo[i] = INFINITY;
        }

        // add all the vertice into vQueue
        for (int vertex : v) {
            marked[vertex] = true;
            distTo[vertex] = 0;
            vQueue.enqueue(vertex);
        }

        while (!vQueue.isEmpty()) {
            int curr = vQueue.dequeue();
            if (minLen < distTo[curr]) {
                break;
            }
            // if the sum of initial digraph and reverse source digraph is smaller
            if (dist2w.hasPathTo(curr) && (dist2w.distTo(curr) + distTo[curr] < minLen)) {
                minLen = dist2w.distTo(curr) + distTo[curr];
                ancestor = curr;
            }
            for (int next : graph.adj(curr)) {
                // if not visited
                if (!marked[next]) {
                    marked[next] = true;
                    distTo[next] = distTo[curr] + 1;
                    vQueue.enqueue(next);
                }
            }
        }

        if (flag == 1) {
            // return length
            if (minLen < INFINITY) {
                length_cache = minLen;
                return minLen;
            } else {
                length_cache = -1;
                return -1;
            }
        } else {
            // return ancestor
            length_cache = (minLen < INFINITY) ? minLen : -1;
            ancestor_cache = ancestor;
            return ancestor;
        }
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

        return bfs_sap(v, w, 1);

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

        return bfs_sap(v, w, 0);

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
        validateVertices(v, numV);

        return bfs_sap(v, w, 1);
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

        return bfs_sap(v, w, 0);

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
        In in = new In("data/digraph2.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        int length = sap.length(1, 5);
        int ancestor = sap.ancestor(1, 5);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
}