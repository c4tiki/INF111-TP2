package vue.centreOperation;

import javax.swing.*;
import java.awt.*;

public class CmdDeplacement extends JPanel {
    // Classe interne pour la paire de JLabels indiquant la position courante
    public static class PositionCourantePanel extends JPanel {
        public JLabel labelX; // Jlabel pour x
        public JLabel labelY; // Jlabel pour y

        public PositionCourantePanel() {
            labelX = new JLabel("Position courante X:");
            labelY = new JLabel("Position courante Y:");

            add(labelX);
            add(labelY);
        }
    }
    // Classe interne publique pour le groupement JLabel + JTextField pour définir la position du Rover
    public static class PositionDefiniePanel extends JPanel {
        public JLabel labelX; // Make label public
        public JTextField textFieldX;
        public JLabel labelY; // Make label public
        public JTextField textFieldY;

        public PositionDefiniePanel() {
             labelX = new JLabel("Position cible X:");
             textFieldX = new JTextField(5); // Jtext field pour la position cible x
             labelY = new JLabel("Position cible Y:");
             textFieldY = new JTextField(5); //Jtext field pour la position cible y

            add(labelX);
            add(textFieldX);
            add(labelY);
            add(textFieldY);
        }
    }
    public CmdDeplacement() {
        setLayout(new GridLayout(2, 1)); // 2 lignes, 1 colonne

        PositionCourantePanel positionCourantePanel = new PositionCourantePanel();
        PositionDefiniePanel positionDefiniePanel = new PositionDefiniePanel();

        add(positionCourantePanel);
        add(positionDefiniePanel);
    }
}
