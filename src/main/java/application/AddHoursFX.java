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

public class AddHoursFX{ //Søren
    private String activityName;
    private String pName;
    Project project;
    public void newStart(String activityName,String pName) throws IOException, ExceptionHandler {
        this.activityName = activityName;
        this.pName = pName;
        this.project = ProjectMenu.getProject(pName);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AddHours.fxml"));
        loader.setControllerFactory(param -> this);
        Parent root = loader.load();

        Stage stage = new Stage();
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

    public void submitHours() throws Exception {
        int Hours = Integer.parseInt(aNameTextfield.getText());
        ProjectActivity activity = project.getActivity(activityName);
        activity.addHours(AuthenticationService.getLoggedInUser() ,Hours);
        ProjectMenuFX.getInstance().updateHours(project);
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
    public void cancel(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
