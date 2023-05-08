package application;


public class ProjectActivity extends Activity {
    private int startWeek;
    private int numberOfWeeks;
    private int budgetedHours;
    private int endDate;

    private String pName;

    public ProjectActivity(String activityName, int budgetedHours, int startWeek, int numberOfWeeks) {
        this.activityName = activityName;
        this.budgetedHours = budgetedHours;
        this.startWeek = startWeek;
        this.numberOfWeeks = numberOfWeeks;
        updateEndDate();
    }

    private void updateEndDate() {
        endDate = (startWeek + numberOfWeeks);
    }


    public void setTime(int startWeek, int numberOfWeeks) throws ExceptionHandler { //Frederik
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

    public void setStartWeek(int startWeek) throws ExceptionHandler { //Lucas
        if (startWeek <= 0 || startWeek > 52) {
            throw new ExceptionHandler("The given time is invalid");
        } else {
            this.startWeek = startWeek;
            updateEndDate();
        }
    }


    public void setDuration(int numberOfWeeks) throws ExceptionHandler { //Lucas
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

    public void setBudgetedHours(Integer budgetedHours) {
        this.budgetedHours = budgetedHours;
    }

}
