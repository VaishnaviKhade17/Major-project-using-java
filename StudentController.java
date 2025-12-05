package com.example.gradetracker.student_grade_tracker.controller;

import com.example.gradetracker.student_grade_tracker.model.Student;
import com.example.gradetracker.student_grade_tracker.model.StudentDTO;
import com.example.gradetracker.student_grade_tracker.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
// CRITICAL FIX: CORS Policy resolved by allowing all origins for local development
@CrossOrigin(origins = "*") 
public class StudentController {

    @Autowired
    private StudentService studentService;

    // CREATE -> Input: Entity, Output: DTO
    @PostMapping
    public StudentDTO createStudent(@Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // READ All -> Output: List<DTO>
    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    // READ by ID -> Output: DTO
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable(value = "id") Long studentId) {
        StudentDTO studentDTO = studentService.getStudentDTOById(studentId);
        return ResponseEntity.ok().body(studentDTO);
    }

    // UPDATE -> Output: DTO
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable(value = "id") Long studentId, 
                                                 @Valid @RequestBody Student studentDetails) {
        StudentDTO updatedStudent = studentService.updateStudent(studentId, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    // DELETE 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }
}