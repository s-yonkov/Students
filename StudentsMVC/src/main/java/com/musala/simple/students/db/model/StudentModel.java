package com.musala.simple.students.db.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.simple.students.db.dao.StudentDAO;
import com.musala.simple.students.db.dbtypes.DataBaseType;
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

    public void addStudent(List<DataBaseType> dbTypes, StudentDTO studentDTO) {
        dbTypes.forEach(db -> addStudent(db, studentDTO));
    }

    private void addStudent(DataBaseType dbType, StudentDTO studentDTO) {
        mapToDb(dbType).insertStudent(studentDTO);
    }

    public StudentDTO getStudentByID(List<DataBaseType> dbTypes, long id) {

        for (DataBaseType db : dbTypes) {
            if (mapToDb(db).getStudentById(id) != null) {
                return mapToDb(db).getStudentById(id);
            }
        }
        return null;
    }

    public StudentGroup getAllStudents(List<DataBaseType> dbTypes) {
        StudentGroup studentGroup = new StudentGroup();

        dbTypes.forEach(db -> studentGroup.addStudents(mapToDb(db).getStudents().getStudents()));

        return studentGroup;
    }

    private StudentDAO mapToDb(DataBaseType db) {
        switch (db) {
            case MONGO:
                return mongo;
            case MYSQL:
                return mysql;
            default:
                return null;
        }
    }
}
