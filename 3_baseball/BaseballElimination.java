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

    /**
     * create a baseball division from given filename in format specified below
     * @param filename
     */
    public BaseballElimination(String filename)

    /**
     * number of teams
     * @return
     */
    public int numberOfTeams()

    /**
     * all teams
     * @return
     */
    public Iterable<String> teams()

    /**
     * number of wins for given team
     * @param team
     * @return
     */
    public int wins(String team)

    /**
     * number of losses for given team
     * @param team
     * @return
     */
    public int losses(String team)

    /**
     * number of remaining games for given team
     * @param team
     * @return
     */
    public int remaining(String team)

    /**
     * number of remaining games between team1 and team2
     * @param team1
     * @param team2
     * @return
     */
    public int against(String team1, String team2)

    /**
     * is given team eliminated?
     * @param team
     * @return
     */
    public boolean isEliminated(String team)

    /**
     * subset R of teams that eliminates given team; null if not eliminated
     * @param team
     * @return
     */
    public Iterable<String> certificateOfElimination(String team)

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