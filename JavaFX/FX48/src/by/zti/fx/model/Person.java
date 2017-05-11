package by.zti.fx.model;

public class Person {
    private String name, surname;

    public Person(String name, String surname) {
        set(name, surname);
    }

    public void set(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public Person() {
        this("", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name+" "+surname;
    }
}
