package com.grupo5.tareas.api.assignment;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Assignment {
    private Integer id;
    private String title;
    private String description;
    private Timestamp createdAt;
    private Integer courseId;
}
