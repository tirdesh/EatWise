package model.implementation.user;

public class AbstractUser implements User {

    private String userName;
    private String password;


    public AbstractUser(String userName) {
        setUserName(userName);
    }
    public AbstractUser(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }
    private void setUserName(String userName) {
        checkUsername(userName);
        this.userName = userName;
    }
    void setPassword(String password) {
        checkPassword(password);
        this.password = password;
    }
    @Override
    public String getUserName() {
        return userName;
    }

    private void checkUsername(String userName) {
        if (userName == null || userName.isEmpty() || userName.contains(" ")) {
            throw new IllegalArgumentException("Invalid user name.");
        }
    }
    
    public boolean authenticate(String userName, String password) {
        if (userName == null || password == null) {
            return false; 
        }
        return this.userName.equals(userName) && this.password.equals(password);
    }
    private void checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid password.");
        }
    }
}
