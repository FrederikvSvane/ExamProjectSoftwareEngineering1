package example.cucumber;

import application.AuthenticationService;
import application.EmployeeBase;
import application.ExceptionHandler;
import application.ProjectMenu;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProjectSteps {

    private ProjectMenu projectBase;
    private ErrorMessageHandler errorMessageHandler;

    String user;


    public ProjectSteps(ProjectMenu base, ErrorMessageHandler errorMessageHandler) {
        this.projectBase = base;
        this.errorMessageHandler = errorMessageHandler;
    }


    @Given("a user is logged in")
    public void a_user_is_logged_in() throws Exception {

        user = "giig";
        AuthenticationService as = new AuthenticationService(user);

        assertTrue(as.loginSuccessful());
    }

    @Given("a project with name {string} does not exist")
    public void a_project_with_name_does_not_exist(String string) throws Exception{
        assertFalse(projectBase.projectExists(string));
    }

    @Given("the year is {int}")
    public void the_year_is(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there has been created {int} projects in {int}")
    public void there_has_been_created_projects_in(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("user creates project with name {string}")
    public void user_creates_project_with_name(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("a new project with the name {string} and project ID {string} is created")
    public void a_new_project_with_the_name_and_project_id_is_created(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a project with name {string} exists")
    public void a_project_with_name_exists(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("a new project is not created")
    public void a_new_project_is_not_created() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
