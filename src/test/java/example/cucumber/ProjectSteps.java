package example.cucumber;

import application.AuthenticationService;
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

    private ProjectHolder projectHolder;
    private ProjectMenuHolder projectMenuHolder;

    private MockDateHolder mockDateHolder;

    private ErrorMessageHolder errorMessageHolder;

    String user;


    public ProjectSteps(ErrorMessageHolder errorMessageHolder, ProjectMenuHolder projectMenuHolder, ProjectHolder projectHolder){
        this.projectMenuHolder = projectMenuHolder;
        mockDateHolder = new MockDateHolder(this.projectMenuHolder.getProjectMenu());
        mockDateHolder.setDate(new GregorianCalendar());
        this.errorMessageHolder = errorMessageHolder;
        this.projectHolder = projectHolder;
    }


    @Given("a user is logged in")
    public void a_user_is_logged_in() throws Exception {

        user = "giig";
        AuthenticationService as = new AuthenticationService(user);

        assertTrue(as.loginSuccessful());
    }

    @Given("a project with name {string} does not exist")
    public void a_project_with_name_does_not_exist(String string) throws Exception{
        assertFalse(projectMenuHolder.getProjectMenu().projectExists(string));
    }

    @Given("the year is {int}")
    public void the_year_is(Integer year) {
        assertEquals(ProjectMenu.getDate().get(Calendar.YEAR), (int) year);
    }

    @Given("there has been created {int} projects in {int}")
    public void there_has_been_created_projects_in(Integer numOfProjects, Integer year) {
        projectMenuHolder.getProjectMenu().setProjectsCreatedInYear(numOfProjects, year);
        assertEquals(numOfProjects,ProjectMenu.getProjectsCreatedInYear(year));
    }

    @When("user creates project with name {string}")
    public void user_creates_project_with_name(String projectName) throws Exception{
        try {
            projectMenuHolder.getProjectMenu().addProject(projectName, 1, 1,1);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("a new project with the name {string} and project ID {int} is created")
    public void a_new_project_with_the_name_and_project_id_is_created(String projectName, int projectID) {
        assertTrue(projectMenuHolder.getProjectMenu().projectExists(projectName));
        assertEquals(projectMenuHolder.getProjectMenu().getProject(projectName).getProjectID(), projectID);
    }

    @Given("a project with name {string} exists")
    public void a_project_with_name_exists(String projectName) throws Exception{
        try{
            projectMenuHolder.getProjectMenu().addProject(projectName, 1, 1, 1);
        assertTrue(projectMenuHolder.getProjectMenu().projectExists(projectName));
        }catch(Exception e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("a user is not logged in")
    public void a_user_is_not_logged_in() {
        AuthenticationService.logout();
    }



}
