package modele.communication;
/**
 * Classe de base qui définit un message.
 * 
 * Nack consiste en une requête pour demander le renvoi d'un message
 * Contraitement aux autres messages, le compte indique quel message est manquant.
 * 
 * Services offerts:
 *  - Nack
 * 
 * @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

public class Nack extends Message {

	/**
	 * Constructeur du Nack
	 * @param compte, compte du message manquant
	 */
	public Nack(int compte) {
		super(compte);
	}
	
}
