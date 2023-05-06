package application;

import io.cucumber.java.bs.A;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String employeeInitials;
    private int currentHours;
    private ArrayList<Project> projectList = new ArrayList<>();

    public Employee(String employeeInitials, int currentHours){
        this.employeeInitials = employeeInitials;
        this.currentHours = currentHours;
    }

    public String getEmployeeInitials() {
        return employeeInitials;
    }


    public List<String> getProjectList(){
        ArrayList<String> projectNames = new ArrayList<>();
        for(Project project : projectList){
            projectNames.add(project.getProjectName());
        }
        return projectNames;
    }

    public void addToMyProjects(Project project){
        projectList.add(project);
    }
}
