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
import com.example.goolemap.Model.UploadClintInfoModel;
import com.example.goolemap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadRenderInfo extends AppCompatActivity {

    private EditText mName,mPhone,mArea,mDistric,mNid;
    private ImageView profileIMG;
    private Button button;

    private DatabaseReference databaseReference,databaseReference2,databaseReference3;
    private FirebaseUser user;

    private String yourStirng;
    private static int PIC_IMAGE_REQUEST = 1;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_render_info);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            yourStirng = extras.getString("user");
        }

        mName = findViewById(R.id.clintUserNameId);
        mPhone = findViewById(R.id.ClintPhoneNumberId);
        mArea = findViewById(R.id.ClintAriaThanaId);
        mDistric = findViewById(R.id.District);
        mNid = findViewById(R.id.nid);
        profileIMG = findViewById(R.id.uploadProfileImagerender);
        button = findViewById(R.id.buttonRenterUpInfo);

        user = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference2 = FirebaseDatabase.getInstance().getReference("Status").child(user.getUid()).child("owner");
        databaseReference2.setValue("false");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("Status").child(user.getUid()).child("renter");
        databaseReference3.setValue("renter");
    }

    public void dataSubmit(View view) {
        insertData();
    }

    public void insertData()
    {

        String name = mName.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
        String area = mArea.getText().toString().trim();
        String district = mDistric.getText().toString().trim();
        String nid = mNid.getText().toString().trim();
        String render = "render";
        String profile = imageToString(bitmap);

        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("renter").child(user.getUid());
        UploadClintInfoModel upload = new UploadClintInfoModel(name,phone,area,district,nid,render,profile);
        databaseReference.setValue(upload);
        startActivity(new Intent(UploadRenderInfo.this, MainActivity.class));
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
                profileIMG.setImageBitmap(bitmap);
                button.setEnabled(true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
