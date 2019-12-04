package com.example.goolemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.goolemap.Authorization.Login;

public class Welcome extends AppCompatActivity {


    private TextView textView;
    Animation upDawn,downUp;
    private RelativeLayout layout1;
    private ImageView mCoverImage;


    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            layout1.setVisibility(View.VISIBLE);
            mCoverImage.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        textView = findViewById(R.id.footer);
        layout1 = findViewById(R.id.relative1Id);
        handler.postDelayed(runnable, 3000);

        mCoverImage = findViewById(R.id.imageViewId);
        upDawn = AnimationUtils.loadAnimation(this,R.anim.up_down_animation);
        downUp = AnimationUtils.loadAnimation(this,R.anim.down_up);

        mCoverImage.setAnimation(upDawn);
        textView.setAnimation(downUp);

        startActivity(new Intent(Welcome.this, MainActivity.class));
        finish();
    }
}
