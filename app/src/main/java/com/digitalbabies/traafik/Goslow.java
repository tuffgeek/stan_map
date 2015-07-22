package com.digitalbabies.traafik;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.digitalbabies.utils.Constants;
import com.digitalbabies.utils.Util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import restapi.RestApiCallListener;
import restapi.RestApiCallPost;

/**
 * Created by ankit on 22/7/15.
 */
public class Goslow extends Activity implements LocationListener,View.OnClickListener, RestApiCallListener {

    Object checkedItem,sidevalue;
    AlertDialog levelDialog;
    private int newside,newchecked;
    protected LocationManager locationManager;
    private EditText et_detail;
    private String detail;
    private Button btn_snd, btn_later;
    private RelativeLayout takepic, myside;
    private ImageView img;
    private TextView txt_myside;
    String response = "";

    private SharedPreferences preferences;
    private SharedPreferences.Editor toEdit;

    final CharSequence[] goslowitem = {"Moderate", "Heavy","Standstill"};
    final CharSequence[] sideoptions= {"My Side", "Other Side"};

    private String Status,message;
    private ProgressDialog pdialog;
    public String latstore;
    public String longistore;
    private String goslowvalue,getuser_id;
    int selection=1;
    private final int  Goslow_WS =1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goslow);

        findid();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setSingleChoiceItems(goslowitem, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                ListView lw = ((AlertDialog) dialog).getListView();
                // 1
                checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                if (checkedItem.equals("Moderate")) {

                    newchecked = 1;


                } else if (checkedItem.equals("Heavy")) {

                    newchecked = 2;


                } else if (checkedItem.equals("Standstill")) {

                    newchecked = 3;

                }
                Log.d("Listtt valueee", "" + checkedItem);
                levelDialog.dismiss();
            }

        });

        levelDialog = builder.create();
        levelDialog.show();

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

    }


    private void findid() {

        et_detail = (EditText) findViewById(R.id.et_detail);
        btn_snd = (Button) findViewById(R.id.sendbtn);
        btn_later = (Button) findViewById(R.id.laterbtn);
        takepic = (RelativeLayout) findViewById(R.id.Takepic);
        myside = (RelativeLayout) findViewById(R.id.Myside);
        img = (ImageView) findViewById(R.id.img);
        txt_myside=(TextView)findViewById(R.id.Myside_txt);
       /* ln=(TextView)findViewById(R.id.lng);
        lt=(TextView)findViewById(R.id.lat);*/

        // 2

        takepic.setOnClickListener(this);
        myside.setOnClickListener(this);
        btn_snd.setOnClickListener(this);
        btn_later.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Takepic:

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(android.os.Environment
                        .getExternalStorageDirectory(), "temp.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(intent, 1);

                break;

            case R.id.Myside:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // builder.setTitle("Select The Difficulty Level");
                builder.setSingleChoiceItems(sideoptions, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        ListView lw = ((AlertDialog)dialog).getListView();
                        // 1
                        sidevalue = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                        if (sidevalue.equals("My Side")){

                            newside=1;

                        }else
                        if(sidevalue.equals("Other Side")){

                            newside=2;

                        }


                        Log.d("Listtt valueee" ,""+sidevalue);
                        Log.d("neww lsit valueee" ,""+newside);
                        //3
                        txt_myside.setText(""+sidevalue);

                        levelDialog.dismiss();
                    }

                });

                levelDialog = builder.create();
                levelDialog.show();
                break;
            case R.id.sendbtn:

                detail = et_detail.getText().toString();


                pdialog = Utils.showDialog("Please wait...", Goslow.this);

                goslowvalue  = "Moderate";

                if (newchecked == 1)
                    goslowvalue = "Heavy";

                else if (newchecked == 2)
                    goslowvalue = "Standstill";

                List<NameValuePair> myArgs = new ArrayList<NameValuePair>();
                myArgs.add(new BasicNameValuePair("userid",
                        getuser_id));
                myArgs.add(new BasicNameValuePair("traffic_types",
                        ""+ goslowvalue));
                myArgs.add(new BasicNameValuePair("description",
                        detail));
                myArgs.add(new BasicNameValuePair("side_type",
                        ""+newside));
                myArgs.add(new BasicNameValuePair("curnt_location",
                        "20mohali"));

                myArgs.add(new BasicNameValuePair("latitude",
                        latstore));
                myArgs.add(new BasicNameValuePair("longitude",
                        longistore));
                myArgs.add(new BasicNameValuePair("t_type",
                        ""+selection));


                Log.d("_hid  is ", "" + getuser_id);
                Log.d("_hhazard_on_h ", "" + goslowvalue);
                Log.d("_hdescription  is_h ", "" + detail);
                Log.d("_hlatitude  is_h ", "" + latstore);
                Log.d("_hlongistore  is_h ", "" + longistore);
                Log.d("_hmapselctn  is_h ", "" + selection);
                Log.d("_hroadside  is_h ", "" + newside);


                RestApiCallPost restpost = new RestApiCallPost(Constants.Hazard,Goslow.this,myArgs,Goslow_WS);
                restpost.start();
                break;

        }


    }

    @Override
    public void onResponse(String response, int pageId) {

        this.response = response;
        Log.d("response ", response + "");

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

                case Goslow_WS: {

                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.optString("Status").equalsIgnoreCase(
                                "1")) {

                            Status = jsonObject.getString("Status");
                            message = jsonObject.getString("message");
                            Utils.dismissDialog(pdialog);

                            Util.getAlertDialog(Goslow.this, "Data saved",
                                    new Handler()).show();



                        } else {
                            if (jsonObject.optString("Status").equalsIgnoreCase(
                                    "0")){

                                Status = jsonObject.getString("Status");
                                message = jsonObject.getString("message");
                                Utils.dismissDialog(pdialog);

                                Util.getAlertDialog(Goslow.this, message,
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


    @Override
    public void onLocationChanged(Location location) {

        latstore=String.valueOf(location.getLatitude());
        longistore=String.valueOf(location.getLongitude());
        Log.d("current---goslow------",latstore);
        Log.d("current----goslow---",longistore);

        toEdit.putString("shared_lat_gs", latstore);
        toEdit.putString("shared_long_gs", longistore);
        toEdit.commit();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }



}
