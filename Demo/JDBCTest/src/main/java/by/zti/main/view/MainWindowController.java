package by.zti.main.view;

import by.zti.main.Connector;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainWindowController {
	@FXML
	private TableView<ObservableMap<String, SimpleStringProperty>> table;
	@FXML
	private TextArea quary_ta;
	@FXML
	private ListView<String> quaries_lv;
	@FXML
	private Button execute_btn;
	@FXML
	private TextField username_tf;
	@FXML
	private TextField password_tf;
	@FXML
	private TextField URL_tf;
	@FXML
	private Button connect_btn;
	@FXML
	private Button disconnect_btn;
	@FXML
	private TabPane tabPane;
	
	private ObservableList<ObservableMap<String, SimpleStringProperty>> data;
	private ObservableList<String> quaries;

	@FXML
	public void initialize() {
		quaries_lv.setOnMouseClicked(e -> {
			if(e.getClickCount() == 2){
				quary_ta.setText(quaries_lv.getSelectionModel().getSelectedItem());
			}
		});
		quaries_lv.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				final ListCell<String> cell = new ListCell<String>();
				final ContextMenu menu = new ContextMenu();
				MenuItem execute = new MenuItem();
				execute.setText("Execute");
				execute.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						executeQuary(cell.getItem());
					}
				});
				MenuItem delete = new MenuItem();
				delete.setText("Delete");
				delete.setOnAction(event -> quaries_lv.getItems().remove(cell.getItem()));
				menu.getItems().addAll(execute, delete);
				cell.textProperty().bind(cell.itemProperty());
				cell.emptyProperty().addListener((observable, oldValue, newValue) -> {
                    if(newValue){
                        cell.setContextMenu(null);
                    }else{
                        cell.setContextMenu(menu);
                    }

                });
				return cell;
			}
		});
		table.setItems(data);
		data = FXCollections.observableArrayList();
		quaries = FXCollections.observableArrayList();
		quary_ta.setDisable(true);
		execute_btn.setDisable(true);
		disconnect_btn.setDisable(true);
	}
	
	@FXML
	public void exit(){
		System.exit(0);
	}

	@FXML
	public void disconnect() {
		try {
			Connector.disconnect();
			connect_btn.setDisable(Connector.connected);
			disconnect_btn.setDisable(!Connector.connected);
			username_tf.setDisable(false);
			URL_tf.setDisable(false);
			password_tf.setDisable(false);
			quary_ta.setDisable(true);
			execute_btn.setDisable(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void executeFromButton(){
		String sql = quary_ta.getText();
		boolean allowed = true;
		for(String s: quaries){
			if(s.toLowerCase().contentEquals(sql.toLowerCase())){
				allowed = false;
				break;
			}
		}
		if(allowed){
			quaries.add(sql);
		}
		executeQuary(sql);
	}

	public void executeQuary(String sql) {
		try {
			table.getColumns().clear();
			data.clear();
			sql = sql.toLowerCase();
			System.out.println(sql);
			if(sql.contains("insert")||sql.contains("update")||sql.contains("delete")||sql.contains("ddl")){
				Connector.executeUpdate(sql);
				Alert a = new Alert(AlertType.INFORMATION);
				a.setContentText("SQL update confirmed!");
				a.show();
				return;
			}
			ResultSet result = Connector.executeQuary(sql);
			int col = result.getMetaData().getColumnCount();
			while(result.next()){
				ObservableMap<String, SimpleStringProperty> map = FXCollections.observableHashMap();
				for (int i = 1; i <= col; i++){
					map.put(result.getMetaData().getColumnName(i), new SimpleStringProperty(result.getString(i)));
				}
				data.add(map);
			}
			for (int i = 1; i <= col; i++){
				String columnName = result.getMetaData().getColumnName(i);
				TableColumn<ObservableMap<String, SimpleStringProperty>, String> column = new TableColumn<>(columnName);
				column.setCellValueFactory(
						param -> param.getValue().get(columnName));
				table.getColumns().add(column);
			}
			table.setItems(data);
			quaries_lv.setItems(quaries);
			tabPane.getSelectionModel().selectFirst();
		} catch (SQLException e) {
			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("SQL eqception has oquered");
			a.show();
			for(String s: quaries){
				if(s.toLowerCase().contentEquals(sql)){
					quaries.remove(s);
				}
			}
			quaries_lv.setItems(quaries);
		}
	}

	@FXML
	public void connect() {
		try {
			Connector.connect(URL_tf.getText(), username_tf.getText(), password_tf.getText());
			connect_btn.setDisable(Connector.connected);
			disconnect_btn.setDisable(!Connector.connected);
			username_tf.setDisable(true);
			password_tf.setDisable(true);
			URL_tf.setDisable(true);
			quary_ta.setDisable(false);
			execute_btn.setDisable(false);
		} catch (SQLException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Invalid database URL or wrong login/pass");
			a.show();
		}
	}
}
