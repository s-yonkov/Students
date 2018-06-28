package com.musala.simple.students.db.controler;

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
import com.musala.simple.students.db.input.Input;
import com.musala.simple.students.db.model.StudentModel;
import com.musala.simple.students.db.response.Response;

@RestController
@RequestMapping("/api/student")
public class StudentControler {

    @Autowired
    private StudentModel studentModel;

    @PostMapping(value = "/save")
    public Response insertStudent(@RequestBody @Valid Input input) {

        List<DataBaseType> dbs = new ArrayList<>();
        for (String db : input.getDbTypes()) {
            dbs.add(DataBaseType.valueOf(db.toUpperCase()));
        }

        studentModel.addStudent(dbs, input.getStudent());

        Response response = new Response("Done", input.getStudent());
        return response;
    }

    @GetMapping(value = "/all")
    public List<StudentDTO> getResource(@RequestParam("dbTypes") String[] dbTypes) {

        List<DataBaseType> dbs = new ArrayList<>();
        for (String db : dbTypes) {
            dbs.add(DataBaseType.valueOf(db.toUpperCase()));
        }

        return studentModel.getAllStudents(dbs).getStudents();
    }

    @GetMapping(value = "/{id}")
    public StudentDTO getStudentById(@PathVariable("id") long id, @RequestParam("dbTypes") List<String> dbTypes) {

        List<DataBaseType> dbs = new ArrayList<>();
        for (String db : dbTypes) {
            dbs.add(DataBaseType.valueOf(db.toUpperCase()));
        }

        return studentModel.getStudentByID(dbs, id);
    }

}
