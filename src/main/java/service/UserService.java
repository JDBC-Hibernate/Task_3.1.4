package service;

import entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User addUser(User user);

    User updateUser(User user);

    User deleteUser(Long id);
}