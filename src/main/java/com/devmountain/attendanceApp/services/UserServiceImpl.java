package com.devmountain.attendanceApp.services;

import com.devmountain.attendanceApp.dtos.UserDto;
import com.devmountain.attendanceApp.entities.User;
import com.devmountain.attendanceApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    public UserDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return new UserDto(userOptional.get());
        } else {
            return null;
        }
    }
}
