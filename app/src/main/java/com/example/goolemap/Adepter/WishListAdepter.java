package com.example.goolemap.Adepter;

import android.content.Context;
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
        return new WishListAdepter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_wish_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return uploadRoomFlatArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImg;
        private TextView name,area,price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.WishList);
            name = itemView.findViewById(R.id.houseNameWishList);
        }
    }
}
