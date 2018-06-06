package com.musala.simple.students.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlDB implements DataBase {

	private String url;
	private String user;
	private String password;
	private SQLObjectMapper mapper = new SQLObjectMapper();
	private Connection connection;
	final Logger logger = LoggerFactory.getLogger(MySqlDB.class);
	private PreparedStatement ps;
	private ResultSet rs;

	public MySqlDB() {
		url = "jdbc:mysql://localhost/studentsdb?";
		user = "simpleUser";
		password = "simple123";
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=simpleUser&password=simple123");
		} catch (SQLException e) {

			logger.error("Connection problem, can not initialize the connection.");
		}

		if (this.isEmpty()) {
			try {
				this.ps = this.connection.prepareStatement("CREATE DATABASE studentsdb");
				ps.executeUpdate();
				this.connection = DriverManager.getConnection(url, user, password);
				this.ps = this.connection.prepareStatement("CREATE TABLE students " + "(id INTEGER not NULL, "
						+ " name VARCHAR(255)," + " age INTEGER not NULL," + " grade INTEGER not NULL");
				ps.executeUpdate();
			} catch (SQLException e) {
				logger.error("Connection problem, can not create studentsdb");
			}
		}

		try {
			this.connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Student student) {

		this.ps = mapper.toSQLStatement(this.connection, student);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Connection problem, can't inser the student" + student.getId());
		}
	}

	@Override
	public Student getStudentById(int id) {
		String query = "SELECT * FROM student WHERE id=" + id;
		try {
			this.ps = connection.prepareStatement(query);
			this.rs = ps.executeQuery();
			return mapper.toStudentObj(rs);
		} catch (SQLException e) {
			logger.error("Connection problem, can't find the ID");
		}
		return null;
	}

	@Override
	public void insertStudents(StudentGroup students) {
		students.getStudents().forEach(student -> this.insert(student));
	}

	@Override
	public StudentGroup getStudents() {
		StudentGroup studentgroup = new StudentGroup();
		ArrayList<Student> students = new ArrayList<>();
		try {
			this.ps = this.connection.prepareStatement("SELECT * FROM students");
			this.rs = ps.executeQuery();

			while (rs.next()) {
				students.add(mapper.toStudentObj(rs));
			}
			studentgroup.setStudents(students);
		} catch (SQLException e) {
			logger.error("Connection problem can't return the students");
		}
		return studentgroup;
	}

	@Override
	public boolean isEmpty() {
		try {
			this.connection = DriverManager.getConnection(this.url, this.user, this.password);
			return false;
		} catch (SQLException e) {
			return true;
		}
	}

}
