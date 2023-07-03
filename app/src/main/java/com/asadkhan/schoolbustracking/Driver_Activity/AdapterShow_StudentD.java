package com.asadkhan.schoolbustracking.Driver_Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.asadkhan.schoolbustracking.Parents_Activity.SendMsg_Activity;
import com.asadkhan.schoolbustracking.Parents_Activity.Show_Child_ModalClass;
import com.asadkhan.schoolbustracking.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdapterShow_StudentD extends ArrayAdapter<Show_Child_ModalClass>{

//    private List<Show_Child_ModalClass> originalDataList; // Original data list
//    private List<Show_Child_ModalClass> filteredDataList; // Filtered data list
//    private ItemFilter itemFilter;


    Show_Child_ModalClass student;
   // ArrayList<Show_Child_ModalClass> dataModalArrayList;
  ArrayList<Show_Child_ModalClass> mData;
   ArrayList<Show_Child_ModalClass> mStringFilterList;
    ValueFilter valueFilter;

    public AdapterShow_StudentD(@NonNull Context context, ArrayList<Show_Child_ModalClass> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
//        this.originalDataList = new ArrayList<>(dataModalArrayList); // Initialize originalDataList with all data initially
//        this.filteredDataList = new ArrayList<>(dataModalArrayList); // Initialize filteredDataList with all data initially
//        this.itemFilter = new ItemFilter();
        mData=dataModalArrayList;
        mStringFilterList = dataModalArrayList;
        }
//
    @Override
    public int getCount() {
        return mData.size();
    }
    @Override
    public Show_Child_ModalClass getItem(int position) {
        return mData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Show_Child_ModalClass> filterList = new ArrayList<>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getStdent_fullname().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mData = (ArrayList<Show_Child_ModalClass>) results.values;
            notifyDataSetChanged();
        }

    }

//
//    @NonNull
//    @Override
//    public Filter getFilter() {
//        return itemFilter;
//    }

    @NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView;
        listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_showstudent_d, parent, false);
        }
    Show_Child_ModalClass    student = getItem(position);
        TextView studentRegisteration = listitemView.findViewById(R.id.text_studentRegister);
        TextView studentFullName = listitemView.findViewById(R.id.text_studentName);
        TextView studentFatherName = listitemView.findViewById(R.id.text_studentFatherName);
        TextView studentMobile = listitemView.findViewById(R.id.text_StudentMobileNo);
       // TextView studentEmail = listitemView.findViewById(R.id.text_StudentEmail);
        TextView studentClass = listitemView.findViewById(R.id.text_StudentClass);
        TextView studentLocation = listitemView.findViewById(R.id.text_StudentLocation);
        TextView studentBus = listitemView.findViewById(R.id.text_StudentBus);
        ImageView studentimg = listitemView.findViewById(R.id.imgstudnt);
        // ImageButton btncard=listitemView.findViewById(R.id.btncard);
        ImageButton btnmsg = listitemView.findViewById(R.id.btnstudentMsg);
        ImageButton btncall = listitemView.findViewById(R.id.btnstudentCall);

        studentRegisteration.setText(student.getStudent_registerationNo());
        studentFullName.setText(student.getStdent_fullname());
        studentFatherName.setText(student.getStudent_fatherName());
        studentMobile.setText(student.getStudent_mobileNo());
        //studentEmail.setText(student.getStudent_email());
        studentClass.setText(student.getStudent_class());
        studentLocation.setText(student.getStudent_location());
        studentBus.setText(student.getSbus());
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        Uri uri = firebaseUser.getPhotoUrl();

        String student_name = student.getStdent_fullname();
        String father_name = student.getStudent_fatherName();
        String dMobile = student.getStudent_mobileNo();
        String student_class = student.getStudent_class();
        String student_Rnumber = student.getStudent_registerationNo();
        //  Picasso.With(UploadAdminProfilePic_Activity.this).load(uri).into(imgProfileAdmin);
        // Picasso.get().load(uri).fit().centerInside().into(studentimg);
        btnmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), SendMsg_Activity.class);
                intent1.putExtra("dMobile", dMobile);
                getContext().startActivity(intent1);

            }
        });
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String number=edittext1.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + dMobile));
                //   getContext().startActivity(callIntent);
                if (Build.VERSION.SDK_INT > 23) {
                    getContext().startActivity(callIntent);
                } else {

                    if (ActivityCompat.checkSelfPermission(getContext(),
                            android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getContext(), "Permission Not Granted ", Toast.LENGTH_SHORT).show();
                    } else {
                        final String[] PERMISSIONS_STORAGE = {android.Manifest.permission.CALL_PHONE};
                        //ActivityCompat.requestPermissions(getContext(), PERMISSIONS_STORAGE, 9);
                        getContext().startActivity(callIntent);
                    }
                }
//                Intent intent2=new Intent(getContext(),StudentCall_Activity.class);
//                intent2.putExtra("student_mobile",student_mobile);
//                getContext().startActivity(intent2);
            }
        });
        return listitemView;
    }
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults results = new FilterResults();
//                List<Show_Child_ModalClass> filteredList = new ArrayList<>();
//
//                if (constraint == null || constraint.length() == 0) {
//                    filteredList.addAll(dataModalArrayList); // Add all data items when search query is empty
//                } else {
//                    String searchQuery = constraint.toString().toLowerCase().trim();
//                    for (Show_Child_ModalClass item : dataModalArrayList) {
//                        if (item.getStdent_fullname().toLowerCase().contains(searchQuery)) {
//                            filteredList.add(item);
//                        }
//                    }
//                }
//
//                results.values = filteredList;
//                results.count = filteredList.size();
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//               // filteredDataList.clear();
//                //filteredDataList.addAll((Collection<? extends Show_Child_ModalClass>) results.values);
//                notifyDataSetChanged();
//            }
//        };
//    }
//private class ItemFilter extends Filter {
//
//    @Override
//    protected FilterResults performFiltering(CharSequence constraint) {
//        FilterResults results = new FilterResults();
//        List<Show_Child_ModalClass> filteredList = new ArrayList<>();
//
//        if (constraint == null || constraint.length() == 0) {
//            filteredList.addAll(originalDataList); // Add all data items when search query is empty
//        } else {
//            String searchQuery = constraint.toString().toLowerCase().trim();
//            for (Show_Child_ModalClass item : originalDataList) {
//                if (item.getStdent_fullname().toLowerCase().contains(searchQuery)) {
//                    filteredList.add(item);
//                }
//            }
//        }
//
//        results.values = filteredList;
//        results.count = filteredList.size();
//        return results;
//    }
//
//    @Override
//    protected void publishResults(CharSequence constraint, FilterResults results) {
//        filteredDataList.clear();
//        filteredDataList.addAll((List<Show_Child_ModalClass>) results.values);
//        notifyDataSetChanged();
//    }
//}

}
