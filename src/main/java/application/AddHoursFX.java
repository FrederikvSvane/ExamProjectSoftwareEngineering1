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

public class AddHoursFX extends Application {
    private String activityName;
    private Project project;
    public void newStart(String activityName, Project project) throws IOException {
        this.activityName = activityName;
        this.project = project;

        Stage stage = new Stage();
        start(stage);

    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddHours.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Label nameLabel = (Label) stage.getScene().lookup("#activityLabel");
        nameLabel.setText("Add hours for " + activityName);
        stage.show();
    }
    @FXML
    Label activityLabel;
    @FXML
    Button cancelButton;
    @FXML
    Button submitButton;
    @FXML
    TextField aNameTextfield;

    public void submitHours() throws ExceptionHandler {
        int Hours = Integer.parseInt(aNameTextfield.getText());
        ProjectActivity activity = project.getActivity(activityName);
        activity.addHoursToActivity(Hours);

        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
    public void cancel(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
