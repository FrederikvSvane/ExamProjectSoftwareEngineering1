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

    public EmployeeProjectSteps(ErrorMessageHolder errorMessageHolder, ProjectMenuHolder projectMenuHolder) {
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
    public void a_user_exists_with_the_initials(String employeeInitials) {
        assertTrue(employeeBase.containsEmployee(employeeInitials));
    }

    @When("An existing user adds {string} to a project")
    public void an_existing_user_adds_to_a_project(String employeeInitials) throws Exception {
        try {
            project.addEmployeeToProject(employeeInitials);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("An existing user {string} has been added to the project")
    public void anExistingUserHasBeenAddedToTheProject(String employeeInitials) throws Exception {
        try {
            project.addEmployeeToProject(employeeInitials);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("An existing user removes {string} from a project")
    public void anExistingUserRemovesFromAProject(String employeeInitials) throws Exception {
        try {
            project.removeEmployeeFromProject(employeeInitials);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("user {string} is removed from the project")
    public void userIsRemovedFromTheProject(String employeeInitials) {
        assertFalse(project.containsEmployee(employeeInitials));
    }


    @Then("user {string} is added to the project")
    public void userIsAddedToTheProject(String employeeInitials) {
        assertTrue(project.containsEmployee(employeeInitials));
    }

    @Then("project {string} is added to user {string}")
    public void projectIsAddedToUser(String projectName, String employeeInitials) {
        assertTrue(employeeBase.getEmployee(employeeInitials).containsProject(projectName));
    }

    @Then("{string} is not added to project {string}")
    public void isNotAddedToProject(String employeeInitials, String projectName) {
        assertFalse(employeeBase.getEmployee(employeeInitials).containsProject(projectName));
    }

    @When("An existing user adds {string} to a project. WhiteBox")
    public void an_existing_user_adds_to_a_project_white_box(String string) throws Exception{
        try {
            project.addEmployeeToProjectWhiteBox(string);
        } catch (AssertionError e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("error message {string}. WhiteBox")
    public void error_message_white_box(String error) {
        assertEquals(error, errorMessageHolder.getErrorMessage());
    }

}



