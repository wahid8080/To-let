package com.example.goolemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.goolemap.Adepter.PerticularOwnerProductAdepter;
import com.example.goolemap.Adepter.ProductAdepter;
import com.example.goolemap.Model.UploadRoomFlat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class YourRentOwner extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UploadRoomFlat> uploadRoomFlatArrayList;
    PerticularOwnerProductAdepter perticularOwnerProductAdepter;
    FirebaseUser user;
    DatabaseReference databaseReference,databaseReference2,databaseReference3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_rent_owner);


        recyclerView = findViewById(R.id.recyclerViewForRentOwner);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uploadRoomFlatArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference("UploadData").child("Flat");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    UploadRoomFlat roomModel = dataSnapshot1.getValue(UploadRoomFlat.class);

                    if (roomModel.getuId().equals(user.getUid()))
                    {
                        uploadRoomFlatArrayList.add(roomModel);
                    }
                }

                perticularOwnerProductAdepter = new PerticularOwnerProductAdepter(YourRentOwner.this, (ArrayList<UploadRoomFlat>) uploadRoomFlatArrayList);
                recyclerView.setAdapter(perticularOwnerProductAdepter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference2 = FirebaseDatabase.getInstance().getReference("UploadData").child("Room");

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    UploadRoomFlat roomModel = dataSnapshot1.getValue(UploadRoomFlat.class);

                    if (roomModel.getuId().equals(user.getUid()))
                    {
                        uploadRoomFlatArrayList.add(roomModel);
                    }
                }

                perticularOwnerProductAdepter = new PerticularOwnerProductAdepter(YourRentOwner.this, (ArrayList<UploadRoomFlat>) uploadRoomFlatArrayList);
                recyclerView.setAdapter(perticularOwnerProductAdepter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference3 = FirebaseDatabase.getInstance().getReference("UploadData").child("Hostel");

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    UploadRoomFlat roomModel = dataSnapshot1.getValue(UploadRoomFlat.class);

                    if (roomModel.getuId().equals(user.getUid()))
                    {
                        uploadRoomFlatArrayList.add(roomModel);
                    }
                }

                perticularOwnerProductAdepter = new PerticularOwnerProductAdepter(YourRentOwner.this, (ArrayList<UploadRoomFlat>) uploadRoomFlatArrayList);
                recyclerView.setAdapter(perticularOwnerProductAdepter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
