package by.zti.main.view;

import by.zti.main.serializer.Serializer;
import by.zti.main.serializer.SimpleXmlConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainWindow extends Application {
	static SimpleXmlConfig cfg = new SimpleXmlConfig();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
		primaryStage.setScene(new Scene(root, 1280, 720));
		primaryStage.show();
	}
}
