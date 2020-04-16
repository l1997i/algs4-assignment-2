import java.util.ArrayList;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SparseVector;
import edu.princeton.cs.algs4.StdOut;

/**
 * The baseball elimination problem: Given the standings in a sports division at
 * some point during the season, determine which teams have been mathematically
 * eliminated from winning their division. {@code BaseballElimination} is an
 * immutable data type that represents a sports division and determines which
 * teams are mathematically eliminated.
 * 
 * @author Li Li
 * @since April 15, 2020
 */
public class BaseballElimination {

    private final int teamN;
    private final ArrayList<String> names; // the name of all teams
    private final int[] w; // wins
    private final int[] l; // loss
    private final int[] r; // left
    private final SparseVector[] g; // game to play with each team

    /**
     * create a baseball division from given filename in specific format
     * 
     * @param filename
     */
    public BaseballElimination(String filename) {
        In in = new In(filename);
        teamN = Integer.parseInt(in.readLine());
        w = new int[teamN];
        l = new int[teamN];
        r = new int[teamN];
        g = new SparseVector[teamN];
        names = new ArrayList<>(teamN);

        for (int i = 0; i < teamN; i++) {
            names.add(in.readString());
            w[i] = in.readInt();
            l[i] = in.readInt();
            r[i] = in.readInt();
            g[i] = new SparseVector(teamN);
            for (int j = 0; j < teamN; j++) {
                int cap = in.readInt();
                if (j >= i) {
                    // saving space
                    g[i].put(j, cap);
                }
            }
        }
    }

    /**
     * number of teams
     * 
     * @return
     */
    public int numberOfTeams() {
        return this.teamN;
    }

    /**
     * all teams
     * 
     * @return
     */
    public Iterable<String> teams() {
        return this.names;
    }

    /**
     * number of wins for given team
     * 
     * @param team
     * @return
     */
    public int wins(String team) {
        int index = indexOf(team);
        return this.w[index];
    }

    /**
     * number of losses for given team
     * 
     * @param team
     * @return
     */
    public int losses(String team) {
        int index = indexOf(team);
        return this.l[index];
    }

    /**
     * number of remaining games for given team
     * 
     * @param team
     * @return
     */
    public int remaining(String team) {
        int index = indexOf(team);
        return this.r[index];
    }

    /**
     * number of remaining games between team1 and team2
     * 
     * @param team1
     * @param team2
     * @return
     */
    public int against(String team1, String team2) {
        int indexOfTeam1 = indexOf(team1);
        int indexOfTeam2 = indexOf(team2);
        return getg(indexOfTeam1, indexOfTeam2);
    }

    private int getg(int indexOfTeam1, int indexOfTeam2) {
        boolean is1Bigger = indexOfTeam1 > indexOfTeam2;
        return (int) (is1Bigger ? g[indexOfTeam2].get(indexOfTeam1) : g[indexOfTeam1].get(indexOfTeam2));

    }

    private FlowNetwork flowNetInit(String team) {
        int indexOfTeam = indexOf(team);
        int s = 0;
        int size = (teamN - 1) * (teamN - 2) / 2;
        int v = size + teamN + 1;
        FlowNetwork flowNet = new FlowNetwork(v + 1);
        int count = 0;

        for (int i = 0; i < g.length; i++) {
            if (i == indexOfTeam)
                continue;
            for (int j = i + 1; j < g[i].dimension(); j++) {
                if (j == indexOfTeam)
                    continue;
                count++;
                flowNet.addEdge(new FlowEdge(s, count, g[i].get(j)));
                flowNet.addEdge(new FlowEdge(count, size + i + 1, Double.POSITIVE_INFINITY));
                flowNet.addEdge(new FlowEdge(count, size + j + 1, Double.POSITIVE_INFINITY));
            }
            int cap = w[indexOfTeam] + r[indexOfTeam] - w[i];
            flowNet.addEdge(new FlowEdge(size + i + 1, v, cap >= 0 ? cap : 0));
        }
        return flowNet;
    }

    /**
     * is given team eliminated?
     * 
     * @param team
     * @return
     */
    public boolean isEliminated(String team) {

        int i = indexOf(team);

        // trivial elimination: w[t]+r[t]<w[i]
        for (int t = 0; t < teamN; t++) {
            if (t != i && w[i] + r[i] < w[t])
                return true;
        }

        // nontrivial elimination: create a flow network
        int size = (teamN - 1) * (teamN - 2) / 2;
        int v = size + teamN + 1;
        FlowNetwork fn = flowNetInit(team);
        FordFulkerson ff = new FordFulkerson(fn, 0, v);
        for (int t = size + 1; t < v; t++) {
            if (t == size + i + 1)
                continue;
            if (ff.inCut(t))
                return true;
        }
        return false;
    }

    /**
     * subset R of teams that eliminates given team; null if not eliminated
     * 
     * @param team
     * @return
     */
    public Iterable<String> certificateOfElimination(String team) {

        int i = indexOf(team);
        ArrayList<String> resTeam = new ArrayList<>();

        // trivial elimination: w[t]+r[t]<w[i]
        for (int t = 0; t < teamN; t++) {
            if (w[i] + r[i] < w[t])
                resTeam.add(names.get(t));
        }

        // nontrivial elimination: create a flow network
        int size = (teamN - 1) * (teamN - 2) / 2;
        int v = size + teamN + 1;
        FlowNetwork fn = flowNetInit(team);
        FordFulkerson ff = new FordFulkerson(fn, 0, v);
        for (int t = size + 1; t < v; t++) {
            if (t == size + i + 1)
                continue;
            if (ff.inCut(t) && !resTeam.contains(names.get(t - size - 1)))
                resTeam.add(names.get(t - size - 1));
        }

        if (resTeam.size()==0){
            return null;
        }

        return resTeam;
    }

    private int indexOf(String team) {
        int index  = this.names.indexOf(team);
        if (index == -1){
            throw new IllegalArgumentException("the input arguments are invalid teams");
        }
        return index;
    }

    // unit test
    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}