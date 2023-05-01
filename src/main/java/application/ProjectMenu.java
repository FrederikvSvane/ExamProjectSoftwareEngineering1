package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectMenu{



    private List<Project> projects = new ArrayList<Project>();


    public ProjectMenu() {
    }



//    public void displayMenu(){
//
//    }


    public void addProject(Project projectName){
        projects.add(projectName);
    }


    public boolean projectExists(String projectName){
        return projects.stream().anyMatch(p -> p.getProjectName().equals(projectName));
    }

}
