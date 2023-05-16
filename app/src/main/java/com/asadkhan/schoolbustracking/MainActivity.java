package com.asadkhan.schoolbustracking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.asadkhan.schoolbustracking.Attendance_Activity.Attendance_Model_Class;
import com.asadkhan.schoolbustracking.Attendance_Activity.Attendance_Out_Activity;
import com.asadkhan.schoolbustracking.Attendance_Activity.EveningScan_Activity;
import com.asadkhan.schoolbustracking.Attendance_Activity.MorningSCan_Activity;
import com.asadkhan.schoolbustracking.Attendance_Activity.OutingScan_Activity;
import com.asadkhan.schoolbustracking.Driver_Activity.LocationShow_Activity;
import com.asadkhan.schoolbustracking.Driver_Activity.LocationTrackingService;
import com.asadkhan.schoolbustracking.Driver_Activity.ShowStudent_D_Activity;
import com.asadkhan.schoolbustracking.Location.ShowLocation_Activity;
import com.asadkhan.schoolbustracking.Parents_Activity.Parent_Profile_Activity;
import com.asadkhan.schoolbustracking.Parents_Activity.SendMsg_Activity;
import com.asadkhan.schoolbustracking.Student_Activity.Capture2_QR;
import com.asadkhan.schoolbustracking.Student_Activity.Capture_QR;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SupportMapFragment smf;

    FusedLocationProviderClient client;
    ValueEventListener valueEventListener;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Attendance_Out_Activity attendance_out_activity;
    int MY_PERMISSIONS_REQUEST_SEND_SMS=0;
    Button  btnscanall,btnLogOutD,btnLocation,btnbackground;
    RadioButton btneveningout, btnscan,btnscan2,btneveningenter;
    // CheckBox chmorningtime,cheveningtime;

    TextView txtshowQR, txtshowQR2;
    String bus,student_name,studnet_RNumber,StudentnameandRno, dMobile,student_mobilenumber;

    String MorningStatus,EveningStatus;
    String eveningDateandTime, eveningentryDate, eveningentryTime;
    SimpleDateFormat simpleDateFormat, sdf, simpleFormat;

    List<String> myList;
    List data;
    public static final String MyPREFERENCES = "Myapp";
    String location,driver_name,driver_mobile,driver_age,drivere_mail,driver_Addres,usertype,driver_password;
   // double  longitude,latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnscan=findViewById(R.id.btnscan);
        txtshowQR=findViewById(R.id.txtshowQr);
        btnscan2=findViewById(R.id.btnscan2);
        btneveningenter=findViewById(R.id.btneveningenter);
        btneveningout=findViewById(R.id.btneveningout);
        //btnbackground=findViewById(R.id.btnbackground);
        btnscanall=findViewById(R.id.btnscanall);
        //btnLogOutD=findViewById(R.id.btnLogoutD);
        btnLocation=findViewById(R.id.btnLocation);
        sdf = new SimpleDateFormat("dd-MM-yyyy,HH:mm:ss");
        simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat = new SimpleDateFormat("HH:mm");
        // assigning ID of the toolbar to a variable
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }


        Intent intent=getIntent();
        driver_name=intent.getStringExtra("driver_name");
         driver_mobile=intent.getStringExtra("driver_mobile");
         driver_age=intent.getStringExtra("driver_age");
        drivere_mail=intent.getStringExtra("emaild");
         driver_Addres=intent.getStringExtra("driver_Addres");
        usertype=intent.getStringExtra("usertype");
        bus=intent.getStringExtra("bus");
         driver_password=intent.getStringExtra("driver_password");
     Double    longitude =intent.getDoubleExtra("Longitude",0);
    Double     latitude= intent.getDoubleExtra("Latitude",0);

//        btnbackground.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //startService(new Intent( getApplicationContext(),  LocationTrackingService.class ) );
//
//
//                Intent serviceIntent = new Intent(getApplicationContext(), ShowLocation_Activity.class);
//                startActivity(serviceIntent);
////                String an="abcde";
////                Location_Modal_class location_modal_class=new Location_Modal_class();
////                location_modal_class.setDriver_name(driver_name);
////                System.out.println(driver_name);
////                System.out.println("dname");
////                intent.putExtra("an",an);
////                intent.putExtra("driver_name",driver_name);
////                intent.putExtra("driver_mobile",driver_mobile);
////                intent.putExtra("driver_age",driver_age);
////                intent.putExtra("emaild",drivere_mail);
////                intent.putExtra("driver_Addres",driver_Addres);
////                intent.putExtra("usertype",usertype);
////                intent.putExtra("bus",bus);
////                intent.putExtra("driver_password",driver_password);
////                intent.putExtra("longitude",longitude);
////                intent.putExtra("Latitude",latitude);
////                startService(serviceIntent);
//            }
//        });


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // startActivity(new Intent(getApplicationContext(), Dashboardb_Activity.class));
//            }
//        });

        // on below line we are creating a variable
        // for current date and time and calling a simple date format in it.
        eveningDateandTime = sdf.format(new Date());
        eveningentryDate = simpleFormat.format(new Date());
        eveningentryTime = simpleDateFormat.format(new Date());
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        client = LocationServices.getFusedLocationProviderClient(this);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ShowStudent_D_Activity.class);
                intent.putExtra("bus",bus);
startActivity(intent);
               // startActivity(new Intent(MainActivity.this, LocationShow_Activity.class));
            }
        });
//        btnLogOutD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
////
////                SharedPreferences.Editor editor = sharedpreferences.edit();
////                // editor.putString("namee", "");
////                editor.remove("driver");
////                editor.clear();
////                editor.apply();
////                startActivity(new Intent(MainActivity.this,Login_Activity.class));
////                finish();
//            }
//        });

        btnscanall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator=new IntentIntegrator(MainActivity.this);
                intentIntegrator.setPrompt("use up key");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture_QR.class);
                intentIntegrator.initiateScan();
            }
        });
//        btneveningout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        btnscan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentIntegrator intentIntegrator=new IntentIntegrator(MainActivity.this);
//                intentIntegrator.setPrompt("use up key");
//                intentIntegrator.setBeepEnabled(true);
//                intentIntegrator.setOrientationLocked(true);
//                intentIntegrator.setCaptureActivity(Capture_QR.class);
//                intentIntegrator.initiateScan();
//                MorningEnter();
//               // Intent intent=new Intent(getApplicationContext(), MorningSCan_Activity.class);
////
////                intent.putExtra("bus",bus);
////                startActivity(intent);
//               // chmorningtime.isChecked();
//            }
//
//            private void MorningEnter() {
//
//              //  studnet_RNumber = txtshowQR.getText().toString().trim();
////                if (TextUtils.isEmpty(studnet_RNumber)) {
////                    txtshowQR.setError("please scan again");
////                    txtshowQR.requestFocus();
////                } else {
//
//               // }
//            }
//        });

//        btnscan2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentIntegrator intentIntegrator=new IntentIntegrator(MainActivity.this);
//                intentIntegrator.setPrompt("use up key");
//                intentIntegrator.setBeepEnabled(true);
//                intentIntegrator.setOrientationLocked(true);
//                intentIntegrator.setCaptureActivity(Capture2_QR.class);
//                intentIntegrator.initiateScan();
//              MorningOut();
//
//            }
//        });


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

//    private void MorningOut() {
//
//        // Intent intent=new Intent(getApplicationContext(), EveningScan_Activity.class);
//
//        //intent.putExtra("bus",bus);
//        //startActivity(intent);
//
//
//        //IntentResult intentResultt = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        //  txtshowQR.setText(intentResultt.getContents());
//       // studnet_RNumber = txtshowQR.getText().toString().trim();
////        if (TextUtils.isEmpty(studnet_RNumber)) {
////            txtshowQR.setError("please scan again");
////            txtshowQR.requestFocus();
////        } else {
//             sdf = new SimpleDateFormat("dd-MM-yyyy,HH:mm");
//             simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
//            simpleDateFormat = new SimpleDateFormat("HH:mm");
//
//            // on below line we are creating a variable
//            // for current date and time and calling a simple date format in it.
//             eveningDateandTime = sdf.format(new Date());
//             eveningentryDate = simpleFormat.format(new Date());
//             eveningentryTime = simpleDateFormat.format(new Date());
//
//            // on below line we are setting current
//            // date and time to our text view.
//            // currentTV.setText(currentDateAndTime);
//            MorningStatus = "absent";
//
//            firebaseDatabase = FirebaseDatabase.getInstance();
//            databaseReference = firebaseDatabase.getReference("AttendanceMorning").child("StudentMorning");
//             attendance_out_activity = new   Attendance_Out_Activity(bus, studnet_RNumber, eveningentryDate, eveningentryTime, MorningStatus, eveningDateandTime);
//            databaseReference.child(eveningDateandTime).setValue(attendance_out_activity);
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Toast.makeText(getApplicationContext(), "Add Student Data Successfully", Toast.LENGTH_SHORT).show();
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(getApplicationContext(), "Faild to add data", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//
//    }


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
            public void onSuccess(Location location1) {

                System.out.println(location1);
                System.out.println("lllooloc");
              smf.getMapAsync(new OnMapReadyCallback() {
                  @Override
                  public void onMapReady(GoogleMap googleMap) {
//                      FirebaseAuth auth=FirebaseAuth.getInstance();
//                      FirebaseUser firebaseUser=auth.getCurrentUser();
//                      firebaseUser.getUid();


                      databaseReference= FirebaseDatabase.getInstance().getReference("Driver").child("Driver Register");
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
                         // Toast.makeText(MainActivity.this, "not", Toast.LENGTH_SHORT).show();
                      }
                  });
                      //  String C,driver_mobile,driver_age,drivere_mail,driver_Addres,usertype,bus,driver_password;
                      FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
//                      Intent intent=getIntent();
//                      String driver_name=intent.getStringExtra("driver_name");
//                      String driver_mobile=intent.getStringExtra("driver_mobile");
//                      String driver_age=intent.getStringExtra("driver_age");
//                      String drivere_mail=intent.getStringExtra("emaild");
//                      String driver_Addres=intent.getStringExtra("driver_Addres");
//                      String usertype=intent.getStringExtra("usertype");
//                       bus=intent.getStringExtra("bus");
//                      String driver_password=intent.getStringExtra("driver_password");
//                 double  longitude =intent.getDoubleExtra("Longitude",location.getLongitude());
//                   double latitude= intent.getDoubleExtra("Latitude",location.getLatitude());







                      Location_Modal_class helper=new Location_Modal_class(location1.getLongitude(),location1.getLatitude(),driver_name,driver_mobile,driver_age,drivere_mail,driver_Addres,usertype,bus,driver_password);


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
                      if(location1!= null){
//                          latitude = location1.getLatitude();
//                         longitude = location1.getLongitude();
//                         LatLng latLng=new LatLng(latitude,longitude);
                          LatLng latLng=new LatLng(location1.getLatitude(),location1.getLongitude());
                          MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("Your Bus Location..");
                          googleMap.addMarker(markerOptions);
                          googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
                      }else {
                          System.out.println("nulll");
//                          MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("You are here..");
//                          googleMap.addMarker(markerOptions);
//                          googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                          Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                     }
                  }
              });
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        IntentResult intentResultt = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            StudentnameandRno=intentResultt.getContents();
            String ab= StudentnameandRno.toString();
//        txtshowQR.setText( studnet_RNumber);
//  String a=      txtshowQR.getText().toString();
            System.out.println(ab);
            System.out.println("aaaa");
            myList = new ArrayList<String>(Arrays.asList(ab.split(",")));

            System.out.println(myList);
            student_name=myList.get(0);
            studnet_RNumber=myList.get(1);
            student_mobilenumber=myList.get(2);

            txtshowQR.setText(  student_name+"        "+ studnet_RNumber);








        if (btnscan.isChecked()){

     SimpleDateFormat        sdf2 = new SimpleDateFormat("dd-MM-yyyy,HH:mm:ss");
            SimpleDateFormat      simpleFormat2 = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat     simpleDateFormat2 = new SimpleDateFormat("HH:mm");

            // on below line we are creating a variable
            // for current date and time and calling a simple date format in it.
         String    eveningDateandTime2 = sdf2.format(new Date());
          String   eveningentryDate2 = simpleFormat2.format(new Date());
           String  eveningentryTime2 = simpleDateFormat2.format(new Date());

//             on below line we are setting current
//             date and time to our text view.
//             currentTV.setText(currentDateAndTime);
              MorningStatus = "present";

            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("AttendanceMorningIn").child(bus);
             attendance_out_activity = new   Attendance_Out_Activity(bus, studnet_RNumber, eveningentryDate2, eveningentryTime2, MorningStatus, eveningDateandTime2,student_name);
            databaseReference.child(eveningentryDate).child(studnet_RNumber).setValue(attendance_out_activity);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        SmsManager smgr = SmsManager.getDefault();
                        smgr.sendTextMessage(student_mobilenumber,null,"your child is enter to bus",null,null);
                       // message.setText("");
                        Toast.makeText(getApplicationContext(), "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getApplicationContext(), "Add Student Data Successfully", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "Faild to add data", Toast.LENGTH_SHORT).show();

                }
            }); }
            if (btnscan2.isChecked()){
                SimpleDateFormat        sdf = new SimpleDateFormat("dd-MM-yyyy,HH:mm:ss");
                SimpleDateFormat      simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat     simpleDateFormat = new SimpleDateFormat("HH:mm");

                // on below line we are creating a variable
                // for current date and time and calling a simple date format in it.
                String    eveningDateandTime = sdf.format(new Date());
                String   eveningentryDate = simpleFormat.format(new Date());
                String  eveningentryTime = simpleDateFormat.format(new Date());

//                 on below line we are setting current
//                 date and time to our text view.
//                 currentTV.setText(currentDateAndTime);
                MorningStatus = "absent";

                firebaseDatabase = FirebaseDatabase.getInstance();

                Attendance_Out_Activity attendance_out_activity1 = new   Attendance_Out_Activity(bus, studnet_RNumber, eveningentryDate, eveningentryTime, MorningStatus, eveningDateandTime,student_name);
                databaseReference = firebaseDatabase.getReference("AttendanceMorningout").child(bus);
                databaseReference.child(eveningentryDate).child(studnet_RNumber).setValue(attendance_out_activity1);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            SmsManager smgr = SmsManager.getDefault();
                            smgr.sendTextMessage(student_mobilenumber,null,"your child is out from bus",null,null);
                            // message.setText("");
                            Toast.makeText(getApplicationContext(), "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(), "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), "Add Student Data Successfully", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Faild to add data", Toast.LENGTH_SHORT).show();

                    }
                });

            }


          if (btneveningenter.isChecked()){
 SimpleDateFormat        sdf = new SimpleDateFormat("dd-MM-yyyy,HH:mm:ss");
              SimpleDateFormat      simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
              SimpleDateFormat     simpleDateFormat = new SimpleDateFormat("HH:mm");

              // on below line we are creating a variable
              // for current date and time and calling a simple date format in it.
              String    eveningDateandTime = sdf.format(new Date());
              String   eveningentryDate = simpleFormat.format(new Date());
              String  eveningentryTime = simpleDateFormat.format(new Date());
              EveningStatus = "present";

              firebaseDatabase = FirebaseDatabase.getInstance();
              databaseReference = firebaseDatabase.getReference("AttendanceEveningIn").child(bus);
              Attendance_Model_Class attendance_model_class=new Attendance_Model_Class(bus,studnet_RNumber, eveningentryDate, eveningentryTime, EveningStatus, eveningDateandTime,student_name);
              databaseReference.child(eveningentryDate).child(studnet_RNumber).setValue(attendance_model_class);
databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        try{
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(student_mobilenumber,null,"your child is enter to bus",null,null);
            // message.setText("");
            Toast.makeText(getApplicationContext(), "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getApplicationContext(), "Add Student Data Successfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(getApplicationContext(), "Faild to add data", Toast.LENGTH_SHORT).show();

    }
});
          }
          if (btneveningout.isChecked()){


              SimpleDateFormat        sdf = new SimpleDateFormat("dd-MM-yyyy,HH:mm:ss");
              SimpleDateFormat      simpleFormat = new SimpleDateFormat("dd-MM-yyyy");

              SimpleDateFormat     simpleDateFormat = new SimpleDateFormat("HH:mm");

              // on below line we are creating a variable
              // for current date and time and calling a simple date format in it.
              String    eveningDateandTime = sdf.format(new Date());
              String   eveningentryDate = simpleFormat.format(new Date());
              String  eveningentryTime = simpleDateFormat.format(new Date());
              EveningStatus = "absent";

              firebaseDatabase = FirebaseDatabase.getInstance();
              databaseReference = firebaseDatabase.getReference("AttendanceEveningOut").child(bus);                Attendance_Model_Class attendance_model_class=new Attendance_Model_Class(bus,studnet_RNumber, eveningentryDate, eveningentryTime, EveningStatus, eveningDateandTime,student_name);
              databaseReference.child(eveningentryDate).child(studnet_RNumber).setValue(attendance_model_class);
              databaseReference.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot snapshot) {
                      try{
                          SmsManager smgr = SmsManager.getDefault();
                          smgr.sendTextMessage(student_mobilenumber,null,"your child is out from bus",null,null);
                          // message.setText("");
                          Toast.makeText(getApplicationContext(), "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                      }
                      catch (Exception e){
                          Toast.makeText(getApplicationContext(), "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                      }
                      Toast.makeText(getApplicationContext(), "Add Student Data Successfully", Toast.LENGTH_SHORT).show();

                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError error) {
                      Toast.makeText(getApplicationContext(), "Faild to add data", Toast.LENGTH_SHORT).show();

                  }
              });

          }






































         //studnet_RNumber = txtshowQR.getText().toString().trim();

//            databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceEvening").child("StudentEveningEnter");
//            valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        System.out.println(dataSnapshot);
//                        System.out.println("data");
//
//                        // Toast.makeText(Show_Buses.this, dataSnapshot+"", Toast.LENGTH_SHORT).show();
//
//                        Attendance_Out_Activity attendance_out_activity = dataSnapshot.getValue( Attendance_Out_Activity.class);
//                        System.out.println("stuu");
//
//                        eveningOStatus =  attendance_out_activity.getEveningOStatus();
//
//                        System.out.println( attendance_out_activity.getBus());
//                        System.out.println( attendance_out_activity.getEveningOStatus());
//                        System.out.println("statuu");}
//                    if (chmorning.isChecked()){
//                  //  if (eveningOStatus==null ) {
//                        IntentResult intentResultt = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//                        txtshowQR.setText(intentResultt.getContents());
//
//                        System.out.println(eveningOStatus);
//                        System.out.println("cvb");
//
//
//                    }else if (chevening.isChecked()){
//
//
//
//
//                       // Toast.makeText(MainActivity.this, "absent???", Toast.LENGTH_SHORT).show();
//
//                    }
//                    }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//

           // Toast.makeText(this, "evening chicked", Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.btnlogout:
                SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                // editor.putString("namee", "");
                editor.remove("driver");
                editor.clear();
                editor.apply();
                startActivity(new Intent(MainActivity.this,Login_Activity.class));
                finish();
               // Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.btncall:
                databaseReference=FirebaseDatabase.getInstance().getReference("Admin").child("Admin Register");
                valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        System.out.println(snapshot);


                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Admin_ModalClass admin_modalClass=dataSnapshot.getValue(Admin_ModalClass.class);
                            dMobile=    admin_modalClass.getMobile();
                            System.out.println(dMobile);
                            System.out.println("ccds");}
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + dMobile));
                        //   getContext().startActivity(callIntent);
                        if (Build.VERSION.SDK_INT > 23) {
                            startActivity(callIntent);
                        } else {

                            if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                    android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(getApplicationContext(), "Permission Not Granted ", Toast.LENGTH_SHORT).show();
                            } else {
                                final String[] PERMISSIONS_STORAGE = {android.Manifest.permission.CALL_PHONE};
                                //ActivityCompat.requestPermissions(getContext(), PERMISSIONS_STORAGE, 9);
                                startActivity(callIntent);
                            }
                        }
                        // adminemail=    admin_modalClass.getAdminNumber();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return true;
            case R.id.btnmsg:
                databaseReference=FirebaseDatabase.getInstance().getReference("Admin").child("Admin Register");
                valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        System.out.println(snapshot);
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Admin_ModalClass admin_modalClass=dataSnapshot.getValue(Admin_ModalClass.class);
                            dMobile=    admin_modalClass.getMobile();
                            System.out.println(dMobile);
                            System.out.println("adsds");}
                        Intent intent1 = new Intent(getApplicationContext(), SendMsg_Activity.class);
                        intent1.putExtra("dMobile", dMobile);
                        startActivity(intent1);
                        // adminemail=    admin_modalClass.getAdminNumber();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}