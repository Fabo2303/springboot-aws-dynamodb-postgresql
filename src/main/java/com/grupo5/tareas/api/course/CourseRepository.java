package com.grupo5.tareas.api.course;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    public CourseRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class CourseMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setDescription(rs.getString("description"));
            course.setCreatedAt(rs.getTimestamp("created_at"));
            return course;
        }
    }

    public Course findById(Integer id) {
        String sql = "SELECT * FROM course WHERE id = ?";
        List<Course> courses = jdbcTemplate.query(sql, new CourseMapper(), id);
        return courses.isEmpty() ? null : courses.get(0);
    }

    public List<Course> findAll() {
        String sql = "SELECT * FROM course";
        return jdbcTemplate.query(sql, new CourseMapper());
    }
}
