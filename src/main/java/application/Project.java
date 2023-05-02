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


    public Project(String projectName){
        this.projectName = projectName;
        setProjectID();
//      this.activityList = new List<Activity>;
        this.employeeList = new ArrayList<Employee>();
        this.employeeHours = new HashMap<>();
//      this.endDate = endDate;
    }

    public Project(String projectName, int budgetedHours, int startDate, int duration){
        this.projectName = projectName;
        projectID += 1;
//      this.activityList = new List<Activity>;
        this.employeeList = new ArrayList<Employee>();
        this.budgetedHours = budgetedHours;
        this.employeeHours = new HashMap<>();
        this.startDate = startDate;
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
        this.projectID = year%100*10000 + numOfProjects+1;
    }

    public Employee getProjectLeader() {
        if(projectLeader == null){
            Employee none = new Employee("none", 0);
            return none;
        }
        return projectLeader;
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

    public int getTotalHours() {
        return 0;
    }
}
