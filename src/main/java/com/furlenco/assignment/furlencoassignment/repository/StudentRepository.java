package com.furlenco.assignment.furlencoassignment.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.furlenco.assignment.furlencoassignment.filter.Filter;
import com.furlenco.assignment.furlencoassignment.filter.PageData;
import com.furlenco.assignment.furlencoassignment.model.Student;
import com.furlenco.assignment.furlencoassignment.utils.DBUtils;

@Repository("studentRepository")
public class StudentRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	HashMap<Integer, Student> studentCache = new HashMap<Integer, Student>();
	
	public Student findById(int id) {
		
		Student student;
		try {
			student = jdbcTemplate.queryForObject("select * from student where id=?", new Object[] {id}, new StudentRowMapper());
		}
		catch(EmptyResultDataAccessException e) {
			student = null;
		}
	    return student;
	}
	
	public Student findStudentById(int id) {
		Student student;
		try {
			student = jdbcTemplate.queryForObject(Queries.GET_STUDENT_BY_ID, new Object[] {id}, new StudentRowMapper());
		}
		catch(EmptyResultDataAccessException e) {
			student = null;
		}
		return student;
	}
	
	public int addStudent(Student student) {
		return jdbcTemplate.update(Queries.INSERT_STUDENT, new Object[] {student.getId(), student.getName(), student.getStandard(), student.getAdmissionYear(), student.isActive()});
	}
	
	public List<Student> getAllStudents(PageData pageData) {
		final String query = DBUtils.buildQuery(Queries.GET_STUDENT_WITH_FILTERS, null, DBUtils.getPageDataSql(pageData));
		return jdbcTemplate.query(query, new StudentRowMapper());
	}
	
	public List<Student> getStudents(List<Filter> filters, PageData pageData) {

		final String query = DBUtils.buildQuery(Queries.GET_STUDENT_WITH_FILTERS, DBUtils.getFilterSql(filters), DBUtils.getPageDataSql(pageData));
		return jdbcTemplate.query(query, new StudentRowMapper());
	}
	
	public int updateStudent(int id, Student student) {
		return jdbcTemplate.update(Queries.UPDATE_STUDENT, new Object[] {student.getName(), student.getStandard(), student.getAdmissionYear(), student.isActive(), student.getId()});
	}
}
