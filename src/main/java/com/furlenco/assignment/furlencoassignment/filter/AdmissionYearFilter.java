package com.furlenco.assignment.furlencoassignment.filter;

import java.util.ArrayList;
import java.util.List;

public class AdmissionYearFilter implements Filter{

	private String field;
	private String values;
	private FilterOperation operation;
	
	public AdmissionYearFilter(String value) {
		field = "admissionyear";
		operation = FilterOperation.GREATERTHANOREQUALS;
		this.values = value;
	}
	
	@Override
	public String getField() {
		return field;
	}

	@Override
	public String getFilterValuesAsString() {
		return "'"+values+"-01-01'";
	}

	@Override
	public List<String> getFilterValues() {
		List<String> valueList = new ArrayList<String>(); 
		valueList.add(values);
		return valueList;
	}
	
	@Override
	public FilterOperation getOperation() {
		return operation;
	}
}
