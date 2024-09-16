import javax.swing.*;

public class Notification {

    private final JFrame mainFrame;

    public Notification(JFrame parentFrame) {
        this.mainFrame = parentFrame;
    }

    public void emptyTimerNotification () {
        SwingUtilities.invokeLater(() -> {
            JDialog dialog = new JOptionPane(
                    "Time to shutdown can't be 0. \n        Please select time",
                    JOptionPane.INFORMATION_MESSAGE
            ).createDialog("Notifocation");

            dialog.setLocationRelativeTo(mainFrame);
            dialog.setModal(false);
            dialog.setVisible(true);

            new Timer(3000, e -> dialog.dispose()).start();
        });
    }
}
