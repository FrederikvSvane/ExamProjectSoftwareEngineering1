package application;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {

    private String username;
    private Boolean isLoggedIn = false;

    private static List<Employee> loggedInEmployees= new ArrayList<Employee>();

    private EmployeeBase employeeBase = new EmployeeBase();

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
