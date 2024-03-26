package modele.communication;
/**
 * Classe qui implémente le protocol de communication entre le Rover
 * et le Centre d'opération.
 * 
 * Il se base sur une interprétation libre du concept de Nack:
 * 	https://webrtcglossary.com/nack/
 *  
 * Les messages envoyés sont mémorisé. À l'aide du compte unique
 * le transporteur de message peut identifier les Messages manquant
 * dans la séquence et demander le renvoi d'un Message à l'aide du Nack.
 * 
 * Pour contourner la situation ou le Nack lui-même est perdu, le Nack
 * est renvoyé periodiquement, tant que le Message manquant n'a pas été reçu.
 * 
 * C'est également cette classe qui gère les comptes unique.
 * 
 * Les messages reçu sont mis en file pour être traité.
 * 
 * La gestion des messages reçu s'effectue comme une tâche s'exécutant indépendamment (Thread)
 * 
 * Quelques détails:
 *  - Le traitement du Nack a priorité sur tout autre message.
 *  - Un message NoOp est envoyé périodiquement pour s'assurer de maintenir
 *    une communication active et identifier les messages manquants en bout de séquence.
 * 
 * Services offerts:
 *  - TransporteurMessage
 *  - receptionMessageDeSatellite
 *  - run
 * 
 * @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import utilitaires.CompteurMessage;

public abstract class TransporteurMessage implements Runnable{
	
	// compteur de message
	protected CompteurMessage compteurMsg;
	// lock qui protège la liste de messages reçu
	private ReentrantLock lock = new ReentrantLock();
	
	// liste de messages reçu et envoyés
	private ArrayList<Message> messageRecu = new ArrayList<Message>();
	protected ArrayList<Message> messageEnvoyes = new ArrayList<Message>();
	
	/**
	 * Constructeur, initialise le compteur de messages unique
	 */
	public TransporteurMessage() {
		compteurMsg = new CompteurMessage();		
	}
	
	/**
	 * Méthode gérant les messages reçu du satellite. La gestion se limite
	 * à l'implémentation du Nack, les messages spécialisé sont envoyés
	 * aux classes dérivés
	 * @param msg, message reçu
	 */
	public void receptionMessageDeSatellite(Message msg) {
		lock.lock();
		
		try {
			// s'il s'agit d'un Nack
			if(msg instanceof Nack) {
				// ajoute au début
				messageRecu.add(0, msg);
			}else {
				
				//sinon, évalue la position du message dans la séquence de compte
				//unique, et insert le message.
				boolean msgPlace = false;
				
				int i=messageRecu.size()-1;
				while(i>=0 && !msgPlace) {
					Message msgPeek = messageRecu.get(i);		
					if(msgPeek.getCompte() < msg.getCompte() || msg instanceof Nack) {
						messageRecu.add(i+1, msg);
						msgPlace = true;
					}
					i--;
				}
				
				// si le message n'a pas été placé c'est qu'il va au début
				if(msgPlace == false) {
					messageRecu.add(0, msg);
				}
			}
		}finally {
			lock.unlock();
		}
	}

	@Override
	/**
	 * Tâche effectuant la gestion des messages reçu
	 */
	public void run() {
		
		int compteCourant = 0;
		
		while(true) {
			
			lock.lock();
			
			try {
				boolean envoiNack = false;
				
				// pour tous les messages, exepté si un message manquant est identifié (Nack)
				while(!messageRecu.isEmpty() && !envoiNack) {
				
					// obtient le prochain message à gérer
					Message msg = messageRecu.get(0);
					
					// s'il s'agit d'un Nack
					if(msg instanceof Nack) {
						
						// trouve le message manquant et le renvoi.
						// au passage, tous les messages précédant le manquant
						// sont effacé, puisque reçu par le destinataire (par déduction logique)
						Message messagePassee;
						Nack nack = (Nack)msg;
						int compteManquant = nack.getCompte();

						messagePassee = messageEnvoyes.get(0);
						
						// tant qu'on traite des messages ancien
						while(messagePassee.getCompte() < compteManquant) {
							// efface ancien message
							messageEnvoyes.remove(0); 
							// passe au prochain
							messagePassee = messageEnvoyes.get(0);
						};
						// répète le message
						envoyerMessage(messagePassee);
						
						// efface le message reçu (soit le Nack), puisque traité
						messageRecu.remove(0);
					
					}
					// détection d'un message manquant
					else if(msg.getCompte() > compteCourant) {
						
						// dans ce cas envoi un Nack, indiquant le message manquant
						Nack nack = new Nack(compteCourant);
						envoyerMessage(nack);
						// quitte la boucle, puisque message manquant
						envoiNack = true;

					// dans certain cas, on peut recevoir d'ancien message qui ont
					// été répété par le Nack, ils sont ignorés
					}else if(msg.getCompte() < compteCourant) {
						messageRecu.remove(0);
					}else {
						// fait passer la gestion du message à la classe dérivé.
						gestionnaireMessage(msg);
						messageRecu.remove(0);
						compteCourant++;
					}
				}

				// envoi un NoOp périodique
				NoOp noOp = new NoOp(compteurMsg.getCompteActuel());
				envoyerMessage(noOp);
				messageEnvoyes.add(noOp);
			
			}finally{
				lock.unlock();
			}
			
			// pause, cycle de traitement de messages
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * méthode abstraite utilisé pour envoyer un message
	 * @param msg, le message à envoyer
	 */
	abstract protected void envoyerMessage(Message msg);

	/**
	 * méthode abstraite utilisé pour effectuer le traitement d'un message
	 * @param msg, le message à traiter
	 */
	abstract protected void gestionnaireMessage(Message msg);

	

}
