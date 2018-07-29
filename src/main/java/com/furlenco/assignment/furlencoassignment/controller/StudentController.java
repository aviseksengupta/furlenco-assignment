package com.furlenco.assignment.furlencoassignment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.furlenco.assignment.furlencoassignment.filter.Filter;
import com.furlenco.assignment.furlencoassignment.filter.PageData;
import com.furlenco.assignment.furlencoassignment.model.Student;
import com.furlenco.assignment.furlencoassignment.service.StudentServiceImpl;
import com.furlenco.assignment.furlencoassignment.utils.FilterUtils;

@RestController
public class StudentController {
	
	@Autowired
	StudentServiceImpl studentService;

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable("id")int id) {
		
		ResponseEntity<Student> studentResponse; 
		Student student = studentService.getStudentById(id);
		if(student!=null) {
			studentResponse = new ResponseEntity<Student>(student, HttpStatus.OK);
		}
		else {
			studentResponse = new ResponseEntity<Student>(student, HttpStatus.NOT_FOUND);
		}
		return studentResponse;
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(
			@RequestParam(value="classes", required=false)String classes,
			@RequestParam(value="active", required=false)String isActive,
			@RequestParam(value="admissionYearAfter", required=false)String admissionYear,
			@RequestParam(value="pageNumber", required=false)Integer page,
			@RequestParam(value="pageSize", required=false)Integer size) {
		
		List<Filter> filters = FilterUtils.createFilters(classes, isActive, admissionYear);
		
		PageData pageData = null;
		if(page!=null && size !=null) {
			pageData = new PageData(page, size);
		}
		
		return studentService.getStudents(filters, pageData);
	}
	
	@PostMapping("/students")
	public ResponseEntity addStudent(@RequestBody Student student) {
		studentService.addStudent(student);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@PatchMapping("/students/{id}")
	public ResponseEntity updateStudent(
			@PathVariable("id") int id,
			@RequestBody Student student) {
		
		try {
			studentService.updateStudentStandard(id, student);
			return new ResponseEntity(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/students/{id}") 
	public ResponseEntity deleteStudent(@PathVariable("id")int id){
		
		try {
			studentService.deleteStudent(id);
			return new ResponseEntity(HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
