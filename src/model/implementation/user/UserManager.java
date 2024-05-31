package model.implementation.user;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private Map<String, AbstractUser> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public void createUser(String userName, String password) {
        AbstractUser newUser = new AbstractUser(userName, password);
        users.put(userName, newUser);
    }

    public AbstractUser getUser(String userName) {
        return users.get(userName);
    }

    public void updateUserPassword(String userName, String newPassword) {
        AbstractUser user = users.get(userName);
        if (user != null) {
            user.setPassword(newPassword);
            users.put(userName, user);
        }
    }

    public void deleteUser(String userName) {
        users.remove(userName);
    }

    public boolean authenticateUser(String userName, String password) {
        AbstractUser user = users.get(userName);
        if (user != null) {
            return user.authenticate(userName, password);
        }
        return false;
    }
}
