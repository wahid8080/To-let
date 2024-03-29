package com.example.goolemap.Adepter;

import android.content.Context;
import android.content.Intent;
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

import com.example.goolemap.Authorization.Login;
import com.example.goolemap.FaceRoomOrFlat;
import com.example.goolemap.Model.UploadRoomFlat;
import com.example.goolemap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProductAdepter extends RecyclerView.Adapter<ProductAdepter.MyViewHolder> {


    private Context context;
    private ArrayList<UploadRoomFlat> uploadRoomFlatsList;
    private Bitmap bitmap;
    FirebaseUser user;
    private String key;

    public ProductAdepter(Context context, ArrayList<UploadRoomFlat> uploadRoomFlatsList, String key) {
        this.context = context;
        this.uploadRoomFlatsList = uploadRoomFlatsList;
        this.key = key;
    }

    public ProductAdepter(ArrayList<UploadRoomFlat> uploadRoomFlatsList) {
        this.uploadRoomFlatsList = uploadRoomFlatsList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_product_list, viewGroup, false));
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        final UploadRoomFlat roomFlat = uploadRoomFlatsList.get(i);
        myViewHolder.houseName.setText(roomFlat.getHouseName());
        myViewHolder.price.setText(roomFlat.getPrice());
        myViewHolder.area.setText(roomFlat.getArea());
        bitmap = StringToBitMap(roomFlat.getImage1());
        myViewHolder.flatImg.setImageBitmap(bitmap);
        myViewHolder.progressBar.setVisibility(View.GONE);
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (key!="wish_list")
        {
            myViewHolder.favouriteImage.setVisibility(View.VISIBLE);
            myViewHolder.favotetRemove.setVisibility(View.GONE);
        } else
        {
            myViewHolder.favouriteImage.setVisibility(View.GONE);
            myViewHolder.favotetRemove.setVisibility(View.VISIBLE);
        }

        myViewHolder.favouriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (user==null)
               {
                   Intent intent = new Intent(context, Login.class);
                   context.startActivity(intent);
               } else
               {

                   DatabaseReference mFavourite = FirebaseDatabase.getInstance().getReference("FavoriteList").child(user.getUid()).child(String.valueOf(roomFlat.getRoomFlatNo()));
                   UploadRoomFlat uploadRoomFlat = new UploadRoomFlat(roomFlat.getFamily(),roomFlat.getBachelor(),roomFlat.getOther(),roomFlat.getRazuk(),roomFlat.getGet(),roomFlat.getInternet(),roomFlat.getGenarator(),
                           roomFlat.getWater(),roomFlat.getImage1(),roomFlat.getImage2(),roomFlat.getImage3(),roomFlat.getImage4(),roomFlat.getPrice(),roomFlat.getHouseName(),roomFlat.getArea(),roomFlat.getRoadNo(),roomFlat.getHouseNumber(),roomFlat.getDetails(),roomFlat.getPhoneNumber(),roomFlat.getLatitute(),roomFlat.getLongatitute(),roomFlat.getRoomFlatNo(),user.getUid(),"Book Now","");
                   mFavourite.setValue(uploadRoomFlat);

                   int image = R.drawable.ic_liked;
                   myViewHolder.favouriteImage.setImageResource(image);
               }
            }
        });

        myViewHolder.favotetRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference mData = FirebaseDatabase.getInstance().getReference("FavoriteList").child(user.getUid()).child(String.valueOf(roomFlat.getRoomFlatNo()));
                mData.removeValue();
                removeAt(i);
            }
        });



        myViewHolder.seeDetailsOnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (user==null)
                {
                    Intent intent = new Intent(context, Login.class);
                    context.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, FaceRoomOrFlat.class);
                    intent.putExtra("houseName", roomFlat.getHouseName());
                    intent.putExtra("price", roomFlat.getPrice());
                    intent.putExtra("area", roomFlat.getArea());
                    intent.putExtra("other", roomFlat.getOther());
                    intent.putExtra("family", roomFlat.getFamily());
                    intent.putExtra("bachelor", roomFlat.getBachelor());
                    intent.putExtra("details", roomFlat.getDetails());
                    intent.putExtra("road", roomFlat.getRoadNo());
                    intent.putExtra("phone", roomFlat.getPhoneNumber());
                    intent.putExtra("img1", roomFlat.getImage1());
                    intent.putExtra("img2", roomFlat.getImage2());
                    intent.putExtra("img3", roomFlat.getImage3());
                    intent.putExtra("img4", roomFlat.getImage4());
                    intent.putExtra("book",roomFlat.getBookStatus());
                    intent.putExtra("customerId",roomFlat.getCustomerId());
                    intent.putExtra("flatNo",roomFlat.getRoomFlatNo());
                    intent.putExtra("key",key);
                    intent.putExtra("uId",roomFlat.getuId());
                    context.startActivity(intent);
                }



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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView flatImg,favouriteImage,favotetRemove;
        TextView houseName, price, area;
        Button seeDetailsOnClick;
        ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            flatImg = itemView.findViewById(R.id.imageViewForFlat);
            houseName = itemView.findViewById(R.id.houseName);
            price = itemView.findViewById(R.id.housePrice);
            area = itemView.findViewById(R.id.houseArea);
            seeDetailsOnClick = itemView.findViewById(R.id.seeMore);
            progressBar = itemView.findViewById(R.id.imageForViewProduct);
            favouriteImage = itemView.findViewById(R.id.favouriteList);
            favotetRemove = itemView.findViewById(R.id.favouriteRemove);


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
