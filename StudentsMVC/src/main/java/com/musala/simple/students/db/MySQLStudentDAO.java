package com.musala.simple.students.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MySQLStudentDAO implements StudentDAO {

    @Autowired
    private MySQLStudentRepository mysqlRepo;

    @Override
    public void insertStudent(Student student) {
        mysqlRepo.save(student);
    }

    @Override
    public Optional<Student> getStudentById(long id) {
        return mysqlRepo.findById(id);
    }

    @Override
    public void insertStudents(StudentGroup students) {
        mysqlRepo.saveAll(students.getStudents());
    }

    @Override
    public StudentGroup getStudents() {
        StudentGroup students = new StudentGroup();
        students.setStudents((List<Student>) mysqlRepo.findAll());
        return students;
    }

    @Override
    public boolean isEmpty() {
        if (mysqlRepo.count() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
