package com.musala.simple.students.db;

import com.musala.simple.students.db.dto.StudentGroup;

public class Input {

    private StudentGroup studentGroup;
    private DataBaseType database;
    private int id;

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
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
