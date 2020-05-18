package connect;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.clients.User;
import com.petersamokhin.bots.sdk.objects.Message;
import handler.CheckMessage;

public class Connect {
    private static final int idGroup = 195134131;
    private static final String token = "token";

    public static void main(String[] args) {
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