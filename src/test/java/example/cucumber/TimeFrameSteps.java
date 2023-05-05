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
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TimeFrameSteps {

    private Project project;
    private EmployeeBase employeeBase = new EmployeeBase();

    private ErrorMessageHolder errorMessageHolder;
    private ProjectMenuHolder projectMenuHolder;

    public TimeFrameSteps(ErrorMessageHolder errorMessageHolder, ProjectMenuHolder projectMenuHolder){
        this.projectMenuHolder = projectMenuHolder;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("a project is selected")
    public void aProjectIsSelected() {
        project = projectMenuHolder.getProjectMenu().getProject("Awesome Project");
    }

    @When("the project is a start week number of {int} and {int} weeks duration")
    public void setTimeframeIsGivenAnd(Integer startDate, Integer duration) throws ExceptionHandler{
        try {
            project.setTimeframe(startDate, duration);
        } catch (ExceptionHandler e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("project start date is set to {int}")
    public void projectStartDateIsSetTo(Integer startDate) {
        assertEquals(project.getStartDate(), startDate);
    }

    @Then("project end date is set to {int}")
    public void projectEndDateIsSetTo(Integer endDate) {
        assertEquals(project.getEndDate(), endDate);
    }

    @When("the project is changed to a start week number of {int} and {int} weeks duration")
    public void theProjectIsChangedToAStartWeekNumberOfAndWeeksDuration(Integer startDate, Integer duration) {
        try {
            project.setTimeframe(startDate, duration);
        } catch (ExceptionHandler e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
