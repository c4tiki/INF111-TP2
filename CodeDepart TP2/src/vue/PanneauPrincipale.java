package vue;

import javax.swing.*;
import java.awt.*;

public class PanneauPrincipale extends JPanel {

    public PanneauPrincipale() {
        this.setBackground(Color.RED);
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
    }

public class panneauPrincipale {
    public static void main(String[] args) {
        panneauPrincipale();
    }

    public static void panneauPrincipale() {
        // Create a JFrame instance
        JFrame aFrame = new JFrame("Main Frame");
        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the size for the JPanel (75% of the screen)
        int panelWidth = (int) (screenSize.width * 0.75);
        int panelHeight = (int) (screenSize.height * 0.75);
        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN); // Set background color (optional)

        // Set the size of the JPanel
        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        // Create a BorderLayout for the JFrame's content pane
        aFrame.getContentPane().setLayout(new BorderLayout());

        // Add the JPanel to the WEST position (aligns it to the left)
        aFrame.getContentPane().add(panel, BorderLayout.WEST);

        // Set default close operation
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pack the frame to fit its contents
        aFrame.pack();

        // Center the frame on the screen
        aFrame.setLocationRelativeTo(null);

        // Make the frame visible
        aFrame.setVisible(true);
    }
}}





