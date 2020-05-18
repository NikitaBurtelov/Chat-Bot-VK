package connect;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.clients.User;
import com.petersamokhin.bots.sdk.objects.Message;
import handler.CheckMessage;

public class Connect {
    private static final int idGroup = 195134131;
    private static final String token = "d9d8dd3f78413d15d0e443763f56d1ca1983196de290317b109e06e12565a1779e6224f3b72f612da5c1d";

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