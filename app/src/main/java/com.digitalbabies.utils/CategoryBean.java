package com.digitalbabies.utils;

import java.io.Serializable;

public class CategoryBean implements Serializable{

	String id,name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryBean( String name, String id) {
		super();
		this.id = id;
		this.name = name;
	}
}
