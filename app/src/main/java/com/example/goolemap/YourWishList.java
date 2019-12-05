package com.example.goolemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.goolemap.Adepter.ProductAdepter;
import com.example.goolemap.Adepter.WishListAdepter;
import com.example.goolemap.Model.UploadRoomFlat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YourWishList extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<UploadRoomFlat> uploadRoomFlatArrayList;
    FirebaseUser user;
    ProductAdepter wishListAdepter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_wish_list);

        recyclerView = findViewById(R.id.recyclerViewForWishList);
        uploadRoomFlatArrayList = new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference mData = FirebaseDatabase.getInstance().getReference("FavoriteList").child(user.getUid());

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    UploadRoomFlat  uploadRoomFlat = dataSnapshot1.getValue(UploadRoomFlat.class);
                    uploadRoomFlatArrayList.add(uploadRoomFlat);
                }

                wishListAdepter = new ProductAdepter(YourWishList.this,uploadRoomFlatArrayList,"wish_list");
                recyclerView.setAdapter(wishListAdepter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
