package news.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class YandexNews {
    public static String runNews() throws IOException {
        StringBuilder str = new StringBuilder();
        Document doc = Jsoup.connect("https://yandex.ru/")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements listNews = doc.select("div.news__panel.mix-tabber-slide2__panel");

        for (Element element : listNews.select("a")) {
            str.append(element.text() + '\n');
        }

        return str.toString();
    }
    /*
    public static void main(String[] args) throws IOException {
        System.out.println(runNews());
    }
    */
}
