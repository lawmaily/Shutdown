import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockThread extends Thread{

    private volatile DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    @Override
    public  void run() {
        JLabel clock = new JLabel();
        Font clockFont = new Font("Serif", Font.BOLD, 110);
        Font clockFontFormat = new Font("Serif", Font.BOLD, 95);
        clock.setFont(clockFont);

        Main.jPanel.add(clock).setBounds(80, 20, 500, 120);

        Main.jPanel.add(new JLabel("am/pm format: ")).setBounds(410, 155, 110, 25);
        JCheckBox timeFormateBox = new JCheckBox();

        Main.jPanel.add(timeFormateBox).setBounds(500, 155, 40, 25);
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
        while (true) {
            LocalTime now = LocalTime.now();
            String time = now.format(formatter).toLowerCase();
            clock.setText(time);

            Main.jPanel.updateUI();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
