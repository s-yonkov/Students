package com.musala.simple.students.db.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musala.simple.students.db.dbtypes.DataBaseType;
import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.dto.StudentGroup;
import com.musala.simple.students.db.exceptions.MongoConnectionException;
import com.musala.simple.students.db.exceptions.MySQLConnectionException;
import com.musala.simple.students.db.input.Input;
import com.musala.simple.students.db.model.StudentModel;
import com.musala.simple.students.db.response.Response;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentModel studentModel;

    @PostMapping(value = "/save")
    public Response insertStudent(@RequestBody @Valid Input input) {
        
        StudentGroup students = new StudentGroup();
        students.addStudent(input.getStudent());
        
        List<DataBaseType> dbs = convertToEnumList(input.getDbTypes());
        
        Response response = new Response();
        response.setDbTypes(input.getDbTypes());
        
        try {
            studentModel.addStudent(dbs, input.getStudent());
            response.setMassage("Success");
            response.setStudents(students);
            return response;
        } catch (MySQLConnectionException | MongoConnectionException e) {
            response.setMassage("ConnectionProblem");
            return response;
        }

    }

    @GetMapping(value = "/all")
    public Response getResource(@RequestParam("dbTypes") String[] dbTypes) {
        
        List<DataBaseType> dbs = convertToEnumList(dbTypes);
        Response response = new Response();
        response.setDbTypes(dbTypes);

        try {
            response.setStudents(studentModel.getAllStudents(dbs));
            response.setMassage("Success");

            return response;
        } catch (MySQLConnectionException | MongoConnectionException e) {
            response.setMassage("ConnectionProblem");

            return response;
        }
    }

    @GetMapping(value = "/{id}")
    public Response getStudentById(@PathVariable("id") long id, @RequestParam("dbTypes") String[] dbTypes) {
        
//        List<DataBaseType> dbs = convertToEnumList(dbTypes);
//        StudentGroup students = new StudentGroup();
//        Response response = new Response();
//        response.setDbTypes(dbTypes);
//        
//        try {
//            students.addStudent(studentModel.getStudentByID(dbs, id));
//            response.setStudents(students);
//            response.setMassage("Success");
//            return response;            
//        } catch (MySQLConnectionException e) {
//        }
        // TO BE FINALIZED
        return null;
        
    }

    private List<DataBaseType> convertToEnumList(String[] dbTypes) {
        
        List<DataBaseType> dbs = new ArrayList<>();

        for (String db : dbTypes) {
            dbs.add(DataBaseType.valueOf(db.toUpperCase()));
        }

        return dbs;
    }

}
