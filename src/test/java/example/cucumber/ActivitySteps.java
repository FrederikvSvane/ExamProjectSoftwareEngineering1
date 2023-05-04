package example.cucumber;

import application.*;
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



    @When("An activity {string} exists in project {string}")
    public void anActivityExistsInProject(String activityName, String projectName){
        project = projectMenuHolder.getProjectMenu().getProject(projectName);
        assertTrue(project.activityExists(activityName));
    }

    @When("An activity {string} is added to project {string}")
    public void anActivityIsAddedToProject(String activityName, String projectName) throws Exception{
        project = projectMenuHolder.getProjectMenu().getProject(projectName);
        project.addProjectActivity(activityName, 10, 1, 2);
    }



//    @Given("The user has {int} unregistered work hours")
//    public void theUserHasUnregisteredWorkHours(Integer int1) {
//
//    }

    @When("The user adds {int} work hours to {string} in project {string}")
    public void theUserAddsWorkHoursToInProject(Integer hours, String activityName, String projectName) {
        Activity activity= projectMenuHolder.getProjectMenu().getProject(projectName).getActivity(activityName);
        try {
            activity.addHours(AuthenticationService.getUsername(), hours);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("{int} work hours are added to {string} in project {string}")
    public void workHoursAreAddedToInProject(Integer int1, String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("No work hours are added to {string} in project {string}")
    public void noWorkHoursAreAddedToInProject(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("An activity {string} does not exist in project {string}")
    public void anActivityDoesNotExistInProject(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
