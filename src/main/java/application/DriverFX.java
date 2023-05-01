package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DriverFX extends Application{
    private Driver driver;
    private Stage stage;

    public void newStart() throws IOException {
        Stage stage = new Stage();
        start(stage);
    }

    @Override
    public void start(Stage stage) throws IOException {;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Button loginButton;
    @FXML
    Button createButton;
    @FXML
    TextField loginField;
    @FXML
    Label createdLabel;



    public void getLogin(){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        String initials = loginField.getText();
        driver = new Driver();
        boolean doesExist = driver.getLogin(initials);

        if(doesExist){
            System.out.println("I exist");
            stage.close();
        } else {
            System.out.println("I do not exist");
        }
    }

    public void createLogin() throws ExceptionHandler {
        String initials = loginField.getText();
        createdLabel.setVisible(true);
        driver.createLogin(initials);
    }
}
