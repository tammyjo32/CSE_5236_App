package com.example.tammy.test.geofence;

/**
 * Created by rongchen on 11/8/16.
 */
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.google.android.gms.location.Geofence;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import android.content.Intent;
import android.app.PendingIntent;
import com.google.android.gms.common.api.*;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.Manifest;

import com.example.tammy.test.R;


public class GeofenceController {

    private final String TAG = GeofenceController.class.getName();
    private Context context;
    private GoogleApiClient googleApiClient;
    private Gson gson;
    private SharedPreferences prefs;

    private List<NamedGeofence> namedGeofences;
    public List<NamedGeofence> getNamedGeofences() {
        return namedGeofences;
    }

    private List<NamedGeofence> namedGeofencesToRemove;

    private Geofence geofenceToAdd;
    private NamedGeofence namedGeofenceToAdd;


    private GeofenceControllerListener listener;
    private GoogleApiClient.ConnectionCallbacks connectionAddListener =
            new GoogleApiClient.ConnectionCallbacks() {
                @Override
                public void onConnected(Bundle bundle) {
                    // 1. Create an IntentService PendingIntent
                    Intent intent = new Intent(context, GeofenceIntentService.class);
                    PendingIntent pendingIntent =
                            PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

// 2. Associate the service PendingIntent with the geofence and call addGeofences
                    // request for permission

                    PendingResult<Status> result = LocationServices.GeofencingApi.addGeofences(
                            googleApiClient, getAddGeofencingRequest(), pendingIntent);



// 3. Implement PendingResult callback
                    result.setResultCallback(new ResultCallback<Status>() {

                        @Override
                        public void onResult(Status status) {
                            if (status.isSuccess()) {
                                // 4. If successful, save the geofence
                                saveGeofence();
                            } else {
                                // 5. If not successful, log and send an error
                                Log.e(TAG, "Registering geofence failed: " + status.getStatusMessage() +
                                        " : " + status.getStatusCode());
                                sendError();
                            }
                        }
                    });
                }

                @Override
                public void onConnectionSuspended(int i) {

                }
            };

    private GoogleApiClient.OnConnectionFailedListener connectionFailedListener =
            new GoogleApiClient.OnConnectionFailedListener() {
                @Override
                public void onConnectionFailed(ConnectionResult connectionResult) {

                }
            };



    private static GeofenceController INSTANCE;

    public static GeofenceController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GeofenceController();
        }
        return INSTANCE;
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();

        gson = new Gson();
        namedGeofences = new ArrayList<>();
        namedGeofencesToRemove = new ArrayList<>();
        prefs = this.context.getSharedPreferences(Constants.SharedPrefs.Geofences, Context.MODE_PRIVATE);

        loadGeofences();
    }

    public interface GeofenceControllerListener {
        void onGeofencesUpdated();
        void onError();
    }

    // helper methods
    private GeofencingRequest getAddGeofencingRequest() {
        List<Geofence> geofencesToAdd = new ArrayList<>();
        geofencesToAdd.add(geofenceToAdd);
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofencesToAdd);
        return builder.build();
    }

    private void connectWithCallbacks(GoogleApiClient.ConnectionCallbacks callbacks) {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(callbacks)
                .addOnConnectionFailedListener(connectionFailedListener)
                .build();
        googleApiClient.connect();
    }

    private void sendError() {
        if (listener != null) {
            listener.onError();
        }
    }

    private void saveGeofence() {
        namedGeofences.add(namedGeofenceToAdd);
        if (listener != null) {
            listener.onGeofencesUpdated();
        }
        // use Gson to convert namedGeofenceToAdd into JSON and store that JSON as a string in the
        // users’ shared preferences
        String json = gson.toJson(namedGeofenceToAdd);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(namedGeofenceToAdd.id, json);
        editor.apply();
    }

    public void addGeofence(NamedGeofence namedGeofence, GeofenceControllerListener listener) {
        this.namedGeofenceToAdd = namedGeofence;
        this.geofenceToAdd = namedGeofence.geofence();
        this.listener = listener;

        connectWithCallbacks(connectionAddListener);
    }

    private void loadGeofences() {
        // Loop over all geofence keys in prefs and add to namedGeofences
        Map<String, ?> keys = prefs.getAll();
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            String jsonString = prefs.getString(entry.getKey(), null);
            NamedGeofence namedGeofence = gson.fromJson(jsonString, NamedGeofence.class);
            namedGeofences.add(namedGeofence);
        }

        // Sort namedGeofences by name
        Collections.sort(namedGeofences);
    }

    // remove geofence
    private GoogleApiClient.ConnectionCallbacks connectionRemoveListener =
            new GoogleApiClient.ConnectionCallbacks() {
                @Override
                public void onConnected(Bundle bundle) {
                    // 1. Create a list of geofences to remove
                    List<String> removeIds = new ArrayList<>();
                    for (NamedGeofence namedGeofence : namedGeofencesToRemove) {
                        removeIds.add(namedGeofence.id);
                    }

                    if (removeIds.size() > 0) {
                        // 2. Use GoogleApiClient and the GeofencingApi to remove the geofences
                        PendingResult<Status> result = LocationServices.GeofencingApi.removeGeofences(
                                googleApiClient, removeIds);
                        result.setResultCallback(new ResultCallback<Status>() {

                            // 3. Handle the success or failure of the PendingResult
                            @Override
                            public void onResult(Status status) {
                                if (status.isSuccess()) {
                                    removeSavedGeofences();
                                } else {
                                    Log.e(TAG, "Removing geofence failed: " + status.getStatusMessage());
                                    sendError();
                                }
                            }
                        });
                    }
                }

                @Override
                public void onConnectionSuspended(int i) {
                    Log.e(TAG, "Connecting to GoogleApiClient suspended.");
                    sendError();
                }
            };

    // remove geofence helper method
    public void removeGeofences(List<NamedGeofence> namedGeofencesToRemove,
                                GeofenceControllerListener listener) {
        this.namedGeofencesToRemove = namedGeofencesToRemove;
        this.listener = listener;

        connectWithCallbacks(connectionRemoveListener);
    }

    public void removeAllGeofences(GeofenceControllerListener listener) {
        namedGeofencesToRemove = new ArrayList<>();
        for (NamedGeofence namedGeofence : namedGeofences) {
            namedGeofencesToRemove.add(namedGeofence);
        }
        this.listener = listener;

        connectWithCallbacks(connectionRemoveListener);
    }

    private void removeSavedGeofences() {
        SharedPreferences.Editor editor = prefs.edit();

        for (NamedGeofence namedGeofence : namedGeofencesToRemove) {
            int index = namedGeofences.indexOf(namedGeofence);
            editor.remove(namedGeofence.id);
            namedGeofences.remove(index);
            editor.apply();
        }

        if (listener != null) {
            listener.onGeofencesUpdated();
        }
    }


}
