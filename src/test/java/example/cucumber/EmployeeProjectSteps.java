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

public class EmployeeProjectSteps {
    @Given("A user is logged in and has a project selected.")
    public void a_user_is_logged_in_and_has_a_project_selected() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("A user is created with the initials “lusj“.")
    public void a_user_is_created_with_the_initials_lusj() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("An existing user adds a new employee to a project")
    public void an_existing_user_adds_a_new_employee_to_a_project() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("The system throws an error, because the employee is already assigned to the project")
    public void the_system_throws_an_error_because_the_employee_is_already_assigned_to_the_project() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
