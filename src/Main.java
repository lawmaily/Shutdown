import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    static JFrame frame = getFrame();
    static JPanel jPanel = new JPanel();

    public static void main(String[] args) {

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

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = HoursBox.getSelectedIndex() * 3600 + MinutesBox.getSelectedIndex() * 60;
                try {
                    Runtime.getRuntime().exec("shutdown /s /t " + time + " /f");
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
                    Runtime.getRuntime().exec("shutdown /a ");
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        });
        jPanel.add(cancelButton).setBounds(370, 265, 100, 40);

        ClockThread clock = new ClockThread();
        clock.start();
        
        jPanel.revalidate();
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
        frame.setBounds(dimension.width/2 - 250, dimension.height/2 - 150, 600, 350);
        frame.setResizable(false);

        return frame;
    }
}