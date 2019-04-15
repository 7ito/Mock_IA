package mock.ia;

import java.text.DecimalFormat;
import java.util.ArrayList;
    
    public class Team
    {
        private String name;
        private int id;
        private ArrayList<Player> players;
        private ArrayList<Game> games;
    
        /**
         * Initialises the class object
         * @param name name of the team
         * @param id of the team
         */
        public Team(String name, int id)
        {
            this.name = name;
            this.id = id;
            players = new ArrayList<>();
            games = new ArrayList<>();
        }
    
        /**
         * Accessor method
         * @return name
         */
        public String getName()
        {
            return name;
        }
    
        /**
         * Accessor method
         * @return team ID
         */
        public int getID()
        {
            return id;
        }
    
        /**
         * Accessor method
         * @return ArrayList players
         */
        public ArrayList<Player> getPlayers()
        {
            return players;
        }
    
        /**
         * Accessor method
         * @return ArrayList games
         */
        public ArrayList<Game> getGames()
        {
            return games;
        }
    
        /**
         * Setter method for name
         * @param name 
         */
        public void setName(String name)
        {
            this.name = name;
        }
    
        /**
         * Setter method for id
         * @param id 
         */
        public void setID(int id)
        {
            this.id = id;
        }
    
        /**
         * Setter method for players
         * @param players 
         */
        public void setPlayers(ArrayList<Player> players)
        {
            this.players = players;
        }
    
        /**
         * Setter method for games
         * @param games 
         */
        public void setGames(ArrayList<Game> games)
        {
            this.games = games;
        }
    
        /**
         * Method that uses ArrayList .add to modify games
         * @param game to add
         */
        public void addGame(Game game)
        {
            games.add(game);
        }
        
        /**
         * Method that uses ArrayList.add to modify players
         * @param player to add
         */
        public void addPlayer(Player player)
        {
            players.add(player);
        }
    
        /**
         * Gets a statistic
         * @param stat the statistic selected
         * @return the average of that statistic throughout the team's games
         * @throws Exception 
         */
        public double getStat(String stat) throws Exception
        {
    
            if (stat.equals("fg%"))
            {
                double total2PA = 0;
                double total2PM = 0;
                double total3PA = 0;
                double total3PM = 0;
    
                for (Game game: games)
                {
                    total2PA = total2PA + game.getStatTeam("2pa", id);
                    total2PM = total2PM + game.getStatTeam("2pm", id);
                    total3PA = total3PA + game.getStatTeam("3pa", id);
                    total3PM = total3PM + game.getStatTeam("3pm" , id);
                }
    
                double totalFGA = total2PA + total3PA;
                double totalFGM = total2PM + total3PM;
    
                return totalFGA/totalFGM;
            }
            else if (stat.equals("3p%"))
            {
                double total3PA = 0;
                double total3PM = 0;
    
                for (Game game: games)
                {
                    total3PA = total3PA + game.getStatTeam("3pa", id);
                    total3PM = total3PM + game.getStatTeam("3pm" , id);
                }
    
                return total3PA/total3PM;
            }
            else if (stat.equals("FGA"))
            {
                double t2pa = 0;
                double t3pa = 0;
    
                for (Game game : games)
                {
                    t2pa = t2pa + game.getStatTeam("2pa", id);
                    t3pa = t3pa + game.getStatTeam("3pa", id);
                }
    
                return t3pa + t2pa;
            }
            else if (stat.equals("FGM"))
            {
                double t2pm = 0;
                double t3pm = 0;
    
                for (Game game: games)
                {
                    t2pm = t2pm + game.getStatTeam("2pm", id);
                    t3pm = t3pm + game.getStatTeam("3pm", id);
                }
    
                return t2pm + t3pm;
            }
            else
            {
                double count = 0;
                double total = 0;
    
                for (Game game : games)
                {
                    total = total + game.getStatTeam(stat, id);
                    count++;
                }
    
                double avg = total/count;
                System.out.println(avg);
                return avg;
            }
    
        }
    
        public double getStatGame(String stat, Game game) throws Exception
        {
            if (stat.equals("fg%"))
            {
                double total2PA = game.getStatTeam("2pa", id);
                double total2PM = game.getStatTeam("2pm", id);
                double total3PA = game.getStatTeam("3pa", id);
                double total3PM = game.getStatTeam("3pm", id);
    
                double totalFGA = total2PA + total3PA;
                double totalFGM = total2PM + total3PM;
    
                return totalFGM/totalFGA;
            }
            else if (stat.equals("3p%"))
            {
                return game.getStatTeam("3pm", id)/game.getStatTeam("3pa", id);
            }
            else if (stat.equals("FGA"))
            {
                return game.getStatTeam("2pa", id) + game.getStatTeam("3pa", id);
            }
            else if (stat.equals("FGM"))
            {
                return game.getStatTeam("2pm", id) + game.getStatTeam("2pa", id);
            }
            else
            {
                return game.getStatTeam(stat, id);
            }
        }
    
        /**
         * Returns the average possessions of a team, used in the Calculations class
         * @return the average amount of possessions
         * @throws Exception 
         */
        public double avgPoss() throws Exception
        {
    
            double count = 0;
            double total = 0;
    
            for (Game game : games)
            {
                total = total + game.getPossesions(id);
                count++;
            }
    
            total = total/count;
    
            System.out.println(total);
            return total;
        }
    
        /**
         * Returns the average amount of possessions of the opponent, used in the Calculations class
         * @return the average amount of possessions by the opponent
         * @throws Exception 
         */
        public double oppAvgPoss() throws Exception
        {
            double count = 0;
            double total = 0;
    
            for (Game game: games)
            {
                total = total + game.getPossesions(game.getOppTeam(id).getID());
                count++;
            }
    
            total = total/count;
    
            System.out.println(total);
            return total;
        }
    
        /**
         * Returns an array of the advanced statistics of a team used in the GUI class to display the values
         * @return an array of statistics
         * @throws Exception  
         */
        public String[] average() throws Exception
        {
            String[] output = new String[5];
            DecimalFormat value = new DecimalFormat("#.#");
            Calculations calculator = new Calculations();
            output[0] = "" + value.format(calculator.PPG(this));
            output[1] = "" + value.format(calculator.oppPPG(this));
            output[2] = "" + value.format(calculator.pace(this));
            output[3] = "" + value.format(calculator.ORTG(this));
            output[4] = "" + value.format(calculator.DRTG(this));
            return output;
    
        }
    
    
    }
    
    
