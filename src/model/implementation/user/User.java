package model.implementation.user;


public interface User {
    String getUserName();
    boolean authenticate(String userName, String password);
}
