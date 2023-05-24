package com.asadkhan.schoolbustracking.Driver_Activity;

public class LocationUpdate_Modal {
    private double  Longitude,Latitude;
    public LocationUpdate_Modal() {
    }

    public LocationUpdate_Modal(double longitude, double latitude) {
        Longitude = longitude;
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}
