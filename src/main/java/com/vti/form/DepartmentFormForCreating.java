package com.vti.form;

public class DepartmentFormForCreating {

	private String name;
	
	private short authorId;

	public DepartmentFormForCreating() {
	}

	public DepartmentFormForCreating(String name, short authorId) {
		this.name = name;
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getAuthorId() {
		return authorId;
	}

	public void setAuthorId(short authorId) {
		this.authorId = authorId;
	}

	
}
