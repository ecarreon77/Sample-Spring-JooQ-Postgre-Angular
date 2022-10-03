package com.jooq.revalida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jooq.revalida.model.tables.pojos.Student;
import com.jooq.revalida.service.StudentService;

@SpringBootApplication
@RestController
@RequestMapping("/students")
public class RevalidaApplication {

	@Autowired
	private StudentService service;
	
	@PostMapping
	public String AddStudent(@RequestBody Student student) {
		service.insertStudent(student);
		return "student added...";
	}
	
	@CrossOrigin("http://localhost:4200/")
	@GetMapping
	public List<Student> getStudents() {
		return service.getStudents();
	}
	public static void main(String[] args) {
		SpringApplication.run(RevalidaApplication.class, args);
	}

}
