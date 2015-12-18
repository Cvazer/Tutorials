package by.zti.main.view;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.zti.main.Connector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class MainWindowController {
	@FXML
	private TableView<ObservableMap<String, SimpleStringProperty>> table;
	@FXML
	private TextArea quary_ta;
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

	private ObservableList<ObservableMap<String, SimpleStringProperty>> data;

	public MainWindowController() {
	}

	@FXML
	public void initialize() {
		data = FXCollections.observableArrayList();
		quary_ta.setDisable(true);
		execute_btn.setDisable(true);
		disconnect_btn.setDisable(true);
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
	public void executeQuary() {
		try {
			table.getColumns().clear();
			data.clear();
			ResultSet result = Connector.executeQuary(quary_ta.getText());
			int col = result.getMetaData().getColumnCount();
			while(result.next()){
				ObservableMap<String, SimpleStringProperty> map = FXCollections.observableHashMap();
				for (int i = 1; i <= col; i++){
					String key = result.getMetaData().getColumnName(i);
					String value = result.getString(i);
					map.put(key, new SimpleStringProperty(value));
				}
				data.add(map);
			}
			for (int i = 1; i <= col; i++){
				String columnName = result.getMetaData().getColumnName(i);
				TableColumn<ObservableMap<String, SimpleStringProperty>, String> column = new TableColumn<>(columnName);
				column.setCellValueFactory(
						new Callback<TableColumn.CellDataFeatures<ObservableMap<String,SimpleStringProperty>,String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<ObservableMap<String, SimpleStringProperty>, String> param) {
						return param.getValue().get(columnName);
					}
				});
				table.getColumns().add(column);
			}
			table.setItems(data);
		} catch (SQLException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("SQL eqception has oquered");
			a.show();
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
