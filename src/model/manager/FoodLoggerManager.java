package model.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import model.implementation.day.Food;
import model.implementation.food_log.LoggedFoodDatabase;
import model.implementation.user.FoodLoggerUser;
import model.io.FoodLoggerIO;

public class FoodLoggerManager {

    private static FoodLoggerManager instance;

    private Map<String, Integer> userNameMap;
    private LoggedFoodDatabase loggedFoodDatabase;
    private FoodLoggerUser currentUser;

    private FoodLoggerManager() throws FileNotFoundException {
        userNameMap = FoodLoggerIO.loadFoodLoggerUserNames();
        loggedFoodDatabase = FoodLoggerIO.loadLoggedFoodDatabase();
    }

    public static FoodLoggerManager loginToManager() throws FileNotFoundException {
        if (instance == null)
            instance = new FoodLoggerManager();
        return instance;
    }

    public boolean logoutOfManager() throws IOException {
        if (instance == null || currentUser == null)
            return false;
        logoutUser();
        FoodLoggerIO.saveFoodLoggerUserNames(userNameMap);
        FoodLoggerIO.saveLoggedFoodDataBase(loggedFoodDatabase);
        instance = null;
        return true;
    }

    public LoggedFoodDatabase getLoggedFoodDatabase() {
        return loggedFoodDatabase;
    }

    public void signUpUser(String userName) throws IOException {
        if (!userNameMap.containsKey(userName)) {
            FoodLoggerUser newUser = new FoodLoggerUser(userName);
            userNameMap.put(userName, userNameMap.size() + 1);
            FoodLoggerIO.saveFoodLoggerUserData(newUser);
        }
    }

    private void loadFoodLoggerUserData(String userName) throws FileNotFoundException {
        currentUser = FoodLoggerIO.loadFoodLoggerUserData(userName);
    }

    private void saveCurrentUserData() throws IOException {
        FoodLoggerIO.saveFoodLoggerUserData(currentUser);
    }

    public boolean loginUser(String userName) throws FileNotFoundException {
        if (!userNameMap.containsKey(userName) || currentUser != null)
            return false;
        loadFoodLoggerUserData(userName);
        currentUser.startNewDayIfNeeded();
        return true;
    }

    public void logoutUser() throws IOException {
        if (currentUser != null) {
            saveCurrentUserData();
            currentUser = null;
        }
    }

    public FoodLoggerUser getUser() {
        return currentUser;
    }

    public void userStartNewDayIfNeeded() {
        if (currentUser != null)
            currentUser.startNewDayIfNeeded();
    }

    public void addFoodToUsersCurrentDayIndexMeal(Food foodToAdd, int mealIndex) {
        if (currentUser != null) {
            currentUser.getCurrentDay().addIndexFood(foodToAdd, mealIndex);
            loggedFoodDatabase.saveFoodToDatabase(foodToAdd);
        }
    }

    public void removeFoodFromUserCurrentDayIndexMeal(String foodName, int mealIndex) {
        if (currentUser != null)
            currentUser.getCurrentDay().removeFoodFromIndexMeal(foodName, mealIndex);
    }

    public boolean checkIfUserStayedLoggedIn() throws IOException {
        String userName = FoodLoggerIO.checkIfUserIsStayingLoggedIn();
        if (userName == null)
            return false;
        loginUser(userName);
        return true;
    }

    public void keepUserNameLoggedIn(FoodLoggerUser user) throws IOException {
        FoodLoggerIO.saveUserNameOfUserStayingLoggedIn(user.getUserName());
    }

    public void removeUserNameLoggedIn() throws IOException {
        FoodLoggerIO.clearUerNameOfUserStayingLoggedIn();
    }
}
