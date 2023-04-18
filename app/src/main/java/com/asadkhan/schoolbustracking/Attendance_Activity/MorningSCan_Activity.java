package com.asadkhan.schoolbustracking.Attendance_Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asadkhan.schoolbustracking.MainActivity;
import com.asadkhan.schoolbustracking.R;
import com.asadkhan.schoolbustracking.Student_Activity.Capture_QR;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MorningSCan_Activity extends AppCompatActivity {
String bus;
Button btnmorningIn,btnmorningOut;
    IntentResult intentResult1,  intentResult2;
    TextView txtmorningIn,txtmorningOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning_scan);
//        btnmorningIn=findViewById(R.id.btnmorningIn);
//        btnmorningOut=findViewById(R.id.btnmorningOut);
//        txtmorningIn=findViewById(R.id.textmorningIn);
//        txtmorningOut=findViewById(R.id.textmorningOut);
//        Intent intent=getIntent();
//        bus=  intent.getStringExtra("bus");
//        btnmorningIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                                IntentIntegrator intentIntegrator=new IntentIntegrator(MorningSCan_Activity.this);
//                intentIntegrator.setPrompt("use up key");
//                intentIntegrator.setBeepEnabled(true);
//                intentIntegrator.setOrientationLocked(true);
//                intentIntegrator.setCaptureActivity(Capture_QR.class);
//
//            }
//        });
//
//        btnmorningOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentIntegrator intentIntegrator=new IntentIntegrator(MorningSCan_Activity.this);
//                intentIntegrator.setPrompt("use up key");
//                intentIntegrator.setBeepEnabled(true);
//                intentIntegrator.setOrientationLocked(true);
//                intentIntegrator.setCaptureActivity(Capture_QR.class);
//                intentIntegrator.initiateScan();
//
//
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        intentResult1=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
//        if (intentResult1.getContents()!=null){
//            txtmorningIn.setText( intentResult1.getContents());
//        }
//         intentResult2=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
//        if (intentResult2.getContents()!=null){
//            txtmorningOut.setText( intentResult2.getContents());
//        }

    }

}