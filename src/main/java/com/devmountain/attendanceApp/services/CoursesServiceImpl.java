package com.devmountain.attendanceApp.services;

import com.devmountain.attendanceApp.dtos.CoursesDto;
import com.devmountain.attendanceApp.entities.Courses;
import com.devmountain.attendanceApp.entities.User;
import com.devmountain.attendanceApp.repositories.CoursesRepository;
import com.devmountain.attendanceApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesServiceImpl {
    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CoursesDto getCoursesById(Long id) {
        Optional<Courses> coursesOptional = coursesRepository.findById(id);
        if (coursesOptional.isPresent()) {
            return new CoursesDto(coursesOptional.get());
        } else {
            return null;
        }
    }

    public CoursesDto createCourse(CoursesDto courseDto) {
        Courses course = new Courses();
        course.setName(courseDto.getName());
        Courses savedCourse = coursesRepository.save(course);
        return new CoursesDto(savedCourse);
    }

    @Transactional
    public CoursesDto addUsersToCourse(Long courseId, List<Long> userIds) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        userIds.forEach(userId -> {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
            course.getUsers().add(user);
        });

        Courses updatedCourse = coursesRepository.save(course);
        return new CoursesDto(updatedCourse);
    }
}
