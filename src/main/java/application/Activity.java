package application;

import java.util.HashMap;

public abstract class Activity{
    private int hours;
    public String activityName;
    public HashMap<Employee,Integer> employeeHours = new HashMap<Employee, Integer>();

    public void addHours(String employeeInitials,int hours) throws Exception{
        if(!AuthenticationService.loginStatus()){                                      //1
            throw new ExceptionHandler("User must be logged in to add hours");      //2
        } else if (hours <= 0) {                                                       //3
            throw new ExceptionHandler("Invalid number of hours");                  //4
        } else {                                                                       //5
            Employee employee = EmployeeBase.getEmployee(employeeInitials);            //6
            if (employeeHours.containsKey(employee)) {                                 //7
                employeeHours.put(employee, employeeHours.get(employee) + hours);      //8
            } else {                                                                   //9
                employeeHours.put(employee, hours);                                    //10
            }
            this.hours += hours;                                                       //11
        }
    }
    public int getHours(){return hours;
    }

    public String getActivityName(){
        return activityName;
    }


    public int getEmployeeHours(String employeeInitials) {
        Employee employee = EmployeeBase.getEmployee(employeeInitials);
    	return employeeHours.get(employee);
    }



}
