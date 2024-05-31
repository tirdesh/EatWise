package model.implementation.food_log;

import java.util.HashMap;
import java.util.Map;

public class Year {
    private Map<Integer, Month> months;
    private String yearString;

    private Year(Map<Integer, Month> months, String yearString) {
        setYearString(yearString);
    }

    public Year(String yearString) {
        this(new HashMap<>(), yearString);
    }

    public Map<Integer, Month> getMonths() {
        return months;
    }

    public String getYearString() {
        return yearString;
    }

    public void setYearString(String yearString) {
        this.yearString = yearString;
    }

	public void setMonths(Map<Integer, Month> months2) {
        this.months = months2;		
	}
}
