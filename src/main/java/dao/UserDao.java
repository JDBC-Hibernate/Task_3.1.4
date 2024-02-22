package dao;

import entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User addUser(User user);

    User updateUser(User user);

    User deleteUser(Long id);
}
