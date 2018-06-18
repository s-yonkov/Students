package com.musala.simple.students.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class StudentGroup {

    @Autowired
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setStudents(Student[] students) {
        for (Student student : students) {
            this.students.add(student);
        }
    }

    public void addStudents(List<Student> students) {
        this.students.addAll(students);
    }

    public Student getStudentById(int id) {

        if (id <= this.students.size()) {
            return this.students.get(id - 1);
        } else {
            throw new IllegalArgumentException("There is no such ID.");
        }
    }

}
