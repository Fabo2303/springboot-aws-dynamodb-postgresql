package com.grupo5.tareas.api.course;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Course {
    private Integer id;
    private String name;
    private String description;
    private Timestamp createdAt;
}
