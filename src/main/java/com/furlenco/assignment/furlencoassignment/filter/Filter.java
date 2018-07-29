package com.furlenco.assignment.furlencoassignment.filter;

import java.util.List;

public interface Filter {
	
	String getField();
	
	String getFilterValuesAsString();
	
	List<String> getFilterValues();
	
	FilterOperation getOperation();
}
