package example.cucumber;

import application.AuthenticationService;
import application.EmployeeBase;
import application.ExceptionHandler;
import application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActivitySteps {

    private String user;

    private Project project;
    private EmployeeBase employeeBase = new EmployeeBase();

    private ErrorMessageHolder errorMessageHolder;
    private ProjectMenuHolder projectMenuHolder;

    public ActivitySteps(ErrorMessageHolder errorMessageHolder, ProjectMenuHolder projectMenuHolder){
        this.projectMenuHolder = projectMenuHolder;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("an off work activity with name {string} exists")
    public void anOffWorkActivityWithNameExists(String activityName) throws Exception{
        projectMenuHolder.getProjectMenu().createOffWorkActivity(activityName);
    }

    @Given("That a user is logged in and have selected an existing project.")
    public void that_a_user_is_logged_in_and_have_selected_an_existing_project() {
        user = "giig";
        AuthenticationService as = new AuthenticationService(user);

        assertTrue(as.loginSuccessful());
        project = projectMenuHolder.getProjectMenu().getProject("Awesome Project");
    }

    @When("a user adds an activity with activityName {string}, with {int} budgeted hours, a start week {int} and duration of {int} weeks")
    public void a_user_adds_an_activity_with_activity_name_with_budgeted_hours_a_start_week_and_duration(String string, Integer int1, Integer int2, Integer int3) throws Exception {
        try{
            project.addProjectActivity(string, int1, int2, int3);
        }catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity with name {string} is added to the project")
    public void the_activity_with_name_is_added_to_the_project(String string) {
        assertTrue(project.activityExists(string));
    }


    @When("a user adds an activity with activityName {string}")
    public void aUserAddsAnActivityWithActivityName(String activityName) throws Exception{
        try{
            projectMenuHolder.getProjectMenu().createOffWorkActivity(activityName);
        }catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity with name {string} is added to off-work activities")
    public void theActivityWithNameIsAddedToOffWorkActivities(String activityName) {
        assertTrue(projectMenuHolder.getProjectMenu().offWorkActivityExists(activityName));
    }



}
