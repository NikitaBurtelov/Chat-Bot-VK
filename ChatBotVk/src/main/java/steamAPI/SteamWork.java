package steamAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;
import java.sql.*;

public class SteamWork {
    private static final String url = "https://store.steampowered.com/search/?term=";

    private static String searchId (String title) {
        return null;
    }
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static final String userName = "root";
    private static final String password = "root";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/steamggg?useSSL=false";


    public static Document getAppList() {
        try {
            String urlSteamApps = "https://api.steampowered.com/ISteamApps/GetAppList/v2/";
            Document doc = Jsoup.connect(urlSteamApps)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .maxBodySize(0)
                    .ignoreContentType(true)
                    .get();

            return doc;
        }
        catch (IOException q) {
            q.printStackTrace();
            return null;
        }
    }
}
