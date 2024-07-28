package com.example.pocket_card.Activities;

import static com.example.pocket_card.Utilities.AlartBox.Invalid_Input_Field;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pocket_card.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    String name, phone, email, pass, confirm_pass, currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());



        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.create();


        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("User");




        binding.createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name =binding.name.getText().toString();
                phone =binding.phone.getText().toString();
                email =binding.email.getText().toString();
                pass =binding.pass.getText().toString();
                confirm_pass =binding.confirmPass.getText().toString();

                if (name.equals("")){
                    Invalid_Input_Field(RegisterActivity.this, "Name field can not be Empty");
                } else if (phone.length()!=11){
                    Invalid_Input_Field(RegisterActivity.this, "Phone number must be 11 digit");
                } else if (email.equals("")){
                    Invalid_Input_Field(RegisterActivity.this, "Email field can not be Empty");
                } else if (pass.equals("")){
                    Invalid_Input_Field(RegisterActivity.this, "Password field can not be Empty");
                } else if (confirm_pass.equals("")) {
                    Invalid_Input_Field(RegisterActivity.this, "Confirm Password field can not be Empty");
                } else if (!pass.equals(confirm_pass)) {
                    Invalid_Input_Field(RegisterActivity.this, "Confirm Password does not match");
                }else{
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                firebaseUser = firebaseAuth.getCurrentUser();
                                currentUser = firebaseUser.getUid();
                                long date = System.currentTimeMillis();

                                HashMap<String, Object> map = new HashMap<>();
                                map.put("Name", name);
                                map.put("E-mail", email);
                                map.put("Phone", phone);
                                map.put("Password", pass);
                                map.put("User-ID", currentUser);
                                map.put("Created At", date);

                                databaseReference.child(currentUser).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            progressDialog.dismiss();
                                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                            finish();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Invalid_Input_Field(RegisterActivity.this, e.getMessage().toString());
                                    }
                                });


                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Invalid_Input_Field(RegisterActivity.this, e.getMessage().toString());
                        }
                    });
                }


            }
        });



        binding.alreadyHaveAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}