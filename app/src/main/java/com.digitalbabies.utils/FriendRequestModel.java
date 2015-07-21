package com.digitalbabies.utils;

public class FriendRequestModel {

	String userid="",Name="",image="",country="";

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public FriendRequestModel(String userid, String name, String image,String country) {
		super();
		this.userid = userid;
		Name = name;
		this.image = image;
		this.country=country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
