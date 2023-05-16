package com.asadkhan.schoolbustracking.Driver_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import com.asadkhan.schoolbustracking.Parents_Activity.Show_Child_ModalClass;
import com.asadkhan.schoolbustracking.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowStudent_D_Activity extends AppCompatActivity {
    GridView gridView;

    ArrayList<Show_Child_ModalClass> studentData;

    ArrayList<Show_Child_ModalClass> filteredDataList;
    DatabaseReference databaseReference;
    String Dbus;
    ValueEventListener valueEventListener;
  AdapterShow_StudentD adopter_studentShow;
    private EditText searchEditText;
   // BaseAdapter_ShowStudent adopter_studentShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_dactivity);
//        searchEditText = findViewById(R.id.searchEditText);
        Intent intent=getIntent();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      Dbus=   intent.getStringExtra("bus");
        System.out.println(Dbus);
        System.out.println("nbn");
        gridView=findViewById(R.id.simpleGridView);

        searchEditText = findViewById(R.id.searchEditText);

        studentData=new ArrayList<>();

//        FirebaseAuth auth=FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser=auth.getCurrentUser();
//        firebaseUser.getUid();
        filteredDataList = new ArrayList<>();
        if (searchEditText.getText().toString().isEmpty()){
            adopter_studentShow=new AdapterShow_StudentD(ShowStudent_D_Activity.this,studentData);

        }else {
      adopter_studentShow=new AdapterShow_StudentD(ShowStudent_D_Activity.this,filteredDataList);
        }
        //adopter_studentShow=new BaseAdapter_ShowStudent(ShowStudent_D_Activity.this,studentData);

        gridView.setAdapter(adopter_studentShow);
        databaseReference= FirebaseDatabase.getInstance().getReference("Students").child("StudentRecord");
        System.out.println(databaseReference);
        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentData.clear();
                //  Toast.makeText(Show_Buses.this, "show data", Toast.LENGTH_SHORT).show();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    System.out.println(dataSnapshot);
                    System.out.println("data");

                    // Toast.makeText(Show_Buses.this, dataSnapshot+"", Toast.LENGTH_SHORT).show();
                    Show_Child_ModalClass student_infromation_class=dataSnapshot.getValue( Show_Child_ModalClass .class);
                  String SBus = student_infromation_class.getSbus();
                  if (Dbus.equals(SBus)){
                    studentData.add(student_infromation_class);
                    System.out.println(studentData);
                    System.out.println("dsds");}
                    // Toast.makeText(Show_Buses.this, ""+busdata.get(2), Toast.LENGTH_SHORT).show();
                }
                adopter_studentShow.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Some thing went worng", Toast.LENGTH_SHORT).show();
            }
        });
searchEditText.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        filterItems(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});

//      aa
    }

    private void filterItems(String searchText) {


        filteredDataList.clear();
        if (TextUtils.isEmpty(searchText)){

            filteredDataList.addAll(studentData);
        }else {
            for (Show_Child_ModalClass modalClass: studentData) {
                System.out.println(searchText);
                System.out.println(modalClass.getStdent_fullname());
                System.out.println("kkll");
                if (modalClass.getStdent_fullname().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredDataList.add(modalClass);

                    System.out.println(  filteredDataList.get(0));

                }
            }
        }
        adopter_studentShow.notifyDataSetChanged();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.serchview_gridview,menu);
//        MenuItem menuItem=menu.findItem(R.id.search_view);
//        SearchView searchView= (SearchView) menuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//             adopter_studentShow.getFilter().filter(newText);
//                return true;
//            }
//        });
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id=item.getItemId();
//        if (id==R.id.search_view){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}