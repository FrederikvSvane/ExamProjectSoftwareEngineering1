package application;

import java.util.HashMap;

public abstract class Activity{
    public int hours;
    public String activityName;
    public HashMap<Employee,Integer> employeeHours;

    public void addHours(Employee employee,int hours){
        if(employeeHours.containsKey(employee)){
            employeeHours.put(employee, employeeHours.get(employee) + hours);
        } else {
            employeeHours.put(employee, hours);
        }
        this.hours += hours;
    }

}
