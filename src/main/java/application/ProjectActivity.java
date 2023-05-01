package application;

public class ProjectActivity extends Activity{
    public int startWeek;
    public int numberOfWeeks;
    public int budgetedHours;

    public ProjectActivity(String activityName, int budgetedHours, int startWeek, int numberOfWeeks){
        this.activityName = activityName;
        this.budgetedHours = budgetedHours;
        this.startWeek = startWeek;
        this.numberOfWeeks = numberOfWeeks;
    }

//    public void displayProjectActivity(){
//
//    }

//    public void backToProjectMenu(){
//
//    }

    public void setTime(int startWeek, int numberOfWeeks){
        this.startWeek = startWeek;
        this.numberOfWeeks = numberOfWeeks;
    }

}
