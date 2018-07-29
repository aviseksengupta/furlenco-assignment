package com.furlenco.assignment.furlencoassignment.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.furlenco.assignment.furlencoassignment.filter.ActiveFilter;
import com.furlenco.assignment.furlencoassignment.filter.AdmissionYearFilter;
import com.furlenco.assignment.furlencoassignment.filter.Filter;
import com.furlenco.assignment.furlencoassignment.filter.StandardFilter;

public class FilterUtils {
	public static List<Filter> createFilters(String classes, String isActive, String year) {
		List<Filter> filters = new ArrayList<Filter>();
		
		if(classes!=null && !classes.isEmpty()) {
					
			List<Integer> filterValues = new ArrayList<Integer>();
			StringTokenizer classTokenizer = new StringTokenizer(classes, ",");
			while(classTokenizer.hasMoreTokens()) {
				filterValues.add(Integer.parseInt(classTokenizer.nextToken()));
			}
			
			filters.add(new StandardFilter(filterValues));
		}
		if(isActive != null) {
			filters.add(new ActiveFilter(isActive));
		}
		if(year != null) {
			filters.add(new AdmissionYearFilter(year));
		}
		return filters;
	}
}
