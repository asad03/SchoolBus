package com.asadkhan.schoolbustracking.Parents_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asadkhan.schoolbustracking.R;
import com.asadkhan.schoolbustracking.Student_Activity.Student_Profile_Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Parent_Profile_Activity extends AppCompatActivity {
TextView textViewpname,textViewpMobile,textViewpAge,textViewpAddres,textViewpEmail,textViewPerentChild;
Button btnchild;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);
        textViewpname=findViewById(R.id.txtpname);
        textViewpMobile=findViewById(R.id.txtpmobile);
        textViewpAge=findViewById(R.id.txtpage);
        textViewpAddres=findViewById(R.id.txtpaddres);
        textViewpEmail=findViewById(R.id.txtpemail);
        textViewPerentChild=findViewById(R.id.txtperent_chil);
        btnchild=findViewById(R.id.btnChild);



        Intent intent=getIntent();
        String Parent_name=intent.getStringExtra("Parent_name");
        String Parent_mobile=intent.getStringExtra("Parent_mobile");
        String Parent_age=intent.getStringExtra("Parent_age");
        String Parent_Addres=intent.getStringExtra("Parent_Addres");
        String Parent_email=intent.getStringExtra("Parent_mail");
        String Parent_Chils=intent.getStringExtra("Parent_Chils");

        textViewpname.setText(Parent_name);
        textViewpMobile.setText(Parent_mobile);
        textViewpAge.setText(Parent_age);
        textViewpAddres.setText(Parent_Addres);
        textViewpEmail.setText(Parent_email);
        textViewPerentChild.setText(Parent_Chils);

        btnchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(Parent_Chils);
                System.out.println("pp");
                Toast.makeText(Parent_Profile_Activity.this, "clack", Toast.LENGTH_SHORT).show();
                databaseReference= FirebaseDatabase.getInstance().getReference("Students").child("StudentRecord");                System.out.println(databaseReference);
                valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                            Show_Child_ModalClass show_child_modalClass=dataSnapshot.getValue(Show_Child_ModalClass.class);
                            //    String student_registerationNo, stdent_fullname, student_fatherName, student_class, student_mobileNo, student_email, student_location;
                           String student_registerationNo  =show_child_modalClass.getStudent_registerationNo();
                           String  stdent_fullname=show_child_modalClass.getStdent_fullname();
                           String student_fatherName=show_child_modalClass.getStudent_fatherName();
                           String student_class=show_child_modalClass.getStudent_class();
                           String student_mobileNo=show_child_modalClass.getStudent_mobileNo();
                           String student_email=show_child_modalClass.getStudent_email();
                           String student_location=show_child_modalClass.getStudent_location();
                            System.out.println(student_registerationNo);
                            System.out.println(Parent_Chils);
                            System.out.println(student_registerationNo+",,,,"+Parent_Chils);
                            System.out.println("sid");

                           if (Parent_Chils.equals(student_registerationNo)){
                               Intent intent1=new Intent(getApplicationContext(), Student_Profile_Activity.class);
                               intent1.putExtra("stdent_fullname",stdent_fullname);
                               intent1.putExtra("student_fatherName",student_fatherName);
                               intent1.putExtra("student_class",student_class);
                               intent1.putExtra("student_mobileNo",student_mobileNo);
                               intent1.putExtra("student_email",student_email);
                               intent1.putExtra("student_location",student_location);



                               startActivity(intent1);
                               Toast.makeText(Parent_Profile_Activity.this, "same", Toast.LENGTH_SHORT).show();
                           }else {


                               Toast.makeText(Parent_Profile_Activity.this, "not same", Toast.LENGTH_SHORT).show();
                           }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Parent_Profile_Activity.this, "not get", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });






    }
}