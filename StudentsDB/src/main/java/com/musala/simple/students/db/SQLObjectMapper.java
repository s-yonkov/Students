package com.musala.simple.students.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLObjectMapper {

	final Logger logger = LoggerFactory.getLogger(SQLObjectMapper.class);

	public PreparedStatement toSQLStatement(Connection connection, Student student) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("INSERT INTO students (id, name, age, grade) VALUES (?, ?, ?, ?)");
			ps.setInt(1, student.getId());
			ps.setString(2, student.getName());
			ps.setInt(3, student.getAge());
			ps.setInt(4, student.getGrade());

			return ps;

		} catch (SQLException e) {
			logger.error("Connection problem, can't transfer to prepared statement");
			return null;
		}
	}

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
