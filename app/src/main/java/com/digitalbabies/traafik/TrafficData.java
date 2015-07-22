package com.digitalbabies.traafik;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ankit on 21/7/15.
 */
public class TrafficData {

    private String description;

    private String sideType;
    private String location;
    private LatLng latLng;

    private String hazardOn;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideType() {
        return sideType;
    }

    public void setSideType(String sideType) {
        this.sideType = sideType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getHazardOn() {
        return hazardOn;
    }

    public void setHazardOn(String hazardOn) {
        this.hazardOn = hazardOn;
    }

}
