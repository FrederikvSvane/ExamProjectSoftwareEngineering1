package application;

public interface ProjectService {

    void setProjectLeader(String initials) throws ExceptionHandler;

    void addProjectActivity(String activityName, int hours, int startDate, int duration) throws ExceptionHandler;

    void addEmployeeToProject(String initials) throws ExceptionHandler;

    void removeEmployeeFromProject(String initials) throws ExceptionHandler;

    void setTimeframe(int startDate, int duration) throws ExceptionHandler;

    boolean activityExists(String activityName);
}
