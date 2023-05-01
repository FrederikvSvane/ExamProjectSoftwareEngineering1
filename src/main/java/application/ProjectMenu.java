package application;

import java.util.HashMap;
import java.util.List;

public class ProjectMenu{



    private HashMap<Project, List<Activity>> projects = new HashMap<Project, List<Activity>>();
    private ProjectService projectService;

    public ProjectMenu(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void displayMenu(){

    }

}
