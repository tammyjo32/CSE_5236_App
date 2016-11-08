package com.example.tammy.test.geofence;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

public class GeofenceIntentService extends IntentService {

  // region Properties

  private final String TAG = GeofenceIntentService.class.getName();

  private SharedPreferences prefs;
  private Gson gson;

  // endregion

  // region Constructors

  public GeofenceIntentService() {
    super("GeofenceIntentService");
  }

  // endregion

  // region Overrides

  @Override
  protected void onHandleIntent(Intent intent) {

  }

  // endregion

  // region Private

  private void onEnteredGeofences(List<String> geofenceIds) {

  }

  private void onError(int i) {
    Log.e(TAG, "Geofencing Error: " + i);
  }

  // endregion
}

