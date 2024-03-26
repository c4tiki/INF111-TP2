package vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


//3.1 vue.CadrePrincipale
//Le cadre principal institue le PanneauPrincipale et remplace son ContentPane avec ce dernier.
//Le cadre principal doit définir le comportement du [X] pour qu’il demande une confirmation de
//l’utilisateur, avant de fermer le programme.

public class CadrePrincipale {
        public static void main(String[] args) {
            cadreJframe();

        }


    public static void cadreJframe() {
        JFrame frame = new JFrame();
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(frame,
                        "Voulez-vous quitter le progrmame?", "Exit Confirmation : ",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    frame.dispose();
            }
        });


        // frame.pack();
        frame.setVisible(true);

    }

}


