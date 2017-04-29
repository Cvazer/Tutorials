package by.zti.main.model;

public class SimpleStringObserver implements StringObserver{
    @Override
    public void handleEvent(String oldValue, String newValue) {
        System.out.println(oldValue+" HAS BEEN CHANGED FOR "+newValue);
    }
}
