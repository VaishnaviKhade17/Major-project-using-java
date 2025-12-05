package com.example.gradetracker.student_grade_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // 1. Marks this class as a JPA entity, mapping it to a database table
public class Grade {
    
    @Id // 2. Designates the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3. Auto-increments the ID
    private Long id;
    
    // Encapsulation: Private fields protect the data
    private String subjectName;
    private double score; // e.g., 95.5

    // Default Constructor (required by JPA/Hibernate)
    public Grade() {
    }

    // Parameterized Constructor (useful for creating objects in code)
    public Grade(String subjectName, double score) {
        this.subjectName = subjectName;
        this.score = score;
    }

    // Public Getter and Setter methods (The controlled interface for Encapsulation)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}