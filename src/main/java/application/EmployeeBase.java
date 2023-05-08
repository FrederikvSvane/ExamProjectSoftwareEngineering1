package application;

import java.util.ArrayList;
import java.util.List;


public class EmployeeBase {

    public static ArrayList<Employee> employeeBase = new ArrayList<Employee>();


    public void createEmployee(String employeeInitials) throws ExceptionHandler{
        if(employeeInitials.length() <= 4 && employeeInitials.length() >= 1 && employeeInitials.matches("[a-zA-Z]+" )){
            if (!containsEmployee(employeeInitials)){
                Employee employee = new Employee(employeeInitials);
                employeeBase.add(employee);
            } else {
                throw new ExceptionHandler("Employee with given initials already exist, please input new initials.");
            }
        } else {
            throw new ExceptionHandler("Initials doesn’t fit the restrictions, please input new initials.");
        }

    }

    /*public boolean checkInitials(String employeeInitials){
        return employeeBase.contains(employeeInitials);
    }*/

    public static boolean containsEmployee(String employeeInitials) {
        return employeeBase.stream().anyMatch(e -> e.getEmployeeInitials().equals(employeeInitials));
    }

    public static Employee getEmployee(String initials){
        return employeeBase.stream().filter(e-> e.getEmployeeInitials().equals(initials)).findFirst().get();
    }

    public static List<String> getEmployeeNames(List<Employee> list){
        List<String> employeeNames = new ArrayList<String>();
        for (Employee employee : list) {
            employeeNames.add(employee.getEmployeeInitials());
        }
        return employeeNames;
    }
}
