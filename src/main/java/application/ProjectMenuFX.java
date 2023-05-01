package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.swing.text.View;

import java.io.IOException;

public class ProjectMenuFX extends Application {

    String[] list = new String[10];

    public void newStart() throws IOException {
        Stage stage = new Stage();
        start(stage);
    }

    @Override
    public void start(Stage stage) throws IOException {;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ProjectMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    ListView<String> projectList;
    @FXML
    Button update1;
    @FXML
    Button update2;
    @FXML
    Button newProject1;
    @FXML
    Button newProject2;
    @FXML
    Button addActivity1;
    @FXML
    Button addActivity2;
    @FXML
    Button deleteActivity1;
    @FXML
    Button deleteActivity2;
    @FXML
    Button addHours1;
    @FXML
    Button addHours2;

    public void addProject(){
        ObservableList<String> items = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");
        projectList.setItems(items);

    }
    public void updateList(){
        ObservableList<String> items = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");
        projectList.setItems(items);
        System.out.println(projectList.getOnMouseClicked());
    }

    public void projectSelect(){
        String selectedItem = projectList.getSelectionModel().getSelectedItem();
        System.out.println("Clicked on: " + selectedItem);
    }
}
