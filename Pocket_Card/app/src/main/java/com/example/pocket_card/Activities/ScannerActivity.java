package com.example.pocket_card.Activities;

import static com.example.pocket_card.Utilities.AlartBox.Invalid_Input_Field;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pocket_card.databinding.ActivityScannerBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ScannerActivity extends AppCompatActivity {


    ActivityScannerBinding binding;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    String currentUser, imagUrl;
    StorageReference storageReference;
    Uri imageUri;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.scannerToolbar.toolTxt.setText("ডকুমেন্ট এর ছবি তুলুন");

        binding.backBtn.setOnClickListener(v -> {
            finish();
        });



        progressDialog = new ProgressDialog(ScannerActivity.this);
        progressDialog.setMessage("দয়া করে অপেক্ষা করুন । \nআপনার কাজটি প্রক্রিয়াধীন রয়েছে ");
        progressDialog.create();


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        storageReference = FirebaseStorage.getInstance().getReference("Images");

        firebaseUser = firebaseAuth.getCurrentUser();
        currentUser = firebaseUser.getUid();



        binding.reTake.setOnClickListener(v -> {
            ImagePicker.with(ScannerActivity.this)
                    .cameraOnly()
                    .crop()
                    .compress(512)
                    .maxResultSize(1080, 1080)
                    .start(121);

        });

        binding.upload.setOnClickListener(v -> {

            if (binding.previewImg.getDrawable() == null){

                Invalid_Input_Field(ScannerActivity.this, "দয়া করে ডকুমেন্ট এর ছবি তুলুন");

            }else {

                progressDialog.show();

                storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imagUrl = String.valueOf(uri);

                                    uploadPhoto();
                                }
                            });
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressDialog.dismiss();
                        Invalid_Input_Field(ScannerActivity.this, e.getMessage().toString());
                    }
                });
            }



        });
    }

    private void uploadPhoto() {

        long dt = System.currentTimeMillis();
        databaseReference.child(currentUser).child("Image").child(""+dt).setValue(imagUrl);

        progressDialog.dismiss();

        startActivity(new Intent(ScannerActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (requestCode ==121 && resultCode == RESULT_OK && data != null){

           imageUri = data.getData();
           binding.previewImg.setImageURI(imageUri);

           binding.reTake.setText("Re-take");
       }
    }
}