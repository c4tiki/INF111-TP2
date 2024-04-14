package vue.centreOperation;


import javax.swing.*;
import java.awt.*;
import utilitaires.Vect2D;

public class VisuelRover extends JPanel {
    //creation des variables utilisé pour la conversion en pixel
    private double largeurLunaire;
    private double hauteurLunaire;
    private double dimSiteX;
    private double dimSiteY;


    public VisuelRover() {
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Définit la couleur pour le dessin du rover
        g.setColor(Color.BLUE);

        /* TEST POUR VOIR SI CA MARCHE
        // Coordonnées et dimensions du "rover"
        int roverX = 50; // Position X du rover
        int roverY = 50; // Position Y du rover
        int roverWidth = 30; // Largeur du rover
        int roverHeight = 30; // Hauteur du rover

        // Dessine le rover comme un cercle (ou ovale si la largeur et la hauteur sont différentes)
        g.fillOval(roverX, roverY, roverWidth, roverHeight);
        */
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
}


