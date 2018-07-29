package com.furlenco.assignment.furlencoassignment.model;

import java.util.Date;


public class Student {
	private Integer id;
	private String name;
	private Integer standard;
	private Boolean isActive;
	private Date admissionYear;
	
	public Student() {
		
	}
	
	public Student(Integer id, String name, Integer standard, Boolean isActive, Date admissionYear) {
		super();
		this.id = id;
		this.name = name;
		this.standard = standard;
		this.isActive = isActive;
		this.admissionYear = admissionYear;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStandard() {
		return standard;
	}

	public void setStandard(Integer standard) {
		this.standard = standard;
	}

	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getAdmissionYear() {
		return admissionYear;
	}

	public void setAdmissionYear(Date admissionYear) {
		this.admissionYear = admissionYear;
	}
	
}
