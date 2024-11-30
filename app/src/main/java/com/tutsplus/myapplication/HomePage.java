package com.tutsplus.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tutsplus.myapplication.databinding.ActivityHomePageBinding;

public class HomePage extends AppCompatActivity {

    private static final String TAG = "HomePage";
    ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_home_page);

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if(getIntent().hasExtra("username")){
            binding.TextViewUser.setText("Welcome " + getIntent().getStringExtra("username"));
        }

        if(getIntent().hasExtra("password")){
            binding.TextViewPassword.setText("Password: " + getIntent().getStringExtra("password"));
        }


    }
}