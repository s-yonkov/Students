package com.musala.simple.students.db.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/student")
public class WebController {

    @GetMapping(value = "/")
    public String homepage() {
        return "index";
    }
}