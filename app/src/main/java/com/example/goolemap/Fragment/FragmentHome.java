package com.example.goolemap.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.goolemap.Model.Status;
import com.example.goolemap.Model.UploadClintInfoModel;

import com.example.goolemap.Model.UserInformation;

import com.example.goolemap.R;
import com.example.goolemap.UploadData.UploadOwnerInfo;
import com.example.goolemap.UploadData.UploadRenderInfo;
import com.example.goolemap.UploadData.UploadRoomOrFlat;
import com.example.goolemap.YourRent;
import com.example.goolemap.YourRentOwner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FragmentHome extends Fragment {

    private TextView mArea, mRoad, mHouseNumber, mPhone, mEmail, userName;
    private DatabaseReference databaseReference,databaseReference2,databaseReference3;
    private FirebaseUser user;
    private ImageView profilePic;
    private Bitmap bitmap;
    private ScrollView renterScrollView,ownerScrollView;

    private ImageView profilePicRenter;
    private TextView district, thana, phone, email, nid, userNameRenter;
    private Bitmap bitmapRenter;
    private Button button,button4;


    public FragmentHome() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.home_fragment, container, false);

        profilePic = v.findViewById(R.id.ProfilePicIdForOwner);
        user = FirebaseAuth.getInstance().getCurrentUser();

        mArea = v.findViewById(R.id.ownerAreaid);
        mRoad = v.findViewById(R.id.ownerRoadNoId);
        mHouseNumber = v.findViewById(R.id.ownerHouseId);
        mPhone = v.findViewById(R.id.ownerPhoneId);
        mEmail = v.findViewById(R.id.ownerEmailId);
        userName = v.findViewById(R.id.nameForOwner);
        button = v.findViewById(R.id.button3);
        button4 = v.findViewById(R.id.button4);

        renterScrollView = v.findViewById(R.id.renter);
        ownerScrollView = v.findViewById(R.id.owner);

        district = v.findViewById(R.id.renderDistrectId);
        thana = v.findViewById(R.id.renderAreaId);
        phone = v.findViewById(R.id.renderPhoneId);
        email = v.findViewById(R.id.renderEmailId);
        nid = v.findViewById(R.id.renderNidId);
        userNameRenter = v.findViewById(R.id.userNameForRenterId2);
        profilePicRenter = v.findViewById(R.id.ProfilePicIdForRenderId);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Status").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Status status = dataSnapshot.getValue(Status.class);

                if (status.getRenter().equals("renter"))
                {
                    renterScrollView.setVisibility(View.VISIBLE);
                    databaseReference3 = FirebaseDatabase.getInstance().getReference("User").child("renter").child(user.getUid());

                    databaseReference3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            UploadClintInfoModel Model = dataSnapshot.getValue(UploadClintInfoModel.class);

                            try {
                                userNameRenter.setText("Name: " + Model.getName());
                                district.setText("District: " + Model.getDistric());
                                thana.setText("Thana: " + Model.getArea());
                                phone.setText("Phone: " + Model.getPhone());
                                email.setText("Email: " + user.getEmail());
                                nid.setText("N ID: " + Model.getNid());
                                bitmapRenter = StringToBitMap(Model.getImage());
                                profilePicRenter.setImageBitmap(bitmapRenter);
                            } catch (Exception e) {
                                Intent intent = new Intent(getActivity(), UploadRenderInfo.class);
                                getActivity().startActivity(intent);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {


                        }
                    });
                }
                else if (status.getOwner().equals("owner"))
                {
                    ownerScrollView.setVisibility(View.VISIBLE);
                    databaseReference2 = FirebaseDatabase.getInstance().getReference("User").child("owner").child(user.getUid());
                    databaseReference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            UserInformation information = dataSnapshot.getValue(UserInformation.class);

                            try {
                                userName.setText("Name: " + information.getUserName());
                                mRoad.setText("Road : " + information.getRoad());
                                mHouseNumber.setText("House : " + information.getHouseNo());
                                mPhone.setText("Contact: " + information.getPhone());
                                mArea.setText("Area: " + information.getArea());
                                mEmail.setText("Email: " + user.getEmail());
                                bitmap = StringToBitMap(information.getImage());
                                profilePic.setImageBitmap(bitmap);
                            }
                            catch (Exception e)
                            {
                                Intent intent = new Intent(getActivity(), UploadOwnerInfo.class);
                                getActivity().startActivity(intent);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YourRent.class);
                getActivity().startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YourRentOwner.class);
                getActivity().startActivity(intent);
            }
        });
        return v;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
