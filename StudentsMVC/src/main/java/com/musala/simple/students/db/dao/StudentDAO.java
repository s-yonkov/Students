package com.musala.simple.students.db.dao;

import org.springframework.stereotype.Component;

import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.dto.StudentGroup;
import com.musala.simple.students.db.exceptions.MongoConnectionException;
import com.musala.simple.students.db.exceptions.MySQLConnectionException;

@Component
public interface StudentDAO {

    public void insertStudent(StudentDTO student) throws MySQLConnectionException, MongoConnectionException;

    public StudentDTO getStudentById(long id) throws MySQLConnectionException, MongoConnectionException;

    public void insertStudents(StudentGroup students) throws MongoConnectionException;

    public StudentGroup getStudents() throws MySQLConnectionException, MongoConnectionException;

    public boolean isEmpty();

}
