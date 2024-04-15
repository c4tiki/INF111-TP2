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
    private ArrayList<Cratere> crateres;
    private Vect2D roverPosition;

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
        for (int c = 0; c < maxWidth; c += caseHeight) {
            drawDashedLine(g, 0, c, maxWidth, c);
        }
        for (int c = 0; c < maxWidth; c += caseWidth) {
            drawDashedLine(g, c, 0, c, maxHeight);
        }

    }
    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){
        Graphics2D g2d = (Graphics2D) g;
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
