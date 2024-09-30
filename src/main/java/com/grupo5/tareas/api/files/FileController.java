package com.grupo5.tareas.api.files;

import com.grupo5.tareas.api.assignment.Assignment;
import com.grupo5.tareas.api.assignment.AssignmentService;
import com.grupo5.tareas.config.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;
    private final AssignmentService assignmentService;

    public FileController(FileService fileService, AssignmentService assignmentService) {
        this.fileService = fileService;
        this.assignmentService = assignmentService;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> saveFile(@RequestParam("file") MultipartFile file, @RequestParam("assignment_id") String assignmentId) {
        Boolean saved = fileService.save(file, assignmentId);
        if (!saved) {
            throw new CustomException("Error saving file", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(Map.of("message", "File uploaded successfully"));
    }

    @PostMapping("/assignments")
    public ResponseEntity<Map<String, String>> saveAssignmentWithFiles(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("courseId") Integer courseId,
            @RequestParam("files") List<MultipartFile> files) {

        if (files.isEmpty()) {
            throw new CustomException("No files uploaded", HttpStatus.BAD_REQUEST);
        }

        if (title.isEmpty() || description.isEmpty() || courseId == null) {
            throw new CustomException("Missing parameters", HttpStatus.BAD_REQUEST);
        }

        if (assignmentService.saveWithParams(title, description, courseId)) {

            Assignment lastAssignment = assignmentService.findLastAssignmentByCourseId(courseId);

            for (MultipartFile file : files) {
                fileService.save(file, lastAssignment.getId().toString());
            }

            return ResponseEntity.ok(Map.of("message", "Assignment and files uploaded successfully"));
        }
        throw new CustomException("Assignment not created", HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/{id}")
    public ResponseEntity<File> getFile(@PathVariable String id) {
        File file = fileService.findById(id);
        if (file == null) {
            throw new CustomException("File not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(file);
    }

    @GetMapping("/assignment/{assignment_id}")
    public ResponseEntity<List<File>> getFilesByAssignment(@PathVariable String assignment_id) {
        List<File> files = fileService.findByAssignmentId(assignment_id);
        if (files.isEmpty()) {
            throw new CustomException("Files not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(files);
    }

    @GetMapping()
    public ResponseEntity<List<File>> getFiles() {
        List<File> files = fileService.findAll();
        if (files.isEmpty()) {
            throw new CustomException("Files not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(files);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteFile(@PathVariable String id) {
        Boolean deleted = fileService.delete(id);
        if (!deleted) {
            throw new CustomException("Error deleting file", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(Map.of("message", "File deleted successfully"));
    }
}
