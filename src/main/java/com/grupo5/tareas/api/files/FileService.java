package com.grupo5.tareas.api.files;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public Boolean save(MultipartFile file, String assignmentId) {
        try {
            Path uploadPath = Paths.get("uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectory(uploadPath);
            }

            Path filePath = uploadPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            File newFile = new File();
            newFile.setAssignmentId(assignmentId);
            newFile.setFileName(file.getOriginalFilename());
            newFile.setFileType(file.getContentType());
            newFile.setFileUrl(filePath.toString());
            newFile.setUploadedAt(LocalDateTime.now().toString());
            fileRepository.save(newFile);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public File findById(String id) {
        return fileRepository.findById(id);
    }

    public List<File> findAll() {
        return fileRepository.findAll();
    }

    public List<File> findByAssignmentId(String assignmentId) {
        return fileRepository.findByAssignmentId(assignmentId);
    }

    public Boolean delete(String id) {
        return fileRepository.delete(id);
    }
}
