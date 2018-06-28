package com.musala.simple.students.db.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.simple.students.db.dao.StudentDAO;
import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.dto.StudentGroup;
import com.musala.simple.students.db.mapper.StudentMapper;

@Component
public class MongoStudentDAO implements StudentDAO {

    @Autowired
    private MongoStudentRepository mongoRepo;

    @Override
    public void insertStudent(StudentDTO student) {
        mongoRepo.save(StudentMapper.mapToMongoStudent(student));
    }

    @Override
    public StudentDTO getStudentById(long id) {
        if (mongoRepo.findById(id).isPresent()) {
            return StudentMapper.mapToStudentDTO(mongoRepo.findById(id).get());
        } else {
            return null;
        }
    }

    @Override
    public void insertStudents(StudentGroup students) {
        students.getStudents().forEach(student -> insertStudent(student));
    }

    @Override
    public StudentGroup getStudents() {
        StudentGroup students = new StudentGroup();
        mongoRepo.findAll().forEach(mongoStudent -> students.addStudent(StudentMapper.mapToStudentDTO(mongoStudent)));

        return students;
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
