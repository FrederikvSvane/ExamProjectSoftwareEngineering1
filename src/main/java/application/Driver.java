package application;

import io.cucumber.java.an.E;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application{ //Lucas

    private AuthenticationService AS;
    private EmployeeBase employeeBase =  new EmployeeBase();


    public static void main(String[] args) throws IOException {
        launch();
    }

    public void start(Stage stage) throws IOException, ExceptionHandler {
        DriverFX driverFX = new DriverFX();
        driverFX.newStart();
        employeeBase.createEmployee("ljs");
    }

    public boolean getLogin(String textField){
        AS = new AuthenticationService(textField);
        boolean doesExist = AS.loginSuccessful();
        return doesExist;
    }

    public void createLogin(String textField) throws ExceptionHandler {
        try{
            employeeBase.createEmployee(textField);
        } catch (ExceptionHandler err) {
            throw new ExceptionHandler("Wrong input");
        }
    }
}
