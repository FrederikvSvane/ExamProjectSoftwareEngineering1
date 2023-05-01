package example.cucumber;

import application.AuthenticationService;
import application.Project;
import application.ProjectMenu;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProjectSteps {

    private ProjectMenu projectMenu;

    private MockDateHolder mockDateHolder;

    private ErrorMessageHandler errorMessageHandler;

    String user;


    public ProjectSteps(ProjectMenu base, ErrorMessageHandler errorMessageHandler) {
        this.projectMenu = base;
        mockDateHolder = new MockDateHolder(projectMenu);
        mockDateHolder.setDate(new GregorianCalendar());
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
        assertFalse(projectMenu.projectExists(string));
    }

    @Given("the year is {int}")
    public void the_year_is(Integer year) {
        assertEquals(ProjectMenu.getDate().get(Calendar.YEAR), (int) year);
    }

    @Given("there has been created {int} projects in {int}")
    public void there_has_been_created_projects_in(Integer numOfProjects, Integer year) {
        assertEquals(ProjectMenu.getProjectsCreatedInYear(year), (int) numOfProjects);
    }

    @When("user creates project with name {string}")
    public void user_creates_project_with_name(String projectName) throws Exception{
        try {
            projectMenu.addProject(projectName);
        } catch (Exception e) {
            errorMessageHandler.setErrorMessage(e.getMessage());
        }
    }

    @Then("a new project with the name {string} and project ID {int} is created")
    public void a_new_project_with_the_name_and_project_id_is_created(String projectName, int projectID) {
        assertTrue(projectMenu.projectExists(projectName));
        assertEquals(projectMenu.getProject(projectName).getProjectID(), projectID);
    }

    @Given("a project with name {string} exists")
    public void a_project_with_name_exists(String projectName) throws Exception{
        try{
            projectMenu.addProject(projectName);
        assertTrue(projectMenu.projectExists(projectName));
        }catch(Exception e){
            errorMessageHandler.setErrorMessage(e.getMessage());
        }
    }

}
