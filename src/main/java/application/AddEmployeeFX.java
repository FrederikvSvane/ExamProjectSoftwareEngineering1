package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

public class AddEmployeeFX extends Application {

    private static String pName;

    private ProjectMenuFX pmFX = new ProjectMenuFX();



    public void newStart(String pName) throws IOException {
        this.pName = pName;

        Stage stage = new Stage();
        start(stage);

    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddEmployee.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Label nameLabel = (Label) stage.getScene().lookup("#addEmployeeLabel");
        nameLabel.setText("Edit Employee list for " + pName);
        stage.show();
    }

    @FXML
    Label addEmployeeLabel;
    @FXML
    TextField employeeInitials;
    @FXML
    Button addEmployeeButton;
    @FXML
    Button cancelButton;

    public void addEmployeeToList() throws ExceptionHandler {
        String initials = employeeInitials.getText();
        Project project = ProjectMenu.getProject(pName);
        project.addEmployeeToProject(initials);


        Stage stage = (Stage) addEmployeeButton.getScene().getWindow();

        ProjectMenuFX.getInstance().setEmployeeView(project);

        stage.close();

    }

    public void cancel(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void removeEmployee(){
        String initials = employeeInitials.getText();
        Project project = ProjectMenu.getProject(pName);
        project.removeEmployeeFromProject(initials);



        Stage stage = (Stage) addEmployeeButton.getScene().getWindow();

        ProjectMenuFX.getInstance().setEmployeeView(project);

        stage.close();

    }
}
