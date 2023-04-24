package application;

public class Employee {
    private String employeeInitials;
    private int currentHours;

    public Employee(String employeeInitials, int currentHours){
        this.employeeInitials = employeeInitials;
        this.currentHours = currentHours;
    }

    public String getEmployeeInitials() {
        return employeeInitials;
    }
}
