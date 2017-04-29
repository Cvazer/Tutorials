package by.zti.main.model;

public interface ObservableString {
    void add(StringObserver observer);
    void remove(StringObserver observer);
    void notifyObs(String oldValue, String newValue);
}
