package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Project {
    private String projectName;
    private static int projectID = 23000; //Indkorporér dato/kalender her
    private List<Activity> activityList;
    private ArrayList<Employee> employeeList;
    private Employee projectLeader;
    private int budgetedHours;
    private HashMap<Employee, Integer> employeeHours;
    private int startDate;
    private int endDate;
    private int duration;


    public Project(String projectName) {
        this.projectName = projectName;
        setProjectID();
//      this.activityList = new List<Activity>;
        this.employeeList = new ArrayList<Employee>();
        this.employeeHours = new HashMap<>();
//      this.endDate = endDate;
    }

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

    private void setProjectID() {
        int year = ProjectMenu.getDate().get(Calendar.YEAR);
        int numOfProjects = ProjectMenu.getProjectsCreatedInYear(year);
        //2023 mod 100 => 23 * 10000 => 230000 + 1 + 1 => 230001
        this.projectID = year % 100 * 10000 + numOfProjects + 1;
    }

    public void setProjectLeader(String initials) throws ExceptionHandler {
        EmployeeBase employeeBase = new EmployeeBase();
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
        return projectLeader;
    }

    public void removeProjectLeader() {
        if (projectLeader != null) {
            projectLeader = null;
        }
    }
}
