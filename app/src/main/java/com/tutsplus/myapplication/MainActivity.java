package com.tutsplus.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tutsplus.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameEditText = findViewById(R.id.UserNameEditTextView);
        passwordEditText = findViewById(R.id.PasswordEditTextView);
        binding.loginButtonView.setOnClickListener(this::login);


    }

    public void login(View view) {

        if (usernameEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("username", usernameEditText.getText().toString());
        intent.putExtra("password", passwordEditText.getText().toString());
        clearFields(null);
        startActivity(intent);
        Toast.makeText(this, "Login button clicked", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Login button clicked, username:" + usernameEditText.getText().toString() + ", password:" + passwordEditText.getText().toString());
    }

    public void clearFields(View view) {
        usernameEditText.setText("");
        passwordEditText.setText("");

    }
}