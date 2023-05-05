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

    public ActivitySteps(ErrorMessageHolder errorMessageHolder, ProjectMenuHolder projectMenuHolder) {
        this.projectMenuHolder = projectMenuHolder;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("an off work activity with name {string} exists")
    public void anOffWorkActivityWithNameExists(String activityName) throws Exception {
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
        try {
            project.addProjectActivity(string, int1, int2, int3);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity with name {string} is added to the project")
    public void the_activity_with_name_is_added_to_the_project(String string) {
        assertTrue(project.activityExists(string));
    }


    @When("a user adds an activity with activityName {string}")
    public void aUserAddsAnActivityWithActivityName(String activityName) throws Exception {
        try {
            projectMenuHolder.getProjectMenu().createOffWorkActivity(activityName);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity with name {string} is added to off-work activities")
    public void theActivityWithNameIsAddedToOffWorkActivities(String activityName) {
        assertTrue(projectMenuHolder.getProjectMenu().offWorkActivityExists(activityName));
    }


    @When("An activity {string} exists in project {string}")
    public void anActivityExistsInProject(String activityName, String projectName) {
        project = projectMenuHolder.getProjectMenu().getProject(projectName);
        assertTrue(project.activityExists(activityName));
    }

    @Given("An activity {string} is added to project {string}")
    public void anActivityIsAddedToProject(String activityName, String projectName) throws Exception {
        project = projectMenuHolder.getProjectMenu().getProject(projectName);
        try {
            project.addProjectActivity(activityName, 10, 1, 2);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }


//    @Given("The user has {int} unregistered work hours")
//    public void theUserHasUnregisteredWorkHours(Integer int1) {
//
//    }

    @When("The user adds {int} work hours to {string} in project {string}")
    public void theUserAddsWorkHoursToInProject(Integer hours, String activityName, String projectName) throws Exception {
        try {
            Activity activity = projectMenuHolder.getProjectMenu().getProject(projectName).getActivity(activityName);
            activity.addHours(AuthenticationService.getLoggedInUser(), hours);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("{int} work hours are added to {string} in project {string}")
    public void workHoursAreAddedToInProject(Integer hours, String activityName, String projectName) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        try {
            assertEquals(hours, projectMenuHolder.getProjectMenu().getProject(projectName).getActivity(activityName).getEmployeeHours(AuthenticationService.getLoggedInUser()));
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("An activity {string} does not exist in project {string}")
    public void anActivityDoesNotExistInProject(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(false, projectMenuHolder.getProjectMenu().getProject(string2).activityExists(string));
    }

    @When("the user adds the week {int} as start week and the length of the activity is {int} to activity {string} in project {string}")
    public void theUserAddsTheWeekAsStartWeekAndTheLengthOfTheActivityIsToActivityInProject(Integer startWeek, Integer duration, String activityName, String projectName) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        try {
            projectMenuHolder.getProjectMenu().getProject(projectName).getActivity(activityName).setTime(startWeek, duration);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Then("The user have added a start week {int} and stopdate {int} to the activity {string} in project {string}")
    public void theUserHaveAddedAStartAndStopdateToTheActivity( int startWeek, int stopDate, String activityName, String projectName) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(startWeek, projectMenuHolder.getProjectMenu().getProject(projectName).getActivity(activityName).getStartWeek());
        assertEquals(stopDate, projectMenuHolder.getProjectMenu().getProject(projectName).getActivity(activityName).getEndDate());

    }


}
