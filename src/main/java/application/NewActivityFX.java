package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.Arrays;

public class NewActivityFX extends Application {
    private static String pName;

    public void newStart(String pName) throws IOException {
        this.pName = pName;

        Stage stage = new Stage();
        start(stage);

    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("NewActivity.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Label nameLabel = (Label) stage.getScene().lookup("#nameLabel");
        nameLabel.setText("Add activity for " + pName);
        stage.show();
    }

    @FXML
    Label nameLabel;
    @FXML
    TextField activityName;
    @FXML
    DatePicker startDate;
    @FXML
    TextField budgetHours;
    @FXML
    TextField duration;
    @FXML
    Button createActivityButton;
    @FXML
    Button cancelButton;

    public void createActivity() throws ExceptionHandler {
        String aName = activityName.getText();
        int sDate = startDate.getValue().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int bHours = Integer.parseInt(budgetHours.getText());
        int aDuration = Integer.parseInt(duration.getText());

        Project project = ProjectMenu.getProject(pName);
        project.addProjectActivity(aName,bHours,sDate,aDuration);
        ProjectMenuFX pmFX = new ProjectMenuFX();
        pmFX.activityData = FXCollections.observableArrayList(ProjectMenu.getProject("Hey").getActivityList());
        Stage stage = (Stage) createActivityButton.getScene().getWindow();
        stage.close();

        //System.out.println(project.activityExists(aName));
    }
    public void cancel(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
