package com.musala.simple.students.db.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.simple.students.db.DataBaseType;
import com.musala.simple.students.db.dao.StudentDAO;
import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.dto.StudentGroup;
import com.musala.simple.students.db.mongo.MongoStudentDAO;
import com.musala.simple.students.db.mysql.MySQLStudentDAO;

@Component
public class StudentModel {

    final static Logger LOGGER = LoggerFactory.getLogger(StudentModel.class);

    @Autowired
    protected MySQLStudentDAO mysql;
    
    @Autowired
    protected MongoStudentDAO mongo;

    private List<StudentDAO> getDBs(DataBaseType dbType) {

        List<StudentDAO> dbs = new ArrayList<StudentDAO>();

        switch (dbType) {
            case MONGO:
                dbs.add(mongo);
                return dbs;
            case MYSQL:
                dbs.add(mysql);
                return dbs;
            case ALL:
                dbs.add(mysql);
                dbs.add(mongo);
                return dbs;
            default:
                // To add logic if invalid argument is passed;
                LOGGER.debug("No such Database");
                return dbs;
        }

    }

    public void addStudent(DataBaseType dbType, StudentDTO studentDTO) {
        List<StudentDAO> dbs = getDBs(dbType);
        dbs.forEach(db -> db.insertStudent(studentDTO));
    }

    public StudentDTO getStudentByID(DataBaseType dbType, long id) {
        List<StudentDAO> dbs = getDBs(dbType);

        for (StudentDAO db : dbs) {
            if (db.getStudentById(id) != null) {
                return db.getStudentById(id);
            }
        }
        return null;
    }

    public StudentGroup getAllStudents(DataBaseType dbType) {
        List<StudentDAO> dbs = getDBs(dbType);
        StudentGroup studentGroup = new StudentGroup();

        dbs.forEach(db -> studentGroup.addStudents(db.getStudents().getStudents()));

        return studentGroup;
    }
}
