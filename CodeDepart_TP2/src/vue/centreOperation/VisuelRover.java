package vue.centreOperation;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import modele.centreOperation.CentreOperation;
import observer.Observeur;
import utilitaires.Vect2D;
import modele.environnement.Cratere;
import modele.environnement.Lune;
import observer.Observable;

public class VisuelRover extends JPanel implements Observeur {
    //creation des variables utilisé pour la conversion en pixel

    // Crateres and Lune objects
    private ArrayList<Cratere> crateres;
    private Vect2D roverPosition;
    private Lune lune;

    public VisuelRover() {
        this.setBackground(Color.BLACK);
        this.crateres = Lune.getInstance().getCrateres();  // Utilise les cratères de l'instance de Lune
        setPreferredSize(new Dimension((int)Lune.DIM_SITE.getX(), (int)Lune.DIM_SITE.getY()));
        setOpaque(false);
        CentreOperation.getInstance().ajouterObserveur(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dessinerCrateres(g);
        dessinerRover(g);
        int maxWidth = getWidth();
        int maxHeight = getHeight();
        int caseWidth = maxWidth/10;
        int caseHeight = maxHeight/10;
        int compteurNombre = 0;

        // Définir la police, le style et la taille du texte
        Font font = new Font("Times New Roman", Font.PLAIN, 25);
        g.setFont(font);

        for (int c = 0; c < maxWidth; c += caseHeight) {
            drawDashedLine(g, 0, c, maxWidth, c);
        }
        for (int c = 0; c < maxWidth; c += caseWidth) {
            drawDashedLine(g, c, 0, c, maxHeight);
        }

        for (int n = 0; n <= 200; n += 20) {
            g.drawString(Integer.toString(n), 5, compteurNombre * caseHeight + 20);
            compteurNombre++;
        }

        compteurNombre = 1;
        for (int n = 20; n <= 200; n += 20) {
            g.drawString(Integer.toString(n), compteurNombre * caseWidth + 5, 20); // Ajuster la hauteur pour le centrage
            compteurNombre++;
        }

        // Définit la couleur pour le dessin du rover
        g.setColor(Color.LIGHT_GRAY);
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
    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);
        //float dash[] = {10.0f};
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1, x2, y2);
    }


    private void dessinerCrateres(Graphics g) {
        for (Cratere cratere : crateres) {
            Vect2D positionPixel = convertirPositionToPixel(cratere.getPosition());
            int diametreExterieur = (int)convertirLongueurToPixel(cratere.getRayon() * 2);
            int diametreInterieur = (int)(diametreExterieur * 0.9);
            int x = (int)(positionPixel.getX() - diametreExterieur / 2);
            int y = (int)(positionPixel.getY() - diametreExterieur / 2);

            g.setColor(Color.DARK_GRAY);
            g.fillOval(x, y, diametreExterieur, diametreExterieur);
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(x + (diametreExterieur - diametreInterieur) / 2, y + (diametreExterieur - diametreInterieur) / 2, diametreInterieur, diametreInterieur);
        }
    }

    private void dessinerRover(Graphics g) {
        if (roverPosition != null) {
            Vect2D positionPixel = convertirPositionToPixel(roverPosition);
            int diametreRover = (int)convertirLongueurToPixel(2);
            g.setColor(Color.BLUE);
            g.fillOval((int)(positionPixel.getX() - diametreRover / 2), (int)(positionPixel.getY() - diametreRover / 2), diametreRover, diametreRover);
        }
    }

    private Vect2D convertirPositionToPixel(Vect2D positionLunaire) {
        double xPixel = positionLunaire.getX() * (getWidth() / Lune.DIM_SITE.getX());
        double yPixel = positionLunaire.getY() * (getHeight() / Lune.DIM_SITE.getY());
        return new Vect2D(xPixel, yPixel);
    }

    private double convertirLongueurToPixel(double longueurLunaire) {
        double facteurCorrection = (getWidth() / Lune.DIM_SITE.getX() + getHeight() / Lune.DIM_SITE.getY()) / 2;
        return longueurLunaire * facteurCorrection;
    }

    @Override
    public void avertir() {
        updateRoverPosition(CentreOperation.getInstance().getPositionRover());
        repaint();  // Redessiner le panneau suite à un changement dans les données observées
    }

    public void updateRoverPosition(Vect2D newPosition) {
        this.roverPosition = newPosition;
        repaint();  // Redessiner pour afficher la nouvelle position du Rover
    }
}
