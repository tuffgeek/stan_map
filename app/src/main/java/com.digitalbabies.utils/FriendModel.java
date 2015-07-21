package com.digitalbabies.utils;

public class FriendModel {

	String image="",name="",id="",about="",location="";

	public FriendModel(String image, String name, String id, String about,String location) {
		super();
		this.image = image;
		this.name = name;
		this.id = id;
		this.about = about;
		this.location=location;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	
}
