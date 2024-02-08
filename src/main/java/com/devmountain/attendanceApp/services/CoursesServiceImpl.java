package com.devmountain.attendanceApp.services;

import com.devmountain.attendanceApp.dtos.CoursesDto;
import com.devmountain.attendanceApp.entities.Courses;
import com.devmountain.attendanceApp.repositories.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursesServiceImpl {
    @Autowired
    private CoursesRepository coursesRepository;

    public CoursesDto getCoursesById(Long id) {
        Optional<Courses> coursesOptional = coursesRepository.findById(id);
        if (coursesOptional.isPresent()) {
            return new CoursesDto(coursesOptional.get());
        } else {
            return null;
        }
    }
}
