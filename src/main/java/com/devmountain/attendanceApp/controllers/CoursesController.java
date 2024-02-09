package com.devmountain.attendanceApp.controllers;


import com.devmountain.attendanceApp.dtos.CoursesDto;
import com.devmountain.attendanceApp.services.CoursesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {
    private final CoursesServiceImpl coursesService;

    @Autowired
    public CoursesController(CoursesServiceImpl coursesService) {
        this.coursesService = coursesService;
    }
    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @GetMapping
    public ResponseEntity<List<CoursesDto>> getAllCourses() {
        List<CoursesDto> coursesDtos = coursesService.getAllCourses();
        return ResponseEntity.ok(coursesDtos);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @GetMapping("/{id}")
    public ResponseEntity<CoursesDto> getCourses(@PathVariable Long id) {
        CoursesDto courses = coursesService.getCoursesById(id);
        if (courses == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courses);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @PostMapping
    public ResponseEntity<CoursesDto> createCourse(@RequestBody CoursesDto course) {
        CoursesDto createdCourse = coursesService.createCourse((course));
        return ResponseEntity.ok(createdCourse);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @PostMapping("/{courseId}/users")
    public ResponseEntity<?> addUsersToCourse(@PathVariable Long courseId, @RequestBody List<Long> userIds) {
        CoursesDto updatedCourse = coursesService.addUsersToCourse(courseId, userIds);
        return ResponseEntity.ok(updatedCourse);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @DeleteMapping("/{courseId}/users")
    public ResponseEntity<?> removeUsersFromCourse(@PathVariable Long courseId, @RequestBody List<Long> userIds) {
        coursesService.removeUserFromCourse(courseId, userIds);
        return ResponseEntity.ok().build();
    }
}
