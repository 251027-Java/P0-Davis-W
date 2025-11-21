package org.example.Repository;

import org.example.ObjectClasses.User;

public interface IUserRepository {
    //Users
    void addUser(User user);
    User getUserById(int id);
    void updateUser(User user);
    void deleteUser(int id);
    
}
