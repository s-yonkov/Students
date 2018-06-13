package com.musala.simple.students.db;

import com.musala.simple.students.db.exceptions.SQLConnectionException;

public interface DataBase {

    public void insert(Student student) throws SQLConnectionException;

    public Student getStudentById(int id) throws SQLConnectionException;

    public void insertStudents(StudentGroup students) throws SQLConnectionException;

    public StudentGroup getStudents() throws SQLConnectionException;

    public boolean isEmpty();

}
