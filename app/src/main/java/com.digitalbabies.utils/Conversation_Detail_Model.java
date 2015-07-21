package com.digitalbabies.utils;

public class Conversation_Detail_Model {

	String message="",Sender_Name="",Sender_id="",Receiver_Name="",Time="";

	public Conversation_Detail_Model(String message, String sender_Name,
			String sender_id, String receiver_Name, String time) {
		super();
		this.message = message;
		this.Sender_Name = sender_Name;
		this.Sender_id = sender_id;
		this.Receiver_Name = receiver_Name;
		this.Time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender_Name() {
		return Sender_Name;
	}

	public void setSender_Name(String sender_Name) {
		Sender_Name = sender_Name;
	}

	public String getSender_id() {
		return Sender_id;
	}

	public void setSender_id(String sender_id) {
		Sender_id = sender_id;
	}

	public String getReceiver_Name() {
		return Receiver_Name;
	}

	public void setReceiver_Name(String receiver_Name) {
		Receiver_Name = receiver_Name;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	
}
