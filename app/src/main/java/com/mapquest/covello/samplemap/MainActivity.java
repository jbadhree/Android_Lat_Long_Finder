package com.mapquest.covello.samplemap;


import android.Manifest;
import android.content.Context;

import android.content.pm.PackageManager;
import android.location.Criteria;

import android.location.Location;

import android.location.LocationListener;

import android.location.LocationManager;

import android.os.Bundle;

import android.app.Activity;

import android.support.v4.app.ActivityCompat;
import android.view.Menu;

import android.widget.TextView;

import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

    LocationManager locationmanager;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria cri = new Criteria();

        String provider = locationmanager.getBestProvider(cri, false);

        if (provider != null & !provider.equals(""))

        {


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            Location location = locationmanager.getLastKnownLocation(provider);

            locationmanager.requestLocationUpdates(provider,2000,1,this);

            if(location!=null)

            {

                onLocationChanged(location);

            }

            else{

                Toast.makeText(getApplicationContext(),"location not found",Toast.LENGTH_LONG ).show();

            }

        }

        else

        {

            Toast.makeText(getApplicationContext(),"Provider is null",Toast.LENGTH_LONG).show();

        }

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

// Inflate the menu; this adds items to the action bar if it is present.

        //getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }

    @Override

    public void onLocationChanged(Location location) {

        TextView textView2=(TextView)findViewById(R.id.textView2);

        TextView textView3=(TextView)findViewById(R.id.textView3);

        textView2.setText("Latitude"+location.getLatitude());

        textView3.setText("Longitude"+ location.getLongitude());

    }

    @Override

    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override

    public void onProviderEnabled(String s) {

    }

    @Override

    public void onProviderDisabled(String s) {

    }

}

