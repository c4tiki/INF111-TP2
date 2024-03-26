package vue;

import vue.centreOperation.Console;

import javax.swing.*;
import java.awt.*;

public class PanneauPrincipale extends JPanel {
    public PanneauPrincipale() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0; //setend sur toute la hauteur
        gbc.fill = GridBagConstraints.BOTH; //fill vertical et horizontal

        //Instanciation de visuelRover
        JPanel visuelRover = new JPanel();
        visuelRover.setBackground(Color.RED);
        gbc.weightx = 0.75; //occupe 75% de l'espace horizontal
        gbc.gridwidth = 3; //occupe 3 colonnes sur 4 donc 75%
        add(visuelRover, gbc);

        //Instanciation de console
        Console console = new Console();
        gbc.gridx = 3; //console commence a la 4ieme colonne
        gbc.weightx = 0.25; //prend 25% de lespace horizontal
        gbc.gridwidth = 1; //occupe 1 colonne sur 4
        add(console, gbc);
    }

    public void ajouterComposantPrincipal(Component composant, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.BOTH;
        add(composant, gbc);
    }
}
