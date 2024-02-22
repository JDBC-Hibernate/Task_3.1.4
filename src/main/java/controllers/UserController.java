package controllers;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> addUser(User user) {
        return ResponseEntity.ok().body(userService.addUser(user));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(User user) {
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(Long id) {
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }
}
