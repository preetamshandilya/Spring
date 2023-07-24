package com.example.SpringCRUD.controller;

import com.example.SpringCRUD.dto.StudentRequestBody;
import com.example.SpringCRUD.dto.StudentResponseBody;
import com.example.SpringCRUD.model.Student;
import com.example.SpringCRUD.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")

public class StudentController {
    @Autowired
    public IStudentService studentService;

    @PostMapping(value = "/create")
    public @ResponseBody StudentResponseBody create(@RequestBody StudentRequestBody studentRequestBody){
        return studentService.create(studentRequestBody);
    }

    @GetMapping(value = "/read")
    public @ResponseBody List<StudentResponseBody> read(){
        return studentService.read();
    }

    @GetMapping(value = "/readById")
    public @ResponseBody StudentResponseBody readById(@RequestParam(value = "Id") Integer id){
        return studentService.readById(id);
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
