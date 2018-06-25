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
import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.model.StudentModel;

@RestController
@RequestMapping("/api/student")
public class StudentControler {

    @Autowired
    private StudentModel studentModel;

    @PostMapping(value = "/save")
    public String postCustomer(@RequestBody List<StudentDTO> students) {
        studentModel.addStudent(DataBaseType.ALL, students.get(0));
        return "Post Successfully!";
    }

    @GetMapping(value = "/all")
    public List<StudentDTO> getResource() {
        return studentModel.getAllStudents(DataBaseType.ALL).getStudents();
    }

    @GetMapping(value = "/{dbType}/{id}")
    public StudentDTO getStudentById(@PathVariable("dbType") String dbType, @PathVariable("id") long id) {
        DataBaseType db = DataBaseType.valueOf(dbType.toUpperCase());
        return studentModel.getStudentByID(db, id);
    }

}
