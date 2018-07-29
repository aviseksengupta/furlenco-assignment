package com.furlenco.assignment.furlencoassignment.filter;

import java.util.ArrayList;
import java.util.List;

public class StandardFilter implements Filter{

	private String field;
	private List<String> values;
	private FilterOperation operation;
	
	public StandardFilter(List<Integer> values) {
		field = "standard";
		operation = FilterOperation.IN;
		this.values = new ArrayList<String>();
		for(Integer value: values) {
			this.values.add(value.toString().trim());
		}
	}
	
	@Override
	public String getField() {
		return field;
	}

	@Override
	public String getFilterValuesAsString() {
		StringBuilder sqlFilterBuilder = new StringBuilder();
		boolean isSeperatorNeeded = false;
		for(String value : values) {

			if(isSeperatorNeeded) {
				sqlFilterBuilder.append(", ");
			}
			
			sqlFilterBuilder.append("\'");
			sqlFilterBuilder.append(value);
			sqlFilterBuilder.append("\'");
			
			isSeperatorNeeded = true;
		}
		
		return sqlFilterBuilder.toString();
	}

	@Override
	public List<String> getFilterValues() {
		return values;
	}
	
	@Override
	public FilterOperation getOperation() {
		return operation;
	}
}
