package com.musala.simple.students.db.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.simple.students.db.StudentDAO;
import com.musala.simple.students.db.StudentDTO;
import com.musala.simple.students.db.StudentGroup;
import com.musala.simple.students.db.StudentMapper;

@Component
public class MySQLStudentDAO implements StudentDAO {

    @Autowired
    private MySQLStudentRepository mySQLRepo;

    @Override
    public void insertStudent(StudentDTO student) {
        mySQLRepo.save(StudentMapper.mapToMySQLStudent(student));
    }

    @Override
    public StudentDTO getStudentById(long id) {
        if (mySQLRepo.findById(id).isPresent()) {
            return StudentMapper.mapToStudentDTO(mySQLRepo.findById(id).get());
        } else {
            return null;
        }
    }

    @Override
    public void insertStudents(StudentGroup students) {
        students.getStudents().forEach(student -> mySQLRepo.save(StudentMapper.mapToMySQLStudent(student)));
    }

    @Override
    public StudentGroup getStudents() {
        StudentGroup students = new StudentGroup();
        mySQLRepo.findAll().forEach(mySQLStudent -> students.addStudent(StudentMapper.mapToStudentDTO(mySQLStudent)));
        
        return students;
    }

    @Override
    public boolean isEmpty() {
        if (mySQLRepo.count() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
