package com.musala.simple.students.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoStudentDAO implements StudentDAO {

    @Autowired
    public MongoStudentRepository repository;

    @Override
    public void insertStudent(Student student) {
        repository.insert(student);
    }

    @Override
    public Optional<Student> getStudentById(long id) {

        return repository.findById(id);
    }

    @Override
    public void insertStudents(StudentGroup students) {
        repository.insert(students.getStudents());
    }

    @Override
    public StudentGroup getStudents() {
        StudentGroup students = new StudentGroup();
        students.setStudents(repository.findAll());
        return students;
    }

    @Override
    public boolean isEmpty() {
        if (repository.count() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
