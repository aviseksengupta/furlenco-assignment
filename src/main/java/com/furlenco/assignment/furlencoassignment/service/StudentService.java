package com.furlenco.assignment.furlencoassignment.service;

import java.util.Map;

import com.furlenco.assignment.furlencoassignment.model.Student;
import com.furlenco.assignment.furlencoassignment.utils.StudentFilters;

public interface StudentService {
	public Student getStudentById(int id);
	
	public void getStudents(Map<StudentFilters, String> filters);
	
	public void addStudent(Student student);
	
	public void updateStudent(int id, Student student);
	
	public void deleteStudent(int id);
}
