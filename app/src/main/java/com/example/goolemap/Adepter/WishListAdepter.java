package com.example.goolemap.Adepter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goolemap.Model.UploadRoomFlat;
import com.example.goolemap.R;

import java.util.ArrayList;

public class WishListAdepter extends RecyclerView.Adapter<WishListAdepter.MyViewHolder> {


    private Bitmap bitmap;

    private Context context;
    private ArrayList<UploadRoomFlat> uploadRoomFlatArrayList;

    public WishListAdepter() {

    }

    public WishListAdepter(Context context, ArrayList<UploadRoomFlat> uploadRoomFlatArrayList) {
        this.context = context;
        this.uploadRoomFlatArrayList = uploadRoomFlatArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WishListAdepter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorate_list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UploadRoomFlat uploadRoomFlat = uploadRoomFlatArrayList.get(position);

        holder.name.setText(uploadRoomFlat.getHouseName());
        holder.phone.setText(uploadRoomFlat.getPhoneNumber());
        holder.area.setText(uploadRoomFlat.getArea());

        bitmap = StringToBitMap(uploadRoomFlat.getImage1());

        holder.productImg.setImageBitmap(bitmap);



    }

    @Override
    public int getItemCount() {
        return uploadRoomFlatArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImg;
        private TextView name,area,phone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.image1_id);
            name = itemView.findViewById(R.id.name_id);
            area = itemView.findViewById(R.id.area_id);
            phone = itemView.findViewById(R.id.phone_id);

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
