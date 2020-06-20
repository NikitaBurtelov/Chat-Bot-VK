package game.hangman;

import com.petersamokhin.bots.sdk.clients.User;
import com.petersamokhin.bots.sdk.objects.Message;
import handler.CheckMessage;

import java.util.Random;

public class Hangman {
    public static String word;
    public static String question;
    public static byte attempt = 6;
    public static int lenWorld;
    private static User user;

    Hangman(User user) {
        this.user = user;
    }
    //получаем рандомное слово из бд
    public static void randomWord() {
        Random random = new Random();
        int num = random.nextInt(2);
        String[] arrStr = GameDataBase.getWord(num);
        question = arrStr[0];
        word = arrStr[1];
        lenWorld = word.length();
    }

    public static boolean checkLetter(String letter) {
        int lenNow;

        word = word.replaceAll(letter, "");
        lenNow = word.length();

        if (lenWorld == lenNow) {
            attempt--;
            return false;
        }
        else {
            lenWorld = lenNow != 0? lenNow: -1;
            return true;
        }
    }

    public static boolean runGame() {
        randomWord(); //...--> word, lenWord
        user.onMessage(message -> {
            String textMessage = message.getText();
            Message gameMessage = new Message();

            gameMessage
                    .from(user)
                    .to(message.authorId())
                    .text(checkLetter(textMessage)?"Угадал":"Не угадал")
                    .send();

            if (attempt == 0) {
                gameMessage
                        .from(user)
                        .to(message.authorId())
                        .text("Проиграл")
                        .send();
            }
            else if (lenWorld == -1) {
                gameMessage
                        .from(user)
                        .to(message.authorId())
                        .text("Победа, вот слово - " + word)
                        .send();
            }
        });

        return false;
    }
}
