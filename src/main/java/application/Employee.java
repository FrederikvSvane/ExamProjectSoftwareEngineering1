package application;


import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String employeeInitials;


    private List<Project> projectList = new ArrayList<>();

    public Employee(String employeeInitials){
        this.employeeInitials = employeeInitials;
    }

    public void addProject(Project project) {
        projectList.add(project);
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

    public void addToMyProjects(Project project) {
        projectList.add(project);
    }
    public boolean containsProject(String projectName) {
        return projectList.stream().anyMatch(p -> p.getProjectName().equals(projectName));
    }

    public void removeProject(Project project) {
        projectList.remove(project);

    }
}
