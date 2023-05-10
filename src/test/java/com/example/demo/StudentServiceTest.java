package com.example.demo;

import com.example.demo.db.StudentRepository;
import io.vavr.collection.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentRepository repository;
    @Test
    public void GetEmptyList()
    {
        final StudentService service=new StudentService(repository);
        List<Student> testList= service.getStudents();
        assertTrue(testList.isEmpty());
    }
    @Test
    public void AddStudentTest(){
        final StudentService service=new StudentService(repository);
        final Student created = service.addStudents(new NewStudent("PierwszyStudent","1-2-3","Pierwsza"));
        assertNotNull(created);
    }
    @Test
    public void AddStudentIsReturned(){
        final StudentService service=new StudentService(repository);
        final Student created = service.addStudents(new NewStudent("PierwszyStudent","1-2-3","Pierwsza"));
        final List<Student> all = service.getStudents();
        assertEquals(created.Name,all.head().Name);
    }
    @Test
    public void AddStudentHasNewId(){
        final StudentService service=new StudentService(repository);
        final Student created = service.addStudents(new NewStudent("PierwszyStudent","1-2-3","Pierwsza"));
        final Student created2 = service.addStudents(new NewStudent("DrugiStudent","1-2-3","Pierwsza"));
        assertEquals(2,service.getStudents().size());
        assertNotEquals(created.ID,created2.ID);
    }
    @After
    public void cleanAfterTest() { this.repository.deleteAll(); }
}