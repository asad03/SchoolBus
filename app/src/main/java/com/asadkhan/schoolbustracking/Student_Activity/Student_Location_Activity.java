package com.asadkhan.schoolbustracking.Student_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.asadkhan.schoolbustracking.Attendance_Activity.Attendance_Model_Class;
import com.asadkhan.schoolbustracking.Attendance_Activity.Attendance_Out_Activity;
import com.asadkhan.schoolbustracking.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student_Location_Activity extends AppCompatActivity {
    SupportMapFragment smf;

    FusedLocationProviderClient client;
    ValueEventListener valueEventListener;
    DatabaseReference databaseReference;
    Intent intent;
    String student_Bus,student_registerationNo, eveningentryDate;
    TextView txtstatus,txtstatusAbsent,txtstatusEvIn,txtstatusEvOut;
    String Attendance="present";
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent=getIntent();
        setContentView(R.layout.activity_student_location);
        txtstatusAbsent=findViewById(R.id.txtstatusAbsent);
        txtstatus=findViewById(R.id.txtstatus);
        txtstatusEvIn=findViewById(R.id.txtstatusevin);
        txtstatusEvOut=findViewById(R.id.txtstatusevout);
       // Intent intent=getIntent();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
           eveningentryDate = simpleFormat.format(new Date());
         student_Bus=intent.getStringExtra("student_Bus");
         student_registerationNo=intent.getStringExtra("student_registerationNo");
        System.out.println(student_Bus);
        System.out.println(student_registerationNo);
        System.out.println("lll");
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


                        double latitude=   intent.getDoubleExtra("student_Latitude",0);
                        double longitude=  intent.getDoubleExtra("student_Longitude",0);
                        LatLng location=new LatLng(latitude,longitude);

                        MarkerOptions markerOptions=new MarkerOptions().position(location).title("You are here..");
                        googleMap.addMarker(markerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,15));
                    }
                });
            }
        });



        databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceMorningIn").child(student_Bus).child( eveningentryDate);
        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshott) {
               // students.clear();


                //HashMap<Integer, HashMap<String, Object>> cardData = new HashMap<Integer, HashMap<String, Object>>();

                System.out.println(snapshott.getValue());

                System.out.println("keyddd");


                for (DataSnapshot dataSnapshott:snapshott.getChildren()) {

                    Attendance_Out_Activity attendance_out_activity=dataSnapshott.getValue(Attendance_Out_Activity.class);
                    String StudentRno=  attendance_out_activity.getStudnet_RNumber();
                     status=attendance_out_activity.getMorningStatus();
//                    System.out.println(student_registerationNo);
//                    System.out.println(StudentRno);
//                    System.out.println("studentt");
                    if (StudentRno.equals(student_registerationNo)){
                        // System.out.println(sr);
//                        System.out.println(status);
                if (Attendance.equals(status)){
                    txtstatus.setText("Morning " + status);
                    txtstatusAbsent.setText("");
                }else {


              txtstatus.setText("");


                }


                       // txtstatus.setText(Attendance);

                      //  System.out.println("studenttr");
//                        students.add(dataSnapshott.getValue(Attendance_Out_Activity.class));
//                        System.out.println(students);
                    }
                }
////            cardData = (HashMap<Integer, HashMap<String, Object>>) dataSnapshott.getValue();
//
//            String buses= (String) dataSnapshott.getValue();
//            System.out.println(buses);
//            //System.out.println(dataSnapshott);
//            System.out.println("data");
//            Log.i("Firebase",                               // 👈 Log the key and value
//                    "Reading Member2 from "+dataSnapshot.getKey() // 👈 to know where the
//                            +", value="+dataSnapshot.getValue()           // 👈 problem is in your
//            );
//            //MorningAtten_Modal_class morningAtten_modal_class = ab.getValue(MorningAtten_Modal_class.class);
//            students.add(dataSnapshott.getValue(MorningAtten_Modal_class.class));
//
//        }

                // System.out.println(morningAtten_modal_class);
//
//          morningAtten_modal_class.getStudent_name();
//s

//        System.out.println( morningAtten_modal_class);

//        for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
//              System.out.println(dataSnapshot);
//
//            System.out.println(morningAtten_modal_class);
//            String   fullrecord=    dataSnapshot.getKey();
//             System.out.println(fullrecord);
//
//        }
               // morning_attenShow.notifyDataSetChanged();
//
//
//
//        }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceMorningout").child(student_Bus).child( eveningentryDate);
        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshott) {
                // students.clear();


                //HashMap<Integer, HashMap<String, Object>> cardData = new HashMap<Integer, HashMap<String, Object>>();

                System.out.println(snapshott.getValue());

                System.out.println("keyddd");


                for (DataSnapshot dataSnapshott:snapshott.getChildren()) {

                    Attendance_Out_Activity attendance_out_activity=dataSnapshott.getValue(Attendance_Out_Activity.class);
                    String StudentRno=  attendance_out_activity.getStudnet_RNumber();
                    status=attendance_out_activity.getMorningStatus();
//                    System.out.println(student_registerationNo);
//                    System.out.println(StudentRno);
//                    System.out.println("studentt");
                    String a="absent";
                    if (StudentRno.equals(student_registerationNo)){
                        // System.out.println(sr);
                        if (a.equals(status)){
                            txtstatus.setText("");
                            txtstatusAbsent.setText("Morning  "+a);
                        }else {
                            txtstatusAbsent.setText("");
                        }
                        //txtstatus.setText(Attendance);
                        // txtstatus.setTextColor(Color.parseColor("#f0"));
                        // System.out.println("studenttr");
//                        students.add(dataSnapshott.getValue(Attendance_Out_Activity.class));
//                        System.out.println(students);
                    }
                }
////            cardData = (HashMap<Integer, HashMap<String, Object>>) dataSnapshott.getValue();
//
//            String buses= (String) dataSnapshott.getValue();
//            System.out.println(buses);
//            //System.out.println(dataSnapshott);
//            System.out.println("data");
//            Log.i("Firebase",                               // 👈 Log the key and value
//                    "Reading Member2 from "+dataSnapshot.getKey() // 👈 to know where the
//                            +", value="+dataSnapshot.getValue()           // 👈 problem is in your
//            );
//            //MorningAtten_Modal_class morningAtten_modal_class = ab.getValue(MorningAtten_Modal_class.class);
//            students.add(dataSnapshott.getValue(MorningAtten_Modal_class.class));
//
//        }

                // System.out.println(morningAtten_modal_class);
//
//          morningAtten_modal_class.getStudent_name();
//s

//        System.out.println( morningAtten_modal_class);

//        for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
//              System.out.println(dataSnapshot);
//
//            System.out.println(morningAtten_modal_class);
//            String   fullrecord=    dataSnapshot.getKey();
//             System.out.println(fullrecord);
//
//        }
                // morning_attenShow.notifyDataSetChanged();
//
//
//
//        }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceEveningIn").child(student_Bus).child( eveningentryDate);
        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshott) {
                // students.clear();


                //HashMap<Integer, HashMap<String, Object>> cardData = new HashMap<Integer, HashMap<String, Object>>();

                System.out.println(snapshott.getValue());

                System.out.println("keyddd44");


                for (DataSnapshot dataSnapshott:snapshott.getChildren()) {

              Attendance_Model_Class attendance_out_activity=dataSnapshott.getValue(    Attendance_Model_Class.class);
                    String StudentRno=  attendance_out_activity.getStudnet_RNumber();
                  String   status1=attendance_out_activity.getEveningStatus();
                  //  System.out.println(student_registerationNo);
                    System.out.println(StudentRno);
                   // System.out.println("studentt");
                    System.out.println(status1);
                    System.out.println("stat12345");
                    String eveningenter="present";
                    if (StudentRno.equals(student_registerationNo)){
                        // System.out.println(sr);

                        if (Attendance.equals(status1)){
                            txtstatusEvIn.setText("evening  "+Attendance);
                            txtstatusEvOut.setText("");
                        }else {


                            txtstatusEvIn.setText("");


                        }
//                        students.add(dataSnapshott.getValue(Attendance_Out_Activity.class));
//                        System.out.println(students);
                    }
                }
////            cardData = (HashMap<Integer, HashMap<String, Object>>) dataSnapshott.getValue();
//
//            String buses= (String) dataSnapshott.getValue();
//            System.out.println(buses);
//            //System.out.println(dataSnapshott);
//            System.out.println("data");
//            Log.i("Firebase",                               // 👈 Log the key and value
//                    "Reading Member2 from "+dataSnapshot.getKey() // 👈 to know where the
//                            +", value="+dataSnapshot.getValue()           // 👈 problem is in your
//            );
//            //MorningAtten_Modal_class morningAtten_modal_class = ab.getValue(MorningAtten_Modal_class.class);
//            students.add(dataSnapshott.getValue(MorningAtten_Modal_class.class));
//
//        }

                // System.out.println(morningAtten_modal_class);
//
//          morningAtten_modal_class.getStudent_name();
//s

//        System.out.println( morningAtten_modal_class);

//        for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
//              System.out.println(dataSnapshot);
//
//            System.out.println(morningAtten_modal_class);
//            String   fullrecord=    dataSnapshot.getKey();
//             System.out.println(fullrecord);
//
//        }
                // morning_attenShow.notifyDataSetChanged();
//
//
//
//        }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceEveningOut").child(student_Bus).child( eveningentryDate);
        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshott) {
                // students.clear();


                //HashMap<Integer, HashMap<String, Object>> cardData = new HashMap<Integer, HashMap<String, Object>>();

                System.out.println(snapshott.getValue());

                System.out.println("keyddd77");


                for (DataSnapshot dataSnapshott:snapshott.getChildren()) {

                    Attendance_Model_Class attendance_out_activity=dataSnapshott.getValue(Attendance_Model_Class.class);
                    String StudentRno=  attendance_out_activity.getStudnet_RNumber();
                    status=attendance_out_activity.getEveningStatus();
//                    System.out.println(student_registerationNo);
//                    System.out.println(StudentRno);
//                    System.out.println("studentt");
                    System.out.println(status);
                    System.out.println("765");
                    String ab="absent";
                    if (StudentRno.equals(student_registerationNo)){
                        // System.out.println(sr);
                       // txtstatus.setTextColor(Color.parseColor("#ff00"));
                        if (ab.equals(status)){
                            txtstatusEvIn.setText("");
                            txtstatusEvOut.setText("Evening  "+ab);
                        }else {
                            txtstatusEvOut.setText("");
                        }
                   //  txtstatus.setText(Attendance);
                       // txtstatus.setTextColor(Color.parseColor(getDrawable(R.drawa)));

                       // System.out.println("studenttr");
//                        students.add(dataSnapshott.getValue(Attendance_Out_Activity.class));
//                        System.out.println(students);
                    }
                }
////            cardData = (HashMap<Integer, HashMap<String, Object>>) dataSnapshott.getValue();
//
//            String buses= (String) dataSnapshott.getValue();
//            System.out.println(buses);
//            //System.out.println(dataSnapshott);
//            System.out.println("data");
//            Log.i("Firebase",                               // 👈 Log the key and value
//                    "Reading Member2 from "+dataSnapshot.getKey() // 👈 to know where the
//                            +", value="+dataSnapshot.getValue()           // 👈 problem is in your
//            );
//            //MorningAtten_Modal_class morningAtten_modal_class = ab.getValue(MorningAtten_Modal_class.class);
//            students.add(dataSnapshott.getValue(MorningAtten_Modal_class.class));
//
//        }

                // System.out.println(morningAtten_modal_class);
//
//          morningAtten_modal_class.getStudent_name();
//s

//        System.out.println( morningAtten_modal_class);

//        for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
//              System.out.println(dataSnapshot);
//
//            System.out.println(morningAtten_modal_class);
//            String   fullrecord=    dataSnapshot.getKey();
//             System.out.println(fullrecord);
//
//        }
                // morning_attenShow.notifyDataSetChanged();
//
//
//
//        }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//txtstatus.setText(Attendance);
//        System.out.println(status);
//
//        System.out.println("Abvvv");
//        if (Attendance.equals("present")){
//            txtstatus.setText("Present");
//
//        }else {
//            txtstatus.setText("Absent");
//        }
    }


}
