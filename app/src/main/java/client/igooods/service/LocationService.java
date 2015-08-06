package client.igooods.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import client.igooods.task.ApiClient;

public class LocationService extends Service {

    private LocationManager mLocationManager;
    private Timer mTimer;
    private double mLat=0d, mLon=0d;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        mLocationManager = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                10 * 1000, 5, listener);

        mTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(isGpsEnabled()) {
                    if(mLat!=0d&&mLon!=0d) {
//                        new ApiClient(getApplicationContext()).sendGeo(LocationService.this,null,mLat,mLon);
                    }
                }
            }
        };
        mTimer.schedule(timerTask, 10 * 1000, 10 * 1000);

        return START_STICKY;
    }

    private boolean isGpsEnabled(){
        LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if (manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            return true;
        }
        return false;
    }

    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);


    }

    private LocationListener listener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            if (location == null)
                return;
            mLat = location.getLatitude();
            mLon = location.getLongitude();
            Log.i("location",location.getLatitude()+"");
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    };


    @Override
    public void onDestroy() {
        mLocationManager.removeUpdates(listener);
        listener = null;
        mTimer.cancel();
        super.onDestroy();
    }


}
