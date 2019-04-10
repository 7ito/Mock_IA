package mock.ia;

import java.util.ArrayList;

public class GUI
{
    
    public static void main(String[] args) throws Exception {
        Parser parser = new Parser();
        parser.init();
        for (Player player : parser.getAllPlayers())
        {
            player.setCalcParser(parser);
        }
        GUIForm GUI = new GUIForm();
        Blue blue = new Blue(parser);
        blue.setGUI(GUI);
        Red red = new Red(parser);
        red.setGUI(GUI);
        White white = new White(parser);
        white.setGUI(GUI);
        Black black = new Black(parser);
        black.setGUI(GUI);
        SearchParser search = new SearchParser(parser);
        search.setGUI(GUI);
        InputGame input = new InputGame(GUI);
        GUI.setInputGame(input);
        GUI.setSearcher(search);
        GUI.setBlue(blue);
        GUI.setRed(red);
        GUI.setWhite(white);
        GUI.setBlack(black);
        GUI.setVisible(true);
        
        
        
    }
}
