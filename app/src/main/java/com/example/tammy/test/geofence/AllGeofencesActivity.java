package com.example.tammy.test.geofence;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.tammy.test.R;

import com.google.android.gms.common.GooglePlayServicesUtil;

public class AllGeofencesActivity extends AppCompatActivity {

  // region Overrides

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_all_geofences);
    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


    setTitle("Geofence");
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
              .add(R.id.container, new AllGeofencesFragment())
              .commit();
    }

    GeofenceController.getInstance().init(this);
  }

  @Override
  protected void onResume() {
    super.onResume();

    int googlePlayServicesCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    Log.i(AllGeofencesActivity.class.getSimpleName(), "googlePlayServicesCode = " + googlePlayServicesCode);

    if (googlePlayServicesCode == 1 || googlePlayServicesCode == 2 || googlePlayServicesCode == 3) {
      GooglePlayServicesUtil.getErrorDialog(googlePlayServicesCode, this, 0).show();
    }
  }

  // endregion
}
