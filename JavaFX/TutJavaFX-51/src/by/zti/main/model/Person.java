package by.zti.main.model;

public class Person {
    private String namme, surname;

    public Person() {}

    public Person(String namme, String surname) {
        this.namme = namme;
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNamme() {
        return namme;
    }

    public void setNamme(String namme) {
        this.namme = namme;
    }

    @Override
    public String toString() {
        return namme+" "+surname;
    }
}
