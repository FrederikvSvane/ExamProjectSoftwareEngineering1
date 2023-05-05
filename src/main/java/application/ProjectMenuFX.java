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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import javax.swing.text.View;

import java.io.IOException;
import java.util.ArrayList;

public class ProjectMenuFX extends Application {


    String[] list = new String[10];
    NewProjectFX npFX = new NewProjectFX();
    NewActivityFX naFX = new NewActivityFX();
    AddHoursFX adFX = new AddHoursFX();

    private Project currentProject;

    private static ProjectMenuFX instance;

    public ProjectMenuFX() {
        instance = this;
    }

    public static ProjectMenuFX getInstance() {
        return instance;
    }

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
    ListView<String> MyProjectsList;
    @FXML
    ListView<String> AllProjectsList = new ListView<String>();
    @FXML
    ListView<String> informationRowRight;
    @FXML
    ListView<String> informationRowRight1 = new ListView<>();
    @FXML
    ListView<String> informationRowLeft;
    @FXML
    ListView<String> informationRowLeft1;
    @FXML
    public ListView<String> employeeListView = new ListView<>();
    @FXML
    Button update1;
    @FXML
    Button update2;
    @FXML
    Button newProject1;
    @FXML
    Button newProject2;
    @FXML
    Button newActivity1;
    @FXML
    Button newActivity2;
    @FXML
    Button deleteActivity1;
    @FXML
    Button deleteActivity2;
    @FXML
    Button addHours;
    @FXML
    Button addHoursAll;
    @FXML
    Button logOut;
    @FXML
    Button addEmployeeButton;
    @FXML
    Button addProjectLeaderButton;
    @FXML
    private TableColumn<ProjectActivity, String> aNameColumn;
    @FXML
    private TableColumn<ProjectActivity, String> hoursColumn;

    @FXML
    private TableColumn<ProjectActivity, String> totalHoursColumn;
    @FXML
    private  TableColumn<ProjectActivity, String> dueDate;

    @FXML
    private TableView<ProjectActivity> table = new TableView<ProjectActivity>();

    @FXML
    public void initialize() {
        aNameColumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("budgetedHours"));
        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalHours"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        updateList();
        setInformationRowLeft();
    }

    public void addProject() throws IOException {
        npFX.newStart();
    }
    public void addProjectActivity() throws IOException {
        String selectedItem = AllProjectsList.getSelectionModel().getSelectedItem();
        naFX.newStart(selectedItem);
    }

    public void setInformationRowLeft() {
        ObservableList<String> info =FXCollections.observableArrayList("Project Name:", "Project Leader:", "End Date:","Start Date:","Duration:", "Budgeted Hours:", "Total Hours:", "Project ID:");
        informationRowLeft.setItems(info);
        informationRowLeft1.setItems(info);
    }

    public void setInformationRowRight1(Project project) {
        ObservableList<String> info =FXCollections.observableArrayList(project.getProjectName(), project.getProjectLeader().getUsername(), "Week " + Integer.toString(project.getEndDate()), "Week " + Integer.toString(project.getStartDate()), Integer.toString(project.getDuration()) + " weeks", Integer.toString(project.getBudgetedHours()), Integer.toString(project.getTotalHours()), Integer.toString(project.getProjectID()));
        informationRowRight1.setItems(info);
    }


    public void addProject2(){
        ObservableList<String> items = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");
        MyProjectsList.setItems(items);

    }
    public void updateList(){
        ObservableList<String> projects = FXCollections.observableArrayList(ProjectMenu.getProjectNames());
        AllProjectsList.setItems((ObservableList<String>) projects);
    }

    public void projectSelect(){
        String selectedItem = AllProjectsList.getSelectionModel().getSelectedItem();
        Project project = ProjectMenu.getProject(selectedItem);
        currentProject = project;
        addProjectActivityToList(project);
        setInformationRowRight1(project);
        setEmployeeView(project);
    }
    public void projectSelect2(){
        String selectedItem = AllProjectsList.getSelectionModel().getSelectedItem();
        System.out.println("Clicked on: " + selectedItem);
    }

    public void setEmployeeView(Project project){
        ObservableList<String> info = FXCollections.observableArrayList(EmployeeBase.getEmployeeNames(project.employeeList));
        employeeListView.setItems(info);
        System.out.println("I was run");
        System.out.println(project.employeeList.contains(EmployeeBase.getEmployee("sss")));
    }

    public void logOut() throws IOException {

        AuthenticationService.logout();

        System.out.println("Logged out");

        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.close();

        DriverFX PM = new DriverFX();
        PM.newStart();
    }

    public void addProjectActivityToList(Project project){
        ObservableList<ProjectActivity> activityData = FXCollections.observableArrayList(project.getActivityList());
        table.setItems(activityData);
    }

    public void addEmployee() throws ExceptionHandler, IOException {
        String selectedItem = AllProjectsList.getSelectionModel().getSelectedItem();
        Project project = ProjectMenu.getProject(selectedItem);
        System.out.println("Add Employee");
        //project.addEmployeeToProject("sss");
        AddEmployeeFX aeFX = new AddEmployeeFX();
        aeFX.newStart(project.getProjectName());
        setEmployeeView(project);
    }

    public void addProjectLeader() throws ExceptionHandler, IOException {
        String selectedItem = AllProjectsList.getSelectionModel().getSelectedItem();
        Project project = ProjectMenu.getProject(selectedItem);
        System.out.println("Add PL");
        AddProjectLeaderFX aplFX = new AddProjectLeaderFX();
        aplFX.newStart(project.getProjectName());
        setInformationRowRight1(project);
    }

<<<<<<< Updated upstream
    public void removeProjectLeader(){
        String selectedItem = AllProjectsList.getSelectionModel().getSelectedItem();
        Project project = ProjectMenu.getProject(selectedItem);

        project.removeProjectLeader();
        setEmployeeView(project);
    }

    public void removeActivityFromList(){
        String selectedProject = AllProjectsList.getSelectionModel().getSelectedItem();
        Project project = ProjectMenu.getProject(selectedProject);

        ProjectActivity selectedActivity = table.getSelectionModel().getSelectedItem();

        project.getActivityList().remove(selectedActivity);
        updateList();
    }
}
=======
    public void addHours() throws IOException {
                ProjectActivity selectedActivity = table.getSelectionModel().getSelectedItem();
                String selectedItem = selectedActivity.getActivityName();
                    adFX.newStart(selectedItem, currentProject);
                System.out.println("Im here and im square");
            }

    }

>>>>>>> Stashed changes
