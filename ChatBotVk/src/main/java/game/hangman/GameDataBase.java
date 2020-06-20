package game.hangman;

import java.sql.*;

public class GameDataBase {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static String userName = "";
    private static String password = "";
    private static String connectionUrl = "jdbc:mysql://eu-cdbr-west-03.cleardb.net:3306/heroku_5f088a083235cc3";

    public GameDataBase(String userName, String password, String connectionUrl) {
        this.userName = userName;
        this.password = password;
        this.connectionUrl = connectionUrl;
    }

    private static void setConnectionUrl() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String query = "select id, words from wordshangman";
            con = DriverManager.getConnection(connectionUrl, userName, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(1);
                String word = rs.getString(2);

                System.out.println(id + " " + word);
        }
        stmt.close();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setConnectionUrl();
    }

}
