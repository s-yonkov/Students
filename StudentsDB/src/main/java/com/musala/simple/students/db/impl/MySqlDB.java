package com.musala.simple.students.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.musala.simple.students.db.DataBase;
import com.musala.simple.students.db.Student;
import com.musala.simple.students.db.StudentGroup;
import com.musala.simple.students.db.exceptions.SQLConnectionException;
import com.musala.simple.students.db.mappers.SQLObjectMapper;

public class MySqlDB implements DataBase {

    protected static final String DB_URL = "jdbc:mysql://localhost/studentsdb?";
    protected static final String DB_USER = "simpleUser";
    protected static final String DB_PASSWORD = "simple123";
    protected static final Logger LOGGER = LoggerFactory.getLogger(MySqlDB.class);

    protected SQLObjectMapper mapper = new SQLObjectMapper();
    protected Connection connection;
    private PreparedStatement ps;
    protected ResultSet rs;

    public MySqlDB() throws SQLConnectionException {
        this.connection = createConnection();
        createTable();

    }

    protected Connection createConnection() throws SQLConnectionException {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new SQLConnectionException("Can't initialize connection", e);
        }
        return connection;
    }

    @Override
    public void insert(Student student) throws SQLConnectionException {

        try {
            if (!(this.connection.isValid(1))) {
                this.connection = createConnection();
            }
        } catch (SQLException e1) {
            LOGGER.error("Connection problem, the status of the connection could not be checked.");
        }

        try {
            setPs(connection.prepareStatement("INSERT INTO students (id, name, age, grade) VALUES (?, ?, ?, ?)"));
            getPs().setInt(1, student.getId());
            getPs().setString(2, student.getName());
            getPs().setInt(3, student.getAge());
            getPs().setInt(4, student.getGrade());
            getPs().executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Connection problem, can't inser the student" + student.getId());
        }
    }

    @Override
    public Student getStudentById(int id) throws SQLConnectionException {
        try {
            if (!(this.connection.isValid(1))) {
                this.connection = createConnection();
            }
        } catch (SQLException e1) {
            LOGGER.error("Connection problem, the status of the connection could not be checked.");
        }

        String query = "SELECT * FROM students WHERE id=" + id;
        try {
            this.setPs(connection.prepareStatement(query));
            this.rs = getPs().executeQuery();
            if (this.rs.next()) {
                return mapper.toStudentObj(this.rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Requested student does not exist in the data.");
        }
        return null;
    }

    @Override
    public void insertStudents(StudentGroup students) throws SQLConnectionException {
        students.getStudents().forEach(student -> {
            try {
                this.insert(student);
            } catch (SQLConnectionException e) {
                LOGGER.error("Connection problem can't insert the students");
            }
        });
    }

    @Override
    public StudentGroup getStudents() throws SQLConnectionException {
        try {
            if (!(this.connection.isValid(1))) {
                this.connection = createConnection();
            }
        } catch (SQLException e1) {
            LOGGER.error("Connection problem, the status of the connection could not be checked.");
        }

        StudentGroup studentgroup = new StudentGroup();
        ArrayList<Student> students = new ArrayList<>();
        try {
            this.setPs(this.connection.prepareStatement("SELECT * FROM students"));
            this.rs = getPs().executeQuery();

            while (rs.next()) {
                students.add(mapper.toStudentObj(rs));
            }
            studentgroup.setStudents(students);
        } catch (SQLException e) {
            LOGGER.error("Connection problem can't return the students");
        }
        return studentgroup;
    }

    @Override
    public boolean isEmpty() {
        try {
            this.setPs(this.connection.prepareStatement("SELECT * FROM students"));
            this.rs = getPs().executeQuery();
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Connection problem can't check if the table exists");
            return false;
        }

    }

    private void createTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS " + "students" + "  (id           INTEGER,"
                + "   name            VARCHAR(255)," + "   age          INTEGER," + "   grade           INTEGER)";
        try {
            this.setPs(this.connection.prepareStatement(sqlCreate));
            getPs().execute();
        } catch (SQLException e) {
            LOGGER.error("Connection problem - can't create table");
        }
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

}
