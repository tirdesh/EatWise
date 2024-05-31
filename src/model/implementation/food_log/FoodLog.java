package model.implementation.food_log;

import model.implementation.day.Day;
import model.implementation.goal.Goal;
import java.util.HashMap;
import java.util.Map;

public class FoodLog {

    private Map<String, Day> log;
    private Goal usersGoal;
    private String firstLoggedDay;
    private boolean containsALog;
    private int daysWereGoalsWhereMet;
    private int daysCounter;

    public FoodLog(String firstLoggedDay, boolean containsALog, int daysWereGoalsWhereMet, int daysCounter,
                   Goal usersGoal) {
        this.log = new HashMap<>();
        this.firstLoggedDay = firstLoggedDay;
        this.containsALog = containsALog;
        this.daysWereGoalsWhereMet = daysWereGoalsWhereMet;
        this.daysCounter = daysCounter;
        this.usersGoal = usersGoal;
    }

    public FoodLog(Goal usersGoal) {
        this("No food logs yet", false, 0, 0, usersGoal);
    }

    public FoodLog() {
        this(new Goal(0, 0, 0, 0));
    }

    public boolean logDay(Day day) {
        if (log.containsKey(day.getDateString()))
            return false;
        log.put(day.getDateString(), day);
        if (usersGoal != null && usersGoal.getCalories() != 0 && usersGoal.goalsMeet(day))
            daysWereGoalsWhereMet++;
        if (!isContainsALog()) {
            setContainsALog(true);
            setFirstLoggedDay(day.getDateString());
        }
        daysCounter++;
        return true;
    }

    public boolean removeLogDay(String daysDateString) {
        if (daysCounter == 0 || !log.containsKey(daysDateString))
            return false;
        Day dayToRemove = log.remove(daysDateString);
        if (usersGoal != null && usersGoal.getCalories() != 0 && usersGoal.goalsMeet(dayToRemove))
            daysWereGoalsWhereMet--;
        if (daysCounter == 0) {
            setContainsALog(false);
            setFirstLoggedDay("No food logs yet");
        }
        daysCounter--;
        return true;
    }

    public Day getLoggedDay(String dateString) {
        return log.get(dateString);
    }

    public String[] getDatesStringArray() {
        return log.keySet().toArray(new String[0]);
    }

public Map<Integer, Year> getYears() {
    Map<Integer, Year> years = new HashMap<>();

    for (Map.Entry<String, Day> entry : log.entrySet()) {
        Day val = entry.getValue();
        String key = entry.getKey();
        int indexOfMonth = key.indexOf("-");
        String yearString = key.substring(0, indexOfMonth);
        String monthString = key.substring(indexOfMonth + 1, key.length() - 3);
        if (monthString.startsWith("0"))
            monthString = monthString.replace("0", "");

        Integer year = Integer.parseInt(yearString);
        Integer month = Integer.parseInt(monthString);

        // Compute or get the year from the map
        Year yearObj = years.computeIfAbsent(year, k -> new Year(yearString));

        // Get the months map from the year
        Map<Integer, Month> months = yearObj.getMonths();

        // If the months map does not exist, create it
        if (months == null) {
            months = new HashMap<>();
            yearObj.setMonths(months);
        }

        // Compute or get the month from the months map
        Month monthObj = months.computeIfAbsent(month, k -> Month.getMonth(month));

        // Get the days map from the month
        Map<String, Day> days = monthObj.getDays();

        // If the days map does not exist, create it
        if (days == null) {
            days = new HashMap<>();
            monthObj.setDays(days);
        }

        // Add the day to the days map
        days.put(key.substring(indexOfMonth + 4), val);
    }
    return years;
}

    private String logToString(boolean toFullString) {
        if (log.isEmpty())
            return "No days logged";
        StringBuilder logString = new StringBuilder("");
        for (Map.Entry<String, Day> entry : log.entrySet()) {
            Day day = entry.getValue();
            logString.append(toFullString ? day.toFullString() : day.toString());
        }
        return logString.toString();
    }

    @Override
    public String toString() {
        String stringOfUsersGoal = usersGoal == null ? "No goal provided" : usersGoal.toString();
        return ("usersGoal=" + stringOfUsersGoal + ", firstLoggedDay=" + firstLoggedDay + ", containsALog=" + containsALog
                + ", daysWereGoalsWhereMet=" + daysWereGoalsWhereMet + ", daysCounter=" + daysCounter
                + ", log=\n" + logToString(false)).trim();
    }

    public String toFullString() {
        String stringOfUsersGoal = usersGoal == null ? "No goal provided" : usersGoal.toFullString();
        return ("FoodLog [\nusersGoal=\n" + stringOfUsersGoal + ",\nfirstLoggedDay=" + firstLoggedDay
                + ",\ncontainsALog=" + containsALog + ",\ndaysWereGoalsWhereMet=" + daysWereGoalsWhereMet
                + ",\ndaysCounter=" + daysCounter + ",\nlog=\n" + logToString(true) + "\n]").trim();
    }

    public Map<String, Day> getLog() {
        return log;
    }

    public String getFirstLoggedDay() {
        return firstLoggedDay;
    }

    public void setFirstLoggedDay(String firstLoggedDay) {
        this.firstLoggedDay = firstLoggedDay;
    }

    public boolean isContainsALog() {
        return containsALog;
    }

    public void setContainsALog(boolean containsALog) {
        this.containsALog = containsALog;
    }

    public int getDaysWereGoalsWereMet() {
        return daysWereGoalsWhereMet;
    }

    public void setDaysWereGoalsWereMet(int daysWereGoalsWereMet) {
        this.daysWereGoalsWhereMet = daysWereGoalsWereMet;
    }

    public int getDaysCounter() {
        return daysCounter;
    }

    public void setDaysCounter(int daysCounter) {
        this.daysCounter = daysCounter;
    }

    public Goal getUsersGoal() {
        return usersGoal;
    }

    public void setUsersGoal(Goal usersGoal) {
        this.usersGoal = usersGoal;
    }
}
