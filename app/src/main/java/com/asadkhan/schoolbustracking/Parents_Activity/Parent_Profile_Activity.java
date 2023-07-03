package com.asadkhan.schoolbustracking.Parents_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asadkhan.schoolbustracking.Admin_ModalClass;
import com.asadkhan.schoolbustracking.Driver_Activity.Driver_Model_Class;
import com.asadkhan.schoolbustracking.Driver_Activity.LocationShow_Activity;
import com.asadkhan.schoolbustracking.Login_Activity;
import com.asadkhan.schoolbustracking.R;
import com.asadkhan.schoolbustracking.Student_Activity.Student_Profile_Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Parent_Profile_Activity extends AppCompatActivity {
TextView textViewpname,textViewpMobile,textViewpAge,textViewpAddres,textViewpEmail,textViewPerentChild;
Button btnchild,btnlogout,btnloc;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    public static final String MyPREFERENCES = "Myapp";
    String student_Bus,dMobile;
    String student_registerationNo,Parent_Chils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);
        textViewpname=findViewById(R.id.txtpname);
        textViewpMobile=findViewById(R.id.txtpmobile);
        //textViewpAge=findViewById(R.id.txtpage);
        textViewpAddres=findViewById(R.id.txtpaddres);
        textViewpEmail=findViewById(R.id.txtpemail);
        textViewPerentChild=findViewById(R.id.txtperent_chil);
        btnchild=findViewById(R.id.btnChild);
        btnlogout=findViewById(R.id.btnlogout);
       // btnloc=findViewById(R.id.btnloc);
        //btnChildLocation=findViewById(R.id.btnChildLocation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        btnloc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), LocationShow_Activity.class));
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
//        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
//        boolean isSignedIn = preferences.getBoolean("isSignedIn", true);
//        if (!isSignedIn) {
//           // startActivity(new Intent(Parent_Profile_Activity.this, Login_Activity.class));
//            // redirect to login activity
//        btnlogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                SharedPreferences  sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
////
////                SharedPreferences.Editor editor = sharedpreferences.edit();
////               // editor.putString("namee", "");
////                editor.remove("namee");
////                editor.clear();
////                editor.apply();
////                startActivity(new Intent(Parent_Profile_Activity.this,Login_Activity.class));
////                finish();
//            }
//        });
//        }
        Intent intent=getIntent();
        String Parent_name=intent.getStringExtra("Parent_name");
        String Parent_mobile=intent.getStringExtra("Parent_mobile");
       // String Parent_age=intent.getStringExtra("Parent_age");
        String Parent_Addres=intent.getStringExtra("Parent_Addres");
        String Parent_email=intent.getStringExtra("Parent_mail");
         Parent_Chils=intent.getStringExtra("Parent_Chils");


        textViewpname.setText(Parent_name);
        textViewpMobile.setText(Parent_mobile);
       // textViewpAge.setText(Parent_age);
        textViewpAddres.setText(Parent_Addres);
        textViewpEmail.setText(Parent_email);
        textViewPerentChild.setText(Parent_Chils);




        btnchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Show_Child_Profile();
            }

            private void Show_Child_Profile() {
                System.out.println(Parent_Chils);
                System.out.println("pp");
             //  Toast.makeText(Parent_Profile_Activity.this, "clack", Toast.LENGTH_SHORT).show();
                databaseReference= FirebaseDatabase.getInstance().getReference("Students").child("StudentRecord");                System.out.println(databaseReference);
                valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                            Show_Child_ModalClass show_child_modalClass=dataSnapshot.getValue(Show_Child_ModalClass.class);
                            //    String student_registerationNo, stdent_fullname, student_fatherName, student_class, student_mobileNo, student_email, student_location;
                             student_registerationNo  =show_child_modalClass.getStudent_registerationNo();
                            String  stdent_fullname=show_child_modalClass.getStdent_fullname();
                            String student_fatherName=show_child_modalClass.getStudent_fatherName();
                            String student_class=show_child_modalClass.getStudent_class();
                            String student_mobileNo=show_child_modalClass.getStudent_mobileNo();
                            String student_email=show_child_modalClass.getStudent_email();
                            String student_location=show_child_modalClass.getStudent_location();
                             student_Bus= show_child_modalClass.getSbus();
                           // System.out.println(student_registerationNo);
                            System.out.println(show_child_modalClass.getSbus());
                            System.out.println(student_registerationNo+",,,,"+Parent_Chils);
                            System.out.println("sid");

                            if (Parent_Chils.equals(student_registerationNo)){
                                Intent intent1=new Intent(getApplicationContext(), Student_Profile_Activity.class);
                                intent1.putExtra("stdent_fullname",stdent_fullname);
                                intent1.putExtra("student_fatherName",student_fatherName);
                                intent1.putExtra("student_class",student_class);
                                intent1.putExtra("student_mobileNo",student_mobileNo);
                                intent1.putExtra("student_email",student_email);
                                intent1.putExtra("student_location",student_location);
                                intent1.putExtra("student_Bus",student_Bus);
                                intent1.putExtra("student_registerationNo",student_registerationNo);



                                startActivity(intent1);
                              //  Toast.makeText(Parent_Profile_Activity.this, "same", Toast.LENGTH_SHORT).show();
                            }else {


                               // Toast.makeText(Parent_Profile_Activity.this, "not same", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Parent_Profile_Activity.this, "not get", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Parent_Profile_Activity.this, "click", Toast.LENGTH_SHORT).show();
                databaseReference= FirebaseDatabase.getInstance().getReference("Students").child("StudentRecord");                System.out.println(databaseReference);
                valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                            Show_Child_ModalClass show_child_modalClass=dataSnapshot.getValue(Show_Child_ModalClass.class);
                            //    String student_registerationNo, stdent_fullname, student_fatherName, student_class, student_mobileNo, student_email, student_location;
                            String student_b  =show_child_modalClass.getStudent_registerationNo();
                            if (student_b.equals(Parent_Chils)){
                           String sbus=show_child_modalClass.getSbus();
                            databaseReference= FirebaseDatabase.getInstance().getReference("Driver").child("Driver Register");
                            valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                        //  String busNumber=dataSnapshot.getValue(String.class);
                                        // System.out.println(busNumber);
                                        System.out.println(dataSnapshot);
                                        System.out.println("buss");
                                        Driver_Model_Class driver_model_class=dataSnapshot.getValue(Driver_Model_Class.class);
                                        System.out.println(driver_model_class.getDriver_name());
                                        System.out.println("nnaammmm");
                                        String dname=driver_model_class.getDriver_name();
                                        String dMobile=driver_model_class.getDriver_mobile();
                                        String dLocation=driver_model_class.getDriver_Addres();
                                        String dAge=driver_model_class.getDriver_age();
                                        String dbBus=driver_model_class.getBus();
                                        String dEmail=driver_model_class.getDrivere_mail();
                                        System.out.println(dbBus);
                                        System.out.println(student_Bus);
                                        System.out.println("bbbnn");
                                        String b="abd";
                                        if (dbBus.equals(sbus)){
                                            Intent intent1=new Intent(getApplicationContext(),ShowDriver_Parent_Activity.class);
                                            intent1.putExtra("dname",dname);
                                            intent1.putExtra("dMobile",dMobile);
                                            intent1.putExtra("dLocation",dLocation);
                                            intent1.putExtra("dAge",dAge);
                                            intent1.putExtra("dbBus",dbBus);
                                            intent1.putExtra("dEmail",dEmail);
                                            intent1.putExtra("b",b);
                                            startActivity(intent1);
                                        }else {
                                           // Toast.makeText(Parent_Profile_Activity.this, "nottr", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    System.out.println("not");
                                    // Toast.makeText(MainActivity.this, "not", Toast.LENGTH_SHORT).show();
                                }
                            });


                        }}

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.btnlogout:
                SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                // editor.putString("namee", "");
                editor.remove("namee");
                editor.clear();
                editor.apply();
                startActivity(new Intent(Parent_Profile_Activity.this, Login_Activity.class));
                finish();

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

                        //   getContext().startActivity(callIntent);
                        if (Build.VERSION.SDK_INT > 23) {
                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse("tel:" + dMobile));
                            startActivity(callIntent);
                        } else {

                            if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                    android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(getApplicationContext(), "Permission Not Granted ", Toast.LENGTH_SHORT).show();
                            } else {
                                final String[] PERMISSIONS_STORAGE = {android.Manifest.permission.CALL_PHONE};
                                //ActivityCompat.requestPermissions(getContext(), PERMISSIONS_STORAGE, 9);
                                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                                callIntent.setData(Uri.parse("tel:" + dMobile));
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
        }}
}