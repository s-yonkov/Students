package com.musala.simple.students.db.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;

import com.musala.simple.students.db.dao.StudentDAO;
import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.dto.StudentGroup;
import com.musala.simple.students.db.exceptions.MySQLConnectionException;
import com.musala.simple.students.db.mapper.StudentMapper;

@Component
public class MySQLStudentDAO implements StudentDAO {

    @Autowired
    private MySQLStudentRepository mySQLRepo;

    @Override
    public void insertStudent(StudentDTO student) throws MySQLConnectionException {
        
        try {
            mySQLRepo.save(StudentMapper.mapToMySQLStudent(student));
        } catch (CannotCreateTransactionException e) {
            throw new MySQLConnectionException("Connection problem", e);
        }
    }

    @Override
    public StudentDTO getStudentById(long id) throws MySQLConnectionException {
        
        try {
            if (mySQLRepo.findById(id).isPresent()) {
                return StudentMapper.mapToStudentDTO(mySQLRepo.findById(id).get());
            } else {
                return null;
            }   
        } catch (CannotCreateTransactionException e) {
            throw new MySQLConnectionException("Connection problem", e);
        }
    }

    @Override
    public void insertStudents(StudentGroup students) {
        students.getStudents().forEach(student -> mySQLRepo.save(StudentMapper.mapToMySQLStudent(student)));
    }

    @Override
    public StudentGroup getStudents() throws MySQLConnectionException {
        
        StudentGroup students = new StudentGroup();
        try {
            mySQLRepo.findAll()
                    .forEach(mySQLStudent -> students.addStudent(StudentMapper.mapToStudentDTO(mySQLStudent)));
            return students;
        } catch (CannotCreateTransactionException e) {
            throw new MySQLConnectionException("Connection problem", e);
        }
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
