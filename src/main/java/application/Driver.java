package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application{

    private AuthenticationService authenticationService;
    private EmployeeBase employeeBase =  new EmployeeBase();;



    /*
    public Driver(String username) {
        authenticationService = new AuthenticationService(username);
        employeeBase = new EmployeeBase();
    } */


    public static void main(String[] args) throws IOException {
        launch();

    }

    public void start(Stage stage) throws IOException, ExceptionHandler {
        DriverFX driverFX = new DriverFX();
        driverFX.newStart();
        employeeBase.createEmployee("ljs");
    }






}
