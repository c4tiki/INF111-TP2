package vue;

import javax.swing.*;
import java.awt.*;

public class PanneauPrincipale extends JPanel {
    public PanneauPrincipale() {
        setLayout(new GridBagLayout());

        JPanel panneauPrincipal = new JPanel();
        panneauPrincipal.setBackground(Color.RED);
        panneauPrincipal.setOpaque(true);

        JPanel console = new JPanel();
        console.setBackground(Color.BLUE);
        console.setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.75;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(panneauPrincipal, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.25;
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
