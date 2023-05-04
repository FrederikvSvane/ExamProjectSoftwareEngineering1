package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Project implements ProjectService{
    private String projectName;
    private static int projectID = 23000; //Indkorporér dato/kalender her
    private List<Activity> activityList = new ArrayList<Activity>();
    private ArrayList<Employee> employeeList;
    private Employee projectLeader;
    private int budgetedHours;
    private HashMap<Employee, Integer> employeeHours;
    private int startDate;
    private int endDate;
    private int duration;

    Activity activity;
    EmployeeBase employeeBase = new EmployeeBase();


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
        if(projectLeader == null){
            Employee none = new Employee("none",0);
            return none;
        } else {
            return projectLeader;
        }
    }

    public void removeProjectLeader() {
        if (projectLeader != null) {
            projectLeader = null;
        }
    }

    public void addEmployeeToProject(String initials) throws ExceptionHandler {
        if (employeeBase.containsEmployee(initials)) {
            if (!employeeList.contains(employeeBase.getEmployee(initials))) {
                employeeList.add(employeeBase.getEmployee(initials));
            } else{
                throw new ExceptionHandler("The user is already assigned to the project");
            }
        } else {
            throw new ExceptionHandler("The user doesn't exist");
        }
    }

    public void addProjectActivity(String activityName, int hours, int startDate, int duration) throws ExceptionHandler{
        if(!activityExists(activityName)){
            activity = new ProjectActivity(activityName, hours, startDate, duration);
            activityList.add(activity);
        } else {
            throw new ExceptionHandler("An activity with the given name already exists");
        }

    }
    public void addConstantActivity(String activityName, int hours, int startDate, int duration){}

    public void removeEmployeeFromProject(String initials){}

    public void setTimeframe(int startDate, int duration) throws ExceptionHandler {
        if (startDate <= 0 || startDate > 52 || duration <= 0) {
            throw new ExceptionHandler("The given timeframe is invalid");
        } else {
            this.startDate = startDate;
            this.duration = duration;
            this.endDate = startDate + duration;
        }
    }


    public boolean activityExists(String activityName){
        return activityList.stream().anyMatch(e -> e.getActivityName().equals(activityName));
    }
    public Activity selectActivity(String activityName){ return new ProjectActivity(null, 0, 0, 0);}


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
        int totalHours = 0;
        for (Activity activity : activityList) {
            //totalHours += ProjectActivity.getHours();
            return totalHours;
        }
        return totalHours;
    }
}
