package com.example.SpringCRUD.service;

import com.example.SpringCRUD.dto.StudentRequestBody;
import com.example.SpringCRUD.dto.StudentResponseBody;
import com.example.SpringCRUD.model.Student;
import com.example.SpringCRUD.repository.StudentRepository;
import jakarta.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service

public class StudentService implements IStudentService{
    @Autowired
    private StudentRepository studentRepository;
    Logger logger= LoggerFactory.getLogger(StudentService.class);
    @Override
    public StudentResponseBody create(StudentRequestBody studentRequestBody) {
        logger.info("create method executed!");
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
        logger.info("read method executed!");
        //List<Student> studentList=studentRepository.getAllStudents(); custom method to fetch all student
        List<Student> studentList=studentRepository.findAll();
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
        logger.info("readById method executed!");
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
        logger.info("update method executed!");
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
        logger.info("delete method executed!");
        Optional<Student> student=studentRepository.findById(id);
        if(student.isEmpty()){
            return "Student not exists !";
        }
        studentRepository.deleteById(id);
        return "deleted successfully!";
    }

    @Override
    public Page<StudentResponseBody> read(int offset, int pageSize, String sortBy,String sortOrder, String departmentFilter, String nameFilter, Integer yearFilter, Double cgpaMin, Double cgpaMax) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }

        Specification<Student> spec = (root, query, builder) -> {
            Predicate predicate = builder.conjunction();

            if (departmentFilter != null && !departmentFilter.isEmpty()) {
                predicate = builder.and(predicate, builder.equal(root.get("department"), departmentFilter));
            }

            if (nameFilter != null && !nameFilter.isEmpty()) {
                predicate = builder.and(predicate, builder.like(root.get("name"), "%" + nameFilter + "%"));
            }

            if (yearFilter != null) {
                predicate = builder.and(predicate, builder.equal(root.get("year"), yearFilter));
            }

            if (cgpaMin != null && cgpaMax != null) {
                predicate = builder.and(predicate, builder.between(root.get("cgpa"), cgpaMin, cgpaMax));
            } else if (cgpaMin != null) {
                predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get("cgpa"), cgpaMin));
            } else if (cgpaMax != null) {
                predicate = builder.and(predicate, builder.lessThanOrEqualTo(root.get("cgpa"), cgpaMax));
            }


            return predicate;
        };


        Page<Student> studentPage = studentRepository.findAll(spec, PageRequest.of(offset, pageSize, direction, sortBy));

        return studentPage.map(this::mapToResponseBody);
    }

    private StudentResponseBody mapToResponseBody(Student student) {
        StudentResponseBody studentResponseBody = new StudentResponseBody();
        studentResponseBody.setId(student.getId());
        studentResponseBody.setName(student.getName());
        studentResponseBody.setDepartment(student.getDepartment());
        studentResponseBody.setYear(student.getYear());
        studentResponseBody.setCgpa(student.getCgpa());
        studentResponseBody.setCreatedAt(student.getCreatedAt());
        studentResponseBody.setUpdatedAt(student.getUpdatedAt());

        return studentResponseBody;
    }
}
