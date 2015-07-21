package com.digitalbabies.utils;

public class MessageModel {

	String cName="",cMessage="",cDate="",cTime="",cId="",cImage="",cOtherUserId="";

	public MessageModel(String cName, String cMessage, String cDate,
			String cTime, String cId, String cImage, String cOtherUserId) {
		super();
		this.cName = cName;
		this.cMessage = cMessage;
		this.cDate = cDate;
		this.cTime = cTime;
		this.cId = cId;
		this.cImage = cImage;
		this.cOtherUserId=cOtherUserId;
	}

	public String getcOtherUserId() {
		return cOtherUserId;
	}

	public void setcOtherUserId(String cOtherUserId) {
		this.cOtherUserId = cOtherUserId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcMessage() {
		return cMessage;
	}

	public void setcMessage(String cMessage) {
		this.cMessage = cMessage;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcImage() {
		return cImage;
	}

	public void setcImage(String cImage) {
		this.cImage = cImage;
	}
}
