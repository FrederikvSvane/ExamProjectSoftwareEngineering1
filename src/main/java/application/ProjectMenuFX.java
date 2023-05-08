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
import java.util.List;

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
    ListView<String> MyProjectsList = new ListView<String>();
    @FXML
    ListView<String> AllProjectsList = new ListView<String>();
    @FXML
    ListView<String> informationRowRight = new ListView<>();
    @FXML
    ListView<String> informationRowRight1 = new ListView<>();
    @FXML
    ListView<String> informationRowLeft;
    @FXML
    ListView<String> informationRowLeft1;
    @FXML
    public ListView<String> employeeListView = new ListView<>();
    @FXML
    public ListView<String> employeeListView1 = new ListView<>();
    @FXML
    Button update1;
    @FXML
    Button update2;
    @FXML
    Button newProject1;
    @FXML
    Button newProject2;
    @FXML
    Button newActivity1 = new Button();
    @FXML
    Button newActivity2 = new Button();
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
    Button logOut1;
    @FXML
    Button addEmployeeButton;
    @FXML
    Button addProjectLeaderButton;
    @FXML
    Button showHoursButton1;
    @FXML
    private TableColumn<ProjectActivity, String> aNameColumn;
    @FXML
    private TableColumn<ProjectActivity, String> hoursColumn;

    @FXML
    private TableColumn<ProjectActivity, String> totalHoursColumn;
    @FXML
    private  TableColumn<ProjectActivity, String> dueDate;
    @FXML
    private TableColumn<ProjectActivity, String> myNameColumn;
    @FXML
    private TableColumn<ProjectActivity, String> myHours;

    @FXML
    private TableColumn<ProjectActivity, String> myTotalHours;
    @FXML
    private  TableColumn<ProjectActivity, String> myDueDate;

    @FXML
    private TableView<ProjectActivity> table = new TableView<ProjectActivity>();
    @FXML
    private TableView<ProjectActivity> myTable = new TableView<ProjectActivity>();

    @FXML
    public void initialize() {
        aNameColumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        myNameColumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("budgetedHours"));
        myHours.setCellValueFactory(new PropertyValueFactory<>("totalHours"));
        //totalHoursColumn.setCellValueFactory(cellData -> cellData.getValue().totalHoursProperty().asObject().asString());
        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        updateList();
        setInformationRowLeft();
        updateMyProjectList();
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
        ObservableList<String> info =FXCollections.observableArrayList(project.getProjectName(), project.getProjectLeader().getEmployeeInitials(), "Week " + Integer.toString(project.getEndDate()), "Week " + Integer.toString(project.getStartDate()), Integer.toString(project.getDuration()) + " weeks", Integer.toString(project.getBudgetedHours()), Integer.toString(project.getTotalHours()), Integer.toString(project.getProjectID()));
        informationRowRight1.setItems(info);
    }

    public void setMyInformationRowRight(Project project) {
        ObservableList<String> info =FXCollections.observableArrayList(project.getProjectName(), project.getProjectLeader().getEmployeeInitials(), "Week " + Integer.toString(project.getEndDate()), "Week " + Integer.toString(project.getStartDate()), Integer.toString(project.getDuration()) + " weeks", Integer.toString(project.getBudgetedHours()), Integer.toString(project.getTotalHours()), Integer.toString(project.getProjectID()));
        informationRowRight.setItems(info);
    }
    public void updateList(){
        ObservableList<String> projects = FXCollections.observableArrayList(ProjectMenu.getProjectNames());
        AllProjectsList.setItems((ObservableList<String>) projects);
    }

    public void updateMyProjectList(){
        ObservableList<String> myProjects = FXCollections.observableArrayList(EmployeeBase.getEmployee(AuthenticationService.getLoggedInUser()).getProjectList());
        MyProjectsList.setItems((ObservableList<String>) myProjects);

    }


    public void correctAccess(Project project){

        if(project.getProjectLeader().getEmployeeInitials().equals("none") || project.getProjectLeader().getEmployeeInitials().equals(ProjectMenu.username)){
            newActivity1.setDisable(false);
            newActivity2.setDisable(false);
        } else{
            newActivity1.setDisable(true);
            newActivity2.setDisable(true);
        }
    }

    public void projectSelect(){
        String selectedItem = AllProjectsList.getSelectionModel().getSelectedItem();
        Project project = ProjectMenu.getProject(selectedItem);

        addProjectActivityToList(project);
        setInformationRowRight1(project);
        setEmployeeView(project);
        correctAccess(project);
    }

    public void myProjectSelect(){
        String mySelectedItem = MyProjectsList.getSelectionModel().getSelectedItem();
        Project myProject = ProjectMenu.getProject(mySelectedItem);
        setMyEmployeeListView(myProject);

        addProjectActivityToList(myProject);
        setMyInformationRowRight(myProject);
        correctAccess(myProject);
    }

    public void setEmployeeView(Project project){
        ObservableList<String> info = FXCollections.observableArrayList(EmployeeBase.getEmployeeNames(project.employeeList));
        employeeListView.setItems(info);
    }

    public void setMyEmployeeListView(Project project){
        ObservableList<String> info = FXCollections.observableArrayList(EmployeeBase.getEmployeeNames(project.employeeList));
        employeeListView1.setItems(info);
    }

    public void logOut() throws IOException {

        AuthenticationService.logout();

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

        AddEmployeeFX aeFX = new AddEmployeeFX();
        aeFX.newStart(project.getProjectName());
    }

    public void addProjectLeader() throws ExceptionHandler, IOException {
        String selectedItem = AllProjectsList.getSelectionModel().getSelectedItem();
        Project project = ProjectMenu.getProject(selectedItem);

        AddProjectLeaderFX aplFX = new AddProjectLeaderFX();
        aplFX.newStart(project.getProjectName());

        updateList();
    }

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


    public void addHours() throws IOException, ExceptionHandler {
        String selectedItem1 = AllProjectsList.getSelectionModel().getSelectedItem();
        ProjectActivity selectedActivity = table.getSelectionModel().getSelectedItem();
        String aName = selectedActivity.getActivityName();
        adFX.newStart(aName, selectedItem1);
    }
    public void updateHours(Project project){
        ObservableList<ProjectActivity> activityData = FXCollections.observableArrayList(project.getActivityList());
        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        table.setItems(activityData);
    }

    public void showHours() throws IOException {
        ProjectActivity selectedActivity = table.getSelectionModel().getSelectedItem();
        String aName = selectedActivity.getActivityName();
        String pName = AllProjectsList.getSelectionModel().getSelectedItem();
        ShowHoursFX shFX = new ShowHoursFX();
        System.out.println(pName);
        shFX.newStart(aName, pName);

    }
    }


