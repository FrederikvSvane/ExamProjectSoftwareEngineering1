package application;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {

    private static String loggedInUser;

    private String username;
    private static Boolean isLoggedIn = false;

    private static List<Employee> loggedInEmployees= new ArrayList<Employee>();

    private EmployeeBase employeeBase = new EmployeeBase();

    public AuthenticationService(String username) {
        this.username = username;
    }

    public boolean loginSuccessful() {

        if (employeeBase.containsEmployee(username)){
             isLoggedIn = true;
             loggedInUser = username;
             return true;
        }
        return false;
    }

    public static void logout() {
    	isLoggedIn = false;
    	loggedInUser = null;
    }

    public static Boolean loginStatus() {
        return isLoggedIn;
    }

    public String getUsername() {
        return username;
    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }

}
