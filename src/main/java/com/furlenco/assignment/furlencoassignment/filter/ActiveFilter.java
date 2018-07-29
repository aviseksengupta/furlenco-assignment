package com.furlenco.assignment.furlencoassignment.filter;

import java.util.ArrayList;
import java.util.List;

public class ActiveFilter implements Filter{

	private String field;
	private String value;
	private FilterOperation operation;
	
	public ActiveFilter(String value) {
		field = "isactive";
		operation = FilterOperation.EQUALS;
		this.value = value;
	}
	
	@Override
	public String getField() {
		return field;
	}

	@Override
	public String getFilterValuesAsString() {
		return value;
	}

	@Override
	public List<String> getFilterValues() {
		List<String> values = new ArrayList<String>();
		values.add(value);
		return values;
	}
	
	@Override
	public FilterOperation getOperation() {
		return operation;
	}
}
