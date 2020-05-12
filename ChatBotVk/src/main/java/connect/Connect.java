package connect;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

public class Connect {
    private static final int idGroup = 195134131;
    private static final String token = "<token>";

    public static void main(String[] args) {
        Group group = new Group(idGroup, token);

        group.onSimpleTextMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("Иди ботай !!!!!")
                        .photo("target/classes/image/picture_2.jpg")
                        .send()
        );

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