package com.springboot.social_media.advanced_concepts.models;

public class PersonV2 {

	String nameFirst;
	String nameLast;

	public PersonV2(String nameFirst, String nameLast) {
		super();
		this.nameFirst = nameFirst;
		this.nameLast = nameLast;

	}

	@Override
	public String toString() {
		return "PersonV2 [nameFirst=" + nameFirst + "nameLast=" + nameLast + "]";
	}

	public String getNameFirst() {
		return nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	public String getNameLast() {
		return nameLast;
	}

	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}

}
