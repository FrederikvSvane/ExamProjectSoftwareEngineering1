package application;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String employeeInitials;

    private List<Project> projectList = new ArrayList<Project>();

    public Employee(String employeeInitials){
        this.employeeInitials = employeeInitials;
    }

    public void addProject(Project project) {
        projectList.add(project);
    }

    public String getEmployeeInitials() {
        return employeeInitials;
    }

    public boolean containsProject(String projectName) {
        return projectList.stream().anyMatch(p -> p.getProjectName().equals(projectName));
    }

    public void removeProject(Project project) {
        projectList.remove(project);
    }
}
