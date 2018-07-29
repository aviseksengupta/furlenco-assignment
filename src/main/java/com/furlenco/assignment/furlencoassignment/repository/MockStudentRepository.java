package com.furlenco.assignment.furlencoassignment.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.furlenco.assignment.furlencoassignment.filter.Filter;
import com.furlenco.assignment.furlencoassignment.filter.PageData;
import com.furlenco.assignment.furlencoassignment.model.Student;

@Repository("mockStudentRepository")
public class MockStudentRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	HashMap<Integer, Student> studentCache = new HashMap<Integer, Student>();
	
	public MockStudentRepository() {
		createMockStudents();
	}
	
	public Student findById(int id) {
	    return jdbcTemplate.queryForObject("select * from student where id=?", new Object[] {id}, new StudentRowMapper());
	}
	
	public Student findStudentById(int id) {
		Student student = studentCache.get(id);
		return student;
	}
	
	public void addStudent(Student student) {
		studentCache.put(student.getId(), student);
	}
	
	public void deleteStudent(int id) {
		Student student = studentCache.get(id);

		if(student!=null) {
			student.setActive(false);
		}
		else {
			throw new DatastoreException("Student not found");
		}
	}
	
	public List<Student> getAllStudents() {
		return new ArrayList<Student>(studentCache.values());
	}
	
	public List<Student> getStudents(List<Filter> filters, PageData pageData) {
		
		Stream<Student> studentStream = studentCache.values().stream();
		
		for(Filter filter: filters) {
			studentStream = studentStream.filter(s -> {
				if(filter.getField().equals("standard")) {
					if(filter.getFilterValues().contains(s.getStandard().toString().trim())) {
						return true;
					}
				}
				else if(filter.getField().equals("admission_year")) {
					if(filter.getFilterValues().contains(s.getAdmissionYear().toString().trim())) {
						return true;
					}
				}
				else if(filter.getField().equals("active")) {
					if(filter.getFilterValues().contains(s.isActive().toString())) {
						return true;
					}
				}
				return false;
			});
		}
		
		return studentStream.collect(Collectors.toList());
	}
	
	public void updateStudent(int id, Student student) {
		if(studentCache.containsKey(id)) {
			studentCache.put(id, student);		
		}
		else {
			throw new DatastoreException("Student not found");
		}
	}

	private void createMockStudents() {
		Calendar cal1 = new GregorianCalendar(2010, 4, 12);
		Calendar cal2 = new GregorianCalendar(2006, 4, 12);
		Calendar cal3 = new GregorianCalendar(2014, 4, 12);
		Calendar cal4 = new GregorianCalendar(2017, 4, 12);
		
		
		studentCache.put(1, new Student(1, "Manish", 10, true, cal1.getTime()));
		studentCache.put(2, new Student(2, "Rohit", 7, true, cal2.getTime()));
		studentCache.put(3, new Student(3, "Sameer", 8, true, cal1.getTime()));
		studentCache.put(4, new Student(4, "Rohan", 7, true, cal2.getTime()));
		studentCache.put(5, new Student(5, "Erik", 10, true, cal4.getTime()));
		studentCache.put(6, new Student(6, "Amit", 10, true, cal4.getTime()));
		studentCache.put(7, new Student(7, "Robert", 12, true, cal3.getTime()));
		studentCache.put(8, new Student(8, "Ashutosh", 12, true, cal3.getTime()));
		studentCache.put(9, new Student(9, "Sreeda", 10, true, cal4.getTime()));
	}
}
