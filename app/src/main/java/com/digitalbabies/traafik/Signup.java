package com.digitalbabies.traafik;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.digitalbabies.utils.Constants;
import com.digitalbabies.utils.Util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import restapi.RestApiCallListener;
import restapi.RestApiCallPost;


public class Signup extends Activity implements View.OnClickListener, RestApiCallListener {

    private static final int Signup_WS = 1;
    private EditText username_ET, email_ET, password_ET, mobile_ET;
    private Button signup, login;
    protected StringBuilder sbr;
    protected String jobj;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findId();
        printKeyHash(Signup.this);
    }


    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }


    private void findId() {
        username_ET = (EditText) findViewById(R.id.username);
        email_ET = (EditText) findViewById(R.id.Email);
        mobile_ET = (EditText) findViewById(R.id.MobileNo);
        password_ET = (EditText) findViewById(R.id.Password);
        signup = (Button) findViewById(R.id.sign_up);
        login = (Button) findViewById(R.id.login);

        setClickListeners();
    }

    private void setClickListeners() {

        signup.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_up:


                if (username_ET.getText().toString().trim().equalsIgnoreCase("")) {
                    Utils.getAlertDialog(Signup.this, "Please enter Username.",
                            new Handler()).show();
                    username_ET.requestFocus();
                    return;
                }

                if (email_ET.getText().toString().trim().equalsIgnoreCase("")) {
                    Utils.getAlertDialog(Signup.this, "Please enter E-mail.",
                            new Handler()).show();
                    email_ET.requestFocus();
                    return;
                }
                if (!Validator.isValidEmail(email_ET.getText().toString().trim())) {
                    Utils.getAlertDialog(Signup.this, "Please enter a valid E-mail.",
                            new Handler()).show();
                    email_ET.requestFocus();
                    return;
                }

                if (mobile_ET.getText().toString().trim().equalsIgnoreCase("")) {
                    Utils.getAlertDialog(Signup.this, "Please enter Mobile NO.",
                            new Handler()).show();
                    mobile_ET.requestFocus();
                    return;
                }

                if (mobile_ET.getText().toString().trim().length() > 10) {
                    Utils.getAlertDialog(Signup.this, "Please enter a valid Mobile NO.",
                            new Handler()).show();
                    mobile_ET.requestFocus();
                    return;
                }
                if (!Validator.isValidMobileNo(mobile_ET.getText().toString().trim())) {
                    Utils.getAlertDialog(Signup.this, "Please enter a valid Mobile NO.",
                            new Handler()).show();
                    mobile_ET.requestFocus();
                    return;
                }

                if (password_ET.getText().toString().trim().equalsIgnoreCase("")) {
                    Utils.getAlertDialog(Signup.this, "Please enter Password.",
                            new Handler()).show();
                    password_ET.requestFocus();
                    return;
                }

                if (Utils.isNetWorking(Signup.this) == true) {


                    dialog = Utils.showDialog("Please wait...", Signup.this);

                    String username_s = username_ET.getText().toString();
                    String email_s = email_ET.getText().toString();
                    String mobile_s = mobile_ET.getText().toString();
                    String password_s = password_ET.getText().toString();

                    List<NameValuePair> myArgs = new ArrayList<NameValuePair>();


                    myArgs.add(new BasicNameValuePair("email",
                            email_s));
                    myArgs.add(new BasicNameValuePair("username",
                            username_s));

                    myArgs.add(new BasicNameValuePair("password",
                            password_s));
                    myArgs.add(new BasicNameValuePair("phone_number",
                            mobile_s));

                    RestApiCallPost restpost = new RestApiCallPost(Constants.Signup, Signup.this, myArgs, Signup_WS);
                    restpost.start();


                }


                else {
                    Utils.getAlertDialog(Signup.this, "Please Check your internet connection",
                            new Handler()).show();
                }
                break;
            case R.id.login:

                Intent lgn = new Intent(Signup.this, Login.class);
                startActivity(lgn);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    String response = "";
    @Override
    public void onResponse(String response, int pageId) {

        this.response = response;

        handler.sendEmptyMessage(pageId);


    }

    @Override
    public void onError(String error) {

        Utils.getAlertDialog(Signup.this,error,new Handler());

    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            switch (msg.what) {

                case Signup_WS: {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("Status").equalsIgnoreCase(
                            "1")) {

                        jobj = jsonObject.getString("Status");
                        Log.d("Status  is ", "" + jobj);

                                Utils.dismissDialog(dialog);

                                Intent lgn= new Intent(Signup.this,Login.class);
                                startActivity(lgn);
                                finish();

                                Util.getAlertDialog(Signup.this, "User Registered",
                                        new Handler()).show();


                    } else {

                        jobj = jsonObject.getString("message");
                        Log.d("errooorrr  is ", "" + jobj);

                                Utils.dismissDialog(dialog);
                                Utils.getAlertDialog(Signup.this,"" + jobj,
                                        new Handler()).show();

                        System.out.println("elseeeeee");
                    }

                }  catch (Exception e) {
                        e.printStackTrace();

                }

            }
        }
    }

    };
}
