package com.example.demo;


import io.vavr.collection.List;

public class StudentService {
    private List<Student> studentsList = List.empty();

    public List<Student> getStudents() {
        return this.studentsList;
    }

    public Student addStudents(NewStudent newStudent) {
        Student createdStudent = new Student(studentsList.size()+1,newStudent.Name,newStudent.Number,newStudent.Number);
        studentsList=studentsList.prepend(createdStudent);
       return createdStudent;
    }
}
