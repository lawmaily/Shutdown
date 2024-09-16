import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimerThread implements Runnable {

    private Thread thread;
    private volatile boolean running = true;
    private volatile boolean paused = false;

    static private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void run() {
        while (running) {
            synchronized (this) {
                try {
                    int totalSeconds = Frame.time;
                    while (totalSeconds >= 0 && !paused) {
                        LocalTime time = LocalTime.of(totalSeconds/3600, totalSeconds/60%60, totalSeconds%60);
                        String timeFormatted = time.format(formatter);
                        Frame.timer.setText(timeFormatted);
                        Thread.sleep(1000);
                        totalSeconds--;
                    }
                } catch (InterruptedException e) {
                    System.out.println("Timer interrupted while sleep");
                }

                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void startThread() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this::run);
            thread.start();
        } else {
            pause();
            resume();
        }
    }

    public void pause() {
        paused = true;
    }

    public synchronized void resume() {
        paused = false;
        notify();
    }

    public synchronized void stopTimer() {
        paused = true;
        running = false;
    }

}
