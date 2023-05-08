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
public class ProjectLeaderSteps {
    private String user;
    private String user1;
    private String employee;
    private Project project;
    private ProjectMenuHolder projectMenuHolder;

    private MockDateHolder mockDateHolder;

    private ErrorMessageHolder errorMessageHolder;
    private EmployeeBase employeeBase;
    public ProjectLeaderSteps(ErrorMessageHolder errorMessageHolder, ProjectMenuHolder projectMenuHolder, EmployeeBase employeeBase){
        this.projectMenuHolder = projectMenuHolder;
        mockDateHolder = new MockDateHolder(this.projectMenuHolder.getProjectMenu());
        mockDateHolder.setDate(new GregorianCalendar());
        this.errorMessageHolder = errorMessageHolder;
        this.employeeBase = employeeBase;
    }
    @Given("a user is logged in and have selected a project")
    public void that_i_am_logged_in_and_have_selected_a_project() {
        // Write code here that turns the phrase above into concrete actions
        user = "giig";
        AuthenticationService as = new AuthenticationService(user);

        assertTrue(as.loginSuccessful());

        project = projectMenuHolder.getProjectMenu().getProject("Not cool project");
    }

    @Given("a user has an employee with username {string}")
    public void i_have_an_employee_with_username(String string) {
        // Write code here that turns the phrase above into concrete actions
        employee = string;

        assertTrue(employeeBase.containsEmployee(string));
    }

    @When("a user add the employee {string} as project leader")
    public void i_add_the_employee_as_project_leader(String employeeInitials) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        try{
            projectMenuHolder.getProjectMenu().getProject("Not cool project").setProjectLeader(employeeInitials);
        }catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the employee is marked as project leader")
    public void the_employee_is_marked_as_project_leader() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(projectMenuHolder.getProjectMenu().getProject("Not cool project").getProjectLeader().getEmployeeInitials(), employee);
    }
    @When("a user add the employee with username {string} as a project leader")
    public void i_add_the_employee_with_username_as_a_project_leader(String string) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        try{
            projectMenuHolder.getProjectMenu().getProject("Not cool project").setProjectLeader(string);
        }catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("a user adds the employee as a project leader")
    public void i_add_the_employee_as_a_project_leader() {
        // Write code here that turns the phrase above into concrete actions
        try{
            projectMenuHolder.getProjectMenu().getProject("Not cool project").setProjectLeader(employee);
        }catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Given("a user is logged in and have selected a project, and project already have a projectleader")
    public void that_i_am_logged_in_and_have_selected_a_project_and_project_already_have_a_projectleader() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        user = "giig";
        user1 = "mate";
        AuthenticationService as = new AuthenticationService(user);

        assertTrue(as.loginSuccessful());

        project = projectMenuHolder.getProjectMenu().getProject("Not cool project");

        try{
            projectMenuHolder.getProjectMenu().getProject("Not cool project").setProjectLeader(user1);
        }catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @When("a user remove the projectleader from the project")
    public void i_remove_the_projectleader_from_the_project() {
        // Write code here that turns the phrase above into concrete actions
        projectMenuHolder.getProjectMenu().getProject("Not cool project").removeProjectLeader();
    }

    @Then("the employee is no longer marked as project leader")
    public void the_employee_is_no_longer_marked_as_project_leader() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(projectMenuHolder.getProjectMenu().getProject("Not cool project").getProjectLeader().getEmployeeInitials(), "none");
    }


}
