package application;

import java.util.ArrayList;
import java.util.List;


public class EmployeeBase { //Lucas

    public static ArrayList<Employee> employeeBase = new ArrayList<>();


    public void createEmployee(String employeeInitials) throws ExceptionHandler{
        if(employeeInitials.length() <= 4 && employeeInitials.length() >= 1 && employeeInitials.matches("[a-zA-Z]+" )){ //1
            if (!containsEmployee(employeeInitials)){                                                                         //2
                Employee employee = new Employee(employeeInitials);                                                           //3
                employeeBase.add(employee);                                                                                   //4
            } else {                                                                                                          //5
                throw new ExceptionHandler("Employee with given initials already exist, please input new initials.");     //6
            }
        } else {                                                                                                              //7
            throw new ExceptionHandler("Initials doesn't fit the restrictions, please input new initials.");              //8
        }
    }

    public void createEmployeeWhiteBox(String employeeInitials) throws ExceptionHandler{

        assert (employeeInitials.length() <= 4
                && employeeInitials.length() >= 1
                && employeeInitials.matches("[a-zA-Z]+")) : "Initials doesn't fit the restrictions, please input new initials."; //Precondition 1

        assert !containsEmployee(employeeInitials) : "Employee with given initials already exist, please input new initials.";         //Precondition 2

        if(employeeInitials.length() <= 4 && employeeInitials.length() >= 1 && employeeInitials.matches("[a-zA-Z]+" )){ //1
            if (!containsEmployee(employeeInitials)){                                                                         //2
                Employee employee = new Employee(employeeInitials);                                                           //3
                employeeBase.add(employee);                                                                                   //4
            } else {                                                                                                          //5
                throw new ExceptionHandler("Employee with given initials already exist, please input new initials.");     //6
            }
        } else {                                                                                                              //7
            throw new ExceptionHandler("Initials doesn't fit the restrictions, please input new initials.");              //8
        }

        assert containsEmployee(employeeInitials) : "The employee was not added to EmployeeBase";                //Postcondition
    }

    public static boolean containsEmployee(String employeeInitials) {
        return employeeBase.stream().anyMatch(e -> e.getEmployeeInitials().equals(employeeInitials));
    }

    public static Employee getEmployee(String initials){
        return employeeBase.stream().filter(e-> e.getEmployeeInitials().equals(initials)).findFirst().get();
    }

    public static List<String> getEmployeeNames(List<Employee> list){
        List<String> employeeNames = new ArrayList<>();
        for (Employee employee : list) {
            employeeNames.add(employee.getEmployeeInitials());
        }
        return employeeNames;
    }
}
