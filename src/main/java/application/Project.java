package application;

import java.util.ArrayList;
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

    public Project(String projectName, int budgetedHours, int startDate, int duration){
        this.projectName = projectName;
        projectID += 1;
//      this.activityList = new List<Activity>;
        this.employeeList = new ArrayList<Employee>();
        this.budgetedHours = budgetedHours;
        this.employeeHours = new HashMap<>();
        this.startDate = startDate;
//      this.endDate = endDate;
    }

}
