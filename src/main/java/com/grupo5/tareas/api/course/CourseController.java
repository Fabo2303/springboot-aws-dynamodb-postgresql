package com.grupo5.tareas.api.course;

import com.grupo5.tareas.config.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public ResponseEntity<List<Course>> findAll() {
        List<Course> courses = courseService.findAll();
        if (courses.isEmpty()) {
            throw new CustomException("Courses not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Integer id) {
        Course course = courseService.findById(id);
        if (course == null) {
            throw new CustomException("Course not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(course);
    }
}
