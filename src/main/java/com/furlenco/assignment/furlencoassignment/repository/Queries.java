package com.furlenco.assignment.furlencoassignment.repository;

public class Queries {
	public static String GET_STUDENT_BY_ID = "SELECT ID, NAME, STANDARD, ADMISSIONYEAR, ISACTIVE FROM STUDENT WHERE ID = ?";
	public static String GET_STUDENT_WITH_FILTERS = "SELECT ID, NAME, STANDARD, ADMISSIONYEAR, ISACTIVE FROM STUDENT "; 
	public static String INSERT_STUDENT = "INSERT INTO STUDENT VALUES (?, ?, ?, ?, ?)";
	public static String DELETE_STUDENT = "UPDATE STUDENT SET ACTIVE = false WHERE ID = ?";
	public static String UPDATE_STUDENT = "UPDATE STUDENT SET NAME=?, STANDARD=?, ADMISSIONYEAR=?, ISACTIVE=? WHERE ID = ?";
}
