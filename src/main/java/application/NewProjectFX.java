package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.IsoFields;

public class NewProjectFX extends Application {

    Project project;
    ProjectMenu projectMenu;
    ProjectMenuFX projectMenuFX;

    public NewProjectFX() {
    }

    public NewProjectFX(ProjectMenuFX projectMenuFX) {
        this.projectMenuFX = projectMenuFX;
    }




    public void newStart() throws IOException {
        Stage stage = new Stage();
        start(stage);
    }

    @Override
    public void start(Stage stage) throws IOException {;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("NewProject.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Button cancel;
    @FXML
    Button createProjectButton;
    @FXML
    TextField projectName;
    @FXML
    DatePicker startDate;
    @FXML
    TextField budgetHours;
    @FXML
    TextField duration;

    public void createProject() throws ExceptionHandler {
        String pName = projectName.getText();
        int sDate = startDate.getValue().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        int bHours = Integer.parseInt(budgetHours.getText());
        int pDuration = Integer.parseInt(duration.getText());
        System.out.println(sDate + " " + pName + " " + bHours + " " + pDuration);

        //Add code to project
        //project = new Project(pName, bHours, sDate, pDuration);
        projectMenu = new ProjectMenu(AuthenticationService.getUsername());
        projectMenu.addProject(pName,bHours,sDate,pDuration);

        //System.out.println(projectMenu.toString());

        ProjectMenuFX.getInstance().updateList();

        Stage stage = (Stage) createProjectButton.getScene().getWindow();
        stage.close();


    }

    public void cancel(){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
