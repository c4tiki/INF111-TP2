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
        PanneauPrincipale panneauPrincipal = new PanneauPrincipale();

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                //
                int result = JOptionPane.showConfirmDialog(this,
                        "Voulez-vous quitter le progrmame?", "Exit Confirmation : ",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION){
                    //TODO FIX THIS
                }
            }
        });
        // this.pack();
        this.setContentPane(panneauPrincipal);

        Console console = new Console();
        this.add(console);


        this.setVisible(true);
    }
}

