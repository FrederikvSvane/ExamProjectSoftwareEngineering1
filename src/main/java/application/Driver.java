package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Driver extends Application {


    @Override
    public void start(Stage stage) {
        Label l = new Label("Indtast dit brugernavn her");


        //Label l = new Label("Lav en ny bruger");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
