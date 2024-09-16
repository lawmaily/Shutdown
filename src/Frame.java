import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class Frame {

    static JFrame frame = getFrame();
    static JPanel jPanel = new JPanel();
    static JLabel clock = new JLabel();
    static JLabel timer = new JLabel();

    static ClockThread clockThread = new ClockThread();
    static TimerThread timerThread = new TimerThread();
    static Notification notification = new Notification(frame);

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static int time = 0;

    public void fillFrame() {
        mainFrame();
        clockFrame();
        timerFrame();

        jPanel.revalidate();
    }

    static void mainFrame(){
        frame.add(jPanel);
        jPanel.setLayout(null);

        JComboBox<Integer> HoursBox = new JComboBox<>();
        for (int i = 0; i < 24; i++) {
            HoursBox.addItem(i);
        }
        jPanel.add(new JLabel("Time to shutdown:                :                after h/m.")).setBounds(13,155, 300,25);
        jPanel.add(HoursBox).setBounds(120, 155, 40,25);

        JComboBox<Integer> MinutesBox = new JComboBox<>();
        for (int i = 0; i < 60; i++) {
            MinutesBox.addItem(i);
        }
        jPanel.add(MinutesBox).setBounds(174, 155, 40,25);

        JButton submitButton = new JButton("Start");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hours = HoursBox.getSelectedIndex();
                int minutes = MinutesBox.getSelectedIndex();
                time = hours * 3600 + minutes * 60;
                try {
                    if (time != 0) {
                        timerThread.startThread();
                        timer.setBounds(80, 20, 500, 120);
                    Runtime.getRuntime().exec("shutdown /s /t " + time + " /f");
                    } else {
                        notification.emptyTimerNotification();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        jPanel.add(submitButton).setBounds(475, 265, 100, 40);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    timerThread.pause();
                    timer.setBounds(60, 20, 500, 120);
                    timer.setText("Timer off");
                    Runtime.getRuntime().exec("shutdown /a ");
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        });
        jPanel.add(cancelButton).setBounds(370, 265, 100, 40);
    }

    static void clockFrame(){
        Font clockFont = new Font("Serif", Font.BOLD, 110);
        Font clockFontFormat = new Font("Serif", Font.BOLD, 95);

        clock.setFont(clockFont);
        Frame.jPanel.add(clock).setBounds(80, 20, 500, 120);

        Frame.jPanel.add(new JLabel("am/pm format: ")).setBounds(410, 155, 110, 25);
        JCheckBox timeFormateBox = new JCheckBox();
        Frame.jPanel.add(timeFormateBox).setBounds(500, 155, 40, 25);
        timeFormateBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeFormateBox.isSelected()) {
                    formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
                    clock.setFont(clockFontFormat);
                    clock.setBounds(40, 20, 550, 120);
                } else {
                    formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    clock.setFont(clockFont);
                    clock.setBounds(80, 20, 500, 120);
                }
            }
        });
    }

    static void timerFrame() {
        Font timerFont = new Font("Serif", Font.BOLD, 110);
        timer.setFont(timerFont);
        Frame.jPanel.add(timer).setBounds(60, 20, 500, 120);

        Frame.jPanel.add(new JLabel("Timer: ")).setBounds(455, 175, 110, 25);
        JCheckBox timerCheckBox = new JCheckBox();
        Frame.timer.setFont(timerFont);
        Frame.timer.setForeground(Color.red);
        Frame.timer.setVisible(false);
        Frame.jPanel.add(timerCheckBox).setBounds(500, 175, 40, 25);

        Frame.timer.setText("Timer off");

        timerCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timerCheckBox.isSelected()) {
                    Frame.clock.setVisible(false);
                    Frame.timer.setVisible(true);
                } else {
                    Frame.timer.setVisible(false);
                    Frame.clock.setVisible(true);
                }
            }
        });
    }

    static JFrame getFrame(){

        JFrame frame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setTitle("Shut.down");
        ImageIcon icon = new ImageIcon("Img/Icon.jpg");
        frame.setIconImage(icon.getImage());
        frame.setLocationRelativeTo(null);
        frame.setBounds(dimension.width/2 - 250, dimension.height/2 - 150, 600, 350);
        frame.setResizable(false);

        return frame;
    }

}
