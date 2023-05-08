package application;

import java.util.*;


public class ProjectMenu {

    private static DateServer dateServer = new DateServer();
    public static String username;

    private ArrayList<offWorkActivity> offWorkActivities = new ArrayList<offWorkActivity>();

    private static List<Project> projects = new ArrayList<Project>();

    private static HashMap<Integer, Integer> projectsCreatedInYear = new HashMap<Integer, Integer>();


    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

    public static Calendar getDate() {
        return dateServer.getDate();
    }

    public ProjectMenu(String username) {
        this.username = username;
    }

    public void addProject(String projectName, int budgetedHours, int startDate, int duration) throws ExceptionHandler { //Christoffer
        if (!AuthenticationService.loginStatus()) {                                                                                         // 1
            throw new ExceptionHandler("User must be logged in to create project");                                                     // 2
        } else {                                                                                                                            // 3
            if (projectExists(projectName)) {                                                                                               // 4
                throw new ExceptionHandler("Project already exists");                                                                   // 5
            } else if (budgetedHours <= 0 || startDate <= 0 || duration <= 0 || startDate > 52) {                                           // 6
                throw new ExceptionHandler("Invalid timeframe");                                                                        // 7
            } else {                                                                                                                        // 8
                Project newProject = new Project(projectName, budgetedHours, startDate, duration);                                          // 9
                projects.add(newProject);                                                                                                   // 10
                if (projectsCreatedInYear.get(getDate().get(Calendar.YEAR)) == null) {                                                      // 11
                    projectsCreatedInYear.put(getDate().get(Calendar.YEAR), 1);                                                             // 12
                } else {                                                                                                                    // 13
                    projectsCreatedInYear.put(getDate().get(Calendar.YEAR), projectsCreatedInYear.get(getDate().get(Calendar.YEAR)) + 1);   // 14
                }
            }
        }
    }

    public void addProjectWhiteBox(String projectName, int budgetedHours, int startDate, int duration) throws ExceptionHandler { //Christoffer

        assert AuthenticationService.loginStatus(): "User must be logged in to create project";                                             // Precondition 1
        assert !projectExists(projectName): "Project already exists";                                                                       // Precondition 2
        assert !(budgetedHours <= 0 || startDate <= 0 || duration <= 0 || startDate > 52) : "Invalid timeframe";                            // Precondition 3
        assert projectName != null;                                                                                                         // Precondition 4

        if (!AuthenticationService.loginStatus()) {                                                                                         // 1
            throw new ExceptionHandler("User must be logged in to create project");                                                     // 2
        } else {                                                                                                                            // 3
            if (projectExists(projectName)) {                                                                                               // 4
                throw new ExceptionHandler("Project already exists");                                                                   // 5
            } else if (budgetedHours <= 0 || startDate <= 0 || duration <= 0 || startDate > 52) {                                           // 6
                throw new ExceptionHandler("Invalid timeframe");                                                                        // 7
            } else {                                                                                                                        // 8
                Project newProject = new Project(projectName, budgetedHours, startDate, duration);                                          // 9
                projects.add(newProject);                                                                                                   // 10

                assert getDate().get(Calendar.YEAR) != 0;                                                                                   // Invariant 1

                if (projectsCreatedInYear.get(getDate().get(Calendar.YEAR)) == null) {                                                      // 11
                    projectsCreatedInYear.put(getDate().get(Calendar.YEAR), 1);                                                             // 12
                } else {                                                                                                                    // 13
                    projectsCreatedInYear.put(getDate().get(Calendar.YEAR), projectsCreatedInYear.get(getDate().get(Calendar.YEAR)) + 1);   // 14
                }
            }
        }

        assert getProject(projectName).getProjectID() == (getDate().get(Calendar.YEAR) % 100) * 10000 + projectsCreatedInYear.get(getDate().get(Calendar.YEAR));    // Postcondition 1
        assert projectExists(projectName);                                                                                                  // Postcondition 2

    }

    public static boolean projectExists(String projectName) { //Lucas
        return projects.stream().anyMatch(p -> p.getProjectName().equals(projectName));
    }

    public static int getProjectsCreatedInYear(int year) { //Frederik
        if (projectsCreatedInYear.get(year) == null) {
            return 0;
        } else {
            return projectsCreatedInYear.get(year);
        }
    }

    public static Project getProject(String projectName) { //Søren
        return projects.stream().filter(p -> p.getProjectName().equals(projectName)).findFirst().get();
    }

    public static List<String> getProjectNames() { //Christoffer
        List<String> projectNames = new ArrayList<String>();
        for (Project project : projects) {
            projectNames.add(project.getProjectName());
        }
        return projectNames;
    }

    public void createOffWorkActivity(String activityName) throws ExceptionHandler { //Rasmus
        if (!AuthenticationService.loginStatus()) {
            throw new ExceptionHandler("User must be logged in to create activity");
        } else if (offWorkActivityExists(activityName)) {
            throw new ExceptionHandler("An off-work activity with the given name already exists");
        } else {
            offWorkActivity offWorkActivity = new offWorkActivity(activityName);
            offWorkActivities.add(offWorkActivity);
        }

    }

    public boolean offWorkActivityExists(String activityName) { //Lucas
        return offWorkActivities.stream().anyMatch(a -> a.getActivityName().equals(activityName));
    }

    public offWorkActivity getOffWorkActivity(String activityName) { //Frederik
        return offWorkActivities.stream().filter(a -> a.getActivityName().equals(activityName)).findFirst().get();
    }

    public void addHoursToOffWorkActivity(String activityName, String employeeInitials, Integer hours) throws Exception { //Søren
        getOffWorkActivity(activityName).addHours(employeeInitials, hours);
    }

    public void setProjectsCreatedInYear(Integer numOfProjects, Integer year) { //Rasmus
        projectsCreatedInYear.put(year, numOfProjects);
    }

    public static void removeProject(String ProjectName) { //Lucas
        Employee employee = EmployeeBase.getEmployee(AuthenticationService.getLoggedInUser());
        employee.removeProject(getProject(ProjectName));
        projects.removeIf(p -> p.getProjectName().equals(ProjectName));
    }

}
