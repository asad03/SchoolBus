package com.asadkhan.schoolbustracking.Parents_Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.asadkhan.schoolbustracking.R;

public class ShowDriver_Parent_Activity extends AppCompatActivity {
TextView txtd_name,txtd_mobile,txtd_age,txtd_location,txtd_bus,txtd_email;
    int MY_PERMISSIONS_REQUEST_SEND_SMS=0;
ImageButton btncall,btnmsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_driver_parent);

        Intent intent=getIntent();
        String dMobile=intent.getStringExtra("dMobile");
        String dLocation=intent.getStringExtra("dLocation");
       // String   dAge   =intent.getStringExtra("dAge");
        String    dbBus  =intent.getStringExtra("dbBus");
        String   dEmail   =intent.getStringExtra("dEmail");
        String    dname  =intent.getStringExtra("dname");
        intent.getStringExtra("b");
        System.out.println(dMobile);
        System.out.println(dLocation);
       // System.out.println(dAge);
        System.out.println(dEmail);
        System.out.println(dname);
        //System.out.println(dEmail);
        System.out.println("wwmm");
        txtd_name=findViewById(R.id.textd_fullname);
        txtd_mobile=findViewById(R.id.textd_mobiloe);
       // txtd_age=findViewById(R.id.textd_age);
        txtd_location=findViewById(R.id.textd_location);
        txtd_bus=findViewById(R.id.textd_bus);
        txtd_email=findViewById(R.id.textd_email);
        btncall=findViewById(R.id.btnDrcall);
        btnmsg=findViewById(R.id.btnDrmsg);

        txtd_name.setText(dname);
        txtd_mobile.setText(dMobile);
       // txtd_age.setText(dAge);
        txtd_bus.setText(dbBus);
        txtd_location.setText(dLocation);
        txtd_email.setText(dEmail);


        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+dMobile));
                //   getContext().startActivity(callIntent);
                if (Build.VERSION.SDK_INT > 23) {
                    ShowDriver_Parent_Activity.this.  startActivity(callIntent);
                } else {

                    if (ActivityCompat.checkSelfPermission(ShowDriver_Parent_Activity.this,
                            android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(ShowDriver_Parent_Activity.this, "Permission Not Granted ", Toast.LENGTH_SHORT).show();
                    } else {
                        final String[] PERMISSIONS_STORAGE = {android.Manifest.permission.CALL_PHONE};
                        //ActivityCompat.requestPermissions(getContext(), PERMISSIONS_STORAGE, 9);
                        ShowDriver_Parent_Activity.this.  startActivity(callIntent);
                    }}
            }
        });
        btnmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(ShowDriver_Parent_Activity.this, SendMsg_Activity.class);
                intent1.putExtra("dMobile", dMobile);
                startActivity(intent1);
            }
        });



    }
}