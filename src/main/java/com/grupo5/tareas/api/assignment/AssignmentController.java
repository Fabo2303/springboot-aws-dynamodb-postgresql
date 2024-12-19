package com.grupo5.tareas.api.assignment;

import com.grupo5.tareas.config.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping()
    public ResponseEntity<List<Assignment>> findAll() {
        List<Assignment> assignments = assignmentService.findAll();
        if (assignments.isEmpty()) {
            throw new CustomException("Assignments not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> findById(@PathVariable Integer id) {
        Assignment assignment = assignmentService.findById(id);
        if (assignment == null) {
            throw new CustomException("Assignment not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(assignment);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assignment>> findByCourseId(@PathVariable Integer courseId) {
        List<Assignment> assignments = assignmentService.findByCourseId(courseId);
        if (assignments.isEmpty()) {
            throw new CustomException("Assignments not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(assignments);
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> save(@RequestBody Assignment assignment) {
        if (assignmentService.save(assignment)) {
            return ResponseEntity.ok(Map.of("message", "Assignment created successfully"));
        }
        throw new CustomException("Assignment not created", HttpStatus.BAD_REQUEST);
    }
}
