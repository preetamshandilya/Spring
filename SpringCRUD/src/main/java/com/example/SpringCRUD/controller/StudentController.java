package com.example.SpringCRUD.controller;

import com.example.SpringCRUD.dto.StudentRequestBody;
import com.example.SpringCRUD.dto.StudentResponseBody;
import com.example.SpringCRUD.model.Student;
import com.example.SpringCRUD.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v0/student")

public class StudentController {
    @Autowired
    public IStudentService studentService;

    @PostMapping()
    public @ResponseBody StudentResponseBody create(@RequestBody StudentRequestBody studentRequestBody){
        return studentService.create(studentRequestBody);
    }

    @GetMapping()
    public @ResponseBody List<StudentResponseBody> read(){
        return studentService.read();
    }

    @GetMapping("/readById")
    public @ResponseBody StudentResponseBody readById(@RequestParam(value = "Id") Integer id){

        StudentResponseBody studentResponseBody = studentService.readById(id);

        //Implementing HATEOAS

        studentResponseBody.add(linkTo(methodOn(StudentController.class).readById(id)).withSelfRel());
        studentResponseBody.add(linkTo(methodOn(StudentController.class).read()).withRel("Read All"));
        return studentResponseBody;
    }



    @GetMapping("/page")
    public @ResponseBody Page<StudentResponseBody> readPage(@RequestParam(value = "offset",defaultValue = "0") int offset,
                                                            @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                                            @RequestParam(value = "sortBy",defaultValue = "id") String sortBy,
                                                            @RequestParam(value = "sortOrder",defaultValue = "asc") String sortOrder,
                                                            @RequestParam(required = false) String departmentFilter,
                                                            @RequestParam(required = false)String nameFilter,
                                                            @RequestParam(required = false)Integer yearFilter,
                                                            @RequestParam(required = false) Double cgpaMin,
                                                            @RequestParam(required = false)Double cgpaMax){
        return studentService.read(offset,pageSize,sortBy,sortOrder,departmentFilter,nameFilter,yearFilter,cgpaMin,cgpaMax);
    }

    @PutMapping(value = "/update")
    public @ResponseBody String update(@RequestBody Student student){
        return studentService.update(student);

    }

    @DeleteMapping("/delete")
    public @ResponseBody String delete(@RequestParam(value = "Id") Integer id){
        return studentService.delete(id);
    }



}
