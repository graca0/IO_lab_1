package com.example.demo;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/students/{id}/number/{number}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Student setNumber(@PathVariable("id") long id, @PathVariable("number") String number) {
        return this.service.changeNumber(id, number).orElseThrow(
                () -> new IllegalArgumentException("Student o id: " + id + " does not exist"));
    }
    @RequestMapping(value = "/students/{id}/scores", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int addScore(@PathVariable("id") long id, @RequestBody Score score) {
        return this.service.addScore(id, score)  .orElseThrow(()->new IllegalArgumentException("Student id: " + id + "does not exist"));
    }
}
