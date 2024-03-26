/*********************************************************************
 * Par : Imane Ben Seghir
 * Date : 26/03/2024
 *
 * Ce fichier définit la classe CadrePrincipale qui crée un cadre
 * principal qui au moment de la fermeture affiche une demande de
 * confirmation.
 *********************************************************************/
package vue;

import vue.centreOperation.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CadrePrincipale extends JFrame {
    public static void main(String[] args) {

        CadrePrincipale cadrePrincipale = new CadrePrincipale();
    }
    /**
     * Initialise et affiche le cadre Principal
     *
     * Cette méthode créé un JFrame servant de cadre principal de l'application
     * Elle configure sa taille à la taille de l'écran et affiche un message de
     * confirmation à la chaque tentative de fermeture de la fenètre de la part
     * de l'utilisateur
     * */
    public CadrePrincipale() {
        PanneauPrincipale panneauPrincipal = new PanneauPrincipale(); //creer un nouveau pannel de type panneaPrincipal
        Console console = new Console();

        panneauPrincipal.ajouterComposantPrincipal(console, 1, 0);

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize()); //ajuste le cadre a la taille de lecran
        this.setLocationRelativeTo(null); //centre la fenetre
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //initier le comportement de x a aucune action
        this.addWindowListener(new WindowAdapter() { //ecouteur devenement

            public void windowClosing(WindowEvent we) { //methode appele lorsque lutilisateur clique sur le x
                //creation dune variable result qui affiche le dialogue de confirmation
                int result = JOptionPane.showConfirmDialog(CadrePrincipale.this,
                        "Voulez-vous quitter le progrmame?", "Exit Confirmation : ",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION){ //si la personne choisi oui, la fenetre se ferme
                    CadrePrincipale.this.dispose();
                }
            }
        });
        // this.pack();
        this.setContentPane(panneauPrincipal); //on set que le ContentPane du cadre est le panneauPrincipal


        this.setVisible(true);
    }
}
