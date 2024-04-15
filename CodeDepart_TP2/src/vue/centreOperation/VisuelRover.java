package vue.centreOperation;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import observer.Observable;
import observer.Observeur;
import utilitaires.Vect2D;
import modele.environnement.Cratere;
import modele.environnement.Lune;

public class VisuelRover extends JPanel implements Observeur {
    //creation des variables utilisé pour la conversion en pixel
    private double largeurLunaire;
    private double hauteurLunaire;
    private double dimSiteX;
    private double dimSiteY;
    // Crateres and Lune objects
    private ArrayList<Cratere> crateres;
    private Lune lune;

    // Constructor with Lune and crateres
    public VisuelRover(Lune lune, ArrayList<Cratere> crateres) {
        this.lune = lune;
        this.crateres = crateres;
        this.setBackground(Color.BLACK);
    }


    public VisuelRover() {
        this.setBackground(Color.BLACK);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Définit la couleur pour le dessin du rover
        g.setColor(Color.BLUE);
        // Draw craters
        if (crateres != null && lune != null) {
            for (Cratere cratere : crateres) {
                // Calculate pixel position of the crater
                Vect2D positionPixel = convertirPositionToPixel(cratere.getPosition());

                // Draw a circle representing the crater
                int x = (int) positionPixel.getX();
                int y = (int) positionPixel.getY();
                int diameter = 10; // Adjust as needed
                g.setColor(Color.WHITE);
                g.fillOval(x, y, diameter, diameter);
            }
        }


        // TEST POUR VOIR SI CA MARCHE
        // Coordonnées et dimensions du "rover"
        int roverX = 50; // Position X du rover
        int roverY = 50; // Position Y du rover
        int roverWidth = 30; // Largeur du rover
        int roverHeight = 30; // Hauteur du rover

        // Dessine le rover comme un cercle (ou ovale si la largeur et la hauteur sont différentes)
        g.fillOval(roverX, roverY, roverWidth, roverHeight);

        int X = 150; // Position X du rover
        int Y = 150; // Position Y du rover
        int Width = 20; // Largeur du rover
        int Height = 20; // Hauteur du rover
        g.setColor(Color.GRAY);
        g.fillOval(X, Y, Width, Height);

    }

    public VisuelRover(double largeurLunaire, double hauteurLunaire, double dimSiteX, double dimSiteY) {
        this.largeurLunaire = largeurLunaire;
        this.hauteurLunaire = hauteurLunaire;
        this.dimSiteX = dimSiteX;
        this.dimSiteY = dimSiteY;
    }

    private double convertirLongueurToPixel(double longueurLunaire) {
        double facteurCorrection = (largeurLunaire / dimSiteX + hauteurLunaire / dimSiteY) / 2;
        return longueurLunaire * facteurCorrection;
    }

    public Vect2D convertirPositionToPixel(Vect2D positionLunaire) {
        double xPixel = positionLunaire.getX() * (largeurLunaire / dimSiteX);
        double yPixel = positionLunaire.getY() * (hauteurLunaire / dimSiteY);
        return new Vect2D(convertirLongueurToPixel(xPixel), convertirLongueurToPixel(yPixel));
    }

    @Override
    public void avertir() {
        Observable.avertirObserveur();
    }
}


