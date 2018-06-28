package com.musala.simple.students.db.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StudentGroup {

    private List<StudentDTO> students = new ArrayList<>();

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public void setStudents(StudentDTO[] students) {
        for (StudentDTO student : students) {
            this.students.add(student);
        }
    }

    public void addStudents(List<StudentDTO> students) {
        this.students.addAll(students);
    }

    public StudentDTO getStudentById(int id) {
        if (id <= this.students.size()) {
            return this.students.get(id - 1);
        } else {
            throw new IllegalArgumentException("There is no such ID.");
        }
    }

    public void addStudent(StudentDTO student) {
        this.students.add(student);
    }
}
