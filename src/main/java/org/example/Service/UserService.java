package org.example.Service;

import java.util.Date;
import org.example.ObjectClasses.User;
import org.example.Repository.IUserRepository;

public class UserService {

    //Fields
    private IUserRepository userRepository;

    //Constructor
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Methods
    public User createUser(int id, String first_name, String last_name, String email){
        if(userRepository.getUserById(id) != null){
            return null;
        } 
        User newUser = new User(id, first_name, last_name, email, new Date());
        userRepository.addUser(newUser);
        return newUser;
    }

    public User getUser(int id){
        return userRepository.getUserById(id);
    }

    public boolean deleteUser(int id){
        if (userRepository.getUserById(id) == null){
            return false;
        }
        userRepository.deleteUser(id);
        return true;
    }

    public boolean updateUser(int id, String first_name, String last_name, String email) {
        User existing = userRepository.getUserById(id);
        if (existing == null) {
            return false;
        }
        if (!isValidEmail(email)) {
            return false;
        }
        existing.setFirstName(first_name);
        existing.setLastName(last_name);
        existing.setEmail(email);
        userRepository.updateUser(existing);
        return true;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

}
