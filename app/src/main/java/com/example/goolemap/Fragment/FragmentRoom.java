package com.example.goolemap.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goolemap.Adepter.ProductAdepter;
import com.example.goolemap.Authorization.Login;
import com.example.goolemap.MapsActivity;
import com.example.goolemap.Model.Status;
import com.example.goolemap.Model.UploadRoomFlat;
import com.example.goolemap.R;
import com.example.goolemap.UploadData.UploadRoomOrFlat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentRoom extends Fragment {


    private Button goToMapActivity;
    private RecyclerView recyclerView;
    private List<UploadRoomFlat> uploadRoomFlatArrayList;
    ProductAdepter productAdepter;
    FirebaseUser user;
    SearchView searchView;
    DatabaseReference databaseReference;
    FloatingActionButton floatingActionButton ;



    public FragmentRoom() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.room_fragment,container,false);

        floatingActionButton = v.findViewById(R.id.floating_action_button);
        recyclerView = v.findViewById(R.id.recyclerViewForFlat);
        goToMapActivity = v.findViewById(R.id.goToMapActivity);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uploadRoomFlatArrayList = new ArrayList<>();
        searchView = v.findViewById(R.id.flatSearchId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        goToMapActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"click",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra("key","Room");
                getActivity().startActivity(intent);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user==null)
                {
                    Intent intent = new Intent(getActivity(), Login.class);
                    getActivity().startActivity(intent);
                } else
                {
                    Intent intent = new Intent(getActivity(), UploadRoomOrFlat.class);
                    intent.putExtra("key","Room");
                    getActivity().startActivity(intent);
                }


            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("UploadData").child("Room");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    UploadRoomFlat roomModel = dataSnapshot1.getValue(UploadRoomFlat.class);
                    uploadRoomFlatArrayList.add(roomModel);
                }

                productAdepter = new ProductAdepter(getActivity(), (ArrayList<UploadRoomFlat>) uploadRoomFlatArrayList,"Room");
                recyclerView.setAdapter(productAdepter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        try {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Status").child(user.getUid());

            databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Status status = dataSnapshot.getValue(Status.class);

                    if (status.getRenter().equals("renter"))
                    {
                        floatingActionButton.setVisibility(View.GONE);
                        searchView.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e)
        {

        }

        return v;
    }

    @Override
    public void onStart() {

        if (searchView!=null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return false;
                }
            });
        }
        super.onStart();
    }

    private void search(String str) {
        ArrayList<UploadRoomFlat> myList = new ArrayList<>();
        for (UploadRoomFlat object : uploadRoomFlatArrayList)
        {
            if (object.getArea().toLowerCase().contains(str.toLowerCase())
                    || object.getPrice().toLowerCase().contains(str.toLowerCase())
                    || object.getFamily().toLowerCase().contains(str.toLowerCase())
                    || object.getBachelor().toLowerCase().contains(str.toLowerCase())
                    || object.getOther().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }
        ProductAdepter flatAdepter = new ProductAdepter(myList);
        recyclerView.setAdapter(flatAdepter);
    }
}
