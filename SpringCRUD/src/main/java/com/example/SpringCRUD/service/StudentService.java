package com.example.SpringCRUD.service;

import com.example.SpringCRUD.dto.StudentRequestBody;
import com.example.SpringCRUD.dto.StudentResponseBody;
import com.example.SpringCRUD.entity.Student;
import com.example.SpringCRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service

public class StudentService implements IStudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public StudentResponseBody create(StudentRequestBody studentRequestBody) {
        Student student=new Student();
        student.setName(studentRequestBody.getName());
        student.setDepartment(studentRequestBody.getDepartment());
        student.setYear(studentRequestBody.getYear());
        student.setCgpa(studentRequestBody.getCgpa());
        studentRepository.save(student);

        StudentResponseBody studentResponseBody=new StudentResponseBody();
        studentResponseBody.setName(studentRequestBody.getName());
        studentResponseBody.setDepartment(studentRequestBody.getDepartment());
        studentResponseBody.setYear(studentRequestBody.getYear());
        studentResponseBody.setCgpa(studentRequestBody.getCgpa());


        return studentResponseBody;
    }

    @Override
    public List<StudentResponseBody> read() {
        List<Student> studentList=studentRepository.getAllStudents();
        List<StudentResponseBody> studentResponseBodyList=new ArrayList<>();
        for(Student student:studentList){
            StudentResponseBody studentResponseBody=new StudentResponseBody();
            studentResponseBody.setId(student.getId());
            studentResponseBody.setName(student.getName());
            studentResponseBody.setDepartment(student.getDepartment());
            studentResponseBody.setYear(student.getYear());
            studentResponseBody.setCgpa(student.getCgpa());
            studentResponseBody.setCreatedAt(student.getCreatedAt());
            studentResponseBody.setUpdatedAt(student.getUpdatedAt());

            studentResponseBodyList.add(studentResponseBody);

        }

        return studentResponseBodyList;
    }

    @Override
    public StudentResponseBody readById(Integer id) {
        Student student=studentRepository.getReferenceById(id);
        StudentResponseBody studentResponseBody=new StudentResponseBody();
        studentResponseBody.setId(student.getId());
        studentResponseBody.setName(student.getName());
        studentResponseBody.setYear(student.getYear());
        studentResponseBody.setDepartment(student.getDepartment());
        studentResponseBody.setCgpa(student.getCgpa());
        studentResponseBody.setCreatedAt(student.getCreatedAt());
        studentResponseBody.setUpdatedAt(student.getUpdatedAt());

        return studentResponseBody;
    }

    @Override
    public String update(Student updatedStudent) {
        Optional<Student> student=studentRepository.findById(updatedStudent.getId());

        if(student.isPresent()){
            Student newStudent=student.get();
            newStudent.setName(updatedStudent.getName());
            newStudent.setDepartment(updatedStudent.getDepartment());
            newStudent.setYear(updatedStudent.getYear());
            newStudent.setCgpa(updatedStudent.getCgpa());
            studentRepository.save(newStudent);

            return "Updated Successfully!";
        }else{
            return "Student not found";
        }

    }

    @Override
    public String delete(Integer id) {
        Optional<Student> student=studentRepository.findById(id);
        if(student.isEmpty()){
            return "Student not exists !";
        }
        studentRepository.deleteById(id);
        return "deleted successfully!";
    }
}
