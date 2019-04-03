package mock.ia;

import java.util.ArrayList;

public class GUI
{

    private static ArrayList<Player> allPlayers;
    private static ArrayList<Team> roster;

    

            
    
    
    
    public static void main(String[] args) throws Exception {
   
        allPlayers = new ArrayList<>();
        roster = new ArrayList<>();
        
        Team blue = new Team("Blue Army", 1);
        Team red = new Team("Red Army", 2);
        Team white = new Team("White Army", 3);
        Team black = new Team("Black Army", 4);
        roster.add(blue);
        roster.add(red);
        roster.add(white);
        roster.add(black);

        Game game = new Game("Blue_Red1", blue, red);
        Game game2 = new Game("Blue_White1", blue, white);


        blue.addGame(game);
        blue.addGame(game2);


        Player Tito = new Player("Justin Tom", 2, 1, blue);
        Tito.addGame(game);
        Tito.addGame(game2);

        Player Sean = new Player("Sean Inciong", 3, 1, blue);
        Sean.addGame(game);
        Sean.addGame(game2);

        allPlayers.add(Tito);
        allPlayers.add(Sean);
        
        Calculations calculator = new Calculations();
        calculator.setAllPlayers(allPlayers);


        calculator.PPG(blue);
        calculator.oppPPG(blue);
        calculator.pace(blue);
        calculator.eFG(Tito);
        calculator.TSG(Tito);
        calculator.STLP(Tito);
        calculator.TOVP(Tito);
        calculator.ASTP(Tito);
        calculator.BLKP(Tito);
        calculator.TRBP(Tito);
        calculator.USGR(Tito);
        calculator.leagueAVG(Tito, "points");
    }
    
   
  
    
    
    
}
