package vue.centreOperation;

import programme.ProgrammePrincipale;

import javax.swing.*;
import java.awt.*;

public class Console extends JPanel {
    //constructeur de la classe Console
    public Console() {
        //utilisation de box layout avec un allignement vertical (Y_AXIS)
        this.setLayout(new GridLayout(2, 1)); // utilisation de GridLayout pour avoir 2 rangées, 1 colonne
        this.setBackground(Color.blue);

        CmdDeplacement cmdDeplacement = new CmdDeplacement(); //instanciation de CmdDeplacement
        GestionPhotos gestionPhotos = new GestionPhotos(); //instanciation de GestionPhotos

        // Ajout de ces deux panneaux a la console
        this.add(cmdDeplacement);
        this.add(gestionPhotos);
    }
}

