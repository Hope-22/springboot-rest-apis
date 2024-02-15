package com.microservices.springbootrestapi.controller;

import com.microservices.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity <Student> getStudent(){
        Student student = new Student(
                1, "Hoppie", "Onofe");

       // return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("custom-header", "lala")
                .body(student);
    }


    //http://localhost:8080/students 
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Hoppie", "Onofe"));
        students.add(new Student(2, "khalil", "Abiodun"));
        students.add(new Student(3,"Olise", "Anandikwe"));
        students.add(new Student(4, "Daniel", "Okoro"));

        return ResponseEntity.ok(students);
    }

    // Spring Boot  REST API  with Path Variable
    // {id} - URI Template Variable
    // http://localhost:8080/students/1/hope/onofe
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(
            @PathVariable("id") int studentId,
            @PathVariable("first-name") String firstName,
            @PathVariable("last-name") String lastName){
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring Boot REST API with Request Param
    //http://localhost:8080/students/query?id=1&firstName=Hope&lastName=Onofe (Query Parameter)
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName){

        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API that handles HTTP POST Request - For Creating a new resource.
    // @PostMapping and @RequestBody
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring Boot REST API that handles HTTP PUT Request - For updating existing resource
    //@PutMapping and @RequestBody.
    @PutMapping("{id}/update")
    public  ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // Spring Boot REST API that handles HTTP DELETE Request - For deleting an existing resource
    //@DeleteMapping
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
