package application;

import javafx.application.Application;
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
    @FXML
    Label errorLabelNewProject;
    public void createProject() throws ExceptionHandler {
        try{
            String pName = projectName.getText();
            int sDate = startDate.getValue().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

            int bHours = Integer.parseInt(budgetHours.getText());
            int pDuration = Integer.parseInt(duration.getText());


            projectMenu = new ProjectMenu(AuthenticationService.getLoggedInUser());
            projectMenu.addProject(pName,bHours,sDate,pDuration);


            ProjectMenuFX.getInstance().updateList();
            ProjectMenuFX.getInstance().updateMyProjectList();

            Stage stage = (Stage) createProjectButton.getScene().getWindow();
            stage.close();
        } catch(ExceptionHandler err){
            errorLabelNewProject.setMaxWidth(200);
            errorLabelNewProject.setMaxHeight(100);
            errorLabelNewProject.setWrapText(true);
            if(err.getMessage().equals("Project already exists")){
                errorLabelNewProject.setText("Project already exists");
            } else if(err.getMessage().equals("Invalid timeframe")){
                errorLabelNewProject.setText("Invalid timeframe");
            }
            System.out.println(err);
        }
    }

    public void cancel(){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
