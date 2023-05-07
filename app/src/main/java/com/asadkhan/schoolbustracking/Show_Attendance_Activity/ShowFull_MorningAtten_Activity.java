package com.asadkhan.schoolbustracking.Show_Attendance_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.asadkhan.schoolbustracking.Attendance_Activity.Attendance_Out_Activity;
import com.asadkhan.schoolbustracking.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowFull_MorningAtten_Activity extends AppCompatActivity {
    DatabaseReference databaseReference;
    GridView simpleGrid;
    ValueEventListener valueEventListener;
    String   keyrecord;
    String key2;
    ArrayList<Attendance_Out_Activity> students;
    String textm="morningIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_full_morning_atten);
        simpleGrid = (GridView) findViewById(R.id.simpleGridView);
        Intent intent=getIntent();
        String date2=  intent.getStringExtra("date");
        String morningin = intent.getStringExtra("morningIn");
        String bus1=intent.getStringExtra("student_Bus");
        String student_registerationNo=intent.getStringExtra("student_registerationNo");
        System.out.println(bus1);
        System.out.println(date2);
        System.out.println("abc");

        ArrayList<String> keyRecordsList = new ArrayList<>();
        students=new ArrayList<>();
        if (textm.equals(morningin)){

            Adapter_FullMorningAtten morning_attenShow = new Adapter_FullMorningAtten(getApplicationContext(),R.layout.gridview_fullatten, students);
            simpleGrid.setAdapter(morning_attenShow);
            databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceMorningIn").child(bus1).child(date2);
            valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshott) {
                    students.clear();


                    //HashMap<Integer, HashMap<String, Object>> cardData = new HashMap<Integer, HashMap<String, Object>>();

                    System.out.println(snapshott.getValue());

                    System.out.println("keyddd");


                    for (DataSnapshot dataSnapshott:snapshott.getChildren()) {

                        Attendance_Out_Activity attendance_out_activity=dataSnapshott.getValue(Attendance_Out_Activity.class);
                      String StudentRno=  attendance_out_activity.getStudnet_RNumber();
                        System.out.println(student_registerationNo);
                        System.out.println(StudentRno);
                        System.out.println("studentt");
                      if (StudentRno.equals(student_registerationNo)){
                       // System.out.println(sr);
                        System.out.println("studenttr");
                        students.add(dataSnapshott.getValue(Attendance_Out_Activity.class));
                        System.out.println(students);
                       }else {
                          Toast.makeText(ShowFull_MorningAtten_Activity.this, "sorry your child is not present today", Toast.LENGTH_SHORT).show();
                      }
                    }
////            cardData = (HashMap<Integer, HashMap<String, Object>>) dataSnapshott.getValue();
//
//            String buses= (String) dataSnapshott.getValue();
//            System.out.println(buses);
//            //System.out.println(dataSnapshott);
//            System.out.println("data");
//            Log.i("Firebase",                               // 👈 Log the key and value
//                    "Reading Member2 from "+dataSnapshot.getKey() // 👈 to know where the
//                            +", value="+dataSnapshot.getValue()           // 👈 problem is in your
//            );
//            //MorningAtten_Modal_class morningAtten_modal_class = ab.getValue(MorningAtten_Modal_class.class);
//            students.add(dataSnapshott.getValue(MorningAtten_Modal_class.class));
//
//        }

                    // System.out.println(morningAtten_modal_class);
//
//          morningAtten_modal_class.getStudent_name();
//s

//        System.out.println( morningAtten_modal_class);

//        for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
//              System.out.println(dataSnapshot);
//
//            System.out.println(morningAtten_modal_class);
//            String   fullrecord=    dataSnapshot.getKey();
//             System.out.println(fullrecord);
//
//        }
                    morning_attenShow.notifyDataSetChanged();
//
//
//
//        }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else {
            System.out.println(bus1);
            System.out.println(date2);
            System.out.println("kkss");

            Adapter_FullMorningAtten morning_attenShow = new Adapter_FullMorningAtten(getApplicationContext(),R.layout.gridview_fullatten, students);
            simpleGrid.setAdapter(morning_attenShow);
            databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceMorningout").child(bus1).child(date2);
            valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshott) {
                    students.clear();


                    //HashMap<Integer, HashMap<String, Object>> cardData = new HashMap<Integer, HashMap<String, Object>>();

                    System.out.println(snapshott.getValue());

                    System.out.println("keyddd");


                    for (DataSnapshot dataSnapshott:snapshott.getChildren()) {

                        Attendance_Out_Activity attendance_out_activity=dataSnapshott.getValue(Attendance_Out_Activity.class);
                        String StudentRno=  attendance_out_activity.getStudnet_RNumber();
                        System.out.println(student_registerationNo);
                        System.out.println(StudentRno);
                        System.out.println("studentt");
                        if (StudentRno.equals(student_registerationNo)){
                            // System.out.println(sr);
                            System.out.println("studenttr");
                            students.add(dataSnapshott.getValue(Attendance_Out_Activity.class));
                            System.out.println(students);
                        }
                    }
////            cardData = (HashMap<Integer, HashMap<String, Object>>) dataSnapshott.getValue();
//
//            String buses= (String) dataSnapshott.getValue();
//            System.out.println(buses);
//            //System.out.println(dataSnapshott);
//            System.out.println("data");
//            Log.i("Firebase",                               // 👈 Log the key and value
//                    "Reading Member2 from "+dataSnapshot.getKey() // 👈 to know where the
//                            +", value="+dataSnapshot.getValue()           // 👈 problem is in your
//            );
//            //MorningAtten_Modal_class morningAtten_modal_class = ab.getValue(MorningAtten_Modal_class.class);
//            students.add(dataSnapshott.getValue(MorningAtten_Modal_class.class));
//
//        }

                    // System.out.println(morningAtten_modal_class);
//
//          morningAtten_modal_class.getStudent_name();
//s

//        System.out.println( morningAtten_modal_class);

//        for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
//              System.out.println(dataSnapshot);
//
//            System.out.println(morningAtten_modal_class);
//            String   fullrecord=    dataSnapshot.getKey();
//             System.out.println(fullrecord);
//
//        }
                    morning_attenShow.notifyDataSetChanged();
//
//
//
//        }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}