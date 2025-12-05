package com.example.gradetracker.student_grade_tracker.model; 

import java.util.List;

public class StudentDTO {
    private Long id;
    private String name;
    private List<Grade> grades;
    // Explicit properties for calculated fields
    private double average; 
    private String letterGrade;

    // Constructor to map from the Student entity
    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.grades = student.getGrades();
        // Call the calculation methods from the entity
        this.average = student.getAverage(); 
        this.letterGrade = student.getLetterGrade();
    }

    // --- Getters and Setters (REQUIRED for Jackson serialization) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Grade> getGrades() { return grades; }
    public void setGrades(List<Grade> grades) { this.grades = grades; }
    public double getAverage() { return average; }
    public void setAverage(double average) { this.average = average; }
    public String getLetterGrade() { return letterGrade; }
    public void setLetterGrade(String letterGrade) { this.letterGrade = letterGrade; }
}