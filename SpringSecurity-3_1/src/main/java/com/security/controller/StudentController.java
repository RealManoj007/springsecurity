package com.security.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

	private static final List<Student> STUDENTS=Arrays.asList(new Student(1,"JAMES BOND"),
			new Student(2,"Michel Jackson"),
			new Student(3,"Skyfall"));
	
	@GetMapping(path="{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return STUDENTS.stream()
				.filter(std->studentId.equals(std.getStudentId()))
				.findFirst()
				.orElseThrow(()->new IllegalStateException("Student "+studentId+" does not exist"));
	} 
}
