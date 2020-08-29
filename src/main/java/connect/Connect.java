package connect;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.clients.User;
import com.petersamokhin.bots.sdk.objects.Message;
import game.hangman.Hangman;
import handler.CheckMessage;
import news.YandexNews;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parser.PageInfo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Connect {
    private static final int idGroup = 195134131;
    private static final String path = "src\\main\\resources\\data\\token.json";
    private static final String token = getJsonObject(path);
    public static Hangman hangman = null;
    public static ConnectGame connectGame = null;
    public static boolean flag = true;
    public static boolean gameFlag = false;

    private static String getJsonObject(String filePath) {
        try {
            return ((JSONObject) (new JSONParser()).parse(new FileReader(filePath))).get("token").toString();

        } catch (IOException | ParseException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return null;
        }
    }

    private static void dataCheckRun() throws Exception {
        while (true) {
            // в отдельном потоке
            Callable<Boolean> callable = new DataCheckThread(new Connect());
            TimeUnit.SECONDS.sleep(10);
            flag = DataCheckThread.run(callable);
        }
    }

    public static void main(String[] args) throws Exception {
        Group group = new Group(idGroup, token);
        User user = new User(token);

        if (gameFlag) {
            connectGame.checkOnMessage();
        } else {
            user.onMessage(message -> {
                String textMessage = message.getText();

                if (new CheckMessage(textMessage).WelcomeCheck()) {
                    new Message()
                            .from(user)
                            .to(message.authorId())
                            .text("Hello, user!")
                            .send();
                } else if (textMessage.equalsIgnoreCase("/hangman")) {
                    if (hangman == null) {
                        System.out.println("run hm");
                        hangman = new Hangman();
                        hangman.randomWord();
                        gameFlag = true;
                    }
                    if (connectGame == null)
                        connectGame = new ConnectGame(user, hangman);

                } else if (textMessage.equalsIgnoreCase("Пока")) {
                    new Message()
                            .from(user)
                            .to(message.authorId())
                            .text("До скорого")
                            .send();
                } else if (textMessage.contains("/game")) {
                    new Message()
                            .from(user)
                            .to(message.authorId())
                            .text(new PageInfo(textMessage).runSearch())
                            .send();
                } else if (textMessage.contains("/go")) {
                    new Message()
                            .from(user)
                            .to(message.authorId())
                            .text("/game title_name - возвращает ссылку на магазин, " +
                                    "где самая низкая цена на ключ к игре")
                            .send();
                } else if (textMessage.contains("/news")) {
                    try {
                        new Message()
                                .from(user)
                                .to(message.authorId())
                                .text(new YandexNews().runNews())
                                .send();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (gameFlag) {
                    new Message()
                            .attachments()
                            .from(group)
                            .to(message.authorId())
                            .text(flag ? "Иди спать" : "Иди ботай !!!!!")
                            .photo(flag ? "target/classes/image/picture_night.jpg"
                                    : "target/classes/image/picture_2.jpg")
                            .send();
                }
            });

            group.onPhotoMessage(message ->
                    new Message()
                            .from(group)
                            .to(message.authorId())
                            .text("Ну и зачем мне твои нужны твои картинки ???")
                            .send()
            );

            group.onStickerMessage(message ->
                    new Message()
                            .from(group)
                            .to(message.authorId())
                            .text("Иииии ????")
                            .send()
            );

            dataCheckRun();
        }
    }
}