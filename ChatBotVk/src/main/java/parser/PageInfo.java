package parser;

import org.eclipse.jetty.util.IO;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class PageInfo {
    private static String title;
    private static final String linkSp = "https://steampay.com/game/";
    private static final String linkZz = "https://zaka-zaka.com/game/";
    private static final String cssQuerySp = "div.product__current-price";
    private static final String cssQueryZz = "div.price";

    public PageInfo(String title) {
        this.title = title;
    }

    private static double parserPage(String link, String cssQuery) {
        try {
            Document doc = Jsoup.connect(link)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();

            Elements listNews = doc.select(cssQuery);

            System.out.println(listNews.text());

            if (listNews.text().toLowerCase().contains("скоро"))
                return -1;

            return Double.parseDouble(listNews.text().split(" ")[0]);
        } catch (HttpStatusException q) {
            q.printStackTrace();
            if (q.getStatusCode() == 404)
                return 0;
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String runSearch() {
        String name = title.substring(5).trim().replaceAll(" ", "-").toLowerCase();
        Double costSp = parserPage(linkZz + name,
                cssQueryZz);
        Double costZz = parserPage(linkSp + name,
                cssQuerySp);
        System.out.println(costSp.compareTo(costZz) >= 0? linkZz + name: linkSp + name);

        if (costSp == -1 && costZz == -1)
            return "Скоро в наличии" + linkSp + name;
        else if (costSp == 0 && costZz == 0)
            return "Я не нашел такую игру";

        return costSp.compareTo(costZz) >= 0? linkZz + name: linkSp + name;
    }
    /*
    public static void main(String[] args) {
        title = "/game total-war-attilaa";
        runSearch();
    }*/
}
