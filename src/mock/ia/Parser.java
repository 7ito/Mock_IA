/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mock.ia;

import java.util.ArrayList;

/**
 *
 * @author tito
 */
public class Parser 
{
    private ArrayList<Player> allPlayers;
    private ArrayList<Team> roster;
    private Team blue;
    private Team red;
    private Team white;
    private Team black;
    private ArrayList<Game> allGames;
    
    
    /**
     * Initialisation of all variables
     */
    public Parser()
    {
        allPlayers = new ArrayList<>();
        roster = new ArrayList<>();
        blue = new Team("Blue Army", 1);
        red = new Team("Red Army", 2);
        white = new Team("White Army", 3);
        black = new Team("Black Army", 4);
        allGames = new ArrayList<>();
        
        roster.add(blue);
        roster.add(red);
        roster.add(white);
        roster.add(black);
    }
    
    /**
     * Initialises all components of the parser
     * @throws Exception 
     */
    public void init() throws Exception
    {
        initPlayers();
        initGames();
        initTeams();
    }
    
    /**
     * Getter method 
     * @return allGames
     */
    public ArrayList<Game> getAllGames()
    {
        return allGames;
    }
    
    /**
     * Getter method
     * @return roster
     */
    public ArrayList<Team> getRoster()
    {
        return roster;
    }
    
    /**
     * Getter method
     * @return allPlayers
     */
    public ArrayList<Player> getAllPlayers()
    {
        return allPlayers;
    }
    
    /**
     * Modifier method using ArrayList.add
     * @param player 
     */
    public void addPlayer(Player player)
    {
        allPlayers.add(player);
    }
    
    
    /**
     * Localises allPlayers from roster tables into the allPlayers variable
     * @throws Exception 
     */
    public void initPlayers() throws Exception
    {
        for (int p = 1; p < Database.Main.length("white") + 1; p++)
        {
            Player current = new Player(Database.Main.get(p, "name", "white"), 
            Integer.parseInt(Database.Main.get(p, "num", "white")), 
            Integer.parseInt(Database.Main.get(p, "pos", "white")), getTeam(3));
            addPlayer(current);
        }    
        for (int p = 1; p < Database.Main.length("blue") + 1; p++)
        {
            Player player = new Player(Database.Main.get(p, "name", "blue"), 
            Integer.parseInt(Database.Main.get(p, "num", "blue")), 
            Integer.parseInt(Database.Main.get(p, "pos", "blue")), getTeam(1));
            addPlayer(player);
        }
        for (int p = 1; p < Database.Main.length("red") + 1; p++)
        {
            Player player = new Player(Database.Main.get(p, "name", "red"),
            Integer.parseInt(Database.Main.get(p, "num", "red")),
            Integer.parseInt(Database.Main.get(p, "pos", "red")), getTeam(2));
            addPlayer(player);
        }
        for (int p = 1; p < Database.Main.length("black") + 1; p++)
        {
            Player player = new Player(Database.Main.get(p, "name", "black"), 
            Integer.parseInt(Database.Main.get(p, "num", "black")), 
            Integer.parseInt(Database.Main.get(p, "pos", "black")), getTeam(4));
            addPlayer(player);
        }
    }
    
    /**
     * Localises the teams from roster tables into the roster variable
     */
    public void initTeams() {
        for (Game game : allGames) {
            if (game.getTeam1().getID() == 1 || game.getTeam2().getID() == 1) {
                blue.addGame(game);
            }
            if (game.getTeam1().getID() == 2 || game.getTeam2().getID() == 2) {
                red.addGame(game);
            }
            if (game.getTeam1().getID() == 3 || game.getTeam2().getID() == 3) {
                white.addGame(game);
            }
            if (game.getTeam1().getID() == 4 || game.getTeam2().getID() == 4) {
                black.addGame(game);
            }
        }
        
        for (Player player : allPlayers) {
            switch (player.getTeam().getID()) {
                case 1:
                    blue.addPlayer(player);
                    break;
                case 2:
                    red.addPlayer(player);
                    break;
                case 3:
                    white.addPlayer(player);
                    break;
                case 4:
                    black.addPlayer(player);
                    break;
            }   
        }
        
        for (Team team : roster) {
            for (Player player : team.getPlayers()) {
                player.setGames(team.getGames());
            }
        }
    }
    
    /**
     * Getter method 
     * @param id teamID
     * @return the team by ID
     */
    public Team getTeam(int id)
    {
        
        switch (id) {
            case 1: 
                return blue;
            case 2:
                return red;
            case 3:
                return white;
            case 4:
                return black;
            default: 
                return blue;
        }
                
    }
    
    /**
     * Getter method
     * @param team name
     * @return team by teamName
     */
    public Team getTeamString(String team)
    {
        switch (team) {
            case "Blue":
                return blue;
            case "Red":
                return red;
            case "White":
                return white;
            case "Black":
                return black;
            default:
                return blue;
        }
    }
    
    /**
     * Localises all variables from game tables into allGames
     * @throws Exception 
     */
    public void initGames() throws Exception
    {
        ArrayList<String> finalGames = new ArrayList<>();
        
        for (int i = 0; i < Database.Main.getTables().size(); i++)
        {
            if (Database.Main.getTables().get(i).contains("_"))
            {
                finalGames.add(Database.Main.getTables().get(i));
            }
        }
        
        for (int i = 0; i < finalGames.size(); i++)
        {
            Game game;
            game = new Game(finalGames.get(i), 
            getTeamString(finalGames.get(i).substring(0, finalGames.get(i).indexOf("_"))), 
            getTeamString(finalGames.get(i).substring(finalGames.get(i).indexOf("_")+1, 
            finalGames.get(i).length()-1)));
            allGames.add(game);
        }
        
    }
    
    /**
     * Getter method 
     * @param num jersey number
     * @param team the team they play for
     * @return player
     */
    public Player getPlayer(int num, Team team)
    {
        Player ans = new Player("temp", 0, 0, team);
        
        for (Player player : team.getPlayers())
        {
            if (player.getNum() == num)
            {
                ans = player;
            }
        }
        
        return ans;
    }
    
}
