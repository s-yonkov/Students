package com.musala.simple.students.db.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.simple.students.db.DataBaseType;
import com.musala.simple.students.db.Input;
import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.model.StudentModel;

@RestController
@RequestMapping("/students")
public class StudentControler {

    @Autowired
    private StudentModel studentModel;

    @PostMapping
    public boolean insertStudent(@RequestBody StudentDTO student) {
        studentModel.addStudent(DataBaseType.ALL, student);
        return true;
    }

    @GetMapping("{id}")
    public StudentDTO getStudentById(@PathVariable("id") long id) {
        DataBaseType dbType;
        dbType = DataBaseType.ALL;
        return studentModel.getStudentByID(dbType, id);
    }

    @GetMapping()
    public List<StudentDTO> getStudents(Input input) {
        DataBaseType dbType;
        dbType = DataBaseType.ALL;
        return (studentModel.getAllStudents(dbType).getStudents());
    }

}
