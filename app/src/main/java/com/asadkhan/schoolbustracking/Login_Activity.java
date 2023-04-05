package com.asadkhan.schoolbustracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.asadkhan.schoolbustracking.Driver_Activity.Driver_Model_Class;
import com.asadkhan.schoolbustracking.Parents_Activity.Parent_Profile_Activity;
import com.asadkhan.schoolbustracking.Parents_Activity.Parents_Model_class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity {
    private EditText editTextLogin_email,editTextLogin_password;
    private ProgressBar progressBar;
    private Button btnlogin;
    FirebaseAuth authprofile;
    RadioButton chparent,chdriver;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    String parentKey,driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextLogin_email=findViewById(R.id.edEmail_login);
        editTextLogin_password=findViewById(R.id.edpasswd_login);
        progressBar=findViewById(R.id.progressbar);
        btnlogin=findViewById(R.id.btnlogin);
        authprofile=FirebaseAuth.getInstance();
        chdriver=findViewById(R.id.chdriver);
        chparent=findViewById(R.id.chparent);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textemail=editTextLogin_email.getText().toString().trim();
                String textpassword=editTextLogin_password.getText().toString().trim();

                if (TextUtils.isEmpty(textemail)){
                    editTextLogin_email.setError("please enter your email");
                    editTextLogin_email.requestFocus();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(textemail).matches()){
                    editTextLogin_email.setError("valid email is required");
                    editTextLogin_email.requestFocus();
                }else if (TextUtils.isEmpty(textpassword)){
                    editTextLogin_password.setError("please enter your password");
                    editTextLogin_password.requestFocus();
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    LoginAdmin(textemail,textpassword);
                }

            }
        });



    }

    private void LoginAdmin(String textemail, String textpassword) {

        databaseReference= FirebaseDatabase.getInstance().getReference("Parent").child("Parents Register");
        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                     parentKey=dataSnapshot.getKey();
                    System.out.println(parentKey);
                    System.out.println("parentKey");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("Driver").child("Driver Register");


        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                     driverKey=dataSnapshot.getKey();
                    System.out.println(driverKey);
                    System.out.println("driverKey");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

   String email=textemail.replace(".","");


System.out.println(email);



        if (email==parentKey) {
            FirebaseAuth auth=FirebaseAuth.getInstance();
            FirebaseUser firebaseUser=auth.getCurrentUser();
            firebaseUser.getUid();

            databaseReference= FirebaseDatabase.getInstance().getReference("Parent").child("Parents Register");
            System.out.println(databaseReference);
            valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                 //   parentData.clear();
                    //  Toast.makeText(Show_Buses.this, "show data", Toast.LENGTH_SHORT).show();
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        System.out.println(dataSnapshot);
                        System.out.println("data22");

                        // Toast.makeText(Show_Buses.this, dataSnapshot+"", Toast.LENGTH_SHORT).show();
                        Parents_Model_class parent_addModal_class=dataSnapshot.getValue(Parents_Model_class.class);
                        String Parent_name=parent_addModal_class.getParent_name();
                        String Parent_mobile=parent_addModal_class.getParent_mobile();
                        String Parent_age=parent_addModal_class.getParent_age();
                        String Parent_Addres=parent_addModal_class.getParent_Addres();
                        String Parent_mail=parent_addModal_class.getParent_mail();
                        String Parent_password=parent_addModal_class.getParent_password();
                        String usertype=parent_addModal_class.getUsertype();



//                       if (textpassword==password){


                           authprofile.signInWithEmailAndPassword(textemail, textpassword).addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (task.isSuccessful()) {
                              progressBar.setVisibility(View.GONE);


                              Intent intent1=new Intent(getApplicationContext(), Parent_Profile_Activity.class);
                              intent1.putExtra("Parent_name",Parent_name);
                              intent1.putExtra("Parent_mobile",Parent_mobile);
                              intent1.putExtra("Parent_age",Parent_age);
                              intent1.putExtra("Parent_Addres",Parent_Addres);
                              intent1.putExtra("Parent_mail",Parent_mail);



                              startActivity(intent1);
                                      // startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                       Toast.makeText(getApplicationContext(),
                                                       "Login p",
                                                       Toast.LENGTH_LONG)
                                               .show();

                                   } else {
                                       Toast.makeText(getApplicationContext(),
                                                       "Login failed!!",
                                                       Toast.LENGTH_LONG)
                                               .show();
                                   }

                               }
                           });
                          // Toast.makeText(Login_Activity.this, "Same", Toast.LENGTH_SHORT).show();
                           System.out.println("aa2");
                           System.out.println("same");
                       }

                    }



                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                   Toast.makeText(getApplicationContext(), "Some thing went worng", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if (email==driverKey){


//            FirebaseAuth auth = FirebaseAuth.getInstance();
//            FirebaseUser firebaseUser = auth.getCurrentUser();
//            firebaseUser.getUid();

            databaseReference = FirebaseDatabase.getInstance().getReference("Driver").child("Driver Register");
            System.out.println(databaseReference);
            valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //   parentData.clear();
                    //  Toast.makeText(Show_Buses.this, "show data", Toast.LENGTH_SHORT).show();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        System.out.println(dataSnapshot);
                        System.out.println("data22");

                        // Toast.makeText(Show_Buses.this, dataSnapshot+"", Toast.LENGTH_SHORT).show();

                        Location_Modal_class driver_model_class = dataSnapshot.getValue(Location_Modal_class.class);
                        String driver_name = driver_model_class.getDriver_name();
                        String driver_mobile = driver_model_class.getDriver_mobile();
                        String driver_age = driver_model_class.getDriver_age();
                        String driver_Addres = driver_model_class.getDriver_Addres();
                        String bus = driver_model_class.getBus();
                        String emaild = driver_model_class.getDrivere_mail();
                        String passwordd = driver_model_class.getDriver_password();
                        String usertype = driver_model_class.getUsertype();
                        String driver_password = driver_model_class.getDriver_password();
                        String Longitude = String.valueOf(driver_model_class.getLongitude());
                        String Latitude = String.valueOf(driver_model_class.getLatitude());


                       // Toast.makeText(Login_Activity.this, driver_model_class + "", Toast.LENGTH_SHORT).show();

  String type="driver";
                      if (emaild==textemail || passwordd==textpassword) {


                          authprofile.signInWithEmailAndPassword(emaild, passwordd).addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                  if (task.isSuccessful()) {
                                      progressBar.setVisibility(View.GONE);

                                      Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                      intent.putExtra("driver_name", driver_name);
                                      intent.putExtra("driver_mobile", driver_mobile);
                                      intent.putExtra("driver_age", driver_age);
                                      intent.putExtra("emaild", emaild);
                                      intent.putExtra("driver_Addres", driver_Addres);
                                      intent.putExtra("usertype", usertype);
                                      intent.putExtra("bus", bus);
                                      intent.putExtra("driver_password", driver_password);
                                      intent.putExtra("Longitude", Longitude);
                                      intent.putExtra("Latitude", Latitude);

                                      startActivity(intent);
                                      Toast.makeText(getApplicationContext(),
                                                      "Login p",
                                                      Toast.LENGTH_LONG)
                                              .show();


//                    }else if (chdriver.isChecked()){
//                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                        Toast.makeText(getApplicationContext(),
//                                        "Login",
//                                        Toast.LENGTH_LONG)
//                                .show();
//                    }

                                  } else {
                                      Toast.makeText(getApplicationContext(),
                                                      "Login failed!!",
                                                      Toast.LENGTH_LONG)
                                              .show();
                                  }

                              }
                          });
                      }else {
                          Toast.makeText(Login_Activity.this, "usertype is not present", Toast.LENGTH_SHORT).show();
                      }
                    }
//                       else {
//                           System.out.println("aa3");
//                           System.out.println("not same");
//                           Toast.makeText(Login_Activity.this, "not same", Toast.LENGTH_SHORT).show();
//                       }
                    //  parentData.add(parent_addModal_class);
                    // Toast.makeText(Show_Buses.this, ""+busdata.get(2), Toast.LENGTH_SHORT).show();
                }
                //   adapter_parentShow.notifyDataSetChanged();


                //    }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "Some thing went worng", Toast.LENGTH_SHORT).show();
                }
            });


        }

    }}