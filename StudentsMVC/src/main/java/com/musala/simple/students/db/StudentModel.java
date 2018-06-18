package com.musala.simple.students.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentModel {

    final static Logger LOGGER = LoggerFactory.getLogger(StudentModel.class);

    @Autowired
    protected MongoStudentDAO mongo;
    @Autowired
    protected MySQLStudentDAO mysql;

    public void addStudent(DataBaseType dbType, Student student) {

        switch (dbType) {
            case MONGO:
                mongo.insertStudent(student);
                break;
            case MYSQL:
                mysql.insertStudent(student);
                break;
            case ALL:
                mongo.insertStudent(student);
                mysql.insertStudent(student);
                break;
            default:
                LOGGER.debug("No such Database");
                break;
        }

    }

    public Student getStudentByID(DataBaseType dbType, int id) {
        switch (dbType) {
            case MONGO:
                if (mongo.getStudentById(id).isPresent()) {
                    return mongo.getStudentById(id).get();
                } else {
                    return null;
                }
            case MYSQL:
                if (mysql.getStudentById(id).isPresent()) {
                    return mysql.getStudentById(id).get();
                } else {
                    return null;
                }
            case ALL:
                if (mongo.getStudentById(id).isPresent()) {
                    return mongo.getStudentById(id).get();
                } else if (mysql.getStudentById(id).isPresent()) {
                    return mysql.getStudentById(id).get();
                } else {
                    return null;
                }
            default:
                LOGGER.debug("No such Database");
                return null;
        }

    }

    public StudentGroup getAllStudents(DataBaseType dbType) {
        StudentGroup students;
        switch (dbType) {
            case MONGO:
                if (!mongo.isEmpty()) {
                    return mongo.getStudents();
                } else {
                    return null;
                }
            case MYSQL:
                if (!mysql.isEmpty()) {
                    return mysql.getStudents();
                } else {
                    return null;
                }
            case ALL:
                if (!mongo.isEmpty() && !mysql.isEmpty()) {
                    students = mongo.getStudents();
                    students.addStudents(mysql.getStudents().getStudents());
                    return students;
                }
            default:
                LOGGER.debug("No such Database");
                return null;
        }
    }

}
