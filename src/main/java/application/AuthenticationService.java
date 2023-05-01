package application;

public class AuthenticationService {

    private String username;
    private Boolean isLoggedIn = false;

    private EmployeeBase employeeBase;

    public AuthenticationService(String username) {
        this.username = username;
    }

    public boolean loginSuccessful() {

        if (employeeBase.containsEmployee(username)){
             isLoggedIn = true;
             return true;
        }
        return false;
    }

    public Boolean loginStatus() {
        return isLoggedIn;
    }

    public String getUsername() {
        return username;
    }

}
