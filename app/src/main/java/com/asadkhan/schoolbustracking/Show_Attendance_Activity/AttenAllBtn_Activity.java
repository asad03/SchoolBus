package com.asadkhan.schoolbustracking.Show_Attendance_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.asadkhan.schoolbustracking.Driver_Activity.Driver_Model_Class;
import com.asadkhan.schoolbustracking.Parents_Activity.ShowDriver_Parent_Activity;
import com.asadkhan.schoolbustracking.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AttenAllBtn_Activity extends AppCompatActivity {
Button btnmorningin,btnmorningout,btneveningin,btneveningout,btnDriver;
    String morningIn="morningIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atten_all_btn);
        btnmorningin=findViewById(R.id.btnmoringattenin);
        btnmorningout=findViewById(R.id.btnmorningattenout);
        btneveningin=findViewById(R.id.btnatteneveningin);
        btneveningout=findViewById(R.id.btnatteneveningout);
        btnDriver=findViewById(R.id.btnDriver);
        Intent intent=getIntent();
     String student_Bus=   intent.getStringExtra("student_Bus");
     String student_registerationNo=intent.getStringExtra("student_registerationNo");
        System.out.println(student_Bus);
        System.out.println(student_registerationNo);
        System.out.println("ssbb");
        btnmorningin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),MorningAttendance_Activity.class);
                intent1.putExtra("morningIn",morningIn);
                intent1.putExtra("student_Bus",student_Bus);
                intent1.putExtra("student_registerationNo",student_registerationNo);
                startActivity(intent1);

            }
        });
        btnmorningout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(getApplicationContext(),MorningAttendance_Activity.class);

                intent2.putExtra("student_Bus",student_Bus);
                intent2.putExtra("student_registerationNo",student_registerationNo);
                startActivity(intent2);
            }
        });
        btneveningin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(getApplicationContext(),EveningAtten_Bus_Activity.class);
                intent3.putExtra("morningIn",morningIn);
                intent3.putExtra("student_Bus",student_Bus);
                intent3.putExtra("student_registerationNo",student_registerationNo);
                startActivity(intent3);
            }
        });
        btneveningout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(getApplicationContext(),MorningAttendance_Activity.class);

                intent4.putExtra("student_Bus",student_Bus);
                intent4.putExtra("student_registerationNo",student_registerationNo);
                startActivity(intent4);
            }
        });
        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Driver").child("Driver Register");
              ValueEventListener  valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
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
                            if (dbBus.equals(student_Bus)){
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














            }
        });
    }
}