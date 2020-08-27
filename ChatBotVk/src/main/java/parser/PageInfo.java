package parser;

import org.eclipse.jetty.util.IO;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import steamAPI.SteamWork;

import java.io.IOException;
import java.util.Objects;

public class PageInfo {
    private static String title;
    private static final String linkSp = "https://steampay.com/game/";
    private static final String linkZz = "https://zaka-zaka.com/game/";
    private static final String linkSteam = "https://store.steampowered.com/app/";
    private static String steamNameApp = null;
    private static final String cssQuerySp = "div.product__current-price";
    private static final String cssQueryZz = "div.price";

    public PageInfo(String title) {
        this.title = title;
    }

    private static double getCostSteamApp(String nameApp) {
        String[] objects = SteamWork.getSteamAppCost(nameApp);

        if (objects[0] != null) {
            steamNameApp = objects[0];

            return Double.parseDouble(objects[1]);
        }
        else
            return -1;
    }

    private static double parserPage(String link, String cssQuery) {
        try {
            Document doc = Jsoup.connect(link)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();

            Elements listNews = doc.select(cssQuery);

            if (listNews.text().toLowerCase().contains("скоро"))
                return -2;

            return Double.parseDouble(listNews.text().split(" ")[0]);
        } catch (HttpStatusException q) {
            q.printStackTrace();
            if (q.getStatusCode() == 404)
                return -1;
        }
        catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String runSearch() {
        String name = title.substring(5).trim().replaceAll(" ", "-").toLowerCase();
        Double costSp = parserPage(linkZz + name,
                cssQueryZz);
        Double costZz = parserPage(linkSp + name,
                cssQuerySp);
        Double costSteam = getCostSteamApp(name);

        System.out.println("Cost " + costSp + " " + costZz + " " + costSteam);

        if (costSp == -1 && costZz == -1 && costSteam == -1)
            return "Я не нашел такую игру";
        else if (costSteam == -1) {
            return costZz == -1? linkSp + name: costZz.compareTo(costSp) < 0 ?
                    linkZz + name: linkSp + name;
        }
        else if (costZz == -1) {
            return costSteam == -1? linkSp + name: costSteam.compareTo(costSp) < 0 ?
                    linkSteam + steamNameApp: linkSp + name;
        }
        else if (costSp == -1) {
            return costSteam == -1? linkZz + name: costSteam.compareTo(costZz) < 0 ?
                    linkSteam + steamNameApp: linkZz + name;
        }
        else {
            return costSteam.compareTo(costSp) < 0 && costSteam.compareTo(costZz) < 0?
                    linkSteam + steamNameApp: costSp.compareTo(costZz) < 0?
                    linkSp + name: linkZz + name;
        }
    }
}
