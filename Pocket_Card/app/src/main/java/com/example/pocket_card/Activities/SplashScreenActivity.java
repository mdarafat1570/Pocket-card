package com.example.pocket_card.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocket_card.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {

    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GoNextPage();
            }
        },2900);

    }

    private void GoNextPage() {

        if (firebaseUser!=null){
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(SplashScreenActivity.this, FirstActivity.class));
            finish();
        }

    }
}