package model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import model.implementation.day.Day;
import model.implementation.day.Food;
import model.implementation.food_log.FoodLog;
import model.implementation.food_log.LoggedFoodDatabase;
import model.implementation.goal.Goal;
import model.implementation.user.FoodLoggerUser;


public class FoodLoggerIO {

	private static final String USERS_DATA_FOLDER = "EATWISE_USER_DATA/";
	private static final String USER_NAMES_FOLDER = "EATWISE_USERNAME_MAP/";
	private static final String USER_NAME_LOGGED_IN_FOLDER = "EATWISE_STAY_LOGGED_IN/";
	private static final String LOGGED_FOOD_DATABASE_FOLDER = "LOGGED_FOODS_DATABASE/";
	private static final String USER_NAMES_FILE = "EatWise_Usernames.txt";
	private static final String USER_NAME_LOGGED_IN_FILE = "Name_of_User_to_Stay_Logged_In.txt";
	private static final String LOGGED_FOOD_DATABASE_FILE = "Logged_Foods_Map.txt";

	private static final Map<String, Integer> FOOD_LOGGER_USERNAMES_MAP_TYPE = new HashMap<String, Integer>();

	public static FoodLoggerUser loadFoodLoggerUserData(String userName) throws FileNotFoundException {
		String fileString = fileToString(USERS_DATA_FOLDER + userName + "_FoodLog_Data.txt");

		int begIndexOfGoal = fileString.indexOf("usersGoal=") + "usersGoal=".length() + 1;
		int endIndexOfGoal = fileString.indexOf("currentDay=") - 1;
		int begIndexOfCurrentDay = endIndexOfGoal + "currentDay=".length() + 2;
		int endIndexOfCurrentDay = fileString.indexOf("foodLog=") - 1;
		int begIndexOfFoodLog = endIndexOfCurrentDay + "foodLog=".length() + 2;
		int endIndexOfFoodLog = fileString.length() - 2;

		String goalString = fileString.substring(begIndexOfGoal, endIndexOfGoal);
		String currentDayString = fileString.substring(begIndexOfCurrentDay, endIndexOfCurrentDay);
		String foodLogString = fileString.substring(begIndexOfFoodLog, endIndexOfFoodLog);

		Goal usersGoal = readGoal(goalString);
		Day usersCurrentDay = readCurrentDay(currentDayString);
		FoodLog usersFoodLog = readFoodLog(foodLogString);
		FoodLoggerUser user = new FoodLoggerUser(userName, usersGoal, usersFoodLog, usersCurrentDay);

		return user;
	}

	public static Map<String, Integer> loadFoodLoggerUserNames() throws FileNotFoundException {
		Scanner reader = new Scanner(new FileInputStream(USER_NAMES_FOLDER + USER_NAMES_FILE));
		Map<String, Integer> userNameMap = FOOD_LOGGER_USERNAMES_MAP_TYPE;

		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			int commaIndex = line.indexOf(',');
			String userName = line.substring(0, commaIndex);
			int userCount = Integer.parseInt(line.substring(commaIndex + 1, line.length()));
			userNameMap.put(userName, userCount);
		}

		reader.close();
		return userNameMap;
	}

	public static String checkIfUserIsStayingLoggedIn() throws IOException {
		String fileString = fileToString(USER_NAME_LOGGED_IN_FOLDER + USER_NAME_LOGGED_IN_FILE);

		if (fileString.isEmpty())
			return null;

		return fileString;
	}

	public static LoggedFoodDatabase loadLoggedFoodDatabase() throws FileNotFoundException {
		LoggedFoodDatabase database = new LoggedFoodDatabase();
		String fileStirng = fileToString(LOGGED_FOOD_DATABASE_FOLDER + LOGGED_FOOD_DATABASE_FILE);

		try (Scanner reader = (new Scanner(fileStirng)).useDelimiter("Food ")) {
			reader.next();
			while (reader.hasNext())
				database.saveFoodToDatabase(readFood(reader.next()));
			reader.close();
		}

		return database;
	}

	public static void saveFoodLoggerUserData(FoodLoggerUser user) throws IOException {
		String fileName = USERS_DATA_FOLDER + user.getUserName() + "_FoodLog_Data.txt";
		overWriteFile(fileName, user.toFullString());
	}

	public static void saveFoodLoggerUserNames(Map<String, Integer> userNameMap) throws IOException {
		String fileName = USER_NAMES_FOLDER + USER_NAMES_FILE;
		StringBuilder sb = new StringBuilder("");
		for (Map.Entry<String, Integer> entry : userNameMap.entrySet())
			sb.append(entry.getKey() + "," + entry.getValue() + "\n");
		overWriteFile(fileName, sb.toString());
	}

	public static void saveUserNameOfUserStayingLoggedIn(String userName) throws IOException {
		String fileName = USER_NAME_LOGGED_IN_FOLDER + USER_NAME_LOGGED_IN_FILE;
		overWriteFile(fileName, userName);
	}

	public static void clearUerNameOfUserStayingLoggedIn() throws IOException {
		overWriteFile(USER_NAME_LOGGED_IN_FOLDER + USER_NAME_LOGGED_IN_FILE, "");
	}

	public static void saveLoggedFoodDataBase(LoggedFoodDatabase database) throws IOException {
		overWriteFile(LOGGED_FOOD_DATABASE_FOLDER + LOGGED_FOOD_DATABASE_FILE, database.toFullString());
	}

	private static void overWriteFile(String fileName, String contentsToWrite) throws IOException {
		File fold = new File(fileName);
		fold.delete();
		File fnew = new File(fileName);
		FileWriter fileWriter = new FileWriter(fnew, false);
		fileWriter.write(contentsToWrite);
		fileWriter.close();
	}

	private static String fileToString(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		StringBuilder fileStringBuilder = new StringBuilder("");
		while (fileReader.hasNextLine())
			fileStringBuilder.append(fileReader.nextLine() + "\n");
		String fileString = fileStringBuilder.toString().trim();
		fileReader.close();
		return fileString;
	}

	private static Goal readGoal(String goalString) {
		if (goalString.equals("No goal provided"))
			return null;

		String[] tokens = goalString.split(",");
		String type = tokens[0].substring(tokens[0].indexOf("=") + 1);
		int calories = Integer.parseInt(tokens[1].substring(tokens[1].indexOf("=") + 1));
		int protein = Integer.parseInt(tokens[2].substring(tokens[2].indexOf("=") + 1));
		int fat = Integer.parseInt(tokens[3].substring(tokens[3].indexOf("=") + 1));
		int carbs = Integer.parseInt(tokens[4].substring(tokens[4].indexOf("=") + 1));
		int saturatedFat = Integer.parseInt(tokens[5].substring(tokens[5].indexOf("=") + 1));
		int transFat = Integer.parseInt(tokens[6].substring(tokens[6].indexOf("=") + 1));
		int fiber = Integer.parseInt(tokens[7].substring(tokens[7].indexOf("=") + 1));
		int sugar = Integer.parseInt(tokens[8].substring(tokens[8].indexOf("=") + 1));
		int sodium = Integer.parseInt(tokens[9].substring(tokens[9].indexOf("=") + 1));
		int cholesterol = Integer.parseInt(tokens[10].substring(tokens[10].indexOf("=") + 1));
		int calcium = Integer.parseInt(tokens[11].substring(tokens[11].indexOf("=") + 1));
		int potassium = Integer.parseInt(tokens[12].substring(tokens[12].indexOf("=") + 1));
		int vitaminA = Integer.parseInt(tokens[13].substring(tokens[13].indexOf("=") + 1));
		int vitaminC = Integer.parseInt(tokens[14].substring(tokens[14].indexOf("=") + 1));
		int vitaminD = Integer.parseInt(tokens[15].substring(tokens[15].indexOf("=") + 1));
		int vitaminE = Integer.parseInt(tokens[16].substring(tokens[16].indexOf("=") + 1));
		int vitaminK = Integer.parseInt(tokens[17].substring(tokens[17].indexOf("=") + 1));
		int vitaminB1 = Integer.parseInt(tokens[18].substring(tokens[18].indexOf("=") + 1));
		int vitaminB2 = Integer.parseInt(tokens[19].substring(tokens[19].indexOf("=") + 1));
		int vitaminB3 = Integer.parseInt(tokens[20].substring(tokens[20].indexOf("=") + 1));
		int vitaminB5 = Integer.parseInt(tokens[21].substring(tokens[21].indexOf("=") + 1));
		int vitaminB6 = Integer.parseInt(tokens[22].substring(tokens[22].indexOf("=") + 1));
		int vitaminB7 = Integer.parseInt(tokens[23].substring(tokens[23].indexOf("=") + 1));
		int vitaminB9 = Integer.parseInt(tokens[24].substring(tokens[24].indexOf("=") + 1));
		int vitaminB12 = Integer.parseInt(tokens[25].substring(tokens[25].indexOf("=") + 1));
		boolean onlyCheckBasicMacros = Boolean.parseBoolean(tokens[26].substring(tokens[26].indexOf("=") + 1));

		return new Goal(type, calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar, sodium, cholesterol,
				calcium, potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1, vitaminB2, vitaminB3,
				vitaminB5, vitaminB6, vitaminB7, vitaminB9, vitaminB12, onlyCheckBasicMacros);
	}

	private static FoodLog readFoodLog(String foodLogString) {
		int begUsersGoalIndex = foodLogString.indexOf("usersGoal=") + "usersGoal=".length() + 1;
		int endUsersGoalIndex = foodLogString.indexOf("firstLoggedDay=") - 2;
		int begLogIndex = foodLogString.indexOf("log=") + "log=".length() + 1;
		int endLogIndex = foodLogString.length() - 2;

		String usersGoalString = foodLogString.substring(begUsersGoalIndex, endUsersGoalIndex);
		String logString = foodLogString.substring(begLogIndex, endLogIndex);

		Goal usersGoal = readGoal(usersGoalString);
		FoodLog log = new FoodLog(usersGoal);
		assignLog(log, logString);

		return log;
	}

	private static Day readCurrentDay(String currentDayString) {
		if (currentDayString.equals("User is not on a current day"))
			return null;

		return readDay(currentDayString);
	}

	private static void assignLog(FoodLog log, String logString) {
		if (logString.equals("No days logged"))
			return;

		Scanner reader = (new Scanner(logString)).useDelimiter("Day ");
		while (reader.hasNext())
			log.logDay(readDay(reader.next().trim()));
		reader.close();
	}

	private static Day readDay(String dayString) {
		String dateString = dayString.substring(dayString.indexOf("date=") + "date=".length(),
				dayString.indexOf("calories=") - 2);
		Date date = null;
		try {
			date = Day.SDF.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try (Scanner reader = (new Scanner(dayString)).useDelimiter("Meal ")) {
			Day day = new Day(date);
			reader.next();
			for (int i = 0; i < Day.NUMBER_OF_MEALS; i++)
				assignMealInDay(day, reader.next().trim(), i);
			reader.close();
			return day;
		}
	}

	private static void assignMealInDay(Day day, String mealString, int mealIndex) {
		Scanner reader = (new Scanner(mealString)).useDelimiter("Food ");
		reader.next();
		while (reader.hasNext())
			day.addIndexFood(readFood(reader.next().trim()), mealIndex);
		reader.close();
	}

	private static Food readFood(String foodString) {
		String[] tokens = foodString.split(",");
		String name = tokens[0].substring(tokens[0].indexOf("=") + 1);
		float servingSize = Float.parseFloat(tokens[1].substring(tokens[1].indexOf("=") + 1));
		float numberOfServings = Float.parseFloat(tokens[2].substring(tokens[2].indexOf("=") + 1));
		int calories = Integer.parseInt(tokens[3].substring(tokens[3].indexOf("=") + 1));
		int protein = Integer.parseInt(tokens[4].substring(tokens[4].indexOf("=") + 1));
		int fat = Integer.parseInt(tokens[5].substring(tokens[5].indexOf("=") + 1));
		int carbs = Integer.parseInt(tokens[6].substring(tokens[6].indexOf("=") + 1));
		int saturatedFat = Integer.parseInt(tokens[7].substring(tokens[7].indexOf("=") + 1));
		int transFat = Integer.parseInt(tokens[8].substring(tokens[8].indexOf("=") + 1));
		int fiber = Integer.parseInt(tokens[9].substring(tokens[9].indexOf("=") + 1));
		int sugar = Integer.parseInt(tokens[10].substring(tokens[10].indexOf("=") + 1));
		int sodium = Integer.parseInt(tokens[11].substring(tokens[11].indexOf("=") + 1));
		int cholesterol = Integer.parseInt(tokens[12].substring(tokens[12].indexOf("=") + 1));
		int calcium = Integer.parseInt(tokens[13].substring(tokens[13].indexOf("=") + 1));
		int potassium = Integer.parseInt(tokens[14].substring(tokens[14].indexOf("=") + 1));
		int vitaminA = Integer.parseInt(tokens[15].substring(tokens[15].indexOf("=") + 1));
		int vitaminC = Integer.parseInt(tokens[16].substring(tokens[16].indexOf("=") + 1));
		int vitaminD = Integer.parseInt(tokens[17].substring(tokens[17].indexOf("=") + 1));
		int vitaminE = Integer.parseInt(tokens[18].substring(tokens[18].indexOf("=") + 1));
		int vitaminK = Integer.parseInt(tokens[19].substring(tokens[19].indexOf("=") + 1));
		int vitaminB1 = Integer.parseInt(tokens[20].substring(tokens[20].indexOf("=") + 1));
		int vitaminB2 = Integer.parseInt(tokens[21].substring(tokens[21].indexOf("=") + 1));
		int vitaminB3 = Integer.parseInt(tokens[22].substring(tokens[22].indexOf("=") + 1));
		int vitaminB5 = Integer.parseInt(tokens[23].substring(tokens[23].indexOf("=") + 1));
		int vitaminB6 = Integer.parseInt(tokens[24].substring(tokens[24].indexOf("=") + 1));
		int vitaminB7 = Integer.parseInt(tokens[25].substring(tokens[25].indexOf("=") + 1));
		int vitaminB9 = Integer.parseInt(tokens[26].substring(tokens[26].indexOf("=") + 1));
		int vitaminB12 = Integer.parseInt(tokens[27].substring(tokens[27].indexOf("=") + 1));
		boolean causesStomachIssues = Boolean.parseBoolean(tokens[28].substring(tokens[28].indexOf("=") + 1));

		Food food = new Food(name, servingSize, numberOfServings, calories, protein, fat, carbs, saturatedFat, transFat,
				fiber, sugar, sodium, cholesterol, calcium, potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK,
				vitaminB1, vitaminB2, vitaminB3, vitaminB5, vitaminB6, vitaminB7, vitaminB9, vitaminB12,
				causesStomachIssues);

		return food;
	}
}
