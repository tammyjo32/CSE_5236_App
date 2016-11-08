package com.example.tammy.test.geofence;

/**
 * Created by rongchen on 11/8/16.
 */
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import android.content.SharedPreferences;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.location.Geofence;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import android.content.Intent;
import android.app.PendingIntent;
import com.google.android.gms.common.api.*;
import android.util.Log;
import android.support.v4.content.ContextCompat;
import android.Manifest;


public class GeofenceController {

    private final String TAG = GeofenceController.class.getName();
    private GeofenceControllerListener listener;

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
    }

    public interface GeofenceControllerListener {
        void onGeofencesUpdated();
        void onError();
    }

    private GoogleApiClient.ConnectionCallbacks connectionAddListener =
            new GoogleApiClient.ConnectionCallbacks() {

                @Override
                public void onConnected(Bundle bundle) {
                    // 1. Create an IntentService PendingIntent
                    Intent intent = new Intent(context, GeofenceIntentService.class);
                    PendingIntent pendingIntent =
                            PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    // 2. Associate the service PendingIntent with the geofence and call addGeofences
                    int permissionCheck = ContextCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_FINE_LOCATION);

                    if (permissionCheck == 0) {
                        PendingResult<Status> result = LocationServices.GeofencingApi.addGeofences(
                                googleApiClient, getAddGeofencingRequest(), pendingIntent);
                        result.setResultCallback(new ResultCallback<Status>() {

                            // 3. Implement PendingResult callback

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

    public void addGeofence(NamedGeofence namedGeofence, GeofenceControllerListener listener) {
        this.namedGeofenceToAdd = namedGeofence;
        this.geofenceToAdd = namedGeofence.geofence();
        this.listener = listener;

        connectWithCallbacks(connectionAddListener);
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
    }

}
