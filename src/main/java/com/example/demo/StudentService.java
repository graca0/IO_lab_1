package com.example.demo;


import com.example.demo.db.StudentRepository;
import com.example.demo.db.StudentRow;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import java.util.function.Function;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;
    public StudentService(StudentRepository studentRepository, ScoreRepository scoreRepository) {
        this.studentRepository = studentRepository;
        this.scoreRepository = scoreRepository;
    }
    return List.ofAll(this.studentRepository.findAll()).map(StudentRow::toStudent);
    Student addStudent(final NewStudent newStudent) { throw new UnsupportedOperationException(); }

    @Transactional
    public Optional<Student> changeNumber(long studentId, String newNumber) {
        final Optional<StudentRow> student = this.studentRepository.findById(studentId);

        student.map(c -> {
            c.setNumber(newNumber);
            studentRepository.save(c);
            return c.toStudent();
        });
        return null;
    }
        Student addStudent(final NewStudent newStudent) {
            return this.studentRepository.save(new StudentRow(newStudent.Name,
                    newStudent.number,
                    newStudent.grupa)).toStudent();
        }
    public Optional<Integer> addScore(final long studentId, final Score score) {
        final Optional<StudentRow> student =
                this.studentRepository.findById(studentId);
        return student.map(c -> {
            int existingScore = List.ofAll(c.getScores())
                    .foldLeft(0, (p, s) -> p + s.getScore());
            final ScoreRow newScore = new ScoreRow(score.score, score.comment, c);
            this.scoreRepository.save(newScore);
            return existingScore + score.score;
        });
    }
}