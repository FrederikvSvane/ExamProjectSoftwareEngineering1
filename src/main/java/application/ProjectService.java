package application;

public interface ProjectService {

    void addProjectLeader(Project project,Employee newProjectLeader);

    void addActivity(Project project, String name, int hours, int startDate, int duration);

    void addEmployee(Project project, Employee newEmployee);

    void removeEmployee(Project project, Employee employee);

    //void displayProject(Project project);

    void setTimeframe(Project project, int startDate, int duration);

    boolean projectExists(String projectName);

    boolean activityExists(Project project, String activityName);

    Activity selectActivity(Project project, String activityName);
}
