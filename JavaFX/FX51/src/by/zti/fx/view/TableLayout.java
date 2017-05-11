package by.zti.fx.view;

import by.zti.fx.Main;
import by.zti.fx.model.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableLayout {

    private ObservableList<Person> items = FXCollections.observableArrayList(Main.people);

    @FXML
    TableView<Person> table;
    @FXML
    TableColumn<Person, String> name;
    @FXML
    TableColumn<Person, String> surname;

    @FXML
    public void initialize(){
        table.setItems(items);

        items.addListener((ListChangeListener<Person>) c -> {
            while (c.next()){
                if(c.wasAdded()){
                    Main.people.addAll(c.getAddedSubList());
                }
                if(c.wasRemoved()){
                    Main.people.removeAll(c.getRemoved());
                }
            }
        });

        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        surname.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSurname()));
    }

}
