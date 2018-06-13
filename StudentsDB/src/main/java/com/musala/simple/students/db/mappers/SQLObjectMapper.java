package com.musala.simple.students.db.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.musala.simple.students.db.Student;

public class SQLObjectMapper {

    final Logger logger = LoggerFactory.getLogger(SQLObjectMapper.class);

    public Student toStudentObj(ResultSet rs) {
        Student student = new Student();
        try {
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setAge(rs.getInt("age"));
            student.setGrade(rs.getInt("grade"));

        } catch (SQLException e) {
            throw new IllegalArgumentException("There is no such ID.");
        }

        return student;

    }

}
