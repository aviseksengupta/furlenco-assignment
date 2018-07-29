package com.furlenco.assignment.furlencoassignment.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furlenco.assignment.furlencoassignment.filter.Filter;
import com.furlenco.assignment.furlencoassignment.filter.PageData;
import com.furlenco.assignment.furlencoassignment.model.Student;
import com.furlenco.assignment.furlencoassignment.repository.StudentRepository;
import com.furlenco.assignment.furlencoassignment.utils.StudentFilters;

@Service("studentService")
public class StudentServiceImpl {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StudentValidator studentValidator;
	
	public Student getStudentById(int id) {
		Student student = studentRepository.findStudentById(id);
		return student;
	}
	
	public List<Student> getStudents(List<Filter> filters, PageData pageData) {
		
		if(pageData == null) {
			pageData = getDefaultPageData();
		}
		
		if(filters==null || filters.size()==0) {
			return studentRepository.getAllStudents(pageData);
		}
		else {
			return studentRepository.getStudents(filters, pageData);
		}
	}
	
	public void addStudent(Student student) {
		if(studentValidator.isValidNewStudent(student)) {
			if(student.isActive()==null) {
				student.setActive(true);
			}
			studentRepository.addStudent(student); 
		}
		else {
			throw new ValidationError();
		}
	}
	
	public void updateStudentStandard(int id, Student updateStudent) {
		if(studentValidator.isValidStandardUpdateStudent(id, updateStudent)) {
			updateStudent(id, updateStudent);
		}
		else {
			throw new ValidationError("Can only update class of student");
		}
	}
	
	public void updateStudent(int id, Student updateStudent) {

		if(studentValidator.isValidUpdateStudent(id, updateStudent)) {
			Student student = studentRepository.findStudentById(id);
			mergeUpdates(student, updateStudent);
			studentRepository.updateStudent(id, student);
		}
		else {
			throw new ValidationError("Non matching id provided. Cannot update id.");
		}
	}
	
	public void deleteStudent(int id) {
		Student student = studentRepository.findStudentById(id);
		student.setActive(false);
		studentRepository.updateStudent(id, student);
	}
	
	private void mergeUpdates(Student student, Student updateStudent) {
		
		if(updateStudent.getName()!=null) {
			student.setName(updateStudent.getName());
		}
		
		if(updateStudent.getStandard()!=null) {
			student.setStandard(updateStudent.getStandard());
		}
		
		if(updateStudent.getAdmissionYear()!=null) {
			student.setAdmissionYear(updateStudent.getAdmissionYear());
		}
		
		if(updateStudent.isActive()!= null) {
			student.setActive(updateStudent.isActive());
		}
		
	}
	
	private PageData getDefaultPageData() {
		return new PageData(0, 10);
	}
}
