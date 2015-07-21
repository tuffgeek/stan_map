package com.digitalbabies.traafik;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.digitalbabies.utils.Constants;
import com.digitalbabies.utils.Util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import restapi.RestApiCallListener;
import restapi.RestApiCallPost;

/**
 * Created by ankit on 15/7/15.
 */
public class Profile extends Activity implements  RestApiCallListener ,View.OnClickListener{

    EditText etfirstname, etlastname, etusername, etPassword;
    private String id;
    String response = "";


    Integer whohasfocus = 0;
    private final int intfirstname = 1, intlastname = 2, intusername = 3, intpassword = 4;
    private String getuser_id,username="",email="",first_name="",password="",last_name="",gender="",phone_number="",firstname_p="",lastname_p="",username_p="",password_p="";
    private SharedPreferences preferences;
    private SharedPreferences.Editor toEdit;
    private final int  profile_WS =1,Editprofile_WS=2;
    private Button btn_save;
    private String Status,message;
    public AlertDialog.Builder dialoggg;
    private ProgressDialog pdialog;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        preferences = getSharedPreferences(Myprefs.MyPreferences, MODE_PRIVATE);
        toEdit = preferences.edit();

        getuser_id = preferences.getString("userid", "15515");
        Log.d("getuser_id", getuser_id + "");

        findId();


       pdialog = Utils.showDialog("Please wait...", Profile.this);
        List<NameValuePair> myArgs = new ArrayList<NameValuePair>();
        myArgs.add(new BasicNameValuePair("userid",
                getuser_id));

        RestApiCallPost restpost = new RestApiCallPost(Constants.Profile, Profile.this,myArgs, profile_WS);
        restpost.start();

    }




    private void findId() {

        etfirstname = (EditText) findViewById(R.id.etfirstname);
        etlastname = (EditText) findViewById(R.id.etlastname);
        etusername = (EditText) findViewById(R.id.etuserrname);
        etPassword = (EditText) findViewById(R.id.etPaswrd);
        btn_save=(Button) findViewById(R.id.save_button);



        btn_save.setOnClickListener(this);



    }

    String updateFirstname="",updateLastname="",updateUsername="",updatePassword="";


    @Override
    public void onResponse(String response, int pageId) {


        this.response = response;
        Log.d("response ",response+"");

        handler.sendEmptyMessage(pageId);
    }

    @Override
    public void onError(String error) {

        Log.d("errorrr ",error+"");

    }



    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            switch (msg.what) {

                case profile_WS: {

                    try {
                        JSONObject jsonObject = new JSONObject(response);


                        id = jsonObject.getString("id");
                        username = jsonObject.getString("username");
                        email = jsonObject.getString("email");
                        first_name = jsonObject.getString("first_name");
                        last_name = jsonObject.getString("last_name");
                        gender = jsonObject.getString("gender");
                        phone_number = jsonObject.getString("phone_number");
                     //   profile_picture = jsonObject.getString("profile_picture");

                        Log.d("id  is ", "" + id);
                        Log.d("username  is ", "" + username);
                        Log.d("email  is ", "" + email);
                        Log.d("first_name  is ", "" + first_name);
                        Log.d("last_name  is ", "" + last_name);
                        Log.d("gender  is ", "" + gender);
                        Log.d("phone_number  is ", "" + phone_number);
                        updateFirstname = first_name;
                        updateLastname = last_name;
                        updateUsername = username;
                        updatePassword=password;



                        updateUI();

                        Utils.dismissDialog(pdialog);


                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
                break;

                case Editprofile_WS: {

                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.optString("Status").equalsIgnoreCase(
                                "1")) {

                            Status = jsonObject.getString("Status");
                            message = jsonObject.getString("message");
                            Utils.dismissDialog(pdialog);

                            Util.getAlertDialog(Profile.this, "Profile Updated",
                                    new Handler()).show();
                            last_name=updateLastname;
                            first_name=updateFirstname;
                            username=updateUsername;
                            password=updatePassword;


                        } else {
                            if (jsonObject.optString("Status").equalsIgnoreCase(
                                    "0")){

                                Status = jsonObject.getString("Status");
                                message = jsonObject.getString("message");
                                Utils.dismissDialog(pdialog);

                                Util.getAlertDialog(Profile.this, message,
                                        new Handler()).show();

                            }
                        }


                        Log.d("profile Status   is ", "" + Status);
                        Log.d("profile messagee  is ", "" + message);


                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }
        }
    };


    public void updateUI()
    {


        etfirstname.setText(first_name+"");
        etlastname.setText(last_name+"");
        etusername.setText(username + "");
       etPassword.setText("");
        etfirstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateFirstname = s.toString();
            }
        });
        etlastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateLastname = s.toString();
            }
        });
        etusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updateUsername = s.toString();
            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                updatePassword = s.toString();
            }
        });




    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.save_button:


                Log.d("username  is ", "" + username+"_-"+updateUsername);
                Log.d("first_name  is ", "" + first_name+"_-"+updateFirstname);
                Log.d("last_name  is ", "" + last_name+"_-"+updateLastname);


                if(!username.equalsIgnoreCase(updateUsername)||!first_name.equalsIgnoreCase(updateFirstname)||!last_name.equalsIgnoreCase(updateLastname))
                {
                  //  Toast.makeText(getApplicationContext(),"Update ??",Toast.LENGTH_LONG).show();


                    dialoggg = new AlertDialog.Builder(Profile.this);
                    dialoggg.setTitle("Update Profile");
                    dialoggg.setMessage("Do you Want to update your profile");
                    dialoggg.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    pdialog = Utils.showDialog("Please wait...", Profile.this);

                                    List<NameValuePair> myArgs2 = new ArrayList<NameValuePair>();
                                    myArgs2.add(new BasicNameValuePair("userid",
                                            getuser_id));
                                    myArgs2.add(new BasicNameValuePair("email",
                                            email+""));
                                    myArgs2.add(new BasicNameValuePair("username",
                                            updateUsername));
                                    myArgs2.add(new BasicNameValuePair("password",
                                            updatePassword));
                                    myArgs2.add(new BasicNameValuePair("phone_number",
                                            phone_number+""));
                                    myArgs2.add(new BasicNameValuePair("first_name",
                                            updateFirstname));
                                    myArgs2.add(new BasicNameValuePair("last_name",
                                            updateLastname));

                                    myArgs2.add(new BasicNameValuePair("gender",
                                            ""));

                                    myArgs2.add(new BasicNameValuePair("dob",
                                            ""));


                                    RestApiCallPost editprofile = new RestApiCallPost(Constants.EditProfile, Profile.this, myArgs2, Editprofile_WS);
                                    editprofile.start();

                                }
                            });

                    dialoggg.setNegativeButton("NO",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.cancel();

                                }
                            });


                    dialoggg.show();

                }
        }

    }
}
