package com.digitalbabies.utils;

public class NewsfeedModel {
	String id,wall_post_creator_id,wallusername,wall_post_title,venueid,type,time,wall_post_created_on,user_pro_pic;
	EventFeed eventFeed;
	
	DealFeed dealFeed;
	
	StatusFeed statusFeed;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWall_post_creator_id() {
		return wall_post_creator_id;
	}
	public void setWall_post_creator_id(String wall_post_creator_id) {
		this.wall_post_creator_id = wall_post_creator_id;
	}
	public String getWallusername() {
		return wallusername;
	}
	public void setWallusername(String wallusername) {
		this.wallusername = wallusername;
	}
	public String getWall_post_title() {
		return wall_post_title;
	}
	public void setWall_post_title(String wall_post_title) {
		this.wall_post_title = wall_post_title;
	}
	public String getVenueid() {
		return venueid;
	}
	public void setVenueid(String venueid) {
		this.venueid = venueid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWall_post_created_on() {
		return wall_post_created_on;
	}
	public void setWall_post_created_on(String wall_post_created_on) {
		this.wall_post_created_on = wall_post_created_on;
	}
	public String getUser_pro_pic() {
		return user_pro_pic;
	}
	public void setUser_pro_pic(String user_pro_pic) {
		this.user_pro_pic = user_pro_pic;
	}
	public DealFeed getDealFeed() {
		return dealFeed;
	}
	public void setDealFeed(DealFeed dealFeed) {
		this.dealFeed = dealFeed;
	}
	public StatusFeed getStatusFeed() {
		return statusFeed;
	}
	public void setStatusFeed(StatusFeed statusFeed) {
		this.statusFeed = statusFeed;
	}
	public EventFeed getEventFeed() {
		return eventFeed;
	}

	public void setEventFeed(EventFeed eventFeed) {
		this.eventFeed = eventFeed;
	}

	

	public NewsfeedModel()
	{
		
	}
	public NewsfeedModel(String id, String wall_post_creator_id,
			String wallusername, String wall_post_title, String venueid,
			String type, String time, String wall_post_created_on,
			String user_pro_pic,EventFeed eventFeed) {
		super();
		this.id = id;
		this.wall_post_creator_id = wall_post_creator_id;
		this.wallusername = wallusername;
		this.wall_post_title = wall_post_title;
		this.venueid = venueid;
		this.type = type;
		this.time = time;
		this.wall_post_created_on = wall_post_created_on;
		this.user_pro_pic = user_pro_pic;
		this.eventFeed=eventFeed;
		
	}
	
	
	
	public NewsfeedModel(String id, String wall_post_creator_id,
			String wallusername, String wall_post_title, String venueid,
			String type, String time, String wall_post_created_on,
			String user_pro_pic,DealFeed dealFeed) {
		super();
		this.id = id;
		this.wall_post_creator_id = wall_post_creator_id;
		this.wallusername = wallusername;
		this.wall_post_title = wall_post_title;
		this.venueid = venueid;
		this.type = type;
		this.time = time;
		this.wall_post_created_on = wall_post_created_on;
		this.user_pro_pic = user_pro_pic;
		this.dealFeed=dealFeed;
	}
	
	
	public NewsfeedModel(String id, String wall_post_creator_id,
			String wallusername, String wall_post_title, String venueid,
			String type, String time, String wall_post_created_on,
			String user_pro_pic,StatusFeed statusFeed) {
		super();
		this.id = id;
		this.wall_post_creator_id = wall_post_creator_id;
		this.wallusername = wallusername;
		this.wall_post_title = wall_post_title;
		this.venueid = venueid;
		this.type = type;
		this.time = time;
		this.wall_post_created_on = wall_post_created_on;
		this.user_pro_pic = user_pro_pic;
		this.statusFeed=statusFeed;
	}

	
	public class EventFeed
	{
		public EventFeed(String event_name, String description,
				String location, String total_likes, String total_comment,
				String total_share, String image, String event_id,String contact2,String time2,String is_liked) {
			super();
			this.event_name = event_name;
			this.description = description;
			this.location = location;
			this.total_likes = total_likes;
			this.total_comment = total_comment;
			this.total_share = total_share;
			this.image = image;
			this.event_id = event_id;
			this.contact=contact2;
			this.time=time2;
			this.is_liked=is_liked;
		}

		public String getIs_liked() {
			return is_liked;
		}

		public void setIs_liked(String is_liked) {
			this.is_liked = is_liked;
		}

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getEvent_name() {
			return event_name;
		}

		public void setEvent_name(String event_name) {
			this.event_name = event_name;
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

		public String getTotal_likes() {
			return total_likes;
		}

		public void setTotal_likes(String total_likes) {
			this.total_likes = total_likes;
		}

		public String getTotal_comment() {
			return total_comment;
		}

		public void setTotal_comment(String total_comment) {
			this.total_comment = total_comment;
		}

		public String getTotal_share() {
			return total_share;
		}

		public void setTotal_share(String total_share) {
			this.total_share = total_share;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getEvent_id() {
			return event_id;
		}

		public void setEvent_id(String event_id) {
			this.event_id = event_id;
		}

		String event_name,description,location,total_likes,total_comment,total_share,image,event_id,contact,time,is_liked;
	}
	
	public class DealFeed
	{
		String deal_name,description,location,total_likes,total_comment,total_share,image,deal_id,price,contact,timee,offer,is_liked;

		public DealFeed(String deal_name, String description, String location,
				String total_likes, String total_comment, String total_share,
				String image, String deal_id,String price,String contact,String timee,String offer,String is_liked) {
			super();
			this.deal_name = deal_name;
			this.description = description;
			this.location = location;
			this.total_likes = total_likes;
			this.total_comment = total_comment;
			this.total_share = total_share;
			this.image = image;
			this.deal_id = deal_id;
			this.price=price;
			this.contact=contact;
			this.timee=timee;
			this.offer=offer;
			this.is_liked=is_liked;
		}

		public String getIs_liked() {
			return is_liked;
		}

		public void setIs_liked(String is_liked) {
			this.is_liked = is_liked;
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

		public String getTimee() {
			return timee;
		}

		public void setTimee(String timee) {
			this.timee = timee;
		}

		public String getOffer() {
			return offer;
		}

		public void setOffer(String offer) {
			this.offer = offer;
		}

		public String getDeal_name() {
			return deal_name;
		}

		public void setDeal_name(String deal_name) {
			this.deal_name = deal_name;
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

		public String getTotal_likes() {
			return total_likes;
		}

		public void setTotal_likes(String total_likes) {
			this.total_likes = total_likes;
		}

		public String getTotal_comment() {
			return total_comment;
		}

		public void setTotal_comment(String total_comment) {
			this.total_comment = total_comment;
		}

		public String getTotal_share() {
			return total_share;
		}

		public void setTotal_share(String total_share) {
			this.total_share = total_share;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getDeal_id() {
			return deal_id;
		}

		public void setDeal_id(String deal_id) {
			this.deal_id = deal_id;
		}
	}
	
	public class StatusFeed
	{
		String status,total_likes,total_comments,total_share,image,status_id;

		public StatusFeed(String status, String total_likes,
				String total_comments, String total_share, String image,
				String status_id) {
			super();
			this.status = status;
			this.total_likes = total_likes;
			this.total_comments = total_comments;
			this.total_share = total_share;
			this.image = image;
			this.status_id = status_id;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getTotal_likes() {
			return total_likes;
		}

		public void setTotal_likes(String total_likes) {
			this.total_likes = total_likes;
		}

		public String getTotal_comments() {
			return total_comments;
		}

		public void setTotal_comments(String total_comments) {
			this.total_comments = total_comments;
		}

		public String getTotal_share() {
			return total_share;
		}

		public void setTotal_share(String total_share) {
			this.total_share = total_share;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getStatus_id() {
			return status_id;
		}

		public void setStatus_id(String status_id) {
			this.status_id = status_id;
		}
	}
	
	
	
}
