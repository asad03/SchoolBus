package com.asadkhan.schoolbustracking.Parents_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.asadkhan.schoolbustracking.R;

public class Parent_Profile_Activity extends AppCompatActivity {
TextView textViewpname,textViewpMobile,textViewpAge,textViewpAddres,textViewpEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);
        textViewpname=findViewById(R.id.txtpname);
        textViewpMobile=findViewById(R.id.txtpmobile);
        textViewpAge=findViewById(R.id.txtpage);
        textViewpAddres=findViewById(R.id.txtpaddres);
        textViewpEmail=findViewById(R.id.txtpemail);


        Intent intent=getIntent();
        String Parent_name=intent.getStringExtra("Parent_name");
        String Parent_mobile=intent.getStringExtra("Parent_mobile");
        String Parent_age=intent.getStringExtra("Parent_age");
        String Parent_Addres=intent.getStringExtra("Parent_Addres");
        String Parent_email=intent.getStringExtra("Parent_mail");

        textViewpname.setText(Parent_name);
        textViewpMobile.setText(Parent_mobile);
        textViewpAge.setText(Parent_age);
        textViewpAddres.setText(Parent_Addres);
        textViewpEmail.setText(Parent_email);





    }
}