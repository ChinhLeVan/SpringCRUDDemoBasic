package com.vti.Specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Account;
import com.vti.entity.Department;

public class DepartmentSpecification implements Specification<Department> {

	private static final long serialVersionUID = 1L;

	private String field;
	private String operator;
	private Object value;

	public DepartmentSpecification(String field, String operator, Object value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (operator.equals("LIKES") && field.equalsIgnoreCase("author")) {
			Join<Department, Account> join1 = root.join("author", JoinType.LEFT);
			return criteriaBuilder.like(join1.get("fullName"), "%" + value.toString() + "%");	
		}
		if (operator.equals("LIKE")) {
			return criteriaBuilder.like(root.get(field), "%" + value.toString() + "%");
		}

		if (operator.equals("Year>=")) {
			if (value instanceof Date) {
				return criteriaBuilder.greaterThanOrEqualTo(root.get(field), (Date) value);
			} else {
				return criteriaBuilder.greaterThanOrEqualTo(root.get(field), value.toString());
			}
		}
		if (operator.equals("Year<")) {
			if (value instanceof Date) {
				return criteriaBuilder.lessThan(root.get(field), (Date) value);
			} else {
			return criteriaBuilder.lessThan(root.get(field), value.toString());
			}
		}
		if (operator.equals("Date<=")) {
			if (value instanceof Date) {
				return criteriaBuilder.lessThanOrEqualTo(root.get(field), (Date) value);
			} else {
				return criteriaBuilder.lessThanOrEqualTo(root.get(field), value.toString());
			}
		}
		
		if (operator.equals("Date>=")) {
			if (value instanceof Date) {
				return criteriaBuilder.greaterThanOrEqualTo(root.get(field), (Date) value);
			} else {
				return criteriaBuilder.greaterThanOrEqualTo(root.get(field), value.toString());
			}
		}
		return null;
	}
}