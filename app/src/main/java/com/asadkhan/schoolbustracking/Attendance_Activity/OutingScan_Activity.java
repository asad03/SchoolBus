package com.asadkhan.schoolbustracking.Attendance_Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asadkhan.schoolbustracking.MainActivity;
import com.asadkhan.schoolbustracking.R;
import com.asadkhan.schoolbustracking.Student_Activity.Capture_QR;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OutingScan_Activity extends AppCompatActivity {
    ValueEventListener valueEventListener;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    Button btnscan,btnscan2;
    TextView txtshowQR;
    String bus;

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outing_scan);
        txtshowQR=findViewById(R.id.txtout);
//        IntentIntegrator intentIntegrator=new IntentIntegrator(OutingScan_Activity.this);
//        intentIntegrator.setPrompt("use up key");
//        intentIntegrator.setBeepEnabled(true);
//        intentIntegrator.setOrientationLocked(true);
//        intentIntegrator.setCaptureActivity(Capture_QR.class);
//        intentIntegrator.initiateScan();
//
//        Intent intent=getIntent();
//      bus=  intent.getStringExtra("bus");
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
//        if (intentResult.getContents()!=null){
//            AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
//            builder.setTitle("Result");
//            builder.setMessage(intentResult.getContents());
//            txtshowQR.setText(intentResult.getContents());
//            if (txtshowQR.equals("")){
//
//            }else {
//                String studnet_RNumber=txtshowQR.getText().toString().trim();
//                if (TextUtils.isEmpty(studnet_RNumber)){
//                    txtshowQR.setError("please scan again");
//                    txtshowQR.requestFocus();
//                }else {
//                    SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
//                    simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//
//                    // on below line we are creating a variable
//                    // for current date and time and calling a simple date format in it.
//                    String morningOutDate = simpleFormat.format(new Date());
//                    String morningOutTime=simpleDateFormat.format(new Date());
//
//                    // on below line we are setting current
//                    // date and time to our text view.
//                    // currentTV.setText(currentDateAndTime);
//                    String morningDateandTime="122222";
//                    String morningAStatus="absent";
//
//                    firebaseDatabase= FirebaseDatabase.getInstance();
//
//                    databaseReference=firebaseDatabase.getReference("AttendanceMorning").child("StudentMorningOut");
//                    Attendance_Model_Class attendance_model_class=new Attendance_Model_Class( bus,studnet_RNumber,morningOutDate,morningOutTime,morningAStatus,morningDateandTime,student_name);
//                    databaseReference.child(studnet_RNumber).child(morningOutDate).setValue(attendance_model_class);
//                    databaseReference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            Toast.makeText(getApplicationContext(), "Add Student Data Successfully", Toast.LENGTH_SHORT).show();
//                           // startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            Toast.makeText(getApplicationContext(), "Faild to add data", Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//                }
//            }
//
//            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    dialog.dismiss();
//                    //startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                }
//            });
//            //builder.show();
//        }else {
//           // startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            Toast.makeText(getApplicationContext(),"you did not scan anything",Toast.LENGTH_LONG).show();
//        }
    }
}