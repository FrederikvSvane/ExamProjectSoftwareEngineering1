package application;

public interface ProjectService {

    void setProjectLeader(String initials) throws ExceptionHandler;

    void addProjectActivity(String activityName, int hours, int startDate, int duration) throws ExceptionHandler;
    void addConstantActivity(String activityName, int hours, int startDate, int duration);

    void addEmployeeToProject(String initials) throws ExceptionHandler;

    void removeEmployeeFromProject(String initials);

    //void displayProject(Project project);

    void setTimeframe(int startDate, int duration) throws ExceptionHandler;

    boolean activityExists(String activityName);

    Activity selectActivity(String activityName);
}
