package com.asadkhan.schoolbustracking.Show_Attendance_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.asadkhan.schoolbustracking.R;

public class AttenAllBtn_Activity extends AppCompatActivity {
Button btnmorningin,btnmorningout,btneveningin,btneveningout;
    String morningIn="morningIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atten_all_btn);
        btnmorningin=findViewById(R.id.btnmoringattenin);
        btnmorningout=findViewById(R.id.btnmorningattenout);
        btneveningin=findViewById(R.id.btnatteneveningin);
        btneveningout=findViewById(R.id.btnatteneveningout);
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
    }
}