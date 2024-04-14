package modele.rover;
/**
 * Classe simulant le Rover.
 * 
 * Cette classe dérive du transporteur message, responsable de gérer le protocol
 * des communications.
 * 
 * Les photos doivent être lu par chunk
 * 
 * Services offerts:
 *  - Rover
 * 
 * @author Frederic Simard, ETS
 * @version Hiver, 2024
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import modele.communication.CmdDeplacerRover;
import modele.communication.Commande;
import modele.communication.eCommande;
import modele.environnement.Lune;
import modele.communication.Constantes;
import modele.communication.Message;
import modele.communication.MorceauImage;
import modele.communication.Nack;
import modele.communication.NoOp;
import modele.communication.Status;
import modele.communication.TransporteurMessage;
import modele.satelliteRelai.SatelliteRelai;
import utilitaires.CompteurMessage;
import utilitaires.Vect2D;

public class Rover extends TransporteurMessage{
	
	private final static int TAILLE_CHUNK = 256;
	
	private Vect2D position;
	private SatelliteRelai satellite;
	private Lune lune;
	private final double VITESSE_MparS = 0.5;

	/**
	 * Constructeur du Rover, requiert référence au satellite, la Lune et la
	 * position initiale du Rover
	 * @param satellite, référence au satellite relai
	 * @param lune, référence à la lune
	 * @param Vect2D, position initial du Rover
	 */
	public Rover(SatelliteRelai satellite, Lune lune, Vect2D position){
		super();
		this.position = position.clone();
		this.satellite = satellite;
		this.lune = lune;
		

		Status status = new Status(compteurMsg.getCompteActuel(),position.clone());
		envoyerMessage(status);
		messageEnvoyes.add(status);
	}

	/**
	 * méthode permettant d'envoyer un message à destination du centre de contrôle
	 * @param msg, message à envoyer
	 */
	protected void envoyerMessage(Message msg) {
		satellite.envoyerMessageVersCentrOp(msg);
	}

	/**
	 * méthode permettant de gérer les messages reçu
	 * @param msg, message à traiter
	 */
	protected void gestionnaireMessage(Message msg) {

		// vérifie s'il s'agit d'un message de commande
		if(msg instanceof Commande) {
			
			// effectue la gestion
			Commande msgCmd = (Commande)msg;
			gestionnaireCommande(msgCmd);
		}
	}

	/**
	 * méthode effectuant la gestion des commandes
	 * @param commande
	 */
	private void gestionnaireCommande(Commande commande) {
		
		// détermine quelle commande a été reçu
		switch(commande.getCommande()) {
		
		case DEPLACER_ROVER:
			// déplace le Rover
			CmdDeplacerRover cmdDeplace = (CmdDeplacerRover)commande;
			deplacerRover(cmdDeplace.getDestination());
			break;
		case PRENDRE_PHOTOS:
			// prend un photo
			prendrePhoto();
			break;
		default:
			break;
		
		}
	}
	
	/**
	 * méthode permettant de déplacer le Rover.
	 * Décompose le mouvement du Rover en mouvement de 1 secondes. À chaque
	 * seconde le Rover informe le centre de contrôle de son status
	 * 
	 * @param destination
	 */
	private void deplacerRover(Vect2D destination) {
		
		// calcule le déplacement à effectuer
		Vect2D deplacement = position.calculerDiff(destination);
		// calcule la distance de la destination
		double distance = deplacement.getLongueur();
		// calcule le temps requis pour y aller
		double temps = distance/VITESSE_MparS;
		// calcule l'angle du mouvement
		double angle = deplacement.getAngle();
		
		// pour toutes les secondes entières
		double fractionSec = temps%1.0;
		
		Status status = new Status(compteurMsg.getCompteActuel(),position.clone());
		envoyerMessage(status);
		messageEnvoyes.add(status);
		
		// déplace le Rover dans la direction demandé
		for(int i=0;i<(int)temps;i++){
			position.ajouter(Math.cos(angle)*VITESSE_MparS, Math.sin(angle)*VITESSE_MparS);

			// envoi le message de status
			status = new Status(compteurMsg.getCompteActuel(),position.clone());
			envoyerMessage(status);
			messageEnvoyes.add(status);
		}

		// dernier mouvement est une fraction de seconde
		position.ajouter(Math.cos(angle)*VITESSE_MparS*fractionSec, Math.sin(angle)*VITESSE_MparS*fractionSec);

		// envoi le message de status
		status = new Status(compteurMsg.getCompteActuel(),position.clone());
		envoyerMessage(status);
		messageEnvoyes.add(status);
	}
	

	/**
	 * Méthode permettant de prendre un photo de la surface lunaire
	 * La photo est pris par chunk qui sont envoyé successivement 
	 * vers le centre de contrôle
	 */
	private void prendrePhoto() {
		
		// contenant d'un chunk
		byte[] chunk = new byte[TAILLE_CHUNK];
		
		try {
			// ouvre un fichier de photo
			long tailleTotale = lune.ouvrirFichierPhoto(position);
			System.out.println("test 1");
			// envoi tous les chunks jusqu'au dernier
			while(lune.lireChunkPhoto(chunk)!=-1) {
				MorceauImage morceauImage = new MorceauImage(compteurMsg.getCompteActuel(),chunk.clone(),tailleTotale);
				envoyerMessage(morceauImage);
				messageEnvoyes.add(morceauImage);
				System.out.println("test 2");
			}
			
			// envoi le message indiquant la fin de la photo
			MorceauImage morceauImage = new MorceauImage(compteurMsg.getCompteActuel(),true);
			envoyerMessage(morceauImage);
			messageEnvoyes.add(morceauImage);
			System.out.println("test 3");
			
		}catch(Exception e) {
			System.out.println("echec prise image");
			System.out.println(e.getMessage());
		}
	}
}