package com.example.goolemap.UploadData;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goolemap.MainActivity;
import com.example.goolemap.Model.UserInformation;
import com.example.goolemap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadOwnerInfo extends AppCompatActivity {

    private ImageView profileImage;
    private EditText mUserName, mPhone, mArea, mRoad, mHouseNo;
    private String yourStirng;
    private DatabaseReference databaseReference, databaseReference2, databaseReference3;
    private FirebaseUser user;
    private static int PIC_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_clint_info);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            yourStirng = extras.getString("user");
        }

        mUserName = findViewById(R.id.userNameId);
        mPhone = findViewById(R.id.phoneNumberId);
        mArea = findViewById(R.id.ariaThanaId);
        mRoad = findViewById(R.id.RoadNoId);
        mHouseNo = findViewById(R.id.houseId);
        profileImage = findViewById(R.id.uploadProfileImage);
        user = FirebaseAuth.getInstance().getCurrentUser();
        button = findViewById(R.id.buttonClintUpInfo);


        databaseReference2 = FirebaseDatabase.getInstance().getReference("Status").child(user.getUid()).child("owner");
        databaseReference2.setValue("owner");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("Status").child(user.getUid()).child("renter");
        databaseReference3.setValue("false");
    }

    public void dataSubmit(View view) {
        uploadInformationMethod();
        finish();
    }

    void uploadInformationMethod() {

        String userName = mUserName.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
        String area = mArea.getText().toString().trim();
        String road = mRoad.getText().toString().trim();
        String house = mHouseNo.getText().toString().trim();
        String profileIMG = imageToString(bitmap);
        String owner = "owner";

        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("owner").child(user.getUid());
        UserInformation userInformation = new UserInformation(userName, phone, area, road, house, owner, profileIMG);
        databaseReference.setValue(userInformation);

        startActivity(new Intent(UploadOwnerInfo.this, MainActivity.class));
    }

    public void uploadProfileImage(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PIC_IMAGE_REQUEST);
    }

    public String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
        byte[] imgbyte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgbyte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PIC_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {

            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                profileImage.setImageBitmap(bitmap);
                button.setEnabled(true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
