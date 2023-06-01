package com.example.firebasewelfare.New;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.firebasewelfare.SampleAdapter;
import com.example.firebasewelfare.SampleModel;
import com.example.firebasewelfare.databinding.ActivityShowDataBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {

    ActivityShowDataBinding binding;

     ArrayList<SampleModel> list;
    SampleAdapter adapter;
     DatabaseReference reference;
 //    FirebaseDatabase firebaseDatabase;
  //  FirebaseDatabase database = FirebaseDatabase.getInstance();
  //  DatabaseReference reference = database.getReference().child("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

     //   firebaseDatabase = FirebaseDatabase.getInstance();
     //   reference = firebaseDatabase.getReference().child("Users");

        reference = FirebaseDatabase.getInstance().getReference("Users");

        list = new ArrayList<>();
        adapter = new SampleAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    SampleModel model = dataSnapshot.getValue(SampleModel.class);
                    list.add(model);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}