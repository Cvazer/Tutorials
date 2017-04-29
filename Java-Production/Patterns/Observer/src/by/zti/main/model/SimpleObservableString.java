package by.zti.main.model;

import java.util.ArrayList;

public class SimpleObservableString implements ObservableString{
    private ArrayList<StringObserver> observers;
    private String value;

    public SimpleObservableString(String value) {
        observers = new ArrayList<>();
        this.value = value;
    }

    @Override
    public void add(StringObserver observer) {
        observers.add(observer);
    }

    @Override
    public void remove(StringObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObs(String oldValue, String neeValue) {
        observers.forEach(stringObserver -> stringObserver.handleEvent(oldValue, neeValue));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        notifyObs(this.value, value);
        this.value = value;
    }
}
