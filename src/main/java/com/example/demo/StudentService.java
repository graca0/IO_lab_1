package com.example.demo;


import com.example.demo.db.StudentRepository;
import com.example.demo.db.StudentRow;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentService {
    private final StudentRepository repository;
    public StudentService(StudentRepository repository) { this.repository = repository; }
    List<Student> getStudents() {
        return List.ofAll(this.repository.findAll()) .map(getStudentRowStudentFunction());
    }
    Student addStudent(final NewStudent newStudent) { throw new UnsupportedOperationException(); }

    public Student addStudents(final NewStudent newStudent) {
        StudentRow created = this.repository.save(new StudentRow(newStudent.Name, newStudent.Number, newStudent.Grupa));
        return getStudentRowStudentFunction().apply(created);
    }
    private Function<StudentRow, Student> getStudentRowStudentFunction() { return dbObj-> new Student( dbObj.getId(), dbObj.getName(), dbObj.getNumber(), dbObj.getGroup()); }
}