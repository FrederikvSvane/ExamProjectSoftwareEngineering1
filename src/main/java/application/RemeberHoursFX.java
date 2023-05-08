package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RemeberHoursFX extends Application {

    @FXML
    Button noButton;
    @FXML
    Button yesButton;

    DriverFX dFX = new DriverFX();
    public void newStart() throws IOException {
        Stage stage = new Stage();
        start(stage);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("RememberHours.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutSecond() throws IOException {
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();

        dFX.newStart();
        AuthenticationService.logout();
    }

    public void closeWindow() throws IOException {
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();

        ProjectMenuFX pmFX = new ProjectMenuFX();
        pmFX.newStart();
    }


}
