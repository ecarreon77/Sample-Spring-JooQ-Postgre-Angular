package com.jooq.revalida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jooq.revalida.model.tables.pojos.Student;
import com.jooq.revalida.service.StudentService;
@SpringBootApplication
@RestController
@CrossOrigin("http://localhost:4200/")
public class RevalidaApplication {

	@Autowired
	private StudentService service;
	
	@PostMapping("/student/addstudent")
	public Student AddStudent(@RequestBody Student student) {
		service.insertStudent(student);
		return student;
	}
	
	@DeleteMapping("student/{Id}")
	public String deleteStudents(@PathVariable int Id) {
		service.deleteStudents(Id);
		return null;
	}
	
	@PatchMapping("student/{Id}")
	public String updateStudent(@RequestBody Student student, @PathVariable int Id) {
		service.updateStudent(student, Id);
		
		return null;
	}
	 	
	@GetMapping("/student")
	public List<Student> getStudents() {
		return service.getStudents();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RevalidaApplication.class, args);
	}
}
