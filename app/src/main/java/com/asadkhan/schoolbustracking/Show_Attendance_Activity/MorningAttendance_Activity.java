package com.asadkhan.schoolbustracking.Show_Attendance_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.asadkhan.schoolbustracking.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MorningAttendance_Activity extends AppCompatActivity {
    ListView simpleList;
    DatabaseReference databaseReference;

    ValueEventListener valueEventListener;
    ArrayList<String> date = new ArrayList();
    String keydate;
    String textm="morningIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning_attendance);
        simpleList=findViewById(R.id.simpleListView);
        Intent intent=getIntent();
      String morningIn=  intent.getStringExtra("morningIn");
      String student_Bus=intent.getStringExtra("student_Bus");
      String student_registerationNo=intent.getStringExtra("student_registerationNo");
        System.out.println(morningIn);
        System.out.println(student_Bus);
        System.out.println(student_registerationNo);
        System.out.println("mav");

        if (textm.equals(morningIn)) {
            databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceMorningIn").child(student_Bus);
            valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        keydate = dataSnapshot.getKey();
                        date.add(keydate);
                        System.out.println(date);
                        System.out.println(keydate);
                    }

                    simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), ShowFull_MorningAtten_Activity.class);
                            //intent.putExtra("Dates",Dates);
                            intent.putExtra("date", date.get(position));
                            intent.putExtra("student_Bus", student_Bus);
                            intent.putExtra("student_registerationNo",student_registerationNo);
                            intent.putExtra("morningIn",morningIn);
                           // intent.putExtra("aa", aa);

                            //intent.putStringArrayListExtra("valuesList", new ArrayList<>(valuesList));
//
//                                        intent.putExtra("student_name",student_name);
//                                        intent.putExtra(" studnet_RNumber", studnet_RNumber);
//                                        intent.putExtra("morningentryDate",morningentryDate);
//                                        intent.putExtra("morningentryTime",morningentryTime);
                            startActivity(intent);
                           // Toast.makeText(ShowFull_MorningAtten_Activity.this, "call", Toast.LENGTH_SHORT).show();
                        }
                    });
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MorningAttendance_Activity.this, R.layout.listview_morningatten, R.id.textView, date);

                    simpleList.setAdapter(arrayAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else {
            databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceMorningout").child(student_Bus);
            valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        keydate = dataSnapshot.getKey();
                        date.add(keydate);
                        System.out.println(date);
                        System.out.println(keydate);
                    }

                    simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), ShowFull_MorningAtten_Activity.class);
                            //intent.putExtra("Dates",Dates);
                            intent.putExtra("date", date.get(position));
                            intent.putExtra("student_Bus", student_Bus);
                            intent.putExtra("student_registerationNo",student_registerationNo);
                          //  intent.putExtra("morningIn",morningIn);
                            // intent.putExtra("aa", aa);

                            //intent.putStringArrayListExtra("valuesList", new ArrayList<>(valuesList));
//
//                                        intent.putExtra("student_name",student_name);
//                                        intent.putExtra(" studnet_RNumber", studnet_RNumber);
//                                        intent.putExtra("morningentryDate",morningentryDate);
//                                        intent.putExtra("morningentryTime",morningentryTime);
                            startActivity(intent);
                            // Toast.makeText(ShowFull_MorningAtten_Activity.this, "call", Toast.LENGTH_SHORT).show();
                        }
                    });
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MorningAttendance_Activity.this, R.layout.listview_morningatten, R.id.textView, date);

                    simpleList.setAdapter(arrayAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}