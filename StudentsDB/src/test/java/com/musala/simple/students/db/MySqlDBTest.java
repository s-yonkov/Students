package com.musala.simple.students.db;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.musala.simple.students.db.exceptions.SQLConnectionException;
import com.musala.simple.students.db.impl.MySqlDB;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;

public class MySqlDBTest {

    class MySqlDBForTest extends MySqlDB {

        public MySqlDBForTest() throws SQLConnectionException {
            super();
        }

        public ResultSet getResultset() {
            return this.rs;
        }

        @Override
        protected Connection createConnection() throws SQLConnectionException {

            String dbName = "mariaDB4jTest";
            DBConfigurationBuilder config = DBConfigurationBuilder.newBuilder();
            config.setPort(0);
            DB db;

            try {
                db = DB.newEmbeddedDB(config.build());
                db.start();
                db.createDB(dbName);

                this.connection = DriverManager.getConnection(config.getURL(dbName), "root", "");
            } catch (ManagedProcessException e) {
            } catch (SQLException e) {
            }

            return connection;
        }
    }

    @Test
    public void testInsert() throws SQLConnectionException {
        MySqlDBForTest db = new MySqlDBForTest();
        Student student = new Student(1, "Angel", 22, 10);

        db.insert(student);
        assertTrue(!db.isEmpty());
    }

    @Test
    public void testGetStudentById() throws SQLConnectionException {
        MySqlDBForTest db = new MySqlDBForTest();
        db.insert(new Student(1, "Angel", 22, 10));
        db.insert(new Student(2, "Ivan", 33, 7));

        assertTrue(db.getStudentById(2).getId() == 2 && db.getStudentById(2).getName().equals("Ivan"));
    }

    @Test
    public void testInsertStudents() throws SQLConnectionException {
        MySqlDBForTest db = new MySqlDBForTest();
        db = initDB(db);

        assertTrue((db.getStudents().getStudents().size() == 3) && db.getStudentById(3).getName().equals("Ivan"));
    }

    @Test
    public void testGetStudents() throws SQLConnectionException {
        MySqlDBForTest db = new MySqlDBForTest();
        db = initDB(db);

        assertTrue(
                db.getStudents().getClass().equals(StudentGroup.class) && (db.getStudents().getStudents().size() == 3));

    }

    @Test
    public void testIsEmpty() throws SQLConnectionException {
        MySqlDBForTest db = new MySqlDBForTest();
        assertTrue(db.isEmpty());
        db = initDB(db);
        assertFalse(db.isEmpty());
    }

    private MySqlDBForTest initDB(MySqlDBForTest db) throws SQLConnectionException {
        StudentGroup studentGroup = new StudentGroup();
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1, "Angel", 22, 10));
        students.add(new Student(2, "Petar", 17, 9));
        students.add(new Student(3, "Ivan", 33, 7));
        studentGroup.setStudents(students);
        db.insertStudents(studentGroup);
        return db;
    }

}
