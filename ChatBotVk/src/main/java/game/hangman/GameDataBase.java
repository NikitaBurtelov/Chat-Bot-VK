package game.hangman;

import java.sql.*;

public class GameDataBase {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    String userName;
    String password;
    String connectionUrl;

    public GameDataBase(String userName, String password, String connectionUrl) {
        this.userName = userName;
        this.password = password;
        this.connectionUrl = connectionUrl;
    }

    private void setConnectionUrl() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String query = "select id, words from wordshangman";

        while (rs.next()) {
            int id = rs.getInt(1);
            String word = rs.getString(2);

            System.out.println(id + " " + word);
        }

        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement statement = connection.createStatement()) {

            statement.close();
        }

    }

}
