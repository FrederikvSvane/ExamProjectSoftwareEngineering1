package example.cucumber;

import application.EmployeeBase;
import application.ExceptionHandler;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class EmployeeTest {
    EmployeeBase base;
    private ErrorMessageHandler errorMessageHandler;
    public EmployeeTest(EmployeeBase base, ErrorMessageHandler errorMessageHandler){
        this.base = base;
        this.errorMessageHandler = errorMessageHandler;
    }



    @When("createEmployee is given {string}")
    public void create_employee_is_given_string(String initials) throws Exception{
        // Write code here that turns the phrase above into concrete actions
        try{
            base.createEmployee(initials);
        } catch (ExceptionHandler e){
            errorMessageHandler.setErrorMessage(e.getMessage());
        }

    }

    @Then("employee {string} is added to the employeeDatabase")
    public void employee_is_added_to_the_employee_database(String employee){
        // Write code here that turns the phrase above into concrete action
        assertTrue(base.containsEmployee(employee));
    }

    @Then("error message {string}")
    public void error_message(String err) throws Exception{
        // Write code here that turns the phrase above into concrete actions
        assertEquals(err, this.errorMessageHandler.getErrorMessage());
    }

}
