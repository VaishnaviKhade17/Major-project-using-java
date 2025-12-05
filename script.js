// The base URL for your Spring Boot API
const API_URL = 'http://localhost:8080/api/students';

// --- I. FORM HANDLING AND VALIDATION ---

document.getElementById('studentForm').addEventListener('submit', function(event) {
    event.preventDefault(); 

    const name = document.getElementById('name').value.trim();
    const gradesInput = document.getElementById('grades').value.trim();
    
    if (!name) {
        alert("Please enter a student name.");
        return;
    }
    
    const grades = parseGrades(gradesInput);
    if (grades === null || grades.length === 0) {
        alert("Invalid grades format or no grades entered. Use 'Subject:Score;Subject:Score'.");
        return;
    }
    
    const studentData = { name: name, grades: grades };
    saveStudent(studentData);
});

// Helper function to parse the grades string into the required JSON format (FINAL CORRECTED)
function parseGrades(gradesString) {
    const gradeArray = [];
    
    // Trim and remove trailing semicolons before splitting
    const cleanedString = gradesString.trim().replace(/;$/, ''); 
    const gradePairs = cleanedString.split(';'); // Use the cleaned string!

    for (const pair of gradePairs) {
        if (!pair.trim()) continue; 

        const parts = pair.split(':');
        if (parts.length !== 2) return null; 

        const subjectName = parts[0].trim();
        const score = parseFloat(parts[1].trim());

        // Basic validation for score
        if (!subjectName || isNaN(score) || score < 0 || score > 100) return null; 

        gradeArray.push({
            subjectName: subjectName,
            score: score
        });
    }
    return gradeArray.length > 0 ? gradeArray : null;
}

// --- II. API CONNECTION ---

async function saveStudent(studentData) {
    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(studentData)
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`API Error: ${response.status} - ${errorText}`);
        }

        document.getElementById('studentForm').reset();
        document.getElementById('formMessage').innerHTML = '<div class="alert alert-success">Student saved successfully!</div>';
        fetchStudents(); 
    } catch (error) {
        console.error('Error saving student:', error);
        document.getElementById('formMessage').innerHTML = `<div class="alert alert-danger">Failed to save. Check Console. Error: ${error.message}</div>`;
    }
}

async function fetchStudents() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error(`Failed to fetch data: ${response.status}`);
        }
        const students = await response.json();
        
        displayStudents(students); 
        document.getElementById('dataMessage').style.display = 'none';

    } catch (error) {
        console.error('Error fetching students:', error);
        document.getElementById('dataMessage').className = 'alert alert-danger';
        document.getElementById('dataMessage').innerHTML = 'Could not connect to the API. Is your Spring Boot app running on port 8080?';
    }
}

async function deleteStudent(id) {
    if (!confirm(`Are you sure you want to delete student ID ${id}?`)) return;

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE',
        });

        if (!response.ok) {
            throw new Error(`Delete failed: ${response.status}`);
        }

        console.log(`Student ${id} deleted.`);
        fetchStudents(); 

    } catch (error) {
        console.error('Error deleting student:', error);
        alert('Failed to delete student: ' + error.message);
    }
}


// --- III. DOM MANIPULATION (FINAL CORRECTED RENDERING) ---

function displayStudents(students) {
    const tableBody = document.getElementById('studentTableBody');
    tableBody.innerHTML = ''; 

    if (!students || students.length === 0) {
        tableBody.innerHTML = '<tr><td colspan="5" class="text-center">No students found. Add one above!</td></tr>';
        return;
    }

    students.forEach(student => {
        const row = tableBody.insertRow();
        row.insertCell().textContent = student.id;
        row.insertCell().textContent = student.name;
        
        // Accessing the explicit 'average' and 'letterGrade' properties from the DTO
        const average = student.average; 
        
        // Robust rendering check
        if (typeof average === 'number' && !isNaN(average)) {
            row.insertCell().textContent = average.toFixed(2);
        } else {
            row.insertCell().textContent = 'N/A';
        }
        
        row.insertCell().textContent = student.letterGrade ? student.letterGrade : 'N/A';
        
        // Action button for Delete
        const actionCell = row.insertCell();
        const deleteButton = document.createElement('button');
        deleteButton.className = 'btn btn-danger btn-sm';
        deleteButton.textContent = 'Delete';
        deleteButton.onclick = () => deleteStudent(student.id);
        actionCell.appendChild(deleteButton);
    });
}

document.addEventListener('DOMContentLoaded', fetchStudents);