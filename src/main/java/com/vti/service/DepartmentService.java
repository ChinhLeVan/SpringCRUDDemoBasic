package com.vti.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.Specification.DepartmentSpecification;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.filter.FilterDepartment;
import com.vti.form.DepartmentFormForCreating;
import com.vti.form.DepartmentFormForUpdating;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.search.SearchDepartment;

@Service
@Transactional
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentRepository departmentRepository;
	
	@Autowired
	private IAccountRepository accountRepository;

	public Page<Department> getAllDepartments(Pageable page,
												SearchDepartment search, 
												FilterDepartment filter) throws ParseException {
		Specification<Department> where = null;
		if(search != null & search.getName() != null) {
			where = new DepartmentSpecification("name", "LIKE", search.getName()); 
		}
		
		if(search != null & search.getAuthor() != null) {
			DepartmentSpecification searchAuthorDepartmentSpecification =
					new DepartmentSpecification("author", "LIKES", search.getAuthor()); 
			if(where == null) {
				where = searchAuthorDepartmentSpecification;
			}else {
				where = where.and(searchAuthorDepartmentSpecification);
			}
		}
		
		if(filter != null && filter.getMinMember()!= null && filter.getMinMember() >0) {
			DepartmentSpecification minTotalDepartmentSpecification = 
					new DepartmentSpecification("totalMember", ">=", filter.getMinMember());
			if(where == null) {
				where = minTotalDepartmentSpecification;
			}else {
				where = where.and(minTotalDepartmentSpecification);
			}
		}
		if(filter != null && filter.getMaxMember()!= null && filter.getMaxMember() >0) {
			DepartmentSpecification maxTotalDepartmentSpecification = 
					new DepartmentSpecification("totalMember", "<=", filter.getMaxMember());
			if(where == null) {
				where = maxTotalDepartmentSpecification;
			}else {
				where = where.and(maxTotalDepartmentSpecification);
			}
		}
		if(filter != null && filter.getMinDate()!= null) {
			DepartmentSpecification minDateSpecification = 
					new DepartmentSpecification("createDate", "Date>=", filter.getMinDate());
			if(where == null) {
				where = minDateSpecification;
			}else {
				where = where.and(minDateSpecification);
			}
		}
		
		if(filter != null && filter.getMaxDate()!= null) {
			DepartmentSpecification maxDateSpecification = 
					new DepartmentSpecification("createDate", "Date<=", filter.getMaxDate());
			if(where == null) {
				where = maxDateSpecification;
			}else {
				where = where.and(maxDateSpecification);
			}
		}
		
		if(filter != null && filter.getMaxYear()!= null) {
			Date maxYear = new SimpleDateFormat("yyyy-MM-dd").parse((filter.getMaxYear().intValue()+1) + "-01-01"); 
			DepartmentSpecification maxYearSpecification = 
					new DepartmentSpecification("createDate", "Year<", maxYear);
			if(where == null) {
				where = maxYearSpecification;
			}else {
				where = where.and(maxYearSpecification);
			}
		}
		if(filter != null && filter.getMinYear()!= null) {
			Date minYear = new SimpleDateFormat("yyyy-MM-dd").parse(filter.getMinYear().toString() + "-01-01"); 
			DepartmentSpecification minYearSpecification = 
					new DepartmentSpecification("createDate", "Year>=", minYear);
			if(where == null) {
				where = minYearSpecification;
			}else {
				where = where.and(minYearSpecification);
			}
		}
				
		return departmentRepository.findAll(where ,page);
	}

	public Department getDepartmentByID(short id) {
		return departmentRepository.findById(id).get();
	}

	public Department getDepartmentByName(String name) {
		return departmentRepository.findByName(name);
	}

	public void createDepartment(DepartmentFormForCreating form) {	
		// convert form -> etity
		Account author = accountRepository.getById(form.getAuthorId());
		Department department = new Department(form.getName());
		department.setAuthor(author);
		departmentRepository.save(department);
	}

	public void updateDepartment(short id, DepartmentFormForUpdating form) {	
		Department department = departmentRepository.getById(id);
		department.setName(form.getName());
		department.setModifiedDate(new Date());
		departmentRepository.save(department);
	}

	public void deleteDepartment(short id) {
		departmentRepository.deleteById(id);
	}
	
	public void deleteDepartments(List<Short> ids) {
		departmentRepository.deleteByIds(ids);
	}

	public boolean isDepartmentExistsByID(short id) {
		return departmentRepository.existsById(id);
	}

	public boolean isDepartmentExistsByName(String name) {
		return departmentRepository.existsByName(name);
	}
}
