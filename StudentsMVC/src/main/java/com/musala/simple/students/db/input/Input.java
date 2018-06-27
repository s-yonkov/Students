package com.musala.simple.students.db.input;

import java.util.List;

import org.springframework.stereotype.Component;

import com.musala.simple.students.db.dto.StudentDTO;

@Component
public class Input {

    private String[] dbTypes;
    private StudentDTO student;

    public Input() {

    }

    public Input(String[] dbType, StudentDTO student) {
        this.dbTypes = dbType;
        this.student = student;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public String[] getDbTypes() {
        return dbTypes;
    }

    public void setDbTypes(String[] dbType) {
        this.dbTypes = dbType;
    }

}
