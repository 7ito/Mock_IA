package mock.ia;

import java.util.ArrayList;

public class Game
{
    private String gameID;
    private Team team1;
    private Team team2;


    /**
     * Initializes the class object 
     * @param gameID the gameID of the game
     * @param team1 the home team
     * @param team2 the away team
     */
    public Game(String gameID, Team team1, Team team2)
    {
        this.gameID = gameID;
        this.team1 = team1;
        this.team2 = team2;
    }

    /**
     * Setter method for gameID
     * @param gameID 
     */
    public void setGameID(String gameID)
    {
        this.gameID = gameID;
    }

    /**
     * Setter method for team1
     * @param team1 
     */
    public void setTeam1(Team team1)
    {
        this.team1 = team1;
    }

    /**
     * Setter method for team2
     * @param team2 
     */
    public void setTeam2(Team team2)
    {
        this.team2 = team2;
    }

    /**
     * Getter method for gameID
     * @return gameID
     */
    public String getGameID()
    {
        return gameID;
    }

    /**
     * Getter method for team1
     * @return team1
     */
    public Team getTeam1()
    {
        return team1;
    }

    /**
     * Getter method for team2
     * @return team2
     */
    public Team getTeam2()
    {
        return team2;
    }

    /**
     * Gets a selected stat from a selected player
     * @param stat selected stat
     * @param player selected player
     * @return the recorded stat of that player for this game
     * @throws Exception 
     */
    public int getStat(String stat, Player player) throws Exception
    {
        return Database.Main.getStat(player.getNum(), stat, gameID, player.getTeam().getID());
    }

    /**
     * Gets a selected stat from a team (accumulated statistics from the entire team)
     * @param stat selected stat
     * @param teamID selected team
     * @return the recorded stat of that team for this game
     * @throws Exception 
     */
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

    /**
     * Gets the ID of the opponent team by teamID
     * @param teamID
     * @return the opponents teamID
     */
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

    /**
     * Returns the amount of possessions in a game by teamID used in the Team class to measure the average possessions
     * @param teamID the team selected
     * @return the calculated amount of possessions
     * @throws Exception 
     */
    public double getPossesions(int teamID) throws Exception
    {
        ArrayList<Integer> tpa = Database.Main.getStatTeam(teamID, "2pa", gameID);
        ArrayList<Integer> threepa = Database.Main.getStatTeam(teamID, "3pa", gameID);
        ArrayList<Integer> tpm = Database.Main.getStatTeam(teamID, "2pm", gameID);
        ArrayList<Integer> threepm = Database.Main.getStatTeam(teamID, "3pm", gameID);

        double t2pa = 0;
        double t3pa = 0;
        double t2pm = 0;
        double t3pm = 0;

        for (int i : tpa)
        {
            t2pa = t2pa + i;
        }

        for (int i : threepa)
        {
            t3pa = t3pa + i;
        }
        
        for (int i : tpm)
        {
            t2pm = t2pm + i;
        }
        
        for (int i : threepm)
        {
            t3pm = t3pm + i;
        }

        double FGA = t2pa + t3pa;
        double FGM = t2pm + t3pm;

        double poss = 0.96*((FGA + getStatTeam("turnovers", teamID) + (0.44 * getStatTeam("fta", teamID)) - getStatTeam("oreb", teamID)));
        
        
        //double poss = 0.5 * ((FGA + 0.4 * getStatTeam("fta", teamID) - 1.07 * (getStatTeam("oreb", teamID) / (getStatTeam("oreb", teamID) + getStatTeam("dreb", getOppTeam(teamID).getID()))) * (FGA - FGM) + getStatTeam("turnovers", teamID)) + ((getStatTeam("2pa", getOppTeam(teamID).getID()) + getStatTeam("3pa", getOppTeam(teamID).getID())) + 0.4 * (getStatTeam("fta", getOppTeam(teamID).getID())) - 1.07 * (getStatTeam("oreb", getOppTeam(teamID).getID()))/(getStatTeam("oreb", getOppTeam(teamID).getID()) + getStatTeam("dreb", teamID))) * ((getStatTeam("2pa", getOppTeam(teamID).getID()) + getStatTeam("3pa", getOppTeam(teamID).getID())) - (getStatTeam("2pm", getOppTeam(teamID).getID()) + getStatTeam("3pm", getOppTeam(teamID).getID()))) + getStatTeam("turnovers", getOppTeam(teamID).getID() ));
                
        return poss;
    }





}
