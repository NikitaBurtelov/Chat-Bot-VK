package game.hangman;

import com.petersamokhin.bots.sdk.clients.User;
import com.petersamokhin.bots.sdk.objects.Message;
import handler.CheckMessage;

import java.util.Random;

public class Hangman {
    public static String word;
    public static String question;
    public static String message;
    public static byte attempt = 6;
    public static int lenWorld;
    private static User user;

    //public Hangman(User user) {
      //  this.user = user;
    //}
    //получаем рандомное слово из бд
    public static void randomWord() {
        Random random = new Random();
        int num = random.nextInt(1) + 1;
        System.out.println(num);
        String[] arrStr = GameDataBase.getWord(num);
        question = arrStr[0];
        word = arrStr[1];
        lenWorld = word.length();
        System.out.println(word + " " + question);
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

    public static void test(Message messag) {
        new Message()
                .from(user)
                .to(messag.authorId())
                .text("test hangman")
                .send();
        System.out.println("run");
    }

    public static boolean runGame(Message message, User user) {
        new Message()
                .from(user)
                .to(message.authorId())
                .text(checkLetter(message.getText())?"Угадал":"Не угадал")
                .send();

        if (attempt == 0) {
            new Message()
                    .from(user)
                    .to(message.authorId())
                    .text("Проиграл")
                    .send();
        }
        else if (lenWorld == -1) {
            new Message()
                    .from(user)
                    .to(message.authorId())
                    .text("Победа, вот слово - " + word)
                    .send();
        }

        new Message()
                .from(user)
                .to(message.authorId())
                .text(";;;;;;;;;;;;;;;;;;;;;;;")
                .send();

        return false;
    }
}
