package com.digitalbabies.utils;

public class SearchUserModel {

	
	private String image,Name,userid,country,already_friend;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAlready_friend() {
		return already_friend;
	}

	public void setAlready_friend(String already_friend) {
		this.already_friend = already_friend;
	}

	public SearchUserModel(String image, String name, String userid,
			String country, String already_friend) {
		super();
		this.image = image;
		Name = name;
		this.userid = userid;
		this.country = country;
		this.already_friend = already_friend;
	}
}
