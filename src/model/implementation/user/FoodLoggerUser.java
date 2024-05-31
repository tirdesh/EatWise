package model.implementation.user;

import java.util.Date;
import model.implementation.day.Day;
import model.implementation.food_log.FoodLog;
import model.implementation.goal.Goal;

public class FoodLoggerUser extends AbstractUser {

    private Goal usersGoal;
    private FoodLog foodLog;
    private Day currentDay;
    private int currentWaterIntake; // New attribute to track water intake

    public FoodLoggerUser(String userName, Goal usersGoal, FoodLog foodLog, Day currentDay) {
        super(userName);
        this.usersGoal = (usersGoal != null) ? usersGoal : new Goal(0, 0, 0, 0);
        this.foodLog = foodLog;
        this.currentDay = (currentDay != null) ? currentDay : new Day(); // Ensure currentDay is not null
    }

    public FoodLoggerUser(String userName, Goal usersGoal, FoodLog foodLog) {
        this(userName, usersGoal, foodLog, null);
    }

    public FoodLoggerUser(String userName, Goal usersGoal) {
        this(userName, usersGoal, new FoodLog());
    }

    public FoodLoggerUser(String userName) {
        this(userName, null);
    }

    public void logDay(Day day) {
        foodLog.logDay(day);
    }

    private void startCurrentDay(Day day) {
        logDay(getCurrentDay());
        setCurrentDay(day);
    }

    public void startNewDayIfNeeded() {
        Date dateToCheck = new Date();
        String dateStringToCheck = Day.SDF.format(dateToCheck);
        if (!dateStringToCheck.equals(currentDay.getDateString()))
            startCurrentDay(new Day());
    }

    public Goal resetUsersGoal(Goal g) {
        Goal oldGoal = usersGoal;
        usersGoal = g;
        return oldGoal;
    }

    public String toString() {
        return getUserName() + ", currentDay=\n" + currentDay.toString() + ", foodLog=\n" + foodLog.toString();
    }

    public String toFullString() {
        return ("FoodLoggerUser [\nuserName=\n" + getUserName() + "\nusersGoal=\n"
                + getUsersGoal().toFullString().trim() + "\ncurrentDay=\n" + getCurrentDay().toFullString().trim()
                + "\nfoodLog=\n" + foodLog.toFullString().trim() + "\n]").trim();
    }

    public FoodLog getFoodLog() {
        return foodLog;
    }

    private void setFoodLog(FoodLog foodLog) {
        this.foodLog = foodLog;
    }

    public Day getCurrentDay() {
        return currentDay;
    }

    private void setCurrentDay(Day currentDay) {
        if (currentDay == null)
            currentDay = new Day();
        this.currentDay = currentDay;
    }

    public Goal getUsersGoal() {
        return usersGoal;
    }

    public void setUsersGoal(Goal usersGoal) {
        if (usersGoal == null)
            usersGoal = new Goal(0, 0, 0, 0);
        this.usersGoal = usersGoal;
        foodLog.setUsersGoal(usersGoal);
    }

    // Method to log water intake
    public void logWaterIntake(int amount) {
        this.currentWaterIntake += amount;
    }

    // Getter for current water intake
    public int getCurrentWaterIntake() {
        return currentWaterIntake;
    }

    // Setter for current water intake
    public void setCurrentWaterIntake(int currentWaterIntake) {
        this.currentWaterIntake = currentWaterIntake;
    }

}
