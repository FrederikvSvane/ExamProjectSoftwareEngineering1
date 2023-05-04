package example.cucumber;
import application.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmployeeProjectSteps {

    private String user;

    private Project project;
    private EmployeeBase employeeBase = new EmployeeBase();

    private ErrorMessageHolder errorMessageHolder;
    private ProjectMenuHolder projectMenuHolder;

    public EmployeeProjectSteps(ErrorMessageHolder errorMessageHolder, ProjectMenuHolder projectMenuHolder){
        this.projectMenuHolder = projectMenuHolder;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("A user is logged in and has a project selected.")
    public void a_user_is_logged_in_and_has_a_project_selected() {
        user = "giig";
        AuthenticationService as = new AuthenticationService(user);

        assertTrue(as.loginSuccessful());
        project = projectMenuHolder.getProjectMenu().getProject("Awesome Project");
    }

    @Given("A user exists with the initials {string}.")
    public void a_user_exists_with_the_initials(String string) {
        assertTrue(employeeBase.containsEmployee(string));
    }

    @When("An existing user adds {string} to a project")
    public void an_existing_user_adds_to_a_project(String string) throws Exception {
        try {
            project.addEmployeeToProject(string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
