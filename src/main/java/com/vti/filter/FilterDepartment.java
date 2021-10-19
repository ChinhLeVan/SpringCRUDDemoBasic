package com.vti.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FilterDepartment {
	private Integer minMember;
	private Integer maxMember;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date maxDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date minDate;
	
	private Integer minYear;
	private Integer maxYear;
	
	public Integer getMinMember() {
		return minMember;
	}
	public void setMinMember(Integer minMember) {
		this.minMember = minMember;
	}
	public Integer getMaxMember() {
		return maxMember;
	}
	public void setMaxMember(Integer maxMember) {
		this.maxMember = maxMember;
	}
	public Date getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	public Date getMinDate() {
		return minDate;
	}
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	public Integer getMinYear() {
		return minYear;
	}
	public void setMinYear(Integer minYear) {
		this.minYear = minYear;
	}
	public Integer getMaxYear() {
		return maxYear;
	}
	public void setMaxYear(Integer maxYear) {
		this.maxYear = maxYear;
	}
	
}
