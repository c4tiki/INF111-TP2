/*********************************************************************
 * Par : Imane Ben Seghir
 * Date : 26/03/2024
 *
 * Ce fichier définit la classe CadrePrincipale qui crée un cadre
 * principal qui au moment de la fermeture affiche une demande de
 * confirmation.
 *********************************************************************/
/*package vue;

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
    public CadrePrincipale() {
        PanneauPrincipale panneauPrincipal = new PanneauPrincipale();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(CadrePrincipale.this,
                        "Voulez-vous quitter le progrmame?", "Exit Confirmation : ",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        this.setContentPane(panneauPrincipal);
        this.setVisible(true);
    }
}
 */

package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CadrePrincipale extends JFrame {
    private PanneauPrincipale panneauPrincipale;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadrePrincipale cadrePrincipale = new CadrePrincipale();
            cadrePrincipale.setVisible(true);
        });
    }

    public CadrePrincipale() {
        setTitle("Cadre Principal");

        panneauPrincipale = new PanneauPrincipale();

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(CadrePrincipale.this,
                        "Voulez-vous quitter le programme?", "Confirmation de sortie",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                } else if (result == JOptionPane.NO_OPTION) {
                }
            }
        });

        add(panneauPrincipale);
    }
}
