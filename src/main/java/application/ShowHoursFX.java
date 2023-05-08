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
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowHoursFX extends Application {
    String pName;
    Project project;
    @FXML
    ListView<ProjectActivity> activity = new ListView<ProjectActivity>();
    @FXML
    ListView totalHours;
    @FXML
    Button returnButton;

    public void newStart(String aName, String pName) throws IOException {
        System.out.println(pName + aName);
        System.out.println(ProjectMenu.getProject(pName));
        this.pName = pName;
        project = ProjectMenu.getProject(pName);
        System.out.println("Project activities: " + project.getActivityList());
        Stage stage = new Stage();
        start(stage);
    }

    @Override
    public void start(Stage stage) throws IOException {;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ShowHours.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //ListView<ProjectActivity> aList = (ListView<ProjectActivity>) stage.getScene().lookup("#activity");
        //ObservableList<ProjectActivity> info = FXCollections.observableArrayList(project.getActivityList());
        //aList.setItems(info);
        updateActivityList();
        stage.show();
    }


    public void updateActivityList(){
        activity.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        ObservableList<ProjectActivity> info = FXCollections.observableArrayList(project.getActivityList().);
        activity.setItems(info);
    }


    public void buttonReturn(){
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }
}
