package observer;
import java.util.ArrayList;


public class Observable {
    //creation de la liste d'observateurs
    private ArrayList<Observeur> observers = new ArrayList<>();

    //methode permettant d'ajouter un observateur
    public void ajouter(Observeur observer) {
        observers.add(observer);
    }
    //methode permettant de supprimer un observateur
    public void enlever(Observeur observer) {
        observers.remove(observer);
    }

    //methode appelée quand observable change d'etat.
    public void avertirObserveur() {
       //boucle permettant de passer a travers tous les observateurs
        for (Observeur observer : observers) {
            observer.avertir(); //appel de la methode avertir de chaque observateur
        }
    }

}
