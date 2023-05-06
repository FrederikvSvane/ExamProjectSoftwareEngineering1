package application;

import java.util.HashMap;

public abstract class Activity{
    private int hours;
    public String activityName;
    public HashMap<Employee,Integer> employeeHours = new HashMap<Employee, Integer>();

    public void addHours(String employeeInitials,int hours) throws Exception{
        if(!AuthenticationService.loginStatus()){
            throw new ExceptionHandler("User must be logged in to add hours");
        } else if (hours <= 0) {
            throw new ExceptionHandler("Invalid number of hours");
        } else {
            Employee employee = EmployeeBase.getEmployee(employeeInitials);
            if (employeeHours.containsKey(employee)) {
                employeeHours.put(employee, employeeHours.get(employee) + hours);
            } else {
                employeeHours.put(employee, hours);
            }
            this.hours += hours;
        }
    }
    public int getHours(){return hours;
    }

    public String getActivityName(){
        return activityName;
    }

    public int getHours(){
        return hours;
    }

    public int getEmployeeHours(String employeeInitials) {
        Employee employee = EmployeeBase.getEmployee(employeeInitials);
    	return employeeHours.get(employee);
    }



}
