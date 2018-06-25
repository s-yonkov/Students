package com.musala.simple.students.db;

import com.musala.simple.students.db.dto.StudentDTO;

public class Input {

    private StudentDTO student;
    private DataBaseType database;
    private int id;

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public DataBaseType getDbType() {
        return database;
    }

    public void setDbType(DataBaseType dataBase) {
        this.database = dataBase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
