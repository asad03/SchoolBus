package com.asadkhan.schoolbustracking.Student_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asadkhan.schoolbustracking.Location_Modal_class;
import com.asadkhan.schoolbustracking.Parents_Activity.Parent_Profile_Activity;
import com.asadkhan.schoolbustracking.R;
import com.asadkhan.schoolbustracking.Show_Attendance_Activity.AttenAllBtn_Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Student_Profile_Activity extends AppCompatActivity {
TextView txtstudent_rno,txtstdent_fullname,txtstudent_fatherName,txtstudent_class,txtstudent_mobileNo,txtstudent_email,txtstudent_location,txtstudent_bus;
 Button    btnChildLocation,btnAtten,btncurrentstatus;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        txtstdent_fullname=findViewById(R.id.textstdent_fullname);
        txtstudent_fatherName=findViewById(R.id.textstudent_fatherName);
        txtstudent_class=findViewById(R.id.textstudent_class);
        txtstudent_mobileNo=findViewById(R.id.textstudent_mobileNo);
        txtstudent_email=findViewById(R.id.textstudent_email);
        txtstudent_location=findViewById(R.id.textstudent_location);
        txtstudent_bus=findViewById(R.id.textstudent_bus);
        txtstudent_rno=findViewById(R.id.textstudent_Rno);
        btnAtten=findViewById(R.id.btnchAtten);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Parent_Profile_Activity.class));
//            }
//        });

        Intent intent=getIntent();
        String  stdent_fullname   =intent.getStringExtra("stdent_fullname");
        String   student_fatherName  =intent.getStringExtra("student_fatherName");
        String   student_class  =intent.getStringExtra("student_class");
        String student_mobileNo    =intent.getStringExtra("student_mobileNo");
        String  student_email   =intent.getStringExtra("student_email");
        String   student_location  =intent.getStringExtra("student_location");
        String student_Bus=intent.getStringExtra("student_Bus");
        String student_registerationNo=intent.getStringExtra("student_registerationNo");

        txtstdent_fullname.setText(stdent_fullname);
        txtstudent_fatherName.setText(student_fatherName);
        txtstudent_class.setText(student_class);
        txtstudent_mobileNo.setText(student_mobileNo);
        txtstudent_email.setText(student_email);
        txtstudent_location.setText(student_location);
        txtstudent_bus.setText(student_Bus);
        txtstudent_rno.setText(student_registerationNo);
        btnChildLocation=findViewById(R.id.btnChidloc);


        btnChildLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(student_Bus);
                System.out.println("pp");
                //Toast.makeText(getApplicationContext(), "clack", Toast.LENGTH_SHORT).show();
                databaseReference = FirebaseDatabase.getInstance().getReference("Driver").child("Driver Register");                valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                           Location_Modal_class location_modal_class=dataSnapshot.getValue(Location_Modal_class.class);
                            //    String student_registerationNo, stdent_fullname, student_fatherName, student_class, student_mobileNo, student_email, student_location;
                         double student_Latitude  =location_modal_class.getLatitude();
                        double student_Longitude= location_modal_class.getLongitude();
                        String studnet_InBus=location_modal_class.getBus();

                            System.out.println(student_Latitude);
                            System.out.println(student_Longitude);
                           System.out.println(student_Bus+",,,,"+studnet_InBus);
                            System.out.println("sid");

                            if (student_Bus.equals(studnet_InBus)){
                                Intent intent1=new Intent(getApplicationContext(), Student_Location_Activity.class);
                                intent1.putExtra("student_Latitude",student_Latitude );
                               intent1.putExtra("student_Longitude",student_Longitude);

                                intent1.putExtra("student_Bus",student_Bus);
                                intent1.putExtra("student_registerationNo",student_registerationNo);
//                                intent1.putExtra("student_class",student_class);
//                                intent1.putExtra("student_mobileNo",student_mobileNo);
//                                intent1.putExtra("student_email",student_email);
//                                intent1.putExtra("student_location",student_location);
//                                intent1.putExtra("student_Bus",student_Bus);



                                startActivity(intent1);
                              //  Toast.makeText(getApplicationContext(), "same", Toast.LENGTH_SHORT).show();
                            }else {


                               // Toast.makeText(getApplicationContext(), "not same", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "not get", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


        btnAtten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Student_Profile_Activity.this, AttenAllBtn_Activity.class);
                intent1.putExtra("student_Bus",student_Bus);
                intent1.putExtra("student_registerationNo",student_registerationNo);
                startActivity(intent1);

            }
        });



    }

}