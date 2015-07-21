package com.digitalbabies.utils;

public class Constants {
	/**
	 * URLS to hit the server
	 */
	// Live URL Base
	public static final String HOST = "http://tuffgeekers.com/demo/traafik/api/";

	//Login Of User
	public static final String LOGIN = HOST + "home/userlogin?";
	//login_name=xyz&password=123

    public static final String LOGIN_FB = HOST + "home/facebookLogin?";
    //email=xyz&facebook_id=123

    public static final String Signup = HOST + "home/RegisterAUser?";
    //email=xyz&username=123&password=123&phone_number=123


	public static final String Profile = HOST + "profile/ViewProfile?";
	//userid=2


	public static final String EditProfile = HOST + "profile/EditProfile?";
	//userid=2&email=test@gmail.com&username=test&password=test&phone_number=9876654345
	// &first_name=test&last_name=testing&gender=female&dob=21-08-1990


	//Registration
	public static final String REGISTRATION = HOST +"register.php?do=signup"; 

	//Forgot
	public static final String FORGOT = HOST +"forgot_password.php?do=forgot_password";

	//EventCategory
	public static final String EVENTCATEGORY = HOST +"event_category.php?do=events_cat";

	//DealCategory
	public static final String DEALCATEGORY = HOST +"deals_category.php?do=deals_cat";

	//Create Deal
	public static final String CREATEDEAL = HOST +"create_deals.php?do=create_deals&type=android";

	//Create Deal
	public static final String CREATEEVENT = HOST +"create_events.php?do=create_event&type=android";

	//Deal List
	public static final String DEALSLIST = HOST +"show_deals.php?do=show_deals";//deals_owner=category=

	//Event List
	public static final String EVENTSLIST = HOST +"show_events.php?do=show_events";//event_owner=category=

	//User Profile
	public static final String SHOWPROFILE = HOST +"view_profile.php?do=view_profile";//&uniq=oF493u45

	//Like Event
	public static final String LIKEEVENT = HOST +"event_likes.php?do=like_dislike_events";//&userid=7T7283aQ&event_id=3971497d

	//Like Deal
	public static final String LIKEDEAL = HOST +"deal_like.php?do=like_dislike_deals";//&userid=7T7283aQ&deal_id=4J7I89ik

	//Like status
	public static final String LIKESTATUS = HOST + "status_like_comment_share.php?do=like_dislike_status";//&userid=7T7283aQ&status_id=1";
	//Create Album
	public static final String CREATEALBUM = HOST +"create_album.php?do=create_album_android";//&album_creator=7T7283aQ&album_title=title&image

	//View all albums of a user
	public static final String VIEWALLALBUMS = HOST +"view_all_albums.php?do=view_all_albums";//&album_creator=7T7283aQ

	//View all photos of an album
	public static final String VIEWALLPHOTOS = HOST +"view_photo_of_album.php?do=view_photo";//&album_creator=oF493u45&album_id=1

	//Upload Photo to a Specific Album
	public static final String UPLOADPHOTOTOALBUM = HOST +"upload_album_pics.php?do=upload_album_photo_android&type=android";//&album_id=1&image=

	//Deal comment
	public static final String DEALCOMMENT = HOST +"deal_comments.php?do=deal_comment";//&userid=7T7283aQ&deal_id=4J7I89ik&comment=hello

	//Event comment
	public static final String EVENTCOMMENT = HOST + "event_comment.php?do=event_comment";//&userid=7T7283aQ&event_id=6ObJ9ak5&comment=hello

	//Status comment
	public static final String STATUSCOMMENT = HOST + "status_like_comment_share.php?do=status_comment";//&userid=7T7283aQ&status_id=1&comment=hey"
	
	//Delete event comment
	public static final String DELETEEVENTCOMMENT = HOST +"delete_event_comment.php?do=del_event_comment";//&id=1&userid=7T7283aQ&event_id=6ObJ9ak5" 

	/*//Edit profile picture
	public static final String EDITPROFILEIMAGE= HOST + "edit_profile_pic.php?do=edit_profile_android";//&userid=1&image
*/
	//Add deals to pinboard
	public static final String ADDDEALTOPINBOARD=HOST+"add_to_pinboard.php?do=add_deal_pinboard";//&userid=oF493u45&deal_id=R7b55zV1

	//Add events to pinboard
	public static final String ADDEVENTTOPINBOARD=HOST+"add_to_pinboard.php?do=add_event_pinboard";//&userid=oF493u45&event_id=R7b55zV1

	//Pinboard events & deals
	public static final String PINBOARD=HOST+"pinboard.php?do=pinboard";//&userid=oF493u45

	//Remove from pinboard
	public static final String REMOVEFROMPINBOARD = HOST +"remove_from_pinboard.php?do=remove_from_pinboard";//&id=1";

	//Change password
	public static final String CHANGEPASSWORD = HOST + "change_password.php?do=change_password";//&uniq=7T7283aQ&current_password=suman123&new_password=suman&confirm_new_password=suman"

	//Message
	public static final String MESSAGELIST = HOST + "message_inbox.php?do=inbox";//&userid=3w94jmk8"

	//Deal comment list
	public static final String DEALCOMMENTLIST = HOST + "comment_list_deals.php?do=show_deal_comment";//&deal_id=5M6VK162"

	//Event comment list
	public static final String EVENTCOMMENTLIST = HOST + "comment_list_events.php?do=show_event_comment";//&eventid=EC3QK326

	//Status comment list
	public static final String STATUSCOMMENTLIST = HOST + "status_comments.php?do=show_status_comment";//&userid=3W94jMk8&status_id=1"
	//Friend list
	public static final String FRIENSDLIST = HOST + "friend_list.php?do=friend_list";//&userid=16428e75"



	//friends
	/*
	 * 0- add as a friend
	 * 1 for req sent
	 * 2 friends
	 */
	//Other user profile
	public static final String OTHERUSER = HOST + "view_user_profile.php?do=view_user_profile";//&userid=3W94jMk8&anotherId=oF493u45";//&uniq=16428e75" 




	/*//Edit profile
	public static final String EDITPROFILE= HOST + "edit_profile.php?do=edit_profile_a&type=android";//&id=552143GM&fname=suman&lname=Sharma&email=sumasn@slinfy.com&username=suman05&dob=1993-08-01&country=india&description=hey%20guys"
*/
	//Send friend request
	public static final String SENDFRIENDREQUEST = HOST + "add_friend.php?do=add_friend";//&userid=7T7283aQ&another_userid=16428e75" 

	//Search user for adding friends
	public static final String SEARCHUSER = HOST + "show_all_users.php?do=all_users";//&userid=oF493u45&name=sum"

	//Send message
	public static final String SEND_MESSGAE = HOST+"send_message.php?do=send_message";// &sender_id=7T7283aQ&reciever_id=16428e75&message=hello

	//Message Detail
	public static final String CONVERSTAION_DETAIL = HOST+"user_conversation.php?do=conversation";// &conversation_id=1N8NrUv9

	//Update status
	public static final String UPDATESTATUS = HOST + "update_status.php?do=update_status_a&type=android";//&userid=7T7283aQ&status=hey%20guyss"

	//Block a user
	public static final String BLOCKUSER = HOST + "block_user.php?do=block";//&blocked_user=7T7283aQ&blocked_by=oF493u45" 

	//Block users
	public static final String BLOCKLIST = HOST + "block_list.php?do=block_list";//&userid=of493u45"

	//Unblock user
	public static final String UNBLOCKUSER = HOST + "unblock.php?do=unblock";//&userid=oF493u45&blocked_user=3W94jMk8"

	//Friend request list
	public static final String FRIENDREQUESTLIST = HOST + "show_all_friend_req.php?do=show_all_friend_req";//&userid=of493u45"

	//Accept/reject friend request
	public static final String ACCEPTREJECTREQUEST = HOST + "accept_reject_request.php?do=accpt_rejct";//&userid=7T7283aQ&another_userid=16428e75&req_status=1"

	//Search deals
	public static final String SEARCHDEALS = HOST + "search_events_deals.php?do=serch_deals";//name=e"

	//Search events
	public static final String SEARCHEVENTS = HOST + "search_events_deals.php?do=serch_events";//name=e"

	//Share deal
	public static final String SHAREDEAL = HOST + "deal_share.php?do=deal_share";//&userid=oF493u45&deal_id=c9pyL51V"

	//Share event
	public static final String SHAREEVENT = HOST + "event_share.php?do=event_share";//&userid=oF493u45&event_id=l5rxy6b2"

	//Share status
	public static final String SHARESTATUS = HOST + "status_like_comment_share.php?do=status_share";//&userid=7T7283aQ&status_id=1"
	
	//Deal delete comment
	public static final String DEALDELETECOMMENT = HOST + "delete_deal_comment.php?do=del_deal_comment";//&id=1&userid=7T7283aQ&deal_id=4J7I89ikg

	//Event delete comment
	public static final String EVENTDELETECOMMENT = HOST + "delete_event_comment.php?do=del_event_comment";//&id=1&userid=7T7283aQ&event_id=6ObJ9ak5"
	
	//Deactivate account
	public static final String DEACTIVATEACCOUNT = HOST + "delete_account.php?do=delete_profile";//&userid=16428e75"
	
	//Status list/newsfeed
	public static final String NEWSFEED = HOST + "status_list.php?do=newsfeed";//&userid=oF493u45"
	
	//Set filters for deal
	public static final String DEALSETTINGS = HOST + "settings.php?do=filter_settings_deals";//&userid=IWp76s35&deal_cat_id=5,6,7"
	
	//Set filters fpr event
	public static final String EVENTSETTINGS = HOST + "settings.php?do=filter_settings_events";//&userid=IWp76s35&event_cat_id=1,3,4"
	
	//Suman Thakur 16428e75
	//Suman Simmy 7T7283aQ
	//Hitesh oF493u45
	//shruti 3W94jMk8

}
