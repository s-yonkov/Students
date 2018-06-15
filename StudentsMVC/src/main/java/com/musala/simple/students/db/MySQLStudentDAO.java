package com.musala.simple.students.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MySQLStudentDAO implements StudentDAO {

    @Autowired
    MySQLStudentRepository repository;

    @Override
    public void insertStudent(Student student) {
        repository.save(student);
    }

    @Override
    public Optional<Student> getStudentById(long id) {
        return repository.findById((int) id);
    }

    @Override
    public void insertStudents(StudentGroup students) {
        repository.saveAll(students.getStudents());
    }

    @Override
    public StudentGroup getStudents() {
        StudentGroup students = new StudentGroup();
        students.setStudents((List<Student>) repository.findAll());
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
