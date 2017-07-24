package by.zti.main.views;

import by.zti.main.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

import java.io.IOException;

public class ModelListView {
    private static String fxml = "ModelListView.fxml";
    @FXML
    private ListView<Person> list;

    @FXML
    public void initialize(){
        ObservableList<Person> array = FXCollections.observableArrayList();
        array.addAll(new Person("Yan", "Frank"), new Person("Julia", "Rudenko"));
        list.setItems(array);
    }

    public Parent init(){
        try {
            return FXMLLoader.load(getClass().getResource(fxml));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
