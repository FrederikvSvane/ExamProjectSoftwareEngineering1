package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Project implements ProjectService {
    private String projectName;
    private int projectID; //Indkorporér dato/kalender her
    private List<ProjectActivity> activityList = new ArrayList<ProjectActivity>();
    ArrayList<Employee> employeeList;
    private Employee projectLeader;
    private int budgetedHours;
    private HashMap<Employee, Integer> employeeHours;
    private int startDate;
    private int endDate;
    private int duration;

    ProjectActivity activity;
    EmployeeBase employeeBase = new EmployeeBase();

    public Project(String projectName, int budgetedHours, int startDate, int duration) {
        this.projectName = projectName;
        setProjectID();
//      this.activityList = new List<Activity>;
        this.employeeList = new ArrayList<Employee>();
        this.budgetedHours = budgetedHours;
        this.employeeHours = new HashMap<>();
        this.startDate = startDate;
        this.duration = duration;
        this.endDate = startDate + duration;


    }

    public int getProjectID() {
        return projectID;
    }


    public String getProjectName() {
        return projectName;
    }

    private void setProjectID() { //Christoffer
        int year = ProjectMenu.getDate().get(Calendar.YEAR);
        int numOfProjects = ProjectMenu.getProjectsCreatedInYear(year);
        //2023 mod 100 => 23 * 10000 => 230000 + 1 + 1 => 230001
        this.projectID = year % 100 * 10000 + numOfProjects + 1;
    }

    public void setProjectLeader(String initials) throws ExceptionHandler { //Rasmus
        if (!employeeBase.containsEmployee(initials)) {
            throw new ExceptionHandler("User does not exist");
        } else {
            if (projectLeader != null) {
                throw new ExceptionHandler("Project leader already assigned to project.");
            } else {
                projectLeader = employeeBase.getEmployee(initials);
            }

        }

    }

    public Employee getProjectLeader() {
        if (projectLeader == null) {
            Employee none = new Employee("none");
            return none;
        } else {
            return projectLeader;
        }
    }

    public void removeProjectLeader() { //Søren
        if (projectLeader != null) {
            projectLeader = null;
        }
    }

    public void addEmployeeToProject(String initials) throws ExceptionHandler {//Rasmus
        if (employeeBase.containsEmployee(initials)) {
            if (!employeeList.contains(employeeBase.getEmployee(initials))) {
                employeeBase.getEmployee(initials).addProject(this);
                employeeList.add(employeeBase.getEmployee(initials));
            } else {
                throw new ExceptionHandler("The user is already assigned to the project");
            }
        } else {
            throw new ExceptionHandler("The user doesn't exist");
        }
    }

    public void addEmployeeToProjectWhiteBox(String initials) throws ExceptionHandler {//Rasmus

        assert initials != null : "Initials is null";
        assert employeeBase.containsEmployee(initials) : "The user doesn't exist";
        assert!employeeList.contains(employeeBase.getEmployee(initials)) : "The user is already assigned to the project";

        if (employeeBase.containsEmployee(initials)) {
            if (!employeeList.contains(employeeBase.getEmployee(initials))) {

                assert !EmployeeBase.employeeBase.contains(null) : "EmployeeBase contains null";

                employeeBase.getEmployee(initials).addProject(this);
                employeeList.add(employeeBase.getEmployee(initials));
            } else {
                throw new ExceptionHandler("The user is already assigned to the project");
            }
        } else {
            throw new ExceptionHandler("The user doesn't exist");
        }

        assert employeeList.contains(employeeBase.getEmployee(initials)) : "Employee not added to project";
        assert employeeBase.getEmployee(initials).containsProject(projectName) : "Project not added to employee";

    }


    public void addProjectActivity(String activityName, int hours, int startDate, int duration) throws ExceptionHandler { //Søren 1
        if (activityName == null || activityName.equals("")) {                                                             //2
            throw new ExceptionHandler("The activity name is invalid");                                                 //3
        } else if (hours <= 0) {                                                                                            //4
            throw new ExceptionHandler("The amount of hours is invalid");                                               //5
        } else if (startDate <= 0 || startDate > 52 || duration <= 0) {                                                    //6
            throw new ExceptionHandler("The given timeframe is invalid");                                               //7
        } else if (activityExists(activityName)) {                                                                         //8
            throw new ExceptionHandler("An activity with the given name already exists");                              //9
        } else {                                                                                                          //10
            if(getProjectLeader().equals(AuthenticationService.getLoggedInUser()) || projectLeader == null){             //11
                activity = new ProjectActivity(activityName, hours, startDate, duration);                            //12
                activityList.add(activity);                                                                            //13
            } else {                                                                                                 //14
                throw new ExceptionHandler("Activity can not be made when user is not the projectleader");        //15
            }

        }

    }

    public void addProjectActivityWhiteBox(String activityName, int hours, int startDate, int duration) throws ExceptionHandler { //Søren 1
        assert EmployeeBase.employeeBase != null && EmployeeBase.containsEmployee("giig");                  //Precondition 1
        assert AuthenticationService.loginStatus();                                                                     //Precondition 2
        assert ProjectMenu.projectExists("Awesome Project");                                                 //Precondition 3
        assert (activityName == null || activityName.equals("")) == false: "The activity name is invalid";                 //Precondition 4
        assert hours > 0: "The amount of hours is invalid";                                                              //Precondition 5
        assert startDate > 0 && startDate <= 52 && duration > 0: "The given timeframe is invalid";                        //Precondition 6
        assert activityExists(activityName) == false: "An activity with the given name already exists";                   //Precondition 7
        assert (getProjectLeader().equals(AuthenticationService.getLoggedInUser()) || projectLeader == null) == true:
                "Activity can not be made when user is not the projectleader"; //Precondition 8

        if (activityName == null || activityName.equals("")) {                                                             //2
            throw new ExceptionHandler("The activity name is invalid");                                                 //3
        } else if (hours <= 0) {                                                                                            //4
            throw new ExceptionHandler("The amount of hours is invalid");                                               //5
        } else if (startDate <= 0 || startDate > 52 || duration <= 0) {                                                    //6
            throw new ExceptionHandler("The given timeframe is invalid");                                               //7
        } else if (activityExists(activityName)) {                                                                         //8
            throw new ExceptionHandler("An activity with the given name already exists");                              //9
        } else {                                                                                                          //10
            if(getProjectLeader().equals(AuthenticationService.getLoggedInUser()) || projectLeader == null){             //11
                assert !activityList.contains(null);                                                               //Invariant 1 before
                activity = new ProjectActivity(activityName, hours, startDate, duration);                            //12
                activityList.add(activity);                                                                            //13
            } else {                                                                                                 //14
                throw new ExceptionHandler("Activity can not be made when user is not the projectleader");        //15
            }
        assert !activityList.contains(null);                                                                        //Invariant 1 after
        }
        assert activityList.contains(activity);                                                                        //Postcondition 1
        }



    public void removeEmployeeFromProject(String initials) throws ExceptionHandler {//Christoffer
        if (containsEmployee(initials)) {
            employeeList.remove(employeeBase.getEmployee(initials));
            employeeBase.getEmployee(initials).removeProject(this);
        } else {
            throw new ExceptionHandler("User doesn't exist in the project");
        }
    }

    public boolean containsEmployee(String initials) {//Christoffer
        return employeeList.stream().anyMatch(e -> e.getEmployeeInitials().equals(initials));
    }

    public void setTimeframe(int startDate, int duration) throws ExceptionHandler {//Rasmus
        if (startDate <= 0 || startDate > 52 || duration <= 0) {
            throw new ExceptionHandler("The given timeframe is invalid");
        } else {
            this.startDate = startDate;
            this.duration = duration;
            this.endDate = startDate + duration;
        }
    }


    public boolean activityExists(String activityName) {//Lucas
        return activityList.stream().anyMatch(e -> e.getActivityName().equals(activityName));
    }

    public int getEndDate() {
        return endDate;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getDuration() {
        return duration;
    }

    public int getBudgetedHours() {
        return budgetedHours;
    }

    public int getTotalHours() {//Frederik
        int totalHours = 0;
        for (ProjectActivity activity : activityList) {
            totalHours += activity.getHours();
        }
        return totalHours;
    }

    public ProjectActivity getActivity(String activityName) throws ExceptionHandler {//Christoffer
        if (!activityExists(activityName)) {
            throw new ExceptionHandler("The activity does not exist in the project");
        } else {
            return activityList.stream().filter(e -> e.getActivityName().equals(activityName)).findFirst().get();
        }
    }

    public List<ProjectActivity> getActivityList() {
        return activityList;
    }

    public void removeActivityFromList(Activity activity) throws ExceptionHandler {//Søren
            if(activityExists(activity.getActivityName())){
                getActivityList().remove(activity);
            } else {
                throw new ExceptionHandler("Activity doesnt exist");
            }
    }

}
