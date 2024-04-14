package utilitaires;


/**
 * Ce fichier implémente une file doublement chaînée.
 * 
 * Les services offerts:
 *  - ajouterElement, permet d'ajouter un élément à la tête de la file
 *  - enleverElement, permet d'enlever un élément à la queue de la file
 * 
 * Le fichier définit également la classe privée Noeud.
 * 
 * Cette classe utilise des exceptions générales pour signifier les erreurs d'utilisation
 * 
 * @author Fred Simard, ETS
 * @version Hiver 2019
 * 
 */
 

public class FileDChainee
{
    private Noeud tete = null;
    private Noeud queue = null;
    private int nbElements = 0;
    
    /**
     * Classe privée qui définit un Noeud de la liste, il s'agit d'un noeud doublement chainée
     * auquel est attaché un élément de type Object.
     * 
     * Il contient des références sur les noeuds suivant et précédent, de même que les
     * accesseurs nécessaires pour insérer et réattaché le noeud.
     */
    class Noeud
    {
        private Object element;
        private Noeud suivant = null;
        private Noeud precedent = null;
        
        public Noeud(){}
        
        public Noeud(Object element){
            this.element = element;
        }
        
    }
    
    
	/**
	 * Constructeur, par défaut
	 */
    public FileDChainee(){}
    
    
	/**
	 * ajouterElement, méthode permettant d'ajouter un élément à la tête
	 * @param element(Object), element à ajouter
	 */
    public void ajouterElement(Object element){
        
        // crée le nouveau noeud
        Noeud nouveau = new Noeud(element);
        
        // cas où la liste est vide
        if(nbElements==0){
            
            // le nouvel élément est à la fois tête et queue
            tete = nouveau;
            queue = nouveau;
        
        }else{
            
            nouveau.suivant = tete;
            tete.precedent = nouveau;
                
            tete = nouveau;
        }
        
        nbElements++;
    }
    
	/**
	 * enleverElement, méthode permettant d'enlever un élément à la queue
	 * @throws exception si la liste est vide
	 */
    public Object enleverElement() throws Exception{
        
        // gestion des exceptions
        if(nbElements==0){
            throw new Exception("Liste vide");
        }
        
        // référence au noeud qui sera enlevé
        Noeud enleve = null;
        
        // cas où la liste n'a qu'un seul élément
        if(nbElements==1){
            enleve = queue;
            tete = null;
            queue = null;
            
        // cas où la liste n'a que deux éléments
        }else{
            enleve = queue;
            queue = queue.precedent;
        }
        
        // ajuste compte, invalide l'itérateur et retourne l'élément
        nbElements--;
        return enleve.element;
    }
    
    
    /**
     * permet de savoir si la file est vide
     * @return true, si vide
     */
    public boolean estVide() {
    	return nbElements==0;
    }
    
}
