package com.example.goolemap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goolemap.Model.Status;
import com.example.goolemap.UploadData.UploadInfoForBooking;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FaceRoomOrFlat extends AppCompatActivity {

    private ImageView mImg1, mImg2, mImg3, mImg4;
    private TextView houseName, area, phone, category, roadNo, details, price;
    private String nameOfHouse, priceOfHouse, areaOfHouse, categoryOfHouse,
            detailOfHouse, roadOfHouse, phoneOfHouse, img1, img2, img3, img4, book, uId, flatNo, key, customerId;
    private Bitmap bitmap1, bitmap2, bitmap3, bitmap4;
    private Button bookNow, callNow;

    private DatabaseReference databaseReference;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_roo_ro_flat);

        mImg1 = findViewById(R.id.image1forFace);
        mImg2 = findViewById(R.id.image2forFace);
        mImg3 = findViewById(R.id.image3forFace);
        mImg4 = findViewById(R.id.image4forFace);

        houseName = findViewById(R.id.houseName);
        area = findViewById(R.id.areaTana);
        phone = findViewById(R.id.housePhone);
        category = findViewById(R.id.category);
        roadNo = findViewById(R.id.roadNo);
        details = findViewById(R.id.houseDetails);
        price = findViewById(R.id.housePrice);
        bookNow = findViewById(R.id.bookNow);
        callNow = findViewById(R.id.callNow);
        user = FirebaseAuth.getInstance().getCurrentUser();


        nameOfHouse = getIntent().getStringExtra("houseName");
        priceOfHouse = getIntent().getStringExtra("price");
        areaOfHouse = getIntent().getStringExtra("area");
        categoryOfHouse = getIntent().getStringExtra("category");
        detailOfHouse = getIntent().getStringExtra("details");
        roadOfHouse = getIntent().getStringExtra("road");
        phoneOfHouse = getIntent().getStringExtra("phone");
        img1 = getIntent().getStringExtra("img1");
        img2 = getIntent().getStringExtra("img2");
        img3 = getIntent().getStringExtra("img3");
        img4 = getIntent().getStringExtra("img4");

        book = getIntent().getStringExtra("book");
        customerId = getIntent().getStringExtra("customerId");
        flatNo = getIntent().getStringExtra("flatNo");
        uId = getIntent().getStringExtra("uId");
        key = getIntent().getStringExtra("key");
        bookNow.setText(book);

        if (book.equals("Booked"))
        {
            if (customerId.equals(user.getUid()))
            {
                bookNow.setText("Cancel Booking");
            } else
            {
                bookNow.setText("Booked");
                bookNow.setEnabled(false);
            }

        }



        bitmap1 = StringToBitMap(img1);
        bitmap2 = StringToBitMap(img2);
        bitmap3 = StringToBitMap(img3);
        bitmap4 = StringToBitMap(img4);


        houseName.setText(nameOfHouse);
        phone.setText(phoneOfHouse);
        area.setText(areaOfHouse);
        category.setText(categoryOfHouse);
        roadNo.setText(roadOfHouse);
        details.setText(detailOfHouse);
        price.setText(priceOfHouse);

        mImg1.setImageBitmap(bitmap1);
        mImg2.setImageBitmap(bitmap2);
        mImg3.setImageBitmap(bitmap3);
        mImg4.setImageBitmap(bitmap4);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Status").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Status status = dataSnapshot.getValue(Status.class);

                if (status.getOwner().equals("owner")) {
                    callNow.setVisibility(View.GONE);
                    bookNow.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void callNow(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneOfHouse));
        startActivity(intent);
    }

    public void booking(View view) {

        if (book.equals("Book Now"))
        {

            Intent intent = new Intent(FaceRoomOrFlat.this, UploadInfoForBooking.class);
            intent.putExtra("flatNo",flatNo);
            intent.putExtra("uId",uId);
            intent.putExtra("key",key);
            intent.putExtra("houseName",nameOfHouse);
            startActivity(intent);
            finish();
        } else if (book.equals("Booked"))
        {
            DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("UploadData").child(key).child(flatNo + uId).child("customerId");
            databaseReference3.setValue("");
            DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("UploadData").child(key).child(flatNo + uId).child("bookStatus");
            databaseReference2.setValue("Book Now");
            DatabaseReference databaseReference4 = FirebaseDatabase.getInstance().getReference("BookingStatus").child(uId).child(flatNo).child("bookingStatus");
            databaseReference4.setValue("Cancel booking");

            startActivity(new Intent(FaceRoomOrFlat.this,MainActivity.class));
            finish();
        }



    }
}
