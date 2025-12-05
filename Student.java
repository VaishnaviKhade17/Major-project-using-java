package com.example.gradetracker.student_grade_tracker.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import jakarta.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Transient; 

@Entity
// Inheritance: The 'extends Person' keyword makes Student inherit id and name.
public class Student extends Person {

	
    // Validation: Ensures the name field is not empty or just whitespace 
    // (Used later in the Controller layer)
    @NotBlank(message = "Student name is required")
    private String name;

    // Collections: Defines a list of Grade objects associated with this student.
    // OneToMany: One Student can have many Grades.
    // CascadeType.ALL: Operations (like delete) applied to Student cascade to its Grades.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "student_id") // Creates a foreign key column in the Grade table
    private List<Grade> grades;

    // Default Constructor (Required by JPA)
    public Student() {
    }

    // Constructor inheriting from Person
    public Student(String name, List<Grade> grades) {
        super(name); // Calls the Person(String name) constructor
        this.name = name;
        this.grades = grades;
    }

    // Core Logic: Uses Loops and Conditionals
    @Transient 
    @JsonProperty("average")
    public double getAverage() {
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        // Loops: Enhanced for loop to calculate the total score
        for (Grade grade : grades) {
            sum += grade.getScore();
        }
        return sum / grades.size();
    }
    @Transient // Also mark the Letter Grade as transient to avoid Hibernate confusion
    @JsonProperty("letterGrade")
    public String getLetterGrade() {
        double average = getAverage();
        
        // Conditionals: Determines the letter grade
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
             return "F";
        }
    }
    
    // Getters and Setters for 'grades' list (Inherited getters/setters handle id/name)

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
