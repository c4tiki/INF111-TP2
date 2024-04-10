package vue.centreOperation;

import programme.ProgrammePrincipale;
import modele.centreOperation.CentreOperation;
import javax.swing.*;
import java.awt.*;

public class Console extends JPanel {
    //constructeur de la classe Console
    public Console() {
        //utilisation de box layout avec un allignement vertical (Y_AXIS)
        this.setLayout(new GridLayout(2, 1)); // utilisation de GridLayout pour avoir 2 rangées, 1 colonne
        this.setBackground(Color.blue);

        CmdDeplacement cmdDeplacement = new CmdDeplacement(); //instanciation de CmdDeplacement

//        // cherche linstance singleton de centreOperation
//        CentreOperation centreOp = CentreOperation.getInstance();
//
//        // mettre CmdDeplacement commme un observateur de CentreOperation
//        centreOp.ajouterObserveur(cmdDeplacement);

        GestionPhotos gestionPhotos = new GestionPhotos(); //instanciation de GestionPhotos

        // Ajout de ces deux panneaux a la console
        this.add(cmdDeplacement);
        this.add(gestionPhotos);
    }
}

