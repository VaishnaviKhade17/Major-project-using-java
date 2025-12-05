package com.example.gradetracker.student_grade_tracker.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

// @MappedSuperclass tells JPA to include the fields of this class 
// in any entity that inherits from it.
@MappedSuperclass
public abstract class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    // Default Constructor (Required by JPA)
    public Person() {
    }
    
    // Parameterized Constructor
    public Person(String name) {
        this.name = name;
    }

    // Getters and Setters (Encapsulation)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
