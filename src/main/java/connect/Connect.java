package connect;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.clients.User;
import com.petersamokhin.bots.sdk.objects.Message;
import handler.CheckMessage;
import news.parser.YandexNews;
import parser.PageInfo;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Connect {
    private static final int idGroup = 195134131;
    private static final String token = "d9d8dd3f78413d15d0e443763f56d1ca1983196de290317b109e06e12565a1779e6224f3b72f612da5c1d";
    public static String str;
    public static boolean flag = false;

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

        user.onMessage(message -> {
            String textMessage = message.getText();

            if (new CheckMessage(textMessage).WelcomeCheck()) {
                new Message()
                        .from(user)
                        .to(message.authorId())
                        .text("Hello, user!")
                        .send();

            } else if (textMessage.equalsIgnoreCase("Пока")) {
                new Message()
                        .from(user)
                        .to(message.authorId())
                        .text("До скорого")
                        .send();
            }
            else if (textMessage.contains("/game")) {
                    new Message()
                            .from(user)
                            .to(message.authorId())
                            .text(new PageInfo(textMessage).runSearch())
                            .send();
            }
            else if (textMessage.contains("/go")) {
                new Message()
                        .from(user)
                        .to(message.authorId())
                        .text("/game title_name - возвращает ссылку на магазин, " +
                                "где самая низкая цена на ключ к игре")
                        .send();
            }
            else if (textMessage.contains("/news")) {
                try {
                    new Message()
                            .from(user)
                            .to(message.authorId())
                            .text(new YandexNews().runNews())
                            .send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                new Message()
                        .attachments()
                        .from(group)
                        .to(message.authorId())
                        .text(flag ? "Иди спать":"Иди ботай !!!!!")
                        .photo(flag ? "target/classes/image/picture_night.jpg"
                                :"target/classes/image/picture_2.jpg")
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