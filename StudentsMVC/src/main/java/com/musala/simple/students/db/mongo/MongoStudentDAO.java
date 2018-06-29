package com.musala.simple.students.db.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.MongoSocketOpenException;
import com.musala.simple.students.db.dao.StudentDAO;
import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.dto.StudentGroup;
import com.musala.simple.students.db.exceptions.MongoConnectionException;
import com.musala.simple.students.db.mapper.StudentMapper;

@Component
public class MongoStudentDAO implements StudentDAO {

    @Autowired
    private MongoStudentRepository mongoRepo;

    @Override
    public void insertStudent(StudentDTO student) throws MongoConnectionException {
        
        try {
            mongoRepo.save(StudentMapper.mapToMongoStudent(student));
        } catch (MongoSocketOpenException e) {
            throw new MongoConnectionException("Connection problem", e);
        }
    }

    @Override
    public StudentDTO getStudentById(long id) throws MongoConnectionException {
        
        try {
            if (mongoRepo.findById(id).isPresent()) {
                return StudentMapper.mapToStudentDTO(mongoRepo.findById(id).get());
            } else {
                return null;
            }
        } catch (MongoSocketOpenException e) {
            throw new MongoConnectionException("Connection problem", e);
        }
    }

    @Override
    public void insertStudents(StudentGroup students) throws MongoConnectionException {
        
        for (StudentDTO student : students.getStudents()) {
            insertStudent(student);
        }
    }

    @Override
    public StudentGroup getStudents() throws MongoConnectionException {
        
        StudentGroup students = new StudentGroup();

        try {
            mongoRepo.findAll()
                    .forEach(mongoStudent -> students.addStudent(StudentMapper.mapToStudentDTO(mongoStudent)));
            return students;
        } catch (MongoSocketOpenException e) {
            throw new MongoConnectionException("Connection problem", e);
        }
    }

    @Override
    public boolean isEmpty() {
        
        if (mongoRepo.count() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
