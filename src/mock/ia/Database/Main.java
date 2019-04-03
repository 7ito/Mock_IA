package Database;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws Exception {
        //createTable("Blue_White1");
        //post("Blue_White1", "Justin Tom", 2, 1, 19.3, 2, 4, 1, 1, 0, 0, 0, 3, 1, 0, 1, 1, 0, 2, 3, 1);
        //post("Blue_Red1", "Bernard Ocran", 1, 1, 21.43, 14, 8, 7, 0, 0, 2, 0, 0, 3, 3, 0, 1, 0, 0, 1, 1);
        //post("Blue_Red1", "Justin Tom", 2, 1, 16.31, 4, 3, 1, 0, 0, 2, 2, 3, 4, 0, 4, 3, 0, 1, 1, 1);
        //post("Blue_Red1", "Josh Daydora", 21, 1, 13.47, 2, 3,0, 5, 0, 2, 2, 0, 2, 0, 2, 0, 0, 1, 2, 2);
        //get(1, "points", "game1");
        //getStatTeam(1, "points", "Blue_Red1");
        //post("Blue_Red1", "Charlie Grinnan", 7, 1, 17.7, 8, 6, 3, 0, 0, 2, 2, 2, 4, 4, 0, 0, 0, 1, 2, 2);
        //post("Blue_White1", "Alex Tan", 35, 1, 29.83, 9, 2, 1, 4, 2, 2, 1, 0, 1, 0, 1, 1, 0, 1, 2, 3);
        //post("Blue_White1", "Robbie Mellors", 17, 1, 35.35, 5, 3, 1, 2, 1, 0, 0, 0, 1, 0, 1, 0, 0, 2, 2, 3);
        //post("Blue_White1", "Bernard Ocran", 1, 1,12.58, 20, 15, 10, 1, 0, 6, 0, 0, 7, 3, 4, 1, 1, 2, 2, 1);
    }

    public static Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String ip = "jdbc:mysql://localhost:3306/Database";
            String username = "root";
            String pwd = "localkey";
            Class.forName(driver);

            Connection connection = DriverManager.getConnection(ip, username, pwd);

            return connection;

        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void createTable(String gameID) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + gameID +"(id int NOT NULL AUTO_INCREMENT, playerName varchar(255), playerNum int, started tinyint(1), minutes double, points int, 2pa int, 2pm int, 3pa int, 3pm int, fta int, ftm int, assists int, treb int, oreb int, dreb int, steal int, block int, turnovers int, fouls int, team int, PRIMARY KEY (id))");
            create.executeUpdate();

        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally {
            System.out.println("Function complete.");
        }
    }

    public static void post(String gameID, String playerName, int playerNum, int started, double minutes, int points, int twopa, int twopm, int threepa, int threepm, int fta, int ftm, int assists, int treb, int oreb, int dreb, int steals, int blocks, int turnovers, int fouls, int team) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement posted = connection.prepareStatement("INSERT INTO " + gameID + " (playerName, playerNum, started, minutes, points, 2pa, 2pm, 3pa, 3pm, fta, ftm, assists, treb, oreb, dreb, steal, block, turnovers, fouls, team) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            posted.setString(1, playerName);
            posted.setInt(2, playerNum);
            posted.setInt(3, started);
            posted.setDouble(4, minutes);
            posted.setInt(5, points);
            posted.setInt(6, twopa);
            posted.setInt(7, twopm);
            posted.setInt(8, threepa);
            posted.setInt(9, threepm);
            posted.setInt(10, fta);
            posted.setInt(11, ftm);
            posted.setInt(12, assists);
            posted.setInt(13, treb);
            posted.setInt(14, oreb);
            posted.setInt(15, dreb);
            posted.setInt(16, steals);
            posted.setInt(17, blocks);
            posted.setInt(18, turnovers);
            posted.setInt(19, fouls);
            posted.setInt(20, team);

            posted.executeUpdate();
        } catch(Exception e) {
            System.out.println(e);
        }
        finally {
            System.out.println("Insert complete. ");
        }

    }

    public static int length(String team) throws Exception
    {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM " + team + "Roster");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                System.out.println("Player count = " + result.getInt(1));
                return result.getInt(1);
            }
            return result.getInt(1);
        }
        catch(Exception e)
        {
            System.out.println(e);
            return 0;
        }
    }
    
    public static ArrayList<String> getTables() throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT table_name FROM information_schema.tables WHERE table_schema = 'Database';");
            
            ResultSet result = statement.executeQuery();
            ArrayList<String> output = new ArrayList<>();
            
            while(result.next()) {
                output.add(result.getString("table_name"));
            }
            return output;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
        
    }
    
    public static String get(int playerNum, String data, String teamName) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT " + data + " FROM " + teamName + "Roster WHERE id = " + playerNum + ";");

            ResultSet result = statement.executeQuery();

            String output = "";

            while(result.next()) {
                output = result.getString(1);
            }
            System.out.println(output);
            return output;

        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static int getStat(int playerNum, String stat, String gameID) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT " + stat + " FROM " + gameID + " WHERE playerNum = " + playerNum + ";");

            ResultSet result = statement.executeQuery();

            int output = 0;

            while(result.next()) {
                output = result.getInt(stat);
            }

            return output;
        } catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public static ArrayList<Integer> getStatTeam(int teamID, String stat, String gameID) throws Exception {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT " + stat + " FROM " + gameID + " WHERE team = " + teamID + ";");

            ResultSet result = statement.executeQuery();

            ArrayList<Integer> output = new ArrayList<>();

            while(result.next())
            {
                output.add(result.getInt(stat));
            }

            //System.out.println("All records have been selected. ");
            //System.out.println(output);
            return output;
        } catch(Exception e) {System.out.println(e);}
        return null;
    }


}
