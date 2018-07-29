package com.furlenco.assignment.furlencoassignment.service;

import org.springframework.stereotype.Component;

import com.furlenco.assignment.furlencoassignment.model.Student;

@Component("studentValidator")
public class StudentValidator {
	
	public boolean isValidNewStudent(Student student) {

		if(student.getStandard()==null || student.getName()==null || student.getId()==null || student.getAdmissionYear()==null) {
			return false;
		}
		
		return true;
	}
	
	
	public boolean isValidUpdateStudent(int id, Student student) {
		
		if(student.getId()!=null && student.getId()!=id) {
			return false;
		}

		return true;
	}
	
	
	public boolean isValidStandardUpdateStudent(int id, Student student) {
		
		if(student.getAdmissionYear()!=null || student.getName()!=null || student.isActive()!=null) {
			return false;
		}
		
		return true;
	}
}
