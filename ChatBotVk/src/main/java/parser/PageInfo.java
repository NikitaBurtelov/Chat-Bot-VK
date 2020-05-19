package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PageInfo {
    private static String title;
    private static final String linkSp = "https://steampay.com/game/";
    private static final String linkZz = "https://zaka-zaka.com/game/";
    private static final String cssQuerySp = "div.product__current-price";
    private static final String cssQueryZz = "div.price";

    PageInfo(String title) {
        this.title = title;
    }

    private static double parserPage(String link, String cssQuery) throws IOException {
        Document doc = Jsoup.connect(link)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements listNews = doc.select(cssQuery);

        System.out.println(Double.parseDouble(listNews.text().split(" ")[0]));

        return Double.parseDouble(listNews.text().split(" ")[0]);
    }

    public static void runSearch() throws IOException {
        HashMap<Integer, String> data = new HashMap<>();

        parserPage(linkZz + title.trim().replaceAll(" ", "-").toLowerCase(),
                cssQueryZz);
        parserPage(linkSp + title.trim().replaceAll(" ", "-").toLowerCase(),
                cssQuerySp);
    }



    public static void main(String[] args) throws IOException {
        title = "total war warhammer";
        runSearch();
    }
}
