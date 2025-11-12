package org.example.Repository;

import java.util.List;

import org.example.User;

public interface IUserRepository {
    //Users
    void addUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
    
}
