package com.example.pocket_card.Activities;

import static com.example.pocket_card.Utilities.AlartBox.Invalid_Input_Field;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pocket_card.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;
    String email, pass;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("অপেক্ষা করুন...");
        progressDialog.create();


        firebaseAuth = FirebaseAuth.getInstance();

        binding.loging.setOnClickListener(v -> {

            email =binding.email.getText().toString();
            pass =binding.pass.getText().toString();

            if (email.equals("")){
                Invalid_Input_Field(LoginActivity.this, "Email field can not be Empty");
            } else if (pass.equals("")){
                Invalid_Input_Field(LoginActivity.this, "Password field can not be Empty");
            }else {
                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Invalid_Input_Field(LoginActivity.this, e.getMessage().toString());
                    }
                });
            }
        });


        binding.creatNewAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }
}