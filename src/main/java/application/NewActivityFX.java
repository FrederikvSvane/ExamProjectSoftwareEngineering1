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

public class NewActivityFX extends Application { //Christoffer
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
    Button cancel;
    @FXML
    Label errorLabelNewActivity;
    public void createActivity() throws ExceptionHandler {
        try{
            String aName = activityName.getText();

            int sDate = startDate.getValue().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
            int bHours = Integer.parseInt(budgetHours.getText());
            int aDuration = Integer.parseInt(duration.getText());

            Project project = ProjectMenu.getProject(pName);

            project.addProjectActivity(aName,bHours,sDate,aDuration);

            ProjectMenuFX.getInstance().addProjectActivityToList(project);

            Stage stage = (Stage) createActivityButton.getScene().getWindow();
            stage.close();
        } catch(ExceptionHandler err){
            errorLabelNewActivity.setMaxWidth(200);
            errorLabelNewActivity.setMaxHeight(100);
            errorLabelNewActivity.setWrapText(true);
            errorLabelNewActivity.setText("Duration and budgeted hours has to be positive!");
            throw new ExceptionHandler("Wrong input");
        }


    }
    public void cancel(){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
