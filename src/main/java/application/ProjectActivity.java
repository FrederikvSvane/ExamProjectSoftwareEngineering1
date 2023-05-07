package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ProjectActivity extends Activity {
    private int startWeek;
    private int numberOfWeeks;
    private int budgetedHours;
    private int endDate;
    private int totalHours;
    private String pName;

    public ProjectActivity(String activityName, int budgetedHours, int startWeek, int numberOfWeeks) {
        this.activityName = activityName;
        this.budgetedHours = budgetedHours;
        this.startWeek = startWeek;
        this.numberOfWeeks = numberOfWeeks;
        this.totalHours = getHours();
        updateEndDate();
    }

    private void updateEndDate() {
        endDate = (startWeek + numberOfWeeks);
    }


    public void setTime(int startWeek, int numberOfWeeks) throws ExceptionHandler {
        if (startWeek <= 0 || startWeek > 52 || numberOfWeeks <= 0) {
            throw new ExceptionHandler("The given time is invalid");
        } else {
            this.startWeek = startWeek;
            this.numberOfWeeks = numberOfWeeks;
            updateEndDate();
        }

    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) throws ExceptionHandler {
        if (startWeek <= 0 || startWeek > 52) {
            throw new ExceptionHandler("The given time is invalid");
        } else {
            this.startWeek = startWeek;
            updateEndDate();
        }
    }


    public void setDuration(int numberOfWeeks) throws ExceptionHandler {
        if (numberOfWeeks <= 0) {
            throw new ExceptionHandler("The given time is invalid");
        } else {
            this.numberOfWeeks = numberOfWeeks;
            updateEndDate();
        }
    }

    public int getEndDate() {
        return endDate;
    }


    public int getBudgetedHours(){
        return budgetedHours;
    }
    public int getTotalHours(){return totalHours;}
    public void setTotalHours(int totalHours){
        this.totalHours = getHours();
    }

    public String getpName(){return pName;}



    public void setBudgetedHours(Integer budgetedHours) {
        this.budgetedHours = budgetedHours;
    }

}
