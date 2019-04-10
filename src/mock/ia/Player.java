package mock.ia;

import java.util.ArrayList;
import java.util.Arrays;
import java.text.DecimalFormat;

public class Player
{
    private String name;
    private int num;
    private int position;
    private ArrayList<Game> games;
    private Team team;
    private Calculations calc;

    public Player(String name, int num, int position, Team team)
    {
        this.name = name;
        this.num = num;
        this.position = position;
        this.games = new ArrayList<>();
        this.team = team;
        calc = new Calculations();
    }

    public void setCalcParser(Parser parser)
    {
        calc.setParser(parser);
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public void setGames(ArrayList<Game> games)
    {
        this.games = games;
    }

    public void setTeam(Team team)
    {
        this.team = team;
    }

    public void addGame(Game game)
    {
        games.add(game);
    }

    public String getName()
    {
        return name;
    }

    public int getNum()
    {
        return num;
    }

    public int getPosition()
    {
        return position;
    }

    public ArrayList<Game> getGames()
    {
        return games;
    }

    public Team getTeam()
    {
        return team;
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
                total2PA = total2PA + game.getStat("2pa", num);
                total2PM = total2PM + game.getStat("2pm", num);
                total3PA = total3PA + game.getStat("3pa", num);
                total3PM = total3PM + game.getStatTeam("3pm" , num);
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
                total3PA = total3PA + game.getStat("3pa", num);
                total3PM = total3PM + game.getStatTeam("3pm" , num);
            }

            return total3PA/total3PM;
        }
        else if (stat.equals("FGA"))
        {
            double t2pa = 0;
            double t3pa = 0;

            for (Game game : games)
            {
                t2pa = t2pa + game.getStat("2pa", num);
                t3pa = t3pa + game.getStat("3pa", num);
            }

            return t3pa + t2pa;
        }
        else if (stat.equals("FGM"))
        {
            double t2pm = 0;
            double t3pm = 0;

            for (Game game: games)
            {
                t2pm = t2pm + game.getStat("2pm", num);
                t3pm = t3pm + game.getStat("3pm", num);
            }

            return t2pm + t3pm;
        }
        else
        {
            double count = 0;
            double total = 0;

            for (Game game : games)
            {
                total = total + game.getStat(stat, num);
                count++;
            }

            double avg = total/count;
            return avg;
        }

    }

    public String getPosString()
    {
        String pos = "";
        switch (getPosition())
        {
            case 1:
                pos = "PG";
                break;
            case 2:
                pos = "SG";
                break;
            case 3:
                pos = "SF";
                break;
            case 4:
                pos = "PF";
                break;
            case 5:
                pos = "C";
        }
        return pos;
    }

    public String[] averages() throws Exception
    {
        String[] output = new String[12];
        DecimalFormat value = new DecimalFormat("#.#");
        output[0] = getName();
        output[1] = getPosString();
        output[2] = "" + getNum();
        output[3] = "" + value.format(getStat("points"));
        output[4] = "" + value.format(getStat("assists"));
        output[5] = "" + value.format(getStat("treb"));
        output[6] = "" + value.format(getStat("oreb"));
        output[7] = "" + value.format(getStat("dreb"));
        output[8] = "" + value.format(getStat("minutes"));
        output[9] = "" + value.format(getStat("steal"));
        output[10] = "" + value.format(getStat("block"));
        output[11] = "" + value.format(getStat("turnovers"));

        System.out.println(Arrays.toString(output));
        return output;
    }

    public String[] advanced() throws Exception
    {
        String[] output = new String[10];
        DecimalFormat value = new DecimalFormat("#.#");
        
        output[0] = "" + value.format(calc.eFG(this));
        output[1] = "" + value.format(calc.TSG(this));
        output[2] = "" + value.format(calc.ORBP(this));
        output[3] = "" + value.format(calc.DRBP(this));
        output[4] = "" + value.format(calc.TRBP(this));
        output[5] = "" + value.format(calc.ASTP(this));
        output[6] = "" + value.format(calc.STLP(this));
        output[7] = "" + value.format(calc.BLKP(this));
        output[8] = "" + value.format(calc.TOVP(this));
        output[9] = "" + value.format(calc.USGR(this));
        
        System.out.println(Arrays.toString(output));
        return output;
    }
    
    public String[] advancedCompare() throws Exception
    {
        
        String[] output = new String[10];
        
        output[0] = "" + calc.leagueAVG(this, "eFG");
        output[1] = "" + calc.leagueAVG(this, "TSG");
        output[2] = "" + calc.leagueAVG(this, "ORBP");
        output[3] = "" + calc.leagueAVG(this, "DRBP");
        output[4] = "" + calc.leagueAVG(this, "TRBP");
        output[5] = "" + calc.leagueAVG(this, "ASTP");
        output[6] = "" + calc.leagueAVG(this, "STLP");
        output[7] = "" + calc.leagueAVG(this, "BLKP");
        output[8] = "" + calc.leagueAVG(this, "TOVP");
        output[9] = "" + calc.leagueAVG(this, "USGR");
        
        return output;
    }
    
    public String[] per36() throws Exception
    {
        String[] output = new String[9];
        DecimalFormat value = new DecimalFormat("#.#");
        
        output[0] = "" + value.format(calc.per36(this, "points"));
        output[1] = "" + value.format(calc.per36(this, "assists"));
        output[2] = "" + value.format(calc.per36(this, "treb"));
        output[3] = "" + value.format(calc.per36(this, "oreb"));
        output[4] = "" + value.format(calc.per36(this, "dreb"));
        output[5] = "" + value.format(calc.per36(this, "minutes"));
        output[6] = "" + value.format(calc.per36(this, "steal"));
        output[7] = "" + value.format(calc.per36(this, "block"));
        output[8] = "" + value.format(calc.per36(this, "turnovers"));
        
        return output;
        
    }

}
