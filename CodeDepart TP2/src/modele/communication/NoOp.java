package modele.communication;
/**
 * Classe de base qui définit un message.
 * 
 * NoOp, No Operation
 * 
 * Ce message est envoyer periodiquement pour forcer un suivit de l'état 
 * des messages échangés
 * 
 * Services offerts:
 *  - NoOp
 * 
 * @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

public class NoOp extends Message {

	/**
	 * Constructeur du NoOp
	 * @param compte, compte unique
	 */
	public NoOp(int compte) {
		super(compte);
	}
}
