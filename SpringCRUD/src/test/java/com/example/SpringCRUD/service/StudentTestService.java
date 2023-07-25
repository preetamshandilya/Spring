package com.example.SpringCRUD.service;

import com.example.SpringCRUD.dto.StudentRequestBody;
import com.example.SpringCRUD.dto.StudentResponseBody;
import com.example.SpringCRUD.model.Student;
import com.example.SpringCRUD.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@WebMvcTest

public class StudentTestService {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;


    @MockBean
    @Autowired
    private IStudentService studentService;

    //the method is responsible for setting up the test environment or initializing dependencies required for the test cases.
    @BeforeEach //The @BeforeEach annotation is used in JUnit 5 to signal that a method should be executed before each test method in the test class.
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }
final Logger logger= LoggerFactory.getLogger(StudentTestService.class);

    @Test
    public void createTest() throws Exception{
        logger.info("createTest method executed!");
//prepare test data
        StudentRequestBody requestBody=new StudentRequestBody();
        requestBody.setName("John Elliot");
        requestBody.setDepartment("Computer Science");
        requestBody.setYear(3);
        requestBody.setCgpa(3.4f);

        Student student= new Student();
        student.setName(requestBody.getName());
        student.setDepartment(requestBody.getDepartment());
        student.setYear(requestBody.getYear());
        student.setCgpa(requestBody.getCgpa());
        when(studentRepository.save(any(Student.class))).thenReturn(student);

// Mock the behavior of the create method in IStudentService
        when(studentService.create(any(StudentRequestBody.class))).thenAnswer(invocation -> {
            StudentRequestBody request = invocation.getArgument(0);
            StudentResponseBody responseBody = new StudentResponseBody();
            responseBody.setName(request.getName());
            responseBody.setDepartment(request.getDepartment());
            responseBody.setYear(request.getYear());
            responseBody.setCgpa(request.getCgpa());
            return responseBody;
        });

        // Perform the test
        StudentResponseBody result = studentService.create(requestBody);

        // Assertions
        assertNotNull(result);
        assertEquals(requestBody.getName(), result.getName());
        assertEquals(requestBody.getDepartment(), result.getDepartment());
        assertEquals(requestBody.getYear(), result.getYear());
        assertEquals(requestBody.getCgpa(), result.getCgpa());

    }


    @Test
    public void readAllTest(){
        logger.info("readAllTest method executed!");

        //prepare test data
        List<StudentRequestBody> requestBodyList=new ArrayList<>();
        requestBodyList.add(new StudentRequestBody("John Heisenberg","Computer Science",3,3.47f));
        requestBodyList.add(new StudentRequestBody("Rita Rollins", "Computer Science", 3, 3.7f));
        List<Student> studentList=new ArrayList<>();
        for(StudentRequestBody requestBody:requestBodyList){
            Student student=new Student();
            student.setName(requestBody.getName());
            student.setDepartment(requestBody.getDepartment());
            student.setYear(requestBody.getYear());
            student.setCgpa(requestBody.getCgpa());
            studentList.add(student);

        }
        when(studentRepository.getAllStudents()).thenReturn(studentList);

        List<StudentResponseBody> expectedResponse = new ArrayList<>();
        for (StudentRequestBody requestBody : requestBodyList) {
            StudentResponseBody responseBody = new StudentResponseBody();
            responseBody.setName(requestBody.getName());
            responseBody.setDepartment(requestBody.getDepartment());
            responseBody.setYear(requestBody.getYear());
            responseBody.setCgpa(requestBody.getCgpa());
            expectedResponse.add(responseBody);
        }
        // Mock the behavior of the read method in IStudentService
        when(studentService.read()).thenReturn(expectedResponse);
// Perform the test
        List<StudentResponseBody> result = studentService.read();
        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void deleteStudentExistsTest() {
        logger.info("deleteStudentExistsTest method executed!");
        // Prepare test data
        Student student = new Student();
        student.setId(1);
        student.setName("John Cena");
        student.setDepartment("Computer Science");
        student.setYear(3);
        student.setCgpa(3.4f);


        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

// Mock the behavior of the delete method in IStudentService
        when(studentService.delete(student.getId())).thenReturn("deleted successfully!");

        // Perform the test
        String result = studentService.delete(student.getId());

        // Assertions
        assertEquals("deleted successfully!", result);
    }


    @Test
    public void deleteStudentNotExistsTest() {
        logger.info("deleteStudentNotExistsTest method executed!");
        // Prepare test data
        Integer studentId = 1;

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // Mock the behavior of the delete method in IStudentService
        when(studentService.delete(studentId)).thenReturn("Student not exists !");

        // Perform the test
        String result = studentService.delete(studentId);

        // Assertions
        assertEquals("Student not exists !", result);

        verify(studentRepository, never()).deleteById(studentId);
    }

    @Test
    public void updateTest() {
        logger.info("updateTest method executed!");
        // Prepare test data
        Integer studentId = 1;
        Student newStudent = new Student(studentId,"Jon Snow", "Computer Science", 3, 3.7f,null,null);
        Student existingStudent = new Student(studentId, "Old Name", "Old Department", 1, 2.5f,null,null);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
        existingStudent.setName(newStudent.getName());
        existingStudent.setDepartment(newStudent.getDepartment());
        existingStudent.setYear(newStudent.getYear());
        existingStudent.setCgpa(newStudent.getCgpa());

        when(studentRepository.save(any())).thenReturn(existingStudent);
        // Mock the update method in the service interface
        when(studentService.update(any())).thenReturn("Updated Successfully!");

        // Perform the test
        String result = studentService.update(newStudent);
        // Retrieve the updated student from the repository

        // Assertions
        assertEquals("Updated Successfully!", result);
        assertEquals(newStudent.getName(), existingStudent.getName());
        assertEquals(newStudent.getDepartment(), existingStudent.getDepartment());
        assertEquals(newStudent.getYear(), existingStudent.getYear());
        assertEquals(newStudent.getCgpa(), existingStudent.getCgpa());
    }

}

