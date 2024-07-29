package uce.edu.ec.SDG_Enterprise.Sevice.Repository;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    List<Observer> observers;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
