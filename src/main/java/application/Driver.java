package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Driver extends Application {


    @Override
    public void start(Stage stage) {
        Label l = new Label("Indtast dit brugernavn her");
        TextField t = new TextField();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setAlignment(Pos.CENTER);
        grid.add(l,0,0);
        grid.add(t,0,1);

        //Label l = new Label("Lav en ny bruger");
        Scene scene = new Scene(grid, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
