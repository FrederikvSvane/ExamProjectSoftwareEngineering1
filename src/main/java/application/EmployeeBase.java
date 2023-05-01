package application;

import java.util.ArrayList;

public class EmployeeBase {
    public static ArrayList<Employee> employeeBase = new ArrayList<Employee>();

    public void createEmployee(String employeeInitials) throws ExceptionHandler{
        if(employeeInitials.length() <= 4){
            if (!containsEmployee(employeeInitials)){
                Employee employee = new Employee(employeeInitials, 0);
                employeeBase.add(employee);
            } else {
                throw new ExceptionHandler("Employee with given initials already exist, please input new initials.");
            }
        } else {
            throw new ExceptionHandler("Initials doesn’t fit the restrictions, please input new initials.");
        }

    }

    public boolean checkInitials(String employeeInitials){
        return employeeBase.contains(employeeInitials);
    }

    public boolean containsEmployee(String employeeInitials) {
        return employeeBase.stream().anyMatch(e -> e.getEmployeeInitials().equals(employeeInitials));
    }
}
