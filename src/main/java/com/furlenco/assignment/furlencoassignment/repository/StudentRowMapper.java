package com.furlenco.assignment.furlencoassignment.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.furlenco.assignment.furlencoassignment.model.Student;

public class StudentRowMapper implements RowMapper<Student>{
	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setId(rs.getInt("id"));
		student.setName(rs.getString("name"));
		student.setStandard(rs.getInt("standard"));
		student.setAdmissionYear(rs.getDate("admissionyear"));
		student.setActive(rs.getBoolean("isactive"));
		return student;
	}
}
