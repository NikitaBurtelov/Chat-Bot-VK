package game.hangman;

import java.sql.*;

public class GameDataBase {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static final String userName = "b5bb353745041b";
    private static final String password = "9a0fa107";
    private static final String connectionUrl = "jdbc:mysql://eu-cdbr-west-03.cleardb.net:3306/heroku_5f088a083235cc3";

    private static String[] setConnectionUrl(int number) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String query = "select question, words from wordshangman where id = ";
            String[] arrStr = new String[2];

            con = DriverManager.getConnection(connectionUrl, userName, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query + number);

            while (rs.next()) {
                arrStr[0] = rs.getString(1); //вопрос
                arrStr[1] = rs.getString(2); //word
                stmt.close();

                return arrStr;
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static String[] getWord(int number) {
        return setConnectionUrl(number);
    }
}
