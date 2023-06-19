package com.example.demo.db;


import org.hibernate.annotations.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ScoreRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int score;
    private String comment;
    @ManyToOne
    private StudentRow student;

    public ScoreRow(int score, String comment, StudentRow student) {
        this.score = score;
        this.comment = comment;
        this.student = student;}
    protected ScoreRow() {}

    public Integer getScore() {
        return score;
    }
}