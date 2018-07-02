package com.musala.simple.students.db.response;

import java.util.ArrayList;

import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.dto.StudentGroup;

/**
 * 
 * Holding response information from particular database
 *
 */
public class DatabaseResponse {

    private String dbType;
    private State state;
    private StudentGroup students;

    public DatabaseResponse(State state, StudentGroup students) {
        this.state = state;
        this.students = students;
    }

    public DatabaseResponse(State state, StudentGroup students, String dbTypes) {
        this.state = state;
        this.students = students;
        this.dbType = dbTypes;
    }

    public DatabaseResponse(State state, String dbTypes) {
        this.state = state;
        this.dbType = dbTypes;
    }

    public DatabaseResponse() {

    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public StudentGroup getStudents() {
        return students;
    }

    public void setStudents(StudentGroup students) {
        this.students = students;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setStudents(ArrayList<StudentDTO> students) {
        this.students.addStudents(students);
    }

}
