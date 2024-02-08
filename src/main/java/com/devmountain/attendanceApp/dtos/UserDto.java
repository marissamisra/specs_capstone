package com.devmountain.attendanceApp.dtos;

import com.devmountain.attendanceApp.entities.User;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private boolean instructor;

    public UserDto(User user){
        if (user.getId() != null) {
            this.id = user.getId();

        }
        if (user.getName() != null){
            this.name = user.getName();

        }
        this.instructor = user.isInstructor();

        }
    }
