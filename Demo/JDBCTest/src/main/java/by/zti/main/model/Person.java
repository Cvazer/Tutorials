package by.zti.main.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private IntegerProperty id;
	private StringProperty name;
	private StringProperty surname;

	public Person() {
		id = new SimpleIntegerProperty(0);
		name = new SimpleStringProperty("");
		surname = new SimpleStringProperty("");
	}

	public int getId() {
		return id.get();
	}
	
	public void setId(int id) {
		this.id.set(id);
	}
	
	public String getName(){
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getSurname() {
		return surname.get();
	}
	
	protected void setSurname(String surname) {
		this.surname.set(surname);
	}

}
