package com.example.gradetracker.student_grade_tracker.repository;


import com.example.gradetracker.student_grade_tracker.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Provides automatic CRUD operations (Abstraction)
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
