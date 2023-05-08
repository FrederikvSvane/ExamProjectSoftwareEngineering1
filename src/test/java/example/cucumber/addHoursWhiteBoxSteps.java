package example.cucumber;

import application.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class addHoursWhiteBoxSteps {

    private String user;

    private Project project;
    private EmployeeBase employeeBase;

    private String errorMessage;

    private Activity activity;

    private ErrorMessageHolder errorMessageHolder;
    private ProjectMenuHolder projectMenuHolder;

    public addHoursWhiteBoxSteps(ErrorMessageHolder errorMessageHolder, ProjectMenuHolder projectMenuHolder, EmployeeBase employeeBase) {
        this.employeeBase = employeeBase;
        this.projectMenuHolder = projectMenuHolder;
        this.errorMessageHolder = errorMessageHolder;
    }


    @When("the method addHours is called with parameters employeeInitials {string} and hours {int}")
    public void theMethodAddHoursIsCalledWithParametersEmployeeInitialsAndHours(String employeeInitials, Integer hours) throws Exception {
        try {
            activity.addHoursWhiteBox(employeeInitials, hours);
        } catch (AssertionError e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("employee {string} is added to the employeeHours")
    public void employeeIsAddedToTheEmployeeHours(String employeeInitials) {
        Employee employee = new Employee(employeeInitials);
        projectMenuHolder.getProjectMenu().getOffWorkActivity("exampleActivity").employeeHours.put(employee,0);
    }

    @Then("the activity {string} has {int} hours")
    public void theActivityHasHours(String activityName, Integer hours) {
        assertEquals(hours, projectMenuHolder.getProjectMenu().getOffWorkActivity(activityName).getHours());
    }

    @Given("employee {string} does not exist in employeeHours")
    public void employeeDoesNotExistInEmployeeHours(String employeeInitials) {
        assertFalse(projectMenuHolder.getProjectMenu().getOffWorkActivity("exampleActivity").employeeHours.containsKey(new Employee(employeeInitials)));
    }

    @Then("\\({string},{int}) is added to employeeHours")
    public void isAddedToEmployeeHours(String employeeInitials, Integer hours) {
        Activity activity = projectMenuHolder.getProjectMenu().getOffWorkActivity("exampleActivity");

        List<Employee> matchingEmployees = activity.employeeHours.keySet().stream()
                .filter(employee -> employee.getEmployeeInitials().equals(employeeInitials))
                .collect(Collectors.toList());

        assertEquals(1, matchingEmployees.size());
        assertEquals(hours, activity.employeeHours.get(matchingEmployees.get(0)));
    }

    @Given("An activity {string} exists")
    public void anActivityExists(String exampleActivity) throws Exception {
        Employee user = new Employee("user");
        EmployeeBase employeeBase = new EmployeeBase();
        EmployeeBase.employeeBase.add(user);
        AuthenticationService as = new AuthenticationService("user");
        as.loginSuccessful();
        projectMenuHolder.getProjectMenu().createOffWorkActivity("exampleActivity");
        activity = projectMenuHolder.getProjectMenu().getOffWorkActivity("exampleActivity");
        as.logout();
    }

    @Given("employee {string} is added to the employeeBase")
    public void employeeIsAddedToTheEmployeeBase(String employeeInitials) throws Exception{
        try{
            employeeBase.createEmployee(employeeInitials);
        } catch (ExceptionHandler e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Then("error message {string} is gotten")
    public void errorMessageIsGotten(String expectedErrorMessage) {

        assertEquals(expectedErrorMessage, errorMessage);
    }


}
