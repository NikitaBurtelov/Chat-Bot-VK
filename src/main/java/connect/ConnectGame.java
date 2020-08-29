package connect;

import com.petersamokhin.bots.sdk.clients.User;
import com.petersamokhin.bots.sdk.objects.Message;
import game.hangman.Hangman;
import handler.CheckMessage;

public class ConnectGame {
    private static User user;
    private static Hangman hangman;

    ConnectGame(User user, Hangman hangman) {
        this.user = user;
        this.hangman = hangman;
    }

    public static void checkOnMessage() throws Exception {
        user.onMessage(message -> {
            String textMessage = message.getText();
            hangman.runGame(message, user);
        });
    }
}
