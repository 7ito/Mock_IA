package mock.ia;

import java.util.ArrayList;

public class Calculations
{

    private ArrayList<Player> allPlayers;

    public void setAllPlayers(ArrayList<Player> players)
    {
        allPlayers = players;
    }

    /**
     * Calculates the average points per game by a team
     * @param team .
     * @return the average ppg
     * @throws Exception
     */
    public double PPG(Team team) throws Exception
    {
        System.out.println("" + team.getName() + " averages " + team.getStat("points") + " points per game.");
        return team.getStat("points");
    }

    /**
     * Calculates the average ppg allowed by a team
     * @param team .
     * @return the average ppg scored on this team
     * @throws Exception
     */
    public double oppPPG(Team team) throws Exception
    {
        double total = 0;
        double count = 0;

        for (Game game : team.getGames())
        {
            total = total + game.getStatTeam("points", game.getOppTeam(team.getID()).getID());
            count++;
        }

        double output = total/count;
        System.out.println("" + team.getName() + " on average allows " + output + " points by opponents.");
        return output;
    }


    /**
     * The pace rating that a team plays at
     * @param team .
     * @return The pace rating
     * @throws Exception
     */
    public double pace(Team team) throws Exception
    {
        double pace = 48 * ((team.avgPoss() + team.oppAvgPoss()) / (2 * (team.getStat("minutes") / 5)));

        System.out.println("" + team.getName() + " plays with " + pace + " pace.");
        return pace;
    }

    /**
     * The efective field goal percentage by a player
     * @param player .
     * @return eFG%
     * @throws Exception
     */
    public double eFG(Player player) throws Exception
    {
        double totalFG = player.getStat("2pm") + player.getStat("3pm");
        double totalFGA = player.getStat("2pa") + player.getStat("3pa");
        double eFGp = 100*(totalFG + 0.5 * player.getStat("3pm")) / totalFGA;
        System.out.println("" + player.getName() + "'s eFG% is " + eFGp + "%");
        return eFGp;
    }

    /**
     * A statistic per 36 minutes of a player
     * @param player .
     * @param stat the stat that will be checked
     * @return the per 36 minute stat
     * @throws Exception
     */
    public double per36(Player player, String stat) throws Exception
    {
        double result = 0;
        double perMinute = player.getStat(stat)/player.getStat("minutes");
        result = perMinute*36;
        System.out.println(result + stat);
        return result;
    }

    /**
     * The true shooting percentage of a player
     * @param player .
     * @return TSG%
     * @throws Exception
     */
    public double TSG(Player player) throws Exception
    {
        double TSA = (player.getStat("2pa") + player.getStat("3pa")) + 0.44 * player.getStat("fta");

        System.out.println("" + player.getName() + " has a TSG% of " + (100*(player.getStat("points")/(2*TSA))) + "%");
        return player.getStat("points")/(2*TSA);
    }

    /**
     * The total rebounding percentage of a player
     * @param player .
     * @return TRB%
     * @throws Exception
     */
    public double TRBP(Player player) throws Exception
    {
        double total = 0;
        double count = 0;

        for (Game game: player.getTeam().getGames())
        {
            total = total + game.getStatTeam("treb", game.getOppTeam(player.getTeam().getID()).getID());
            count++;
        }
        double oppTRB = total/count;

        double result = 100 * (player.getStat("treb") * (player.getTeam().getStat("minutes") / 5))/ (player.getStat("minutes") * (player.getTeam().getStat("treb") + oppTRB));
        System.out.println("" + player.getName() + " has a TRB% of " + result + "%");
        return result;
    }

    /**
     * The assist percentage of a player
     * @param player .
     * @return AST%
     * @throws Exception
     */
    public double ASTP(Player player) throws Exception
    {
        double result = 100 * player.getStat("assists")/(((player.getStat("minutes")/(player.getTeam().getStat("minutes")/5) * player.getTeam().getStat("FGM"))) - player.getStat("FGM"));
        System.out.println("" + player.getName() + " has a AST% of " + result + "%");
        return result;
    }

    /**
     * The steal percentage of a player
     * @param player .
     * @return STL%
     * @throws Exception
     */
    public double STLP(Player player) throws Exception
    {
        double result = 100 * (player.getStat("steal") * (player.getTeam().getStat("minutes")/5)) / (player.getStat("minutes") * player.getTeam().oppAvgPoss());
        System.out.println("" + player.getName() + " has a STL% of " + result + "%");
        return result;
    }

    /**
     * The block percentage of a player
     * @param player .
     * @return BLK%
     * @throws Exception
     */
    public double BLKP(Player player) throws Exception
    {
        double total2PA = 0;
        double total3PA = 0;

        for (Game game : player.getTeam().getGames())
        {
            total2PA = game.getStatTeam("2pa", game.getOppTeam(player.getTeam().getID()).getID());
            total3PA = game.getStatTeam("3pa", game.getOppTeam(player.getTeam().getID()).getID());
        }

        double totalFG = total2PA + total3PA;

        double result = 100 * (player.getStat("block") * (player.getTeam().getStat("minutes")/5)) / (player.getTeam().getStat("minutes") * (totalFG - total3PA));
        System.out.println("" + player.getName() + " has a BLK% of " + result + "%");
        return result;
    }

    /**
     * The turnover percentage of a player
     * @param player .
     * @return TOV%
     * @throws Exception
     */
    public double TOVP(Player player) throws Exception
    {
        double result = 100 * player.getStat("turnovers") / (player.getStat("FGA") + 0.44 * player.getStat("fta") + player.getStat("turnovers"));
        System.out.println("" + player.getName() + " has a TOV% of " + result + "%");
        return result;
    }

    /**
     * The usage rate of a player
     * @param player .
     * @return usage rate
     * @throws Exception
     */
    public double USGR(Player player) throws Exception
    {
        double result = 100 * ((player.getStat("FGA") + 0.44 * player.getStat("fta") + player.getStat("turnovers")) * (player.getTeam().getStat("minutes")/5)/(player.getStat("minutes")*(player.getTeam().getStat("FGA") + 0.44 * player.getTeam().getStat("fta") + player.getTeam().getStat("turnovers"))));
        System.out.println("" + player.getName() + " has a usage rate of " + result + "%");
        return result;
    }

    public double ORTG(Team team) throws Exception
    {
        double result = (PPG(team)/team.avgPoss())*100;
        System.out.println("ORTG = " + result);
        return result;
    }

    public double DRTG(Team team) throws Exception
    {
        double result = (oppPPG(team)/team.oppAvgPoss()*100);
        System.out.println("DRTG = " + result);
        return result;
    }

    /**
     * Returns an integer number based on if a player is below, above or at league average in a particular stat category.
     * 1 is above average, 2 is league average and 3 is below league average
     * @param player .
     * @param stat the stat being assessed
     * @return the integer that gives the signal
     * @throws Exception
     */
    public int leagueAVG(Player player, String stat) throws Exception
    {
        double leagueAVG = 0;
        ArrayList<Player> samePos = new ArrayList<>();

        for (Player p : allPlayers)
        {
            if (p.getPosition() == player.getPosition())
            {
                samePos.add(p);
            }
        }

        double total = 0;
        double count = 0;

        for (Player p : samePos)
        {
            total = total + p.getStat(stat);
            count++;
        }

        leagueAVG = total/count;

        if (leagueAVG < player.getStat(stat))
        {
            System.out.println("" + player.getName() + " is above the league average in " + stat + ".");
            return 1;
        }
        else if (leagueAVG == player.getStat(stat))
        {
            System.out.println("" + player.getName() + " is league average in " + stat + ".");
            return 2;
        }
        else
        {
            System.out.println("" + player.getName() + " is below league average in " + stat + ".");
            return 3;
        }
    }


}
