import java.time.LocalTime;

public class ClockThread implements Runnable{

    private Thread thread;
    private volatile boolean running = true;
    private volatile boolean paused = false;

    @Override
    public  void run() {

        while (running) {
            synchronized (this) {
                while (!paused) {
                    LocalTime now = LocalTime.now();
                    String time = now.format(Frame.formatter).toLowerCase();
                    Frame.clock.setText(time);

                    Frame.jPanel.updateUI();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void startClock() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this::run);
            thread.start();
        } else {
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

}