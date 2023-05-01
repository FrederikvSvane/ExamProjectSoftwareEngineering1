package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DriverFX extends Application{

    private AuthenticationService AS;
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
    public void getLogin(){
        Stage stage = (Stage) loginButton.getScene().getWindow();

        String initials = loginField.getText();
        AS = new AuthenticationService(initials);
        boolean doesExist = AS.loginSuccessful();
        System.out.println(doesExist);

        if(doesExist){
            stage.close();
        } else {
            System.out.println("I do not exist");
        }
    }
}
