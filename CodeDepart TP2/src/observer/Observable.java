package observer;
import java.util.ArrayList;


public class Observable {
    private ArrayList<Observeur> observers = new ArrayList<>();


    public void ajouter(Observeur observer) {
        observers.add(observer);
    }

    public void enlever(Observeur observer) {
        observers.remove(observer);
    }

    public void avertirObserveur() {
        for (Observeur observer : observers) {
            observer.avertir();
        }
    }

}
