package com.example.SpringCRUD.service;

import com.example.SpringCRUD.dto.StudentRequestBody;
import com.example.SpringCRUD.dto.StudentResponseBody;
import com.example.SpringCRUD.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IStudentService {
    public StudentResponseBody create(StudentRequestBody studentRequestBody);
    public Page<StudentResponseBody> read(int offset, int pageSize, String sortBy,String sortOrder, String departmentFilter, String nameFilter, Integer yearFilter, Double cgpaMin, Double cgpaMax);
    public List<StudentResponseBody> read();
    public StudentResponseBody readById(Integer id);
    public String update(Student updatedStudent);

    public String delete(Integer id);


}
