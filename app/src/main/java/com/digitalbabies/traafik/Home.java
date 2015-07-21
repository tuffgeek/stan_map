package com.digitalbabies.traafik;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ankit on 15/7/15.
 */


public class Home extends Activity implements LocationListener {

    private GoogleMap googleMap;

    private Drawer nav;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        try {
            nav= new Drawer(this);

        } catch (Exception e) {
            e.printStackTrace();
        }



        try {
            // Loading map
            initilizeMap();

            // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void initilizeMap() {

        if (googleMap == null) {


            try {
                googleMap = ((MapFragment) getFragmentManager()
                        .findFragmentById(R.id.map)).getMap();


                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);


            }catch (Exception e)
            {
                e.printStackTrace();
            }

            try {
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            try {
                boolean ans = googleMap.getUiSettings().isRotateGesturesEnabled();
                Log.d("give", "" + ans);
            }catch (Exception e)
            {
                e.printStackTrace();
            }


            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();

            }
        }
    }

    protected void onResume() {
        super.onResume();

        try {
            new Checker(this).pass(new Checker.Pass() {
                @Override public void pass() {
                    //do your stuff here, do nothing outside here
                }
            }).check(Checker.Resource.GPS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Loading map
            initilizeMap();

             googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {



        try {
            googleMap.clear();
        }catch (Exception e)
        {
            e.printStackTrace();
        }



        try {
            MarkerOptions mp = new MarkerOptions();
            mp.position(new LatLng(location.getLatitude(), location.getLongitude()));
            mp.title("my position");
            googleMap.addMarker(mp);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 15));

        }catch (Exception e)
        {
            e.printStackTrace();
        }

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


