package vue.centreOperation;

import modele.centreOperation.CentreOperation;
import observer.Observable;
import observer.Observeur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class GestionPhotos extends JPanel implements Observeur {

    private CentreOperation centreOperation;

    private JList<String> listePhotos;
    private JButton btnPhoto;
    private JProgressBar barreProgres;

    public GestionPhotos() {

        this.centreOperation = CentreOperation.getInstance();
        centreOperation.ajouterObserveur(this);


        setBackground(Color.DARK_GRAY); //couleur de fond gris fonce
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //disposition box layout verticale
        setBorder(BorderFactory.createLineBorder(Color.WHITE)); //mettre une bordure blanche

        //bouton "Prendre une photo"
        btnPhoto = new JButton("Prendre photo");
        btnPhoto.setAlignmentX(Component.CENTER_ALIGNMENT); //center le bouton horizontalement
        btnPhoto.setMaximumSize(new Dimension(220, 50)); // taille du bouton max
        add(Box.createVerticalStrut(5)); //espace entre le haut du panneau et le bouton photo
        add(btnPhoto); //ajout de lobjet btnPhoto au panel GestionPhoto

        //instanciation de la barre de progres
        barreProgres = new JProgressBar();
        barreProgres.setMaximumSize(new Dimension(220, 50)); //taille max barre de progres
        barreProgres.setForeground(Color.BLUE);
        add(Box.createVerticalStrut(5)); //espace entre le haut du panneau et le bouton photo
        add(barreProgres);//ajout de lobjet barreProgres au panel GestionPhoto

        //instanciation de la listePhotos
        listePhotos = new JList<String>();
        listePhotos.setBackground(Color.WHITE); //fond blanc pour la liste
        listePhotos.setMaximumSize(new Dimension(300, 250)); //taille max de la liste
        add(Box.createVerticalStrut(5)); //espace entre le haut du panneau et le bouton photo
        add(listePhotos); //ajout de lobjet listePHotos au panel GestionPhoto

        Vector<String> contenuListe = new Vector<>();

        try {
            File[] fichiers = new File("CodeDepart_TP2\\photos").listFiles();
            for (File fichier : fichiers) {
                System.out.println(fichier.getName());
                contenuListe.addElement(fichier.getName());
            }

            listePhotos.setListData(contenuListe);
        }
        catch(Exception e){
            System.out.println("Erreur dans la liste");
        }

        btnPhoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CentreOperation.getInstance().prendrePhoto();
            }
        });
    }

    @Override
    public void avertir() {
        SwingUtilities.invokeLater(() -> {
            double progressionImage = CentreOperation.getInstance().getProgresFichier();
            int valeurProgression = (int) (progressionImage * 100);
            barreProgres.setValue(valeurProgression);
        });
    }
}