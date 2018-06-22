package com.musala.simple.students.db.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.simple.students.db.model.StudentModel;

@RestController
public class StudentControler {

    @Autowired
    private StudentModel studentModel;

    @RestController
    @RequestMapping("api/students")
    public class RestWebController {

        @GetMapping(value = "/")
        public String homepage() {
            return "index";
        }

    }
}
