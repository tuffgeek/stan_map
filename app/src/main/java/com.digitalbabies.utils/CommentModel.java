package com.digitalbabies.utils;

public class CommentModel {

	
	String Username,Profile_pic,Comment_time,Commentid,Comment_likes,Comment;
	private String Deal_id;

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getProfile_pic() {
		return Profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		Profile_pic = profile_pic;
	}

	public String getComment_time() {
		return Comment_time;
	}

	public void setComment_time(String comment_time) {
		Comment_time = comment_time;
	}

	public String getCommentid() {
		return Commentid;
	}

	public void setCommentid(String commentid) {
		Commentid = commentid;
	}

	public String getComment_likes() {
		return Comment_likes;
	}

	public void setComment_likes(String comment_likes) {
		Comment_likes = comment_likes;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public CommentModel(String username, String profile_pic,
			String comment_time, String commentid, String comment_likes,
			String comment,String Deal_id) {
		super();
		Username = username;
		Profile_pic = profile_pic;
		Comment_time = comment_time;
		Commentid = commentid;
		Comment_likes = comment_likes;
		Comment = comment;
		this.Deal_id=Deal_id;
	}

	public String getDeal_id() {
		return Deal_id;
	}

	public void setDeal_id(String deal_id) {
		Deal_id = deal_id;
	}
}
