package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Driver extends Application{

    private AuthenticationService authenticationService;
    private EmployeeBase employeeBase;
/*
    public Driver(String username) {
        authenticationService = new AuthenticationService(username);
        employeeBase = new EmployeeBase();
    }
*/



    public static void main(String[] args) throws IOException {
      launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}
