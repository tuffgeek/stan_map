package com.digitalbabies.traafik;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.digitalbabies.utils.Constants;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import restapi.RestApiCallListener;
import restapi.RestApiCallPost;


/**
 * Created by ankit on 13/7/15.
 */
public class Login extends Activity implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, RestApiCallListener
        {
            private static final int LOGIN_WS = 1,LOGIN_WS_FB=2;
            private static final String TAG =" Google plus";
            private EditText etUsername, etPassword;
            private Button login , signup;
           // private GoogleApiClient mGoogleApiClient;

            private static final int RC_SIGN_IN = 0;
            protected StringBuilder sbr;
            protected String jobj,userid,username_pref,password_pref;

            /* Client used to interact with Google APIs. */
            private GoogleApiClient mGoogleApiClient;


            private boolean mIsResolving = false;
            private SharedPreferences preferences;
            	private SharedPreferences.Editor toEdit;
            /* Should we automatically resolve ConnectionResults when possible? */
            private boolean mShouldResolve = false;

            private static final String PERMISSION = "publish_actions";
            private static final Location SEATTLE_LOCATION = new Location("") {
                {
                    setLatitude(47.6097);
                    setLongitude(-122.3331);
                }
            };




            private final String PENDING_ACTION_BUNDLE_KEY =
                    "com.example.hellofacebook:PendingAction";

            private Button postStatusUpdateButton;
            private Button postPhotoButton;
            private ProfilePictureView profilePictureView;
            private TextView greeting;
            private PendingAction pendingAction = PendingAction.NONE;
            private boolean canPresentShareDialog;
            private boolean canPresentShareDialogWithPhotos;
            private CallbackManager callbackManager;
            private ProfileTracker profileTracker;
            private ShareDialog shareDialog;
            private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
                @Override
                public void onCancel() {
                    Log.d("HelloFacebook", "Canceled");
                }

                @Override
                public void onError(FacebookException error) {
                    Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
                    String title = getString(R.string.error);
                    String alertMessage = error.getMessage();
                    showResult(title, alertMessage);
                }

                @Override
                public void onSuccess(Sharer.Result result) {
                    Log.d("HelloFacebook", "Success!");
                    if (result.getPostId() != null) {
                        String title = getString(R.string.success);
                        String id = result.getPostId();
                        String alertMessage = getString(R.string.successfully_posted_post, id);
                        showResult(title, alertMessage);
                    }
                }

                private void showResult(String title, String alertMessage) {
                    new AlertDialog.Builder(Login.this)
                            .setTitle(title)
                            .setMessage(alertMessage)
                            .setPositiveButton(R.string.ok, null)
                            .show();
                }
            };
            private ProgressDialog dialog;
            String response = "";
            @Override
            public void onResponse(String response, int pageId) {
                this.response = response;

                handler.sendEmptyMessage(pageId);

            }

            @Override
            public void onError(String error) {

                Utils.getAlertDialog(Login.this,error,new Handler());

            }

            Handler handler = new Handler()
            {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case LOGIN_WS: {

                            try {
                                Log.d("dialog 4" , "Response");
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.optString("Status").equalsIgnoreCase(
                                        "1")) {

                                    Log.d("dialog 5" , "Response");

                                    jobj = jsonObject.getString("Status");
                                    userid = jsonObject.getString("userid");

                                    toEdit.putString("userid", userid);
                                    toEdit.commit();

                                    Log.d("Status  is ", "" + jobj);
                                    Log.d("userid kkkk is ", "" + userid);



                                            Intent map = new Intent(Login.this, Home.class);
                                            startActivity(map);
                                            overridePendingTransition(0, 0);
                                            finish();


                                } else {

                                    jobj = jsonObject.getString("message");
                                    Log.d("errooorrr  is ", "" + jobj);



                                            Utils.dismissDialog(dialog);
                                            Utils.getAlertDialog(Login.this, "" + jobj,
                                          new Handler()).show();



                                    System.out.println("elseeeeee");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;

                        case LOGIN_WS_FB: {

                            try {
                                 Log.d("dialog 3" , "Response");
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.optString("Status").equalsIgnoreCase(
                                        "1")) {

                                    jobj = jsonObject.getString("Status");
                                    Log.d("Status  is ", "" + jobj);


                                            Utils.dismissDialog(dialog);

                                            Intent map = new Intent(Login.this, Home.class);
                                            startActivity(map);
                                            overridePendingTransition(0, 0);
                                            finish();


                                } else {

                                    jobj = jsonObject.getString("message");
                                    Log.d("errooorrr  is ", "" + jobj);


                                            Utils.dismissDialog(dialog);
                                            Utils.getAlertDialog(Login.this, "" + jobj,
                                                    new Handler()).show();

                                    System.out.println("elseeeeee");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };

            private enum PendingAction {
                NONE,
                POST_PHOTO,
                POST_STATUS_UPDATE
            }
            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        try{

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handlePendingAction();
                        updateUI();
                    }

                    @Override
                    public void onCancel() {
                        if (pendingAction != PendingAction.NONE) {
                            showAlert();
                            pendingAction = PendingAction.NONE;
                        }
                        updateUI();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        if (pendingAction != PendingAction.NONE
                                && exception instanceof FacebookAuthorizationException) {
                            showAlert();
                            pendingAction = PendingAction.NONE;
                        }
                        updateUI();
                    }

                    private void showAlert() {
                        new AlertDialog.Builder(Login.this)
                                .setTitle(R.string.cancelled)
                                .setMessage(R.string.permission_not_granted)
                                .setPositiveButton(R.string.ok, null)
                                .show();
                    }
                });

        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(
                callbackManager,
                shareCallback);

        if (savedInstanceState != null) {
            String name = savedInstanceState.getString(PENDING_ACTION_BUNDLE_KEY);
            pendingAction = PendingAction.valueOf(name);
        }

        setContentView(R.layout.login);

                preferences = getSharedPreferences(Myprefs.MyPreferences, MODE_PRIVATE);
                toEdit = preferences.edit();



                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .addApi(Plus.API)
                        .addScope(new Scope(Scopes.PROFILE))
                        .build();

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                updateUI();
                // It's possible that we were waiting for Profile to be populated in order to
                // post a status update.
                handlePendingAction();
            }
        };

        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
        greeting = (TextView) findViewById(R.id.greeting);

        postStatusUpdateButton = (Button) findViewById(R.id.postStatusUpdateButton);
        postStatusUpdateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickPostStatusUpdate();
            }
        });

        postPhotoButton = (Button) findViewById(R.id.postPhotoButton);
        postPhotoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickPostPhoto();
            }
        });

        // Can we present the share dialog for regular links?
        canPresentShareDialog = ShareDialog.canShow(
                ShareLinkContent.class);

        // Can we present the share dialog for photos?
        canPresentShareDialogWithPhotos = ShareDialog.canShow(
                SharePhotoContent.class);
        findId();

    }

            @Override
            protected void onStart() {
                super.onStart();
                mGoogleApiClient.connect();
            }

            @Override
            protected void onStop() {
                super.onStop();
                mGoogleApiClient.disconnect();
            }



            @Override
            protected void onResume() {
                super.onResume();

                AppEventsLogger.activateApp(this);

                updateUI();
            }

            @Override
            protected void onSaveInstanceState(Bundle outState) {
                super.onSaveInstanceState(outState);

                outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                callbackManager.onActivityResult(requestCode, resultCode, data);

                Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

                if (requestCode == RC_SIGN_IN) {
                    // If the error resolution was not successful we should not resolve further.
                    if (resultCode != RESULT_OK) {
                        mShouldResolve = false;
                    }

                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }


            }

            @Override
            public void onPause() {
                super.onPause();

                // Call the 'deactivateApp' method to log an app event for use in analytics and advertising
                // reporting.  Do so in the onPause methods of the primary Activities that an app may be
                // launched into.
                AppEventsLogger.deactivateApp(this);
            }

            @Override
            protected void onDestroy() {
                super.onDestroy();
                profileTracker.stopTracking();
                          }

            private void updateUI() {
                boolean enableButtons = AccessToken.getCurrentAccessToken() != null;

                postStatusUpdateButton.setEnabled(enableButtons || canPresentShareDialog);
                postPhotoButton.setEnabled(enableButtons || canPresentShareDialogWithPhotos);

                Profile profile = Profile.getCurrentProfile();
                if (enableButtons && profile != null) {
                    profilePictureView.setProfileId(profile.getId());
                    greeting.setText(getString(R.string.hello_user, profile.getFirstName()));

                    fblogin(profile);
                } else {
                    profilePictureView.setProfileId(null);
                    greeting.setText(null);
                }
            }

            private void fblogin(Profile currentProfile) {


                // ** parse facebook data in to wrapper class*//*
                //UserProfile holder = new UserProfile();
//TODO: save user fb ID
                //AppController.saveUserFBId(currentProfile.getId());
                findViewById(R.id.main_ui_container).setVisibility(View.GONE);
                final String arr[] = new String[2];
                arr[0] = currentProfile.getId();//fb id
                arr[1] = AccessToken.getCurrentAccessToken().toString().substring(0,8)+"@gmail.com"; //email

                Log.d("FACEBOOK id", arr[0]);
                Log.d("FACEBOOK email", arr[1]);


                if (Utils.isNetWorking(Login.this)) {

                   // dialog = Utils.showDialog("Please wait..." , Login.this);
                     Log.d("dialog 2" , "fbapi");

                    dialog = Utils.showDialog("Please wait...", Login.this);
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    List<NameValuePair> myArgs = new ArrayList<NameValuePair>();

                    myArgs.add(new BasicNameValuePair("email",
                            arr[1]));
                    myArgs.add(new BasicNameValuePair("facebook_id",
                            arr[0]));
                    RestApiCallPost restpost= new RestApiCallPost(Constants.LOGIN_FB,Login.this,myArgs,LOGIN_WS_FB);
                    restpost.start();
                  //  Utils.dismissDialog(dialog);


                }
                else {
                    Utils.getAlertDialog(Login.this, "Please Check your internet connection",
                            new Handler()).show();
                }


            }

            private void handlePendingAction() {
                PendingAction previouslyPendingAction = pendingAction;
                // These actions may re-set pendingAction if they are still pending, but we assume they
                // will succeed.
                pendingAction = PendingAction.NONE;

                switch (previouslyPendingAction) {
                    case NONE:
                        break;
                    case POST_PHOTO:
                        postPhoto();
                        break;
                    case POST_STATUS_UPDATE:
                        postStatusUpdate();
                        break;
                }
            }

            private void onClickPostStatusUpdate() {
                performPublish(PendingAction.POST_STATUS_UPDATE, canPresentShareDialog);
            }

            private void postStatusUpdate() {
                Profile profile = Profile.getCurrentProfile();
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle("Hello Facebook")
                        .setContentDescription(
                                "The 'Hello Facebook' sample  showcases simple Facebook integration")
                        .setContentUrl(Uri.parse("http://developers.facebook.com/docs/android"))
                        .build();
                if (canPresentShareDialog) {
                    shareDialog.show(linkContent);
                } else if (profile != null && hasPublishPermission()) {
                    ShareApi.share(linkContent, shareCallback);
                } else {
                    pendingAction = PendingAction.POST_STATUS_UPDATE;
                }
            }

            private void onClickPostPhoto() {
                performPublish(PendingAction.POST_PHOTO, canPresentShareDialogWithPhotos);
            }

            private void postPhoto() {
                Bitmap image = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon);
                SharePhoto sharePhoto = new SharePhoto.Builder().setBitmap(image).build();
                ArrayList<SharePhoto> photos = new ArrayList<>();
                photos.add(sharePhoto);

                SharePhotoContent sharePhotoContent =
                        new SharePhotoContent.Builder().setPhotos(photos).build();
                if (canPresentShareDialogWithPhotos) {
                    shareDialog.show(sharePhotoContent);
                } else if (hasPublishPermission()) {
                    ShareApi.share(sharePhotoContent, shareCallback);
                } else {
                    pendingAction = PendingAction.POST_PHOTO;
                    // We need to get new permissions, then complete the action when we get called back.
                    LoginManager.getInstance().logInWithPublishPermissions(
                            this,
                            Arrays.asList(PERMISSION));
                }
            }

            private boolean hasPublishPermission() {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                return accessToken != null && accessToken.getPermissions().contains("publish_actions");
            }

            private void performPublish(PendingAction action, boolean allowNoToken) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken != null || allowNoToken) {
                    pendingAction = action;
                    handlePendingAction();
                }
            }







    private void findId() {

            etUsername = (EditText) findViewById(R.id.Email_login);
            etPassword = (EditText) findViewById(R.id.Passwrd_login);


                		String shared_name = preferences.getString("Username", null);
                		String shared_pasword = preferences.getString("Password", null);

                            etUsername.setText(shared_name);
                            etPassword.setText(shared_pasword);


            login = (Button) findViewById(R.id.Login);
            signup = (Button) findViewById(R.id.signup);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
            setClickListeners();

    }





    private void setClickListeners() {

        login.setOnClickListener(this);
        signup.setOnClickListener(this);


    }







    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            /*case R.id.sign_in_button:
            {
                onSignInClicked();
                break;
            }*/

            case R.id.signup:
            {
               // Toast.makeText(getApplication(),"hhhh",Toast.LENGTH_LONG).show();

                Intent lgn= new Intent(Login.this,Signup.class);
                startActivity(lgn);
                overridePendingTransition(0, 0);

                break;
            }

            case R.id.Login:
                
                sharedPrefernces();

                if (etUsername.getText().toString().trim().equalsIgnoreCase("")) {
                    Utils.getAlertDialog(Login.this, "Please Enter Username or Email or Mobile No.",
                            new Handler()).show();
                    etUsername.requestFocus();
                    return;
                }

                if (etPassword.getText().toString().trim().equalsIgnoreCase("")) {
                    Utils.getAlertDialog(Login.this, "Please enter Password.",
                            new Handler()).show();
                    etPassword.requestFocus();
                    return;
                }


                if (Utils.isNetWorking(Login.this)) {

                    Log.d("dialog 1" , "Restapi");

                    dialog = Utils.showDialog("Please wait...", Login.this);
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    List<NameValuePair> myArgs = new ArrayList<NameValuePair>();

                    myArgs.add(new BasicNameValuePair("login_name",
                            username));
                    myArgs.add(new BasicNameValuePair("password",
                            password));
                    RestApiCallPost restpost= new RestApiCallPost(Constants.LOGIN,Login.this,myArgs,LOGIN_WS);
                    restpost.start();

                 //   Utils.dismissDialog(dialog);

                }
                else {
                    Utils.getAlertDialog(Login.this, "Please Check your internet connection",
                            new Handler()).show();
                }

                break;
        }




        }

            private void sharedPrefernces() {

                    username_pref = etUsername.getText().toString();
               		password_pref =etPassword.getText().toString();

               		Log.d("username_f", username_pref);
               		Log.d("password_f", password_pref);

               		toEdit.putString("Username", username_pref);
               		toEdit.putString("Password", password_pref);
               		toEdit.commit();


            }

            private void onSignInClicked() {

                mShouldResolve = true;
                mGoogleApiClient.connect();

                // Show a message to the user that we are signing in.
               // mStatusTextView.setText(R.string.signing_in);
            }




            @Override
            public void onConnected(Bundle bundle) {

                // onConnected indicates that an account was selected on the device, that the selected
                // account has granted any requested permissions to our app and that we were able to
                // establish a service connection to Google Play services.
                Log.d(TAG, "onConnected:" + bundle);
                mShouldResolve = false;

                // Show the signed-in UI
              //  showSignedInUI();
                Log.e(TAG, "Defail lines");

            }

            @Override
            public void onConnectionSuspended(int i) {

            }

            @Override
            public void onConnectionFailed(ConnectionResult connectionResult) {

                Log.d(TAG, "onConnectionFailed:" + connectionResult);

                if (!mIsResolving && mShouldResolve) {
                    if (connectionResult.hasResolution()) {
                        try {
                            connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                            mIsResolving = true;
                        } catch (IntentSender.SendIntentException e) {
                            Log.e(TAG, "Could not resolve ConnectionResult.", e);
                            mIsResolving = false;
                            mGoogleApiClient.connect();
                        }
                    } else {
                        // Could not resolve the connection result, show the user an
                        // error dialog.
                       // showErrorDialog(connectionResult);
                    }
                } else {
                    // Show the signed-out UI
                    Log.e(TAG, "Defail lines");
                }

            }





        }
