package example.cucumber;

import application.EmployeeBase;
import application.Project;

import application.AuthenticationService;
import application.EmployeeBase;
import application.ExceptionHandler;
import application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OffWorkActivitySteps {

    private String user;

    private Project project;
    private EmployeeBase employeeBase = new EmployeeBase();

    private ErrorMessageHolder errorMessageHolder;
    private ProjectMenuHolder projectMenuHolder;

    public OffWorkActivitySteps(ErrorMessageHolder errorMessageHolder, ProjectMenuHolder projectMenuHolder) {
        this.projectMenuHolder = projectMenuHolder;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("a user {string} is logged in")
    public void aUserIsLoggedIn(String user) {
        AuthenticationService as = new AuthenticationService(user);
        assertTrue(as.loginSuccessful());
        assertEquals(user, as.getLoggedInUser());
    }

    @When("a user adds {int} hours to off-work activity {string}")
    public void aUserAddsHoursToOffWorkActivity(Integer hours, String activityName) throws Exception {
        try {
            projectMenuHolder.getProjectMenu().addHoursToOffWorkActivity(activityName, AuthenticationService.getLoggedInUser(), hours);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity {string} has {int} hours on user")
    public void theActivityHasHoursOnUser(String activityName, Integer hours) {
        assertEquals(hours, projectMenuHolder.getProjectMenu().getOffWorkActivity(activityName).getEmployeeHours(AuthenticationService.getLoggedInUser()));
    }

}
