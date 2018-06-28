package com.musala.simple.students.db.response;

import com.musala.simple.students.db.dto.StudentDTO;

public class Response {

    private String massage;
    private StudentDTO student;

    public Response(String massage, StudentDTO student) {
        this.massage = massage;
        this.student = student;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

}
