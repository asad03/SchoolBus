package com.asadkhan.schoolbustracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class MainActivity extends AppCompatActivity {
    SupportMapFragment smf;

    FusedLocationProviderClient client;
    ValueEventListener valueEventListener;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        client = LocationServices.getFusedLocationProviderClient(this);

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getMyLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    private void getMyLocation() {
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
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
              smf.getMapAsync(new OnMapReadyCallback() {
                  @Override
                  public void onMapReady(GoogleMap googleMap) {
                      FirebaseAuth auth=FirebaseAuth.getInstance();
                      FirebaseUser firebaseUser=auth.getCurrentUser();
                      firebaseUser.getUid();

                      databaseReference= FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).child("Driver Register");
                  valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            //  String busNumber=dataSnapshot.getValue(String.class);
                             // System.out.println(busNumber);
                              System.out.println(dataSnapshot);
                              System.out.println("buss");
                          }
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {
                          System.out.println("not");
                          Toast.makeText(MainActivity.this, "not", Toast.LENGTH_SHORT).show();
                      }
                  });
                      //  String C,driver_mobile,driver_age,drivere_mail,driver_Addres,usertype,bus,driver_password;
                      FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                      Intent intent=getIntent();
                      String driver_name=intent.getStringExtra("driver_name");
                      String driver_mobile=intent.getStringExtra("driver_mobile");
                      String driver_age=intent.getStringExtra("driver_age");
                      String drivere_mail=intent.getStringExtra("emaild");
                      String driver_Addres=intent.getStringExtra("driver_Addres");
                      String usertype=intent.getStringExtra("usertype");
                      String bus=intent.getStringExtra("bus");
                      String driver_password=intent.getStringExtra("driver_password");
                      double Longitude=intent.getDoubleExtra("Longitude",location.getLongitude());
                      double Latitude=intent.getDoubleExtra("Latitude",location.getLatitude());






                      Location_Modal_class helper=new Location_Modal_class(location.getLongitude(),location.getLatitude(),driver_name,driver_mobile,driver_age,drivere_mail,driver_Addres,usertype,bus,driver_password);


                      DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Driver").child("Driver Register");
                      databaseReference.child(drivere_mail.replace(".","")).setValue(helper).addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                              if (task.isSuccessful()){
                                  Toast.makeText(MainActivity.this, "Location update", Toast.LENGTH_SHORT).show();
                              }else {
                                  Toast.makeText(MainActivity.this, "Location not update", Toast.LENGTH_SHORT).show();
                              }

                          }
                      });


                      LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                      MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("You are here..");
                      googleMap.addMarker(markerOptions);
                      googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                  }
              });
            }
        });
    }
}