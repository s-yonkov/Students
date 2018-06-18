package com.musala.simple.students.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoStudentDAO implements StudentDAO {

    @Autowired
    private MongoStudentRepository mongoRepo;

    @Override
    public void insertStudent(Student student) {
        mongoRepo.insert(student);
    }

    @Override
    public Optional<Student> getStudentById(long id) {

        return mongoRepo.findById(id);
    }

    @Override
    public void insertStudents(StudentGroup students) {
        mongoRepo.insert(students.getStudents());
    }

    @Override
    public StudentGroup getStudents() {
        StudentGroup students = new StudentGroup();
        students.setStudents(mongoRepo.findAll());
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
