package com.devmountain.attendanceApp.controllers;

import com.devmountain.attendanceApp.dtos.UserDto;
import com.devmountain.attendanceApp.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @PostMapping("/{userId}/enroll/{courseId}")
    public ResponseEntity<?> enrollUserInCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        try {
            userService.enrollUserInCourse(userId, courseId);
            return ResponseEntity.ok("User enrolled in course successfully.");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @DeleteMapping("/{userId}/drop/{courseId}")
    public ResponseEntity<?> dropUserFromCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        try {
            userService.dropUserFromCourse(userId, courseId);
            return ResponseEntity.ok("Student successfully dropped");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
