package com.example.tammy.test.geofence;

import android.support.annotation.NonNull;

import com.google.android.gms.location.Geofence;

import java.util.UUID;

public class NamedGeofence implements Comparable {

    public String id;
    public String name;
    public double latitude;
    public double longitude;
    public float radius;

    @Override
    public int compareTo(@NonNull Object another) {
        NamedGeofence other = (NamedGeofence) another;
        return name.compareTo(other.name);
    }

    public Geofence geofence() {
    id = UUID.randomUUID().toString();
    return new Geofence.Builder()
            .setRequestId(id)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .setCircularRegion(latitude, longitude, radius)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .build();
  }
    
}
