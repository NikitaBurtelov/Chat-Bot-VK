package connect;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.clients.User;
import com.petersamokhin.bots.sdk.objects.Message;
import handler.CheckMessage;
import news.parser.YandexNews;
import parser.PageInfo;

import java.io.IOException;

public class Connect {
    private static final int idGroup = 195134131;
    private static final String token = "1488";

    public static void main(String[] args) throws IOException {
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
                        .text("Иди ботай !!!!!")
                        .photo("target/classes/image/picture_2.jpg")
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
    }
}