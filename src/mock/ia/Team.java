package mock.ia;

import java.util.ArrayList;
    
    public class Team
    {
        private String name;
        private int id;
        private ArrayList<Player> players;
        private ArrayList<Game> games;
    
        public Team(String name, int id)
        {
            this.name = name;
            this.id = id;
            players = new ArrayList<>();
            games = new ArrayList<>();
        }
    
        public String getName()
        {
            return name;
        }
    
        public int getID()
        {
            return id;
        }
    
        public ArrayList<Player> getPlayers()
        {
            return players;
        }
    
        public ArrayList<Game> getGames()
        {
            return games;
        }
    
        public void setName(String name)
        {
            this.name = name;
        }
    
        public void setID(int id)
        {
            this.id = id;
        }
    
        public void setPlayers(ArrayList<Player> players)
        {
            this.players = players;
        }
    
        public void setGames(ArrayList<Game> games)
        {
            this.games = games;
        }
    
        public void addGame(Game game)
        {
            games.add(game);
        }
        
        public void addPlayer(Player player)
        {
            players.add(player);
        }
    
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
                    total2PM = total2PM + game.getStat("2pm", id);
                    total3PA = total3PA + game.getStat("3pa", id);
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
                return game.getStatTeam("2pa", id) + game.getStat("3pa", id);
            }
            else if (stat.equals("FGM"))
            {
                return game.getStat("2pm", id) + game.getStat("2pa", id);
            }
            else
            {
                return game.getStatTeam(stat, id);
            }
        }
    
        public double avgPoss() throws Exception
        {
    
            double count = 0;
            double total = 0;
    
            for (Game game : games)
            {
                total = game.getPossesions(id);
                count++;
            }
    
            total = total/count;
    
            System.out.println(total);
            return total;
        }
    
        public double oppAvgPoss() throws Exception
        {
            double count = 0;
            double total = 0;
    
            for (Game game: games)
            {
                total = game.getPossesions(game.getOppTeam(id).getID());
                count++;
            }
    
            total = total/count;
    
            System.out.println(total);
            return total;
        }
    
        public String[] average() throws Exception
        {
            String[] output = new String[5];
            Calculations calculator = new Calculations();
            output[0] = "" + calculator.PPG(this);
            output[1] = "" + calculator.oppPPG(this);
            output[2] = "" + calculator.pace(this);
            output[3] = "" + calculator.ORTG(this);
            output[4] = "" + calculator.DRTG(this);
            return output;
    
        }
    
    
    }
    
    
