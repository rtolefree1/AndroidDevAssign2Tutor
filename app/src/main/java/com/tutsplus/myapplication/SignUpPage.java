package com.tutsplus.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tutsplus.myapplication.databinding.ActivitySignUpPageBinding;

public class SignUpPage extends AppCompatActivity {

    ActivitySignUpPageBinding binding;
    private static final String TAG = "SignUpPage";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySignUpPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setContentView(R.layout.activity_sign_up_page);
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void clearFields(View view) {
        binding.emailEditView.setText("");
        binding.SignUpPasswordView.setText("");
    }


    public void signUp(View view) {
        Log.d(TAG, "signUp: email: " + binding.emailEditView.getText().toString() + ", password: " + binding.SignUpPasswordView.getText().toString());

        mAuth.createUserWithEmailAndPassword(binding.emailEditView.getText().toString(), binding.SignUpPasswordView.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(SignUpPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Log.d("RegisterActivity", "createUserWithEmail:success");
                      //  updateUI(user);
                    } else {
                        Log.w("RegisterActivity", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUpPage.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        //updateUI(null);
                    }
                });
        clearFields(null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}