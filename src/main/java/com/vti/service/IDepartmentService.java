package com.vti.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Department;
import com.vti.filter.FilterDepartment;
import com.vti.form.DepartmentFormForCreating;
import com.vti.form.DepartmentFormForUpdating;
import com.vti.search.SearchDepartment;

public interface IDepartmentService {

	public Page<Department> getAllDepartments(Pageable page, SearchDepartment search, FilterDepartment filter) throws ParseException;

	public Department getDepartmentByID(short id);

	public Department getDepartmentByName(String name);

	public void createDepartment(DepartmentFormForCreating form);

	public void updateDepartment(short id, DepartmentFormForUpdating form);

	public void deleteDepartment(short id);
	
	public void deleteDepartments(List<Short> ids);

	public boolean isDepartmentExistsByID(short id);

	public boolean isDepartmentExistsByName(String name);
}
