package utilitaires;

/**
 * Classe définissant un vecteur2D 
 * 
 * 
 * services offerts:
 * 	- Vect2D (3x)
 * 	- calculerDiff
 * 	- diviser
 * 	- getLongueur
 * 	- getAngle
 * 	- ajouter
 * 	- clone
 * 	- toString
 * 
 * @author Frederic Simard, ETS
 * @version Hiver, 2019
 */

public class Vect2D {
	
	private double x = 0;
	private double y = 0;

	/**
	 * Constructeur par défaut
	 */
	public Vect2D() {}
	
	/**
	 * Constructeur par paramètre
	 * @param x, longueur en x
	 * @param y, longueur en y
	 */
	public Vect2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructeur par copie
	 * @param vect2d, vecteur à copier
	 */
	public Vect2D(Vect2D vect2d) {
		this.x = vect2d.x;
		this.y = vect2d.y;
	}
	
	/**
	 * Accesseur informateur, position x
	 * @return coordonnee x
	 */
	public double getX(){
		return x;
	}

	/**
	 * Accesseur informateur, position y
	 * @return coordonnee y
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Calcule la différence entre this et un autre vect2d
	 * @param posFinal, fin du vecteur à calculer
	 * @return vecteur différentiel
	 */
	public Vect2D calculerDiff(Vect2D posFinal) {
		Vect2D diff = new Vect2D();
		
		diff.x = posFinal.x - this.x;
		diff.y = posFinal.y - this.y;
		
		return diff;
	}

	/**
	 * permet de diviser le vecteur par un scalaire
	 * @param diviseur, scalaire
	 */
	public void diviser(double diviseur) {
		this.x /= diviseur;
		this.y /= diviseur;
	}

	/**
	 * permet d'obtenir la longueur du vecteur
	 * @return longueur
	 */
	public double getLongueur() {
		return Math.sqrt(x*x+y*y);
	}

	/**
	 * permet d'obtenir l'angle du vecteur
	 * @return
	 */
	public double getAngle() {
		return Math.atan2(y,x);
	}
	
	/**
	 * permet d'ajouter au vecteur des coordonnées en x et y
	 * @param x, à ajouter
	 * @param y, à ajouter
	 */
	public void ajouter(double x,double y) {
		this.x += x;
		this.y += y;
	}

	/**
	 * permet de cloner le vect2d
	 * return clone du vect2d
	 */
	public Vect2D clone() {
		return new Vect2D(this);
	}

	/**
	 * permet d'obtenir une représentation String du Vect2D
	 */
	public String toString() {
		return "x: " + x + " y: " + y;
	}
	
	

}
