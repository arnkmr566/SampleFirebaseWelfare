package com.example.firebasewelfare.New;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasewelfare.SampleModel;
import com.example.firebasewelfare.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private DatabaseReference reference;
  private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        binding.btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 btnSampleModel();
               //  btnHashMap();


            }

        });
        binding.btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReadTime();
            }
        });


       binding.btnShow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
               startActivity(intent);
           }
       });


    }

    private void btnReadTime() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    String post = "name : " + dataSnapshot.child("name").getValue(String.class) + "\n" + "address : " + dataSnapshot.child("address").getValue(String.class) + "\n" + "phone : " + dataSnapshot.child("phone").getValue(String.class);

                    binding.tvRead.setText(post);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void btnSampleModel() {

        String username = binding.edtName.getText().toString();
        String useraddress = binding.edtAddress.getText().toString();
        String userphone = binding.edtPhoneNumber.getText().toString();
        String id = reference.push().getKey();

        SampleModel model = new SampleModel(username, useraddress, userphone);
        reference.child(id).setValue(model)

                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "User data details", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, " Not User data details", Toast.LENGTH_SHORT).show();

                        }

                    }
                });


    }

    private void btnHashMap() {

            String Name = binding.edtName.getText().toString();
            String Address = binding.edtAddress.getText().toString();
            String Phone = binding.edtPhoneNumber.getText().toString();
            // String id = reference.push().getKey();

            // HashMap
            HashMap<String, String> usermap = new HashMap<>();
            usermap.put("name",Name);
            usermap.put("address",Address);
            usermap.put("phone",Phone);



            reference.push().setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();

                }
            });

    }




}