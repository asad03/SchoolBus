package com.asadkhan.schoolbustracking.Driver_Activity;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.asadkhan.schoolbustracking.Attendance_Activity.Attendance_Out_Activity;
import com.asadkhan.schoolbustracking.Location_Modal_class;
import com.asadkhan.schoolbustracking.MainActivity;
import com.asadkhan.schoolbustracking.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class LocationTrackingService extends Service implements LocationListener {

    private static final int NOTIFICATION_ID = 1;

    private LocationManager locationManager;
    private LocationListener locationListener;
    String drivere_mail,driver_name,driver_mobile,driver_age,driver_Addres,usertype,driver_password,bus;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        drivere_mail=  intent.getStringExtra("drivere_mail");
        driver_name=intent.getStringExtra("driver_name");
        driver_mobile=intent.getStringExtra("driver_mobile");
        driver_age=intent.getStringExtra("driver_age");
        driver_Addres=intent.getStringExtra("driver_Addres");
        usertype=intent.getStringExtra("usertype");
        driver_password=intent.getStringExtra("driver_password");
        bus=intent.getStringExtra("bus");
        System.out.println(driver_Addres);
        System.out.println(driver_age);
        System.out.println(usertype);
        System.out.println(drivere_mail);
        System.out.println("mqq");
        // Start the location updates
        startLocationUpdates();
        // Create and show the notification
        showNotification();
        // Return START_STICKY to keep the service running in the background
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop the location updates and remove the notification
        stopLocationUpdates();
        removeNotification();
    }

    private void startLocationUpdates() {
        // Initialize the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Initialize the location listener
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Upload the location to Firebase
                uploadLocationToFirebase(location);
            }

            // Other overridden methods of LocationListener
        };

        // Request location updates from the location manager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
    }

    private void stopLocationUpdates() {
        // Stop receiving location updates from the location manager
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    private void uploadLocationToFirebase(Location location) {
        // Get the current user ID or any other identifier you use

        System.out.println( location.getLatitude());
        System.out.println(location.getLongitude());
        System.out.println(location.getSpeed());
        System.out.println("lmlpp");
        System.out.println(driver_Addres+"adress");
        System.out.println(driver_age+"age");
        System.out.println(drivere_mail);

        location.getAltitude();



        Location_Modal_class helper=new Location_Modal_class(location.getLongitude(),location.getLatitude(),driver_name,driver_mobile,driver_age,drivere_mail,driver_Addres,usertype,bus,driver_password);


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Driver").child("Driver Register");
        databaseReference.child(drivere_mail.replace(".","")).setValue(helper).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Location update", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Location not update", Toast.LENGTH_SHORT).show();
                }

            }
        });



//        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//        // Upload the location data to Firebase Realtime Database
//        DatabaseReference locationRef = FirebaseDatabase.getInstance().getReference("locations");
//        locationRef.child(userId).setValue(location);
    }

    private void showNotification() {
        // Create a notification channel (for Android 8.0 and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("location_channel",
                    "Location Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Create a notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "location_channel")
                .setContentTitle("Location Updates")
                .setContentText("Getting your current location...")
                .setSmallIcon(R.drawable.location)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Start the service in the foreground with the notification
        startForeground(NOTIFICATION_ID, builder.build());
    }

    private void removeNotification() {
        // Stop the service from running in the foreground and remove the notification
        stopForeground(true);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        // Upload the location to Firebase
//        double latitude = location.getLatitude();
//        double longitude = location.getLongitude();
        uploadLocationToFirebase(location);
        // Update the current location marker on the map
        Intent intent = new Intent("location_update");
        intent.putExtra("location", location);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}