package com.example.goolemap.Authorization;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goolemap.Empty;
import com.example.goolemap.MainActivity;
import com.example.goolemap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {


    private EditText mEmail, mPassword;

    private ImageView mCoverImage;

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog1;
    private TextView textView;
    Animation upDawn,downUp;
    private RelativeLayout layout1;


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
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.emailForLogin);
        mPassword = findViewById(R.id.passwordForLogin);
        mCoverImage = findViewById(R.id.imageViewId);
        mAuth = FirebaseAuth.getInstance();

        textView = findViewById(R.id.footer);
        layout1 = findViewById(R.id.relative1Id);
        progressDialog1 = new ProgressDialog(this);
        handler.postDelayed(runnable, 3000);

        upDawn = AnimationUtils.loadAnimation(this,R.anim.up_down_animation);
        downUp = AnimationUtils.loadAnimation(this,R.anim.down_up);

        mCoverImage.setAnimation(upDawn);
        textView.setAnimation(downUp);



    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser!=null)
        {
            startActivity(new Intent(Login.this, Empty.class));
            progressDialog1.dismiss();
            finish();

        }
    }

    public void loginButton(View view) {
        String email = mEmail.getText().toString().trim();
        String pass = mPassword.getText().toString();

        if (validate() == true) {
            progressDialog1.setMessage("Try to Login");
            progressDialog1.setTitle("Processing...");
            progressDialog1.show();
            login(email, pass);
        } else {
            validate();
        }
    }


    public boolean validate() {
        boolean valid = true;
        String email = mEmail.getText().toString().trim();
        String pass = mPassword.getText().toString();

        if (email.isEmpty() | !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("enter a valid email address");
            return false;
        } else {
            mEmail.setError(null);
        }

        if (pass.isEmpty()) {
            mPassword.setError("enter your correct password");
            return false;
        } else {
            mPassword.setError(null);
        }
        return valid;
    }

    private void login(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(Login.this, Empty.class));
                            progressDialog1.dismiss();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Check your email or password",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog1.cancel();
                        }

                        // ...
                    }
                });
    }


    public void goToSingin(View view) {
        startActivity(new Intent(this,Registration.class));
        finish();
    }
}
