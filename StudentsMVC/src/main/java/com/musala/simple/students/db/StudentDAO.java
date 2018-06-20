package com.musala.simple.students.db;

import org.springframework.stereotype.Component;

@Component
public interface StudentDAO {

    public void insertStudent(StudentDTO student);

    public StudentDTO getStudentById(long id);

    public void insertStudents(StudentGroup students);

    public StudentGroup getStudents();

    public boolean isEmpty();

}
