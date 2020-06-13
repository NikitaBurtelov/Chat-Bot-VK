package connect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class DataCheckThread implements Callable{
    private Connect param;
    final int gmt = 3; //часовой пояс

    public DataCheckThread(Connect connect) {
        this.param = connect;
    }
    public Object call() throws InterruptedException {
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            return checkData();
        }
    }

    public boolean checkData() {
        Date dateNow = new Date();
        String formatForDateNow = new SimpleDateFormat("HH").format(dateNow);
        int data = Integer.parseInt(formatForDateNow) + gmt;
        //System.out.println("Текущая дата " + formatForDateNow.format(dateNow));
        System.out.println(data);

        if (data >= 23 || data < 5) {
            System.out.println(data);
            return true;
        }
        else
            return false;
    }

    public static boolean run(Callable<Boolean> callable) throws Exception {
        return callable.call();
    }
}