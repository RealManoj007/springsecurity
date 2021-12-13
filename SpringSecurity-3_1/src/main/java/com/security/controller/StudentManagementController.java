package com.security.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
	
	private static final List<Student> STUDENTS=Arrays.asList(
			new Student(1,"JAMES BOND"),
			new Student(2,"Michel Jackson"),
			new Student(3,"Skyfall")
			);
	@GetMapping("/home")
	public List<Student> getAllStudent(){
		System.out.println("Get all student");
		return STUDENTS;
	}
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		System.out.println("Register new Student");
		System.out.println(student);
	}
	@DeleteMapping(path= "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Integer id) {
		System.out.println("Deleting Student");
		System.out.println(id);
	}
	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId") Integer studentId,@RequestBody Student student) {
		System.out.println("Update Student ");
		System.out.println(String.format("%s %s", studentId, student));
	}
			
}