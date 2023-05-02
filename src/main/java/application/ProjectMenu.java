package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class ProjectMenu{

    private static DateServer dateServer;
    private String username;

    private List<Project> projects = new ArrayList<Project>();

    private static HashMap<Integer, Integer> projectsCreatedInYear = new HashMap<Integer, Integer>();


    public void setDateServer(DateServer dateServer) { this.dateServer = dateServer;}

    public static Calendar getDate() {
        return dateServer.getDate();
    }

    public ProjectMenu(String username){
        this.username = username;
    }


//    public void displayMenu(){
//
//    }


    public void addProject(String projectName) throws ExceptionHandler{
        if(!AuthenticationService.loginStatus()){
            throw new ExceptionHandler("User must be logged in to create project");
        }else {
            if (projectExists(projectName)) {
                throw new ExceptionHandler("Project already exists");
            } else {
                Project newProject = new Project(projectName);
                projects.add(newProject);
            }
        }
    }


    public boolean projectExists(String projectName) {
        return projects.stream().anyMatch(p -> p.getProjectName().equals(projectName));
    }

    public static int getProjectsCreatedInYear(int year) {
    	if(projectsCreatedInYear.get(year)==null) {
    		return 0;
    	}else {
    		return projectsCreatedInYear.get(year);
    	}
    }

    public Project getProject(String projectName) {
        return projects.stream().filter(p -> p.getProjectName().equals(projectName)).findFirst().get();
    }
}
