package com.digitalbabies.utils;

public class BlockedUserModel {

	String Blocked_User_Id="",Profile_Picture="",Name="",Country="";
	

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getBlocked_User_Id() {
		return Blocked_User_Id;
	}

	public void setBlocked_User_Id(String blocked_User_Id) {
		Blocked_User_Id = blocked_User_Id;
	}

	public String getProfile_Picture() {
		return Profile_Picture;
	}

	public void setProfile_Picture(String profile_Picture) {
		Profile_Picture = profile_Picture;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public BlockedUserModel(String blocked_User_Id, String profile_Picture,
			String name,String country) {
		super();
		this.Blocked_User_Id = blocked_User_Id;
		this.Profile_Picture = profile_Picture;
		this.Name = name;
		this.Country=country;
	}
}
