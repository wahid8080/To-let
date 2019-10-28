package com.example.goolemap;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goolemap.Adepter.BookingAdepter;
import com.example.goolemap.Model.BookingInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YourRent extends AppCompatActivity {

    RecyclerView recyclerViewForFlat;
    FirebaseUser user;
    ArrayList<BookingInformation> bookingInformationArrayList;

    BookingAdepter bookingAdepter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_rent);

        recyclerViewForFlat = findViewById(R.id.recyclerViewForYourRentFlat);

        user = FirebaseAuth.getInstance().getCurrentUser();

        bookingInformationArrayList = new ArrayList<>();

        recyclerViewForFlat.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BookingStatus").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    BookingInformation bookingInformation = dataSnapshot1.getValue(BookingInformation.class);
                    bookingInformationArrayList.add(bookingInformation);
                }

                bookingAdepter = new BookingAdepter(YourRent.this,bookingInformationArrayList);
                recyclerViewForFlat.setAdapter(bookingAdepter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
