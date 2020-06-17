package game.hangman;

import java.util.Random;

public class Hangman {
    public static String word = "джава";
    public static byte attempt = 6;
    public static int lenWorld;

    //получаем рандомное слово из бд
    public static void randomWord() {
        Random random = new Random();
        int num = random.nextInt(100);
        //word;
        //lenWorld;
    }

    public static boolean checkLetter(String letter) {
        int lenNow;
        while (attempt > 0) {
            word = word.replaceAll(letter, "");
            lenNow = word.length();
            if (lenWorld == lenNow) {
                attempt--;
            }
            else if (lenNow == 0)
                return true;
            else
                lenWorld = lenNow;
        }
        return false;
    }
}
