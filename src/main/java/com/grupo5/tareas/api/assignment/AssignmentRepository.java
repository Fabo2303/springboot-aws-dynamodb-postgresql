package com.grupo5.tareas.api.assignment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AssignmentRepository {
    private final JdbcTemplate jdbcTemplate;

    public AssignmentRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class AssignmentMapper implements RowMapper<Assignment> {
        @Override
        public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Assignment assignment = new Assignment();
            assignment.setId(rs.getInt("id"));
            assignment.setTitle(rs.getString("title"));
            assignment.setDescription(rs.getString("description"));
            assignment.setCreatedAt(rs.getTimestamp("created_at"));
            assignment.setCourseId(rs.getInt("course_id"));
            return assignment;
        }
    }

    public Assignment findById(Integer id) {
        String sql = "SELECT * FROM assignments WHERE id = ?";
        List<Assignment> assignmentList = jdbcTemplate.query(sql, new AssignmentMapper(), id);
        return assignmentList.isEmpty() ? null : assignmentList.get(0);
    }

    public List<Assignment> findAll() {
        String sql = "SELECT * FROM assignments";
        return jdbcTemplate.query(sql, new AssignmentMapper());
    }

    public List<Assignment> findByCourseId(Integer courseId) {
        String sql = "SELECT * FROM assignments WHERE course_id = ?";
        return jdbcTemplate.query(sql, new AssignmentMapper(), courseId);
    }

    public Assignment findLastAssignmentByCourseId(Integer courseId) {
        String sql = "SELECT * FROM assignments WHERE course_id = ? ORDER BY created_at DESC LIMIT 1";
        List<Assignment> assignmentList = jdbcTemplate.query(sql, new AssignmentMapper(), courseId);
        return assignmentList.isEmpty() ? null : assignmentList.get(0);
    }

    public Boolean save(Assignment assignment) {
        String sql = "INSERT INTO assignments (title, description, created_at, course_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, assignment.getTitle(), assignment.getDescription(), assignment.getCreatedAt(), assignment.getCourseId()) > 0;
    }
}
