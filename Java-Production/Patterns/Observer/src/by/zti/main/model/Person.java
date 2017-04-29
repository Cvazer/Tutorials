package by.zti.main.model;

public class Person {
    private SimpleObservableString firstName;
    private SimpleObservableString lastName;
    private int id;

    public Person() {
        firstName = new SimpleObservableString("");
        lastName = new SimpleObservableString("");
    }

    public void addStringObserver(StringObserver observer){
        firstName.add(observer);
        lastName.add(observer);
    }

    public void removeStringObserver(StringObserver observer){
        firstName.remove(observer);
        lastName.remove(observer);
    }

    public void setFirstName(String firstName){
        this.firstName.setValue(firstName);
    }

    public String getFirstName(){
        return firstName.getValue();
    }

    public void setLastName(String lastName){
        this.lastName.setValue(lastName);
    }

    public String getLastName(){
        return lastName.getValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id+" "+firstName.getValue()+" "+lastName.getValue();
    }
}
