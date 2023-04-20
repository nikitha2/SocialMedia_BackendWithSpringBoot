package com.springboot.social_media.advanced_concepts.models;

public class PersonV1 {

	String name;
	
	public PersonV1(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "PersonV1 [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
