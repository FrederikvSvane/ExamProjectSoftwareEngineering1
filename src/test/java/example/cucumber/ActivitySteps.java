package example.cucumber;

import application.AuthenticationService;
import application.EmployeeBase;
import application.ExceptionHandler;
import application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

    @Given("That a user is logged in and have selected an existing project.")
    public void that_a_user_is_logged_in_and_have_selected_an_existing_project() {
        user = "giig";
        AuthenticationService as = new AuthenticationService(user);

        assertTrue(as.loginSuccessful());
        project = projectMenuHolder.getProjectMenu().getProject("Awesome Project");
    }

    @When("a user adds an activity with activityName {string}, with budgettetHours {int} a start week {int} and duration {int}")
    public void a_user_adds_an_activity_with_activity_name_with_budgettet_hours_a_start_week_and_duration(String string, Integer int1, Integer int2, Integer int3) throws ExceptionHandler {
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

    @When("a user adds an activity with activityName {string}, with budgettetHours {string} a start week {int} and duration {int}")
    public void a_user_adds_an_activity_with_activity_name_with_budgettet_hours_a_start_week_and_duration(String string, String string2, Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
