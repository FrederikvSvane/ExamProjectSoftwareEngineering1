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

public class DriverFX extends Application{ //Søren
    private Driver driver = new Driver();
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



    public void getLogin() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        String initials = loginField.getText();
        boolean doesExist = driver.getLogin(initials);

        if(doesExist){
            stage.close();
            ProjectMenuFX PM = new ProjectMenuFX();
            PM.newStart();
        } else {
            createdLabel.setText("The user does not exist.");
        }
    }

    public void createLogin() throws ExceptionHandler {
        String initials = loginField.getText();
        if(driver.getLogin(initials)){
            createdLabel.setText("User already exists.");
        } else{
            try{
                driver.createLogin(initials);
                createdLabel.setText("User Created!");
            } catch(ExceptionHandler err){
                createdLabel.setText("Username has to be between 1 to 4 letters");
                throw new ExceptionHandler("Wrong input");
            }
        }
    }
}
