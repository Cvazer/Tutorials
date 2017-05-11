package by.zti.fx.view;

import by.zti.fx.Main;
import by.zti.fx.model.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

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

        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        surname.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSurname()));
    }

}
