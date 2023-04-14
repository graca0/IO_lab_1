package com.example.demo;

import io.vavr.collection.List;
import org.junit.Test;


import static org.junit.Assert.*;

public class StudentServiceTest {
    @Test
public void getemptylist()
    {
        final StudentService service=new StudentService();
        List<Student> testList= service.getStudents();
        assertTrue(testList.isEmpty());
    }
    @Test
    public void testAddStudent(){
        final StudentService service=new StudentService();
        final Student created = service.addStudents(new NewStudent("Student1","1-2-3","IP"));
        assertNotNull(created);
    }
    @Test
    public void testAddStudentIsReturned(){
        final StudentService service=new StudentService();
        final Student created = service.addStudents(new NewStudent("Student1","1-2-3","IP"));
        final List<Student> all = service.getStudents();
        assertEquals(created.Name,all.head().Name);
    }
    @Test
    public void addStudentHasNewId(){
        final StudentService service=new StudentService();
        final Student created = service.addStudents(new NewStudent("Student1","1-2-3","IP"));
        final Student created2 = service.addStudents(new NewStudent("Student2","1-2-3","IP"));
        assertEquals(2,service.getStudents().size());
        assertNotEquals(created.ID,created2.ID);
    }
}