package com.asadkhan.schoolbustracking.Student_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.asadkhan.schoolbustracking.R;

public class Student_Profile_Activity extends AppCompatActivity {
TextView txtstdent_fullname,txtstudent_fatherName,txtstudent_class,txtstudent_mobileNo,txtstudent_email,txtstudent_location;
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

        Intent intent=getIntent();
        String  stdent_fullname   =intent.getStringExtra("stdent_fullname");
        String   student_fatherName  =intent.getStringExtra("student_fatherName");
        String   student_class  =intent.getStringExtra("student_class");
        String student_mobileNo    =intent.getStringExtra("student_mobileNo");
        String  student_email   =intent.getStringExtra("student_email");
        String   student_location  =intent.getStringExtra("student_location");

        txtstdent_fullname.setText(stdent_fullname);
        txtstudent_fatherName.setText(student_fatherName);
        txtstudent_class.setText(student_class);
        txtstudent_mobileNo.setText(student_mobileNo);
        txtstudent_email.setText(student_email);
        txtstudent_location.setText(student_location);

    }
}