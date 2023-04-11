package com.asadkhan.schoolbustracking.Attendance_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.asadkhan.schoolbustracking.R;

public class EveningScan_Activity extends AppCompatActivity {
String bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evening_scan);
        Intent intent=getIntent();
        bus=  intent.getStringExtra("bus");
    }
}