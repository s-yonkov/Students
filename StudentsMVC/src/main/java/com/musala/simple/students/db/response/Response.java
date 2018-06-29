package com.musala.simple.students.db.response;

import java.util.ArrayList;

import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.dto.StudentGroup;

public class Response {

    private String[] dbTypes;
    private String massage;
    private StudentGroup students;

    public Response(String massage, StudentGroup students) {
        this.massage = massage;
        this.students = students;
    }

    public Response(String massage, StudentGroup students, String[] dbTypes) {
        this.massage = massage;
        this.students = students;
        this.dbTypes = dbTypes;
    }

    public Response() {

    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public StudentGroup getStudents() {
        return students;
    }

    public void setStudents(StudentGroup students) {
        this.students = students;
    }

    public String[] getDbTypes() {
        return dbTypes;
    }

    public void setDbTypes(String[] dbTypes) {
        this.dbTypes = dbTypes;
    }
    
    public void setStudents(ArrayList<StudentDTO> students) {
        this.students.addStudents(students);
    }

}
