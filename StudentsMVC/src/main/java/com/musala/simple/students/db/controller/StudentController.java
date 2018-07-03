package com.musala.simple.students.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musala.simple.students.db.input.Input;
import com.musala.simple.students.db.model.StudentModel;
import com.musala.simple.students.db.response.Response;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentModel studentModel;

    @PostMapping(value = "/save")
    public Response insertStudent(@RequestBody Input input) {

        Response response = studentModel.addStudent(input.getDbTypes(), input.getStudent());
        return response;
    }

    @GetMapping(value = "/all")
    public Response getStudents(@RequestParam("dbTypes") String[] dbTypes) {

        Response response = studentModel.getAllStudents(dbTypes);
        return response;
    }

    @GetMapping(value = "/{id}")
    public Response getStudentById(@PathVariable("id") long id, @RequestParam("dbTypes") String[] dbTypes) {

        Response response = studentModel.getStudentByID(dbTypes, id);
        return response;

    }

    /*
     * @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
     * public ResponseEntity<StudentDTO> insertStudent(@RequestBody Input input) {
     * 
     * 
     * return ResponseEntity.ok(new StudentDTO.StudentDTOBuilder(3,"asdas").build());
     * }
     */
}
