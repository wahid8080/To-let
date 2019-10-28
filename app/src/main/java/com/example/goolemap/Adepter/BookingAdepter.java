package com.example.goolemap.Adepter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goolemap.Model.BookingInformation;
import com.example.goolemap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BookingAdepter extends RecyclerView.Adapter<BookingAdepter.MyViewHolder> {


    private Context context;
    private ArrayList<BookingInformation> bookingInformationArrayList;
    FirebaseUser user;


    public BookingAdepter(Context context, ArrayList<BookingInformation> bookingInformationArrayList) {
        this.context = context;
        this.bookingInformationArrayList = bookingInformationArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new BookingAdepter.MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_booking_product, viewGroup, false));
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final BookingInformation bookingInformation = bookingInformationArrayList.get(position);

        holder.customerName.setText(bookingInformation.getCustomerName());
        holder.customerPhone.setText(bookingInformation.getCustomerPhone());
        holder.custmerAge.setText(bookingInformation.getCustomerAge());
        holder.unitNo.setText(bookingInformation.getRoomFlatNo());
        holder.bookingstatus.setText(bookingInformation.getBookingStatus());
        holder.stayHere.setText(bookingInformation.getStay_month());
        holder.houseName.setText(bookingInformation.getHouseName());

        if (bookingInformation.getBookingStatus().equals("Confirm"))
        {
            int image = R.drawable.ic_success;
            holder.bookingIcon.setImageResource(image);
        } else
        {
            int image = R.drawable.ic_error;
            holder.bookingIcon.setImageResource(image);
        }

        holder.callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + bookingInformation.getCustomerPhone()));
                context.startActivity(intent);
            }
        });

        holder.removeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAt(position);

                user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BookingStatus").child(user.getUid()).child(bookingInformation.getRoomFlatNo());
                databaseReference.removeValue();

                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("UploadData").child(bookingInformation.getProductType())
                        .child(bookingInformation.getRoomFlatNo() + user.getUid()).child("bookStatus");
                databaseReference2.setValue("Book Now");

            }
        });

    }

    public void removeAt(int position) {
        bookingInformationArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, bookingInformationArrayList.size());
    }

    @Override
    public int getItemCount() {
        return bookingInformationArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView customerName,custmerAge,unitNo,customerPhone,bookingstatus,stayHere,houseName;
        ImageView bookingIcon,callIcon,removeIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            customerName = itemView.findViewById(R.id.customerName);
            custmerAge = itemView.findViewById(R.id.customerAge);
            customerPhone = itemView.findViewById(R.id.customerPhone);
            unitNo = itemView.findViewById(R.id.flatRoomNo);
            bookingstatus = itemView.findViewById(R.id.bookingStatus);
            stayHere = itemView.findViewById(R.id.how_many_time);
            bookingIcon = itemView.findViewById(R.id.bookingIcon);
            callIcon = itemView.findViewById(R.id.callIcon);
            removeIcon = itemView.findViewById(R.id.removeIcon);
            houseName = itemView.findViewById(R.id.houseName);

        }
    }
}
