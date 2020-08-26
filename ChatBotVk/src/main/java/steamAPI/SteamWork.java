package steamAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.sql.*;
import java.util.*;

public class SteamWork {
    private static final String url = "https://store.steampowered.com/app/";
    private static final String userName = "root";
    private static final String password = "root";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/steamggg?useSSL=false";

    private static Document getAppList() {
        try {
            String urlSteamApps = "https://api.steampowered.com/ISteamApps/GetAppList/v2/";

            return Jsoup.connect(urlSteamApps)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .maxBodySize(0)
                    .ignoreContentType(true)
                    .get();
        }
        catch (IOException q) {
            q.printStackTrace();
            return null;
        }
    }

    private static String getIdApp(String name) {
        try {
            String query = "SELECT GameId FROM steamidtable WHERE Name=?";
            Connection con = DriverManager.getConnection(connectionUrl, userName, password);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getString(1);

            return null;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
    }
        return null;
    }

    private static String getCost(String id) {
        try {
            String cost = "";
            Document doc = Jsoup.connect(url.concat(id))
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();

            try {
                cost = doc.select("div.game_purchase_price.price[data-price-final]").first().text();
            }
            catch (NullPointerException exception) {
                cost = doc.select("div.discount_final_price").first().text();
            }

            return cost.isEmpty() ? null : cost;

        } catch (IOException | NullPointerException exception ) {
            exception.printStackTrace();
        }
        return null;
    }

    public static String[] getSteamAppCost(String nameApp) {
        String[] objects = new String[2]; //null, null
        String id = getIdApp(nameApp);

        if (id != null) {
            objects[0] = id;
            String str = getCost(id).replace(",", ".");
            objects[1] = str.substring(0, str.length() - 4);

            return objects;
        }
        return null;
    }
}
