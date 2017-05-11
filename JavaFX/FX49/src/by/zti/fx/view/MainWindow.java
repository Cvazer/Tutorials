package by.zti.fx.view;

import by.zti.fx.Main;
import by.zti.fx.model.Person;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {
    @FXML
    private BorderPane pane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("Tutorila 46");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize(){
        try {
            pane.centerProperty().setValue(FXMLLoader.load(getClass().getResource("tableLayout.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Main.people.add(new Person("Yan", "Frankovski"));
        Main.people.add(new Person("Julia", "Rudenko"));
        launch(args);
    }
}
