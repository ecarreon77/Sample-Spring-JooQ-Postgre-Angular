package com.jooq.revalida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jooq.revalida.model.tables.pojos.Student;
import com.jooq.revalida.service.StudentService;
@SpringBootApplication
@RestController
public class RevalidaApplication {

	@Autowired
	private StudentService service;
	
	@CrossOrigin("http://localhost:4200/")
	@PostMapping("/student/addstudent")
	public Student AddStudent(@RequestBody Student student) {
		service.insertStudent(student);
		return student;
	}
	
	@CrossOrigin("http://localhost:4200/")
	@DeleteMapping("/student/{id}/delete")
	public String deleteStudents(@RequestBody Student student) {
		service.deleteStudents(student);
		return "student deleted...";
	}
	
	@CrossOrigin("http://localhost:4200/")
	@PutMapping("/student")
	public String updateStudent(@RequestBody Student student) {
		service.updateStudent(student);
		return "student updated...";
	}
	 	
	@CrossOrigin("http://localhost:4200/")
	@GetMapping("/student")
	public List<Student> getStudents() {
		return service.getStudents();
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(RevalidaApplication.class, args);
	}

}
