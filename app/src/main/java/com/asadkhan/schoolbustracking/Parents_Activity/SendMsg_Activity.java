package com.asadkhan.schoolbustracking.Parents_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asadkhan.schoolbustracking.R;

public class SendMsg_Activity extends AppCompatActivity {
    EditText message;
    TextView mobileno;
    Button sendsms;
    int MY_PERMISSIONS_REQUEST_SEND_SMS=0;
    String student_mobile;
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);
        Intent intent=getIntent();
        student_mobile=intent.getStringExtra("dMobile");

        mobileno=findViewById(R.id.editText1);
        message=(EditText)findViewById(R.id.editText2);
        sendsms=(Button)findViewById(R.id.button1);
        mobileno.setText( student_mobile);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        //Performing action on button click
        sendsms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String no=mobileno.getText().toString();
                msg=message.getText().toString();
                System.out.println(msg);
                System.out.println(no);
                System.out.println("nmnm");
                //String msg=student_mobile;

//                if (ContextCompat.checkSelfPermission(StudentMsg_Activity.this,
//                        Manifest.permission.SEND_SMS)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(StudentMsg_Activity.this,
//                            Manifest.permission.SEND_SMS)) {
//                    } else {
//
//                        ActivityCompat.requestPermissions(StudentMsg_Activity.this,
//                                new String[]{Manifest.permission.SEND_SMS},
//                                MY_PERMISSIONS_REQUEST_SEND_SMS);
//                    }
//                }
//                //Getting intent and PendingIntent instance
//                Intent intent=new Intent(getApplicationContext(),ShowStudents.class);
//                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
//
//                //Get the SmsManager instance and call the sendTextMessage method to send message
//                SmsManager sms=SmsManager.getDefault();
//                sms.sendTextMessage("+923139931587", null, msg, pi,null);
//
//                Toast.makeText(getApplicationContext(), "Message Sent successfully!",
//                        Toast.LENGTH_LONG).show();

//                SmsManager smgr = SmsManager.getDefault();
//                smgr.sendTextMessage(no,null,msg,null,null);
//                Toast.makeText(getApplicationContext(), "SMS Sent Successfully", Toast.LENGTH_SHORT).show();

                try{
                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage(no,null,msg,null,null);
                    message.setText("");
                    Toast.makeText(getApplicationContext(), "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(student_mobile, null, msg, null, null);
//                    Toast.makeText(getApplicationContext(), "SMS sent.",
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(),
//                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}