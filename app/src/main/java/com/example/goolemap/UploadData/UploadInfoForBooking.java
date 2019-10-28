package com.example.goolemap.UploadData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.goolemap.MainActivity;
import com.example.goolemap.Model.BookingInformation;
import com.example.goolemap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadInfoForBooking extends AppCompatActivity {

    EditText  your_name, your_age,how_many_month,your_phone;
    String flatNo,uId,key,houseName;
    Button informationSubmit;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_info_for_booking);
        user = FirebaseAuth.getInstance().getCurrentUser();

        flatNo = getIntent().getStringExtra("flatNo");
        uId = getIntent().getStringExtra("uId");
        key = getIntent().getStringExtra("key");
        houseName = getIntent().getStringExtra("houseName");

        your_name = findViewById(R.id.your_name);
        your_age = findViewById(R.id.your_age);
        how_many_month = findViewById(R.id.how_many_month);
        informationSubmit = findViewById(R.id.informationSubmit);
        your_phone = findViewById(R.id.your_phone);


        informationSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = your_name.getText().toString();
                String age = your_age.getText().toString();
                String month = how_many_month.getText().toString();
                String phone = your_phone.getText().toString();
                String status = "Confirm";

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BookingStatus").child(uId).child(flatNo);
                BookingInformation bookingInformation = new BookingInformation(flatNo,name,phone,age,month,status,key,houseName);
                databaseReference.setValue(bookingInformation);
                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("UploadData").child(key).child(flatNo + uId).child("bookStatus");
                databaseReference2.setValue("Booked");
                DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference("UploadData").child(key).child(flatNo + uId).child("customerId");
                databaseReference3.setValue(user.getUid());
                startActivity(new Intent(UploadInfoForBooking.this, MainActivity.class));
                finish();
            }
        });
    }
}
