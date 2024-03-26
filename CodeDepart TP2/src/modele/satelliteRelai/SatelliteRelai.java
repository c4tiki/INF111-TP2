package modele.satelliteRelai;

/**
 * Classe simulant le satellite relai
 * 
 * Le satellite ne se contente que de transferer les messages du Rover vers le centre de contrôle
 * et vice-versa.
 * 
 * Le satellite exécute des cycles à intervale de TEMPS_CYCLE_MS. Période à
 * laquelle tous les messages en attente sont transmis. Ceci est implémenté à
 * l'aide d'une tâche (Thread).
 * 
 * Le relai satellite simule également les interférence dans l'envoi des messages.
 * 
 * Services offerts:
 *  - lierCentrOp
 *  - lierRover
 *  - envoyerMessageVersCentrOp
 *  - envoyerMessageVersRover
 * 
 * @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import modele.centreOperation.CentreOperation;
import modele.communication.Message;
import modele.rover.Rover;
import utilitaires.FileDChainee;

public class SatelliteRelai extends Thread{
	
	static final int TEMPS_CYCLE_MS = 500;
	static final double PROBABILITE_PERTE_MESSAGE = 0.15;
	
	ReentrantLock lock = new ReentrantLock();
	private FileDChainee versRover = new FileDChainee();
	private FileDChainee versCentreOperation = new FileDChainee();
	
	private Rover rover;
	private CentreOperation centreOperation;
	private Random rand = new Random();
	
	/**
	 * Méthode permettant d'attacher le centre d'opération au satellite
	 * @param centreOperation, référence au centre d'opération
	 */
	public void lierCentrOp(CentreOperation centreOperation) {
		this.centreOperation = centreOperation;
	}
	
	/**
	 * Méthode permettant d'attacher le rover au satellite
	 * @param rover, référence au rover
	 */
	public void lierRover(Rover rover) {
		this.rover = rover;
	}
	
	/**
	 * Méthode permettant d'envoyer un message vers le centre d'opération
	 * @param msg, message à envoyer
	 */
	public void envoyerMessageVersCentrOp(Message msg) {
		
		lock.lock();
		
		try {
			//implémentation des interférences
			if(rand.nextDouble()>=PROBABILITE_PERTE_MESSAGE) {
				// si pas d'interférence, ajoute à la liste des message à envoyer
				versCentreOperation.ajouterElement(msg);
			}
		}finally {
			lock.unlock();
		}
	}
	
	/**
	 * Méthode permettant d'envoyer un message vers le rover
	 * @param msg, message à envoyer
	 */
	public void envoyerMessageVersRover(Message msg) {
		lock.lock();
		
		try {
			//implémentation des interférences
			if(rand.nextDouble()>=PROBABILITE_PERTE_MESSAGE) {
				// si pas d'interférence, ajoute à la liste des message à envoyer
				versRover.ajouterElement(msg);
			}
		}finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {
		
		while(true) {
			
			// fait suivre tous les message à destination du Rover
			while(!versRover.estVide()) {
				try {
					rover.receptionMessageDeSatellite((Message)versRover.enleverElement());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			// fait suivre tous les message à destination du Centre de contrôle
			while(!versCentreOperation.estVide()) {
				try {
					centreOperation.receptionMessageDeSatellite((Message)versCentreOperation.enleverElement());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			// attend le prochain cycle
			try {
				Thread.sleep(TEMPS_CYCLE_MS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
