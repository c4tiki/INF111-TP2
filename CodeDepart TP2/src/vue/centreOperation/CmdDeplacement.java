package vue.centreOperation;

import modele.centreOperation.CentreOperation;
import observer.Observeur;
import utilitaires.Vect2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CmdDeplacement extends JPanel implements Observeur {

    private CentreOperation centreOperation; // Référence au centre d'opération

    //Declaration des positions initiales (-1 pour indiquer qu'elles sont pas definies)
    private int posX = -1;
    private int posY = -1;

    //declaration des labels pour la position courante
    private JLabel labelPositionX = new JLabel("Pos courante X: xxx");
    private JLabel labelPositionY = new JLabel("Pos courante Y: yyy");

    //declaration des textfields de position cible
    private JTextField textFieldPositionX = new JTextField();
    private JTextField textFieldPositionY = new JTextField();

    //implementation de la methode avertir
    @Override
    public void avertir() {
        SwingUtilities.invokeLater(() -> {
            // Vérifier si la position du Rover est non-null et mettre à jour les JLabels
            Vect2D position = CentreOperation.getInstance().getPositionRover();
            if(position != null) {
                labelPositionX.setText("Pos courante X: " + position.getX());
                labelPositionY.setText("Pos courante Y: " + position.getY());
            } else {
                labelPositionX.setText("Pos courante X: xxx");
                labelPositionY.setText("Pos courante Y: yyy");
            }
        });
    }

    //definition de la classe interne Position Courante qui contient:
        //  position courante x
        // position courante y
    private class PositionCourante extends JPanel {
        //creer les labels pour la position courante x et y

        public PositionCourante() {
            setBackground(Color.LIGHT_GRAY); //ajout de la couleur de fond du pannel PositionCourante
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //utilisation d'un box layout vertical

            //alignement centré horizontalement pour les deux labels
            labelPositionX.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelPositionY.setAlignmentX(Component.CENTER_ALIGNMENT);
            //ajout des labels au panel PositionCourante
            add(labelPositionX);
            add(labelPositionY);
            //ajout d'une bordure interne a l'interieur du panel pour mieux center le texte a l'interieur
            setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        }
    }

    private class PositionCible extends JPanel {
        //creer les labels et textFields pour la position cible x et y
        JLabel labelPositionX = new JLabel("Pos Cible X:");
        JLabel labelPositionY = new JLabel("Pos Cible Y:");

        public PositionCible() {
            //Utilisation d'un box layout avec une disposition verticale
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            setOpaque(false); //rend le panel transparent

            // panneau pour la position X
            JPanel panelX = new JPanel(); // création d'un nouveau panneau pour la position X
            panelX.setLayout(new BoxLayout(panelX, BoxLayout.LINE_AXIS)); // disposition horizontale pour les composants label et textField pour qu'ils soient l'un à côté de l'autre
            panelX.add(labelPositionX); // ajout du label pour la position X
            panelX.add(Box.createHorizontalStrut(10)); // mettre un espace horizontal entre le label et le textField
            textFieldPositionX.setMaximumSize(new Dimension(120, 25)); // taille maximale pour le champ de texte de la position X
            panelX.add(textFieldPositionX); // ajout du champ de texte pour la position X
            panelX.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50)); // ajout bordure interne pour centrer les composants
            panelX.setMaximumSize(new Dimension(250, 120)); // largeur et hauteur maximum pour panelX
            panelX.setPreferredSize(new Dimension(100, 50)); // largeur et hauteur préférées pour panelX

            // panneau pour la position Y
            JPanel panelY = new JPanel(); // création d'un nouveau panneau pour la position Y
            panelY.setLayout(new BoxLayout(panelY, BoxLayout.LINE_AXIS)); // disposition horizontale pour les composants label et textField pour qu'ils soient l'un à côté de l'autre
            panelY.add(labelPositionY); // ajout du label pour la position Y
            panelY.add(Box.createHorizontalStrut(10)); // mettre un espace horizontal entre le label et le textField
            textFieldPositionY.setMaximumSize(new Dimension(120, 25)); // taille maximale pour le champ de texte de la position Y
            panelY.add(textFieldPositionY); // ajout du champ de texte pour la position Y
            panelY.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50)); // ajout bordure interne pour centrer les composants
            panelY.setMaximumSize(new Dimension(250, 150)); // largeur et hauteur maximum pour panelY
            panelY.setPreferredSize(new Dimension(100, 50)); // largeur et hauteur préférées pour panelY

            // ajouter les panneaux de position X et Y à PositionCible avec un espace plus grand entre eux
            add(panelX);
            add(Box.createVerticalStrut(5)); // espacement vertical plus grand entre panelX et panelY
            add(panelY);
        }
    }

    public CmdDeplacement() {
        //intialisaton de CentreOperation
        this.centreOperation = CentreOperation.getInstance();

        //mettre la couleur de fond gris fonce
        setBackground(Color.DARK_GRAY);
        //utilisation du boxLayout vertical pour le panel CmdDeplacement
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.WHITE)); //mettre une bordure blanche

        // Instanciation des composants pour la position courante
        PositionCourante positionCourante = new PositionCourante(); //creer nouveau objet de la classe PositionCourante
        PositionCible positionCible = new PositionCible(); //creer nouveau objet de la classe PositionCible
        JButton boutonDeplacer = new JButton("Déplacer Rover"); //creet nouveau bouton pour deplacer Rover
        boutonDeplacer.setAlignmentX(Component.CENTER_ALIGNMENT); //center le bouton horizontalement

        // taille du bouton
        boutonDeplacer.setPreferredSize(new Dimension(250, 50));
        boutonDeplacer.setMaximumSize(new Dimension(250, 50));

        add(Box.createVerticalStrut(20)); //espace entre le haut du panneau et la position courante
        add(positionCourante); // Ajout composant de la position courante au panneau
        add(Box.createVerticalStrut(5)); //Ajout espace vertical entre position courante et position cible
        add(positionCible); // Ajout  composant de la position cible au panneau
        add(Box.createVerticalStrut(5)); // Espacement vertical entre Position Cible et le Bouton
        add(boutonDeplacer); // Ajoute le bouton de déplacement au panneau
        add(Box.createVerticalGlue());// Ajoute un espace vertical flexible en bas du panneau pour ajuster la disposition vers le bas

        boutonDeplacer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                // Vérifier si centreOperation est null
                if (centreOperation != null) {
                    // Valider que les valeurs sont des entiers
                    try {
                        int posX = Integer.parseInt(textFieldPositionX.getText());
                        int posY = Integer.parseInt(textFieldPositionY.getText());

                        // Envoyer la nouvelle position au centre d'opération pour déplacer le Rover
                        centreOperation.deplacerRover(posX,posY);

                    } catch (NumberFormatException ex) {
                        // Afficher une erreur si les entrées des textFields ne sont pas des entiers valides
                        JOptionPane.showMessageDialog(CmdDeplacement.this, "Veuillez entrer des entiers valides pour les positions X et Y.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Afficher un message d'erreur si centreOperation est null
                    JOptionPane.showMessageDialog(CmdDeplacement.this, "Le centre d'opération n'est pas correctement initialisé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }
}