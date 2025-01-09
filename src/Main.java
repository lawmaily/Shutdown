public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.fillFrame();

        ClockThread clockThread = new ClockThread();
        clockThread.startClock();
    }
}