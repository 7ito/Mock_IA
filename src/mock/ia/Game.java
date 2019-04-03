package mock.ia;

import java.util.ArrayList;

public class Game
{
    private String gameID;
    private Team team1;
    private Team team2;


    public Game(String gameID, Team team1, Team team2)
    {
        this.gameID = gameID;
        this.team1 = team1;
        this.team2 = team2;
    }

    public void setGameID(String gameID)
    {
        this.gameID = gameID;
    }

    public void setTeam1(Team team1)
    {
        this.team1 = team1;
    }

    public void setTeam2(Team team2)
    {
        this.team2 = team2;
    }

    public String getGameID()
    {
        return gameID;
    }

    public Team getTeam1()
    {
        return team1;
    }

    public Team getTeam2()
    {
        return team2;
    }

    public int getStat(String stat, int playerNum) throws Exception
    {
        return Database.Main.getStat(playerNum, stat, gameID);
    }

    public int getStatTeam(String stat, int teamID) throws Exception
    {
        ArrayList<Integer> statList = Database.Main.getStatTeam(teamID, stat, gameID);
        int total = 0;

        for (int i : statList)
        {
            total = total + i;
        }
        return total;
    }

    public Team getOppTeam(int teamID)
    {
        if (teamID == team1.getID())
        {
            return team2;
        }
        else
        {
            return team1;
        }
    }

    public double getPossesions(int teamID) throws Exception
    {
        ArrayList<Integer> tpa = Database.Main.getStatTeam(teamID, "2pa", gameID);
        ArrayList<Integer> threepa = Database.Main.getStatTeam(teamID, "3pa", gameID);

        double t2pa = 0;
        double t3pa = 0;

        for (int i : tpa)
        {
            t2pa = t2pa + i;
        }

        for (int i : threepa)
        {
            t3pa = t3pa + i;
        }

        double FGA = t2pa + t3pa;

        double poss = 0.96*((FGA + getStatTeam("turnovers", teamID) + 0.44 * getStatTeam("fta", teamID) - getStatTeam("oreb", teamID)));
        System.out.println(poss);
        return poss;
    }





}
