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

public class AddProjectLeaderFX extends Application {

    private static String pName;

    public void newStart(String pName) throws IOException {
        this.pName = pName;

        Stage stage = new Stage();
        start(stage);

    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddProjectLeader.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Label nameLabel = (Label) stage.getScene().lookup("#addEmployeeLabel");
        nameLabel.setText("Assign a project leader to " + pName);
        stage.show();
    }

    @FXML
    Label addEmployeeLabel;
    @FXML
    TextField projectLeaderInitials;
    @FXML
    Button addProjectLeaderButton;
    @FXML
    Button cancelButton;

    public void addProjectLeaderToList() throws ExceptionHandler {
        String initials = projectLeaderInitials.getText();
        Project project = ProjectMenu.getProject(pName);
        if(project.employeeList.contains(EmployeeBase.getEmployee(initials))){
            project.setProjectLeader(initials);
        } else {
            addEmployeeLabel.setWrapText(true);
            addEmployeeLabel.setMaxWidth(250);
            addEmployeeLabel.setText("The user is not assigned the project, add the user and try again");
            throw new ExceptionHandler("The user is not assigned the project.");
        }
        ProjectMenuFX.getInstance().setInformationRowRight1(project);


        Stage stage = (Stage) addProjectLeaderButton.getScene().getWindow();
        stage.close();
    }

    public void cancel(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void removeProjectLeader(){
        String initials = projectLeaderInitials.getText();
        Project project = ProjectMenu.getProject(pName);
        project.removeProjectLeader();

        Stage stage = (Stage) addProjectLeaderButton.getScene().getWindow();
        stage.close();
    }
}
