package connect;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

public class Connect {
    private static final int idGroup = 195134131;
    private static final String token = "idi_.....(очень далеко)";

    public static void main(String[] args) {
        Group group = new Group(idGroup, token);
        group.onSimpleTextMessage(message ->
                new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Пообщаемся???")
                    .send()
        );
    }
}
