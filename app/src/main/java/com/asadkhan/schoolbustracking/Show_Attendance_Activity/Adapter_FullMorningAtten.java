package com.asadkhan.schoolbustracking.Show_Attendance_Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.asadkhan.schoolbustracking.Attendance_Activity.Attendance_Out_Activity;
import com.asadkhan.schoolbustracking.R;

import java.util.ArrayList;

public class Adapter_FullMorningAtten extends ArrayAdapter {

    ArrayList<Attendance_Out_Activity> morningdata = new ArrayList<>();

    public Adapter_FullMorningAtten(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);
        morningdata = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.gridview_fullatten, null);






        TextView txtname=view.findViewById(R.id.txtnameshow);
        TextView txtRNumber=view.findViewById(R.id.txtRnumber);
        TextView txtdate=view.findViewById(R.id.txtdate);
        TextView txttime=view.findViewById(R.id.txttime);
        TextView txtstatus=view.findViewById(R.id.txtstatus);
        //textView.setText(birdList.get(position).getbirdName());
        System.out.println(morningdata.get(position).getMorningStatus());
        System.out.println(morningdata.get(position).getStudent_name());
        System.out.println(morningdata.get(position).getBus());
        System.out.println("psh");
        txtname.setText( morningdata.get(position).getStudent_name());
        txtRNumber.setText(morningdata.get(position).getStudnet_RNumber());
        txtdate.setText(morningdata.get(position).getMorningentryDate());
        txttime.setText(morningdata.get(position).getMorningentryTime());
        txtstatus.setText(morningdata.get(position).getMorningStatus());





//        TextView textView = (TextView) v.findViewById(R.id.textView);
//        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
//        textView.setText(birdList.get(position).getbirdName());
//        imageView.setImageResource(birdList.get(position).getbirdImage());
        return view;

    }
}
