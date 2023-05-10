package com.example.demo;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/punkty")
public class PunktyController {
    public PunktyController(StudentService service) {
        this.service = service;
    }

    private final StudentService service;

    @RequestMapping(value = "/students", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Student> GetUsers() {
        return service.getStudents().asJava();
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public Student AddUsers(@RequestBody NewStudent student) {
        return service.addStudents(student);
    }
}
