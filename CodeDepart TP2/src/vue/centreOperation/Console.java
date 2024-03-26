package vue.centreOperation;

import javax.swing.*;
import java.awt.*;

public class Console {
    public static void main(String[] args) {
        cadreJframe();
    }

    public static void cadreJframe() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Full Screen Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel panel = new JPanel();
            panel.setBackground(Color.BLUE);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int panelWidth = (int) (screenSize.width * 0.25);
            int panelHeight = screenSize.height;
            panel.setPreferredSize(new Dimension(panelWidth, panelHeight));

            JPanel containerPanel = new JPanel(new BorderLayout());
            containerPanel.add(panel, BorderLayout.EAST);

            frame.add(containerPanel, BorderLayout.CENTER);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            gd.setFullScreenWindow(frame);

            frame.setVisible(true);
        });
    }
}
