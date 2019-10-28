package com.example.goolemap.Adepter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goolemap.Model.BookingInformation;
import com.example.goolemap.Model.UploadRoomFlat;
import com.example.goolemap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PerticularOwnerProductAdepter extends RecyclerView.Adapter<PerticularOwnerProductAdepter.MyViewHolder> {

    private Context context;
    private ArrayList<UploadRoomFlat> uploadRoomFlatsList;
    private FirebaseUser user;

    public PerticularOwnerProductAdepter(Context context, ArrayList<UploadRoomFlat> uploadRoomFlatsList) {
        this.context = context;
        this.uploadRoomFlatsList = uploadRoomFlatsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PerticularOwnerProductAdepter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_owner_product_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final UploadRoomFlat roomFlat = uploadRoomFlatsList.get(position);
        holder.houseName.setText(roomFlat.getHouseName());
        holder.price.setText(roomFlat.getPrice());
        holder.area.setText(roomFlat.getArea());
        Bitmap bitmap = StringToBitMap(roomFlat.getImage1());
        holder.flatImg.setImageBitmap(bitmap);
        holder.progressBar.setVisibility(View.GONE);
        user = FirebaseAuth.getInstance().getCurrentUser();

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAt(position);
                DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference("UploadData").child("Flat").child(roomFlat.getRoomFlatNo()+user.getUid());
                databaseReference.removeValue();
                DatabaseReference databaseReference2 =  FirebaseDatabase.getInstance().getReference("UploadData").child("Room").child(roomFlat.getRoomFlatNo()+user.getUid());
                databaseReference2.removeValue();
                DatabaseReference databaseReference3 =  FirebaseDatabase.getInstance().getReference("UploadData").child("Hostel").child(roomFlat.getRoomFlatNo()+user.getUid());
                databaseReference3.removeValue();
            }
        });
    }

    public void removeAt(int position) {
        uploadRoomFlatsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, uploadRoomFlatsList.size());
    }


    @Override
    public int getItemCount() {
        return uploadRoomFlatsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView flatImg,removeItem;
        TextView houseName, price, area;
        ProgressBar progressBar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            flatImg = itemView.findViewById(R.id.imageViewForFlat);
            houseName = itemView.findViewById(R.id.houseName);
            price = itemView.findViewById(R.id.housePrice);
            area = itemView.findViewById(R.id.houseArea);
            progressBar = itemView.findViewById(R.id.imageForViewProduct);
            removeItem = itemView.findViewById(R.id.removeItem);


        }
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

}
