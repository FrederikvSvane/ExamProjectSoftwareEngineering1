package application;

public class AuthenticationService {

    private String username;

    private EmployeeBase employeeBase = new EmployeeBase();

    public AuthenticationService(String username) {
        this.username = username;
    }

    public boolean loginSuccessful() {
        return employeeBase.containsEmployee(username);
    }





    public String getUsername() {
        return username;
    }

}
