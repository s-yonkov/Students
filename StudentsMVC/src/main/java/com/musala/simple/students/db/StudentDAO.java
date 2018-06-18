package com.musala.simple.students.db;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public interface StudentDAO {

    public void insertStudent(Student student);

    public Optional<Student> getStudentById(long id);

    public void insertStudents(StudentGroup students);

    public StudentGroup getStudents();

    public boolean isEmpty();

}
