package com.grupo5.tareas.api.assignment;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public Assignment findById(Integer id) {
        return assignmentRepository.findById(id);
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public Boolean saveWithParams(String title, String description, Integer courseId) {
        Assignment assignment = new Assignment();
        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setCourseId(courseId);
        assignment.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> findByCourseId(Integer courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }

    public Assignment findLastAssignmentByCourseId(Integer courseId) {
        return assignmentRepository.findLastAssignmentByCourseId(courseId);
    }

    public Boolean save(Assignment assignment) {
        assignment.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return assignmentRepository.save(assignment);
    }
}
