package com.musala.simple.students.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentControler {

    @Autowired
    private StudentModel studentModel;
    

    @PostMapping
    public boolean insertStudent(StudentDTO student) {
        studentModel.addStudent(DataBaseType.MONGO, student);
        return true;
    }

    @GetMapping("{id}")
    public StudentDTO getStudentById(Input input) {
        DataBaseType dbType;
        dbType = DataBaseType.MYSQL;
        return studentModel.getStudentByID(dbType, 3);
    }

    @GetMapping()
    public List<StudentDTO> getStudents(Input input) {
        DataBaseType dbType;
        dbType = DataBaseType.MYSQL;
        return (studentModel.getAllStudents(dbType).getStudents());
    }

    // @PostMapping
    // public boolean insertStudent(Input input) {
    // studentModel.addStudents(input.getDbType(), input.getStudentGroup());
    // return true;
    // }
    //
    // @GetMapping
    // public StudentGroup getStudents(Input input) {
    // return studentModel.getAllStudents(input.getDbType());
    // }
    //

}
