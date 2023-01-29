package Time;

public class Time {
    public static void sleep(int sec) {
        try {
            Thread.sleep(sec* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
