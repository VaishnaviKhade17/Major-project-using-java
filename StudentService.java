package com.example.gradetracker.student_grade_tracker.service;

import com.example.gradetracker.student_grade_tracker.exception.ResourceNotFoundException;
import com.example.gradetracker.student_grade_tracker.model.Student;
import com.example.gradetracker.student_grade_tracker.model.StudentDTO; // Import the DTO
import com.example.gradetracker.student_grade_tracker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors; // Import Collector for list mapping

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    // *** CHANGES START HERE ***
    
    // READ (All students) -> Now returns a List of DTOs
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentDTO::new) // Map each Student Entity to a new StudentDTO
                .collect(Collectors.toList());
    }

    // READ (By ID) -> Now returns a DTO
    public StudentDTO getStudentDTOById(Long id) { // Renamed method to be clear it returns DTO
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return new StudentDTO(student);
    }
    
    // CREATE -> Remains as Entity to save to DB, but returns the DTO
    public StudentDTO createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        return new StudentDTO(savedStudent);
    }
    
    // *** You need to update all methods that return Student or List<Student> ***

    // UPDATE -> Now returns DTO
    public StudentDTO updateStudent(Long id, Student studentDetails) {
        // ... (find student, update details) ...
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        
        student.setName(studentDetails.getName());
        student.setGrades(studentDetails.getGrades());
        
        Student updatedStudent = studentRepository.save(student);
        return new StudentDTO(updatedStudent); // Return DTO
    }
    
    // DELETE method remains the same (returns void)
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.delete(student);
    }
}