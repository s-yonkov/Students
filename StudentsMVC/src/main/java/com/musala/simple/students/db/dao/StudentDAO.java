package com.musala.simple.students.db.dao;

import org.springframework.stereotype.Component;

import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.dto.StudentGroup;

@Component
public interface StudentDAO {

    public void insertStudent(StudentDTO student);

    public StudentDTO getStudentById(long id);

    public void insertStudents(StudentGroup students);

    public StudentGroup getStudents();

    public boolean isEmpty();

}
