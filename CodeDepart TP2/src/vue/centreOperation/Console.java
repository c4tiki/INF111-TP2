package vue.centreOperation;

import programme.ProgrammePrincipale;

import javax.swing.*;
import java.awt.*;

public class Console extends JPanel {
    public void console() {
        SwingUtilities.invokeLater(() -> {

            JPanel panel = new JPanel();
            panel.setBackground(Color.BLUE);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int panelWidth = (int) (screenSize.width * 0.25);
            int panelHeight = screenSize.height;
            panel.setPreferredSize(new Dimension(panelWidth, panelHeight));

            JPanel containerPanel = new JPanel(new BorderLayout());
            containerPanel.add(panel, BorderLayout.EAST);

            this.add(containerPanel, BorderLayout.CENTER);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();

            this.setVisible(true);
        });
    }
}
