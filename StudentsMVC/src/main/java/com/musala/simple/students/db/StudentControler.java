package com.musala.simple.students.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentControler {

    @Autowired
    private StudentModel studentModel;

    @PostMapping
    public boolean insertStudent(@RequestBody StudentDTO student) {
        studentModel.addStudent(DataBaseType.MONGO, student);
        return true;
    }

    @GetMapping("{id}")
    public StudentDTO getStudentById(@PathVariable("id") long id) {
        DataBaseType dbType;
        dbType = DataBaseType.MONGO;
        return studentModel.getStudentByID(dbType, id);
    }

    @GetMapping()
    public List<StudentDTO> getStudents(Input input) {
        DataBaseType dbType;
        dbType = DataBaseType.ALL;
        return (studentModel.getAllStudents(dbType).getStudents());
    }

}
