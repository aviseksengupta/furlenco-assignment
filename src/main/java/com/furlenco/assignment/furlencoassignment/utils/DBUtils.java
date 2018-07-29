package com.furlenco.assignment.furlencoassignment.utils;

import java.util.List;

import com.furlenco.assignment.furlencoassignment.filter.Filter;
import com.furlenco.assignment.furlencoassignment.filter.FilterOperation;
import com.furlenco.assignment.furlencoassignment.filter.PageData;

public class DBUtils {
	public static String getFilterSql(List<Filter> filters) {
		StringBuilder filterSql = new StringBuilder();
		
		for(int i=0; i<filters.size(); i++) {
			
			Filter filter = filters.get(i);
			
			filterSql.append(filter.getField());
			
			if(filter.getOperation().equals(FilterOperation.IN)) {
				filterSql.append(" IN (");
				filterSql.append(filter.getFilterValuesAsString());
				filterSql.append(")");
			}
			else if(filter.getOperation().equals(FilterOperation.EQUALS)) {
				filterSql.append(" = ");
				filterSql.append(filter.getFilterValuesAsString());
			}
			else if(filter.getOperation().equals(FilterOperation.GREATERTHAN)) {
				filterSql.append(" > ");
				filterSql.append(filter.getFilterValuesAsString());
			}
			else if(filter.getOperation().equals(FilterOperation.LESSTHAN)) {
				filterSql.append(" < ");
				filterSql.append(filter.getFilterValuesAsString());
			}
			else if(filter.getOperation().equals(FilterOperation.GREATERTHANOREQUALS)) {
				filterSql.append(" >= ");
				filterSql.append(filter.getFilterValuesAsString());
			}
			else if(filter.getOperation().equals(FilterOperation.LESSTHANOREQUALS)) {
				filterSql.append(" <= ");
				filterSql.append(filter.getFilterValuesAsString());
			}
			
			if(i<(filters.size()-1)) {
				filterSql.append(" AND ");
			}
		}
		
		return filterSql.toString();
	}
	
	public static String getPageDataSql(PageData pageData) {
		if(pageData == null) {
			return "";
		}
		
		StringBuilder pageSql = new StringBuilder();
		
		pageSql.append("LIMIT ");
		pageSql.append(pageData.getPageSize());
		pageSql.append(" OFFSET ");
		pageSql.append(pageData.getPageNumber()*pageData.getPageSize());
		
		return pageSql.toString();
	}
	
	public static String buildQuery(String query, String filter, String pagination) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(query);
		
		//No filters or pages - return query directly
		if((filter==null || filter.isEmpty()) && (pagination==null || pagination.isEmpty())) {
			return queryBuilder.toString();
		}
		
		//Filters present add filters and WHERE clause
		if(filter!=null && !filter.isEmpty()) {
			
			queryBuilder.append(" WHERE ");
			queryBuilder.append(filter);
		}
		
		//Pagination present - add pagination
		if(pagination!=null && !pagination.isEmpty()) {
			queryBuilder.append(" ");
			queryBuilder.append(pagination);
		}
		
		return queryBuilder.toString();
	}
}
