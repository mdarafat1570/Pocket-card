package com.example.pocket_card.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocket_card.databinding.ActivityCategoryBinding;

public class CategoryActivity extends AppCompatActivity {
ActivityCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.CategoryToolbar.toolTxt.setText("নতুন তথ্য সংরক্ষণ করুন");

        binding.backBtn.setOnClickListener(v -> {
            finish();
        });


        binding.passport.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, ScannerActivity.class);
            startActivity(intent);

        });
        binding.bioData.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, ScannerActivity.class);
            startActivity(intent);
        });
        binding.birthCertificate.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, ScannerActivity.class);
            startActivity(intent);
        });
        binding.certificate.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, ScannerActivity.class);
            startActivity(intent);
        });
        binding.drivingLicence.setOnClickListener(v -> {
            startActivity(new Intent(CategoryActivity.this, ScannerActivity.class));
        });
        binding.nidCard.setOnClickListener(v -> {
            startActivity(new Intent(CategoryActivity.this, ScannerActivity.class));
        });
        binding.others.setOnClickListener(v -> {
            startActivity(new Intent(CategoryActivity.this, ScannerActivity.class));
        });
    }
}