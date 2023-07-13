package com.example.SpringCRUD.service;

import com.example.SpringCRUD.dto.StudentRequestBody;
import com.example.SpringCRUD.dto.StudentResponseBody;
import com.example.SpringCRUD.entity.Student;

import java.util.List;

public interface IStudentService {
    public StudentResponseBody create(StudentRequestBody studentRequestBody);
    public List<StudentResponseBody> read();
    public StudentResponseBody readById(Integer id);
    public String update(Student updatedStudent);

    public String delete(Integer id);


}
