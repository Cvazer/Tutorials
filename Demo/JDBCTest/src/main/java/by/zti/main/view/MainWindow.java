package by.zti.main.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainWindow extends Application {
	private Stage primaryStage;
	private FXMLLoader loader;
	private AnchorPane root;
	private Scene currentScene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("JDBC-MYSQL Test App");

		loadRoot();
		
	}

	private void loadRoot() {

		loader = new FXMLLoader(MainWindow.class.getResource("MainWindow.fxml"));

		try {
			root = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		currentScene = new Scene(root);
		primaryStage.setScene(currentScene);
		primaryStage.show();

	}
}
