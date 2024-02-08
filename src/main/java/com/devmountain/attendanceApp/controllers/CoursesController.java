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

    @GetMapping("/{id}")
    public ResponseEntity<CoursesDto> getCourses(@PathVariable Long id) {
        CoursesDto courses = coursesService.getCoursesById(id);
        if (courses == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<CoursesDto> createCourse(@RequestBody CoursesDto course) {
        CoursesDto createdCourse = coursesService.createCourse((course));
        return ResponseEntity.ok(createdCourse);
    }

    @PostMapping("/{courseId}/users")
    public ResponseEntity<?> addUsersToCourse(@PathVariable Long courseId, @RequestBody List<Long> userIds) {
//        try {
            CoursesDto updatedCourse = coursesService.addUsersToCourse(courseId, userIds);
            return ResponseEntity.ok(updatedCourse);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
    }
}
