package com.devmountain.attendanceApp.services;

import com.devmountain.attendanceApp.dtos.UserDto;
import com.devmountain.attendanceApp.entities.Courses;
import com.devmountain.attendanceApp.entities.User;
import com.devmountain.attendanceApp.repositories.CoursesRepository;
import com.devmountain.attendanceApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return new UserDto(userOptional.get());
        } else {
            return null;
        }
    }

    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setInstructor(userDto.isInstructor());
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser);
    }

    @Transactional
    public void enrollUserInCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Courses course = coursesRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));

        user.addCourse(course);
        userRepository.save(user);
    }

    public void dropUserFromCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Courses course = coursesRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));

        user.removeCourse(course);
        userRepository.save(user);
    }
}
