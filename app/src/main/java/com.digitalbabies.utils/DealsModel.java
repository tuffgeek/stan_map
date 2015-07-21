package com.digitalbabies.utils;

import java.io.Serializable;

public class DealsModel implements Serializable {
	private String username,posted_time,likes,shares,comments,offer,Deal_Name,Date,deal_id,description,location,price,contact,Deal_Photo,isLiked;
	
	public String getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(String isLiked) {
		this.isLiked = isLiked;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPosted_time() {
		return posted_time;
	}

	public void setPosted_time(String posted_time) {
		this.posted_time = posted_time;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getShares() {
		return shares;
	}

	public void setShares(String shares) {
		this.shares = shares;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public DealsModel(String deal_Name2, String date2, String deal_id2,
			String description2, String location2, String price2,
			String contact2, String deal_Photo2, String offer2, String likes2, String shares2, String comments2, String posted_time2, String username2,String isLiked2) {
		this.Deal_Name=deal_Name2;
		this.Date=date2;
		this.deal_id=deal_id2;
		this.description=description2;
		this.location=location2;
		this.price=price2;
		this.contact=contact2;
		this.Deal_Photo=deal_Photo2;
		this.offer=offer2;
		this.likes=likes2;
		this.shares=shares2;
		this.comments=comments2;
		this.posted_time=posted_time2;
		this.username=username2;
		this.isLiked=isLiked2;
	}

	public String getDeal_Name() {
		return Deal_Name;
	}

	public void setDeal_Name(String deal_Name) {
		Deal_Name = deal_Name;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDeal_Photo() {
		return Deal_Photo;
	}

	public void setDeal_Photo(String deal_Photo) {
		Deal_Photo = deal_Photo;
	}
}
