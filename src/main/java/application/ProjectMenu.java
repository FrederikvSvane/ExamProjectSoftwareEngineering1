package application;

import java.util.*;


public class ProjectMenu{

    private static DateServer dateServer = new DateServer();
    private String username;

    private ArrayList<offWorkActivity> offWorkActivities = new ArrayList<offWorkActivity>();

    private static List<Project> projects = new ArrayList<Project>();

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


    public void addProject(String projectName, int budgetedHours, int startDate, int duration) throws ExceptionHandler{
        if(!AuthenticationService.loginStatus()){
            throw new ExceptionHandler("User must be logged in to create project");
        }else {
            if (projectExists(projectName)) {
                throw new ExceptionHandler("Project already exists");
            } else {
                Project newProject = new Project(projectName, budgetedHours, startDate, duration);
                projects.add(newProject);
                if (projectsCreatedInYear.get(getDate().get(Calendar.YEAR)) == null) {
                    projectsCreatedInYear.put(getDate().get(Calendar.YEAR), 1);
                } else {
                    projectsCreatedInYear.put(getDate().get(Calendar.YEAR), projectsCreatedInYear.get(getDate().get(Calendar.YEAR)) + 1);
                }
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

    public static Project getProject(String projectName) {
        return projects.stream().filter(p -> p.getProjectName().equals(projectName)).findFirst().get();
    }

    public static ArrayList<Project> getProjects() {
        return (ArrayList<Project>) projects;
    }

    public static List<String> getProjectNames() {
        List<String> projectNames = new ArrayList<String>();
        for (Project project : projects) {
            projectNames.add(project.getProjectName());
        }
        return projectNames;
    }

    public void createOffWorkActivity(String activityName) throws ExceptionHandler{
        if(!AuthenticationService.loginStatus()){
            throw new ExceptionHandler("User must be logged in to create activity");
        }else if(offWorkActivityExists(activityName)){
            throw new ExceptionHandler("An off-work activity with the given name already exists");
        }else {
            offWorkActivity offWorkActivity = new offWorkActivity(activityName);
            offWorkActivities.add(offWorkActivity);
        }
    }

    public boolean offWorkActivityExists(String activityName) {
        return offWorkActivities.stream().anyMatch(a -> a.getActivityName().equals(activityName));
    }

    public offWorkActivity getOffWorkActivity(String activityName) {
        return offWorkActivities.stream().filter(a -> a.getActivityName().equals(activityName)).findFirst().get();
    }

    public void addHoursToOffWorkActivity(String activityName, String employeeInitials,Integer hours) throws Exception{
        getOffWorkActivity(activityName).addHours(employeeInitials, hours);
    }

    public void setProjectsCreatedInYear(Integer numOfProjects, Integer year) {
        projectsCreatedInYear.put(year, numOfProjects);
    }
}
