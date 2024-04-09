package vue.centreOperation;

import javax.swing.*;
import java.awt.*;

public class CmdDeplacement extends JPanel {

    private class PositionCourante extends JPanel {
        JLabel labelPositionX = new JLabel("Pos courante X: xxx");
        JLabel labelPositionY = new JLabel("Pos courante Y: yyy");

        public PositionCourante() {
            setBackground(Color.LIGHT_GRAY);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            labelPositionX.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelPositionY.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(labelPositionX);
            add(labelPositionY);
            setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        }
    }

    private class PositionCible extends JPanel {
        JLabel labelPositionX = new JLabel("Pos Cible X:");
        JTextField textFieldPositionX = new JTextField(); // Taille ajustée pour l'exemple
        JLabel labelPositionY = new JLabel("Pos Cible Y:");
        JTextField textFieldPositionY = new JTextField(); // Taille ajustée pour l'exemple

        public PositionCible() {
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            setOpaque(false); // Rend ce JPanel transparent

            // Panneau pour la position X
            JPanel panelX = new JPanel();
            panelX.setLayout(new BoxLayout(panelX, BoxLayout.LINE_AXIS));
            panelX.add(labelPositionX);
            panelX.add(Box.createHorizontalStrut(10)); // Espacement horizontal entre le label et le champ de texte
            textFieldPositionX.setMaximumSize(new Dimension(100, 20)); // Largeur illimitée, hauteur fixe
            panelX.add(textFieldPositionX);
            panelX.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Ajoute de l'espace autour des composants dans panelX
            panelX.setMaximumSize(new Dimension(160, 60)); // Largeur et hauteur maximum pour panelX
            panelX.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Panneau pour la position Y
            JPanel panelY = new JPanel();
            panelY.setLayout(new BoxLayout(panelY, BoxLayout.LINE_AXIS));
            panelY.add(labelPositionY);
            panelY.add(Box.createHorizontalStrut(10)); // Espacement horizontal entre le label et le champ de texte
            textFieldPositionY.setMaximumSize(new Dimension(100, 20)); // Largeur illimitée, hauteur fixe
            panelY.add(textFieldPositionY);
            panelY.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Ajoute de l'espace autour des composants dans panelY
            panelY.setMaximumSize(new Dimension(160, 60)); // Largeur et hauteur maximum pour panelY
            panelX.setAlignmentX(Component.CENTER_ALIGNMENT);
            // Ajouter les panneaux de position X et Y à PositionCible avec un espace plus grand entre eux
            add(panelX);
            add(Box.createVerticalStrut(20)); // Espacement vertical plus grand entre panelX et panelY
            add(panelY);
        }


    }



    public CmdDeplacement() {
        setBackground(Color.DARK_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.WHITE));

        PositionCourante positionCourante = new PositionCourante();
        PositionCible positionCible = new PositionCible();
        JButton boutonDeplacer = new JButton("Déplacer Rover");
        boutonDeplacer.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Assurez-vous que le bouton a la même largeur que les composants de position
        boutonDeplacer.setMaximumSize(new Dimension(positionCourante.getMaximumSize().width, 40));

        add(Box.createVerticalGlue());
        add(positionCourante);
        add(Box.createVerticalStrut(10)); // Espacement entre Position Courante et Position Cible
        add(positionCible);
        add(Box.createVerticalStrut(10)); // Espacement entre Position Cible et le Bouton
        add(boutonDeplacer);
        add(Box.createVerticalGlue());
    }
}
