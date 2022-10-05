package com.jooq.revalida.service;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jooq.revalida.model.Tables;
import com.jooq.revalida.model.tables.pojos.Student;

@Service
public class StudentService {

	@Autowired
	private DSLContext dslContext;
	
	public void insertStudent(Student student) {
		dslContext.insertInto(Tables.STUDENT,Tables.STUDENT.FIRST_NAME,Tables.STUDENT.LAST_NAME,Tables.STUDENT.AGE)
		.values(student.getFirstName(),student.getLastName()
				,student.getAge()).execute();
	}
	
	public List<Student> getStudents() {
		return dslContext.selectFrom(Tables.STUDENT)
				.fetchInto(Student.class);
	}

	public void deleteStudents(Student student ) {
		dslContext.delete(Tables.STUDENT)
		.where(Tables.STUDENT.ID.eq(student.getId()))
		.execute();
	}
	
	public void updateStudent(Student student) {
		dslContext.update(Tables.STUDENT)
		.set(Tables.STUDENT.FIRST_NAME, student.getFirstName())
		.set(Tables.STUDENT.LAST_NAME, student.getLastName())
		.set(Tables.STUDENT.AGE, student.getAge())
		.where(Tables.STUDENT.ID.eq(student.getId()))
		.execute();
	}
}
