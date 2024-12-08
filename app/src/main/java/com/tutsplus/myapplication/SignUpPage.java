package com.tutsplus.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tutsplus.myapplication.databinding.ActivitySignUpPageBinding;

public class SignUpPage  extends AppCompatActivity implements View.OnClickListener {

    ActivitySignUpPageBinding binding;
    private static final String TAG = "SignUpPage";
    private FirebaseAuth mAuth;
    private Button signUpButton;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySignUpPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
    }

    public void clearFields() {
        binding.emailEditView.setText("");
        binding.SignUpPasswordView.setText("");
    }


//    binding.signUpButtonView.setOnClickListener(new View.OnClickListener() {
//
//        @Override
//        public void onClick (View view2){
//            Log.d(TAG, "signUp: email: " + binding.emailEditView.getText().toString() +
//                    ", password: " + binding.SignUpPasswordView.getText().toString());
//        }
//
//    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "signUp: email: " + binding.emailEditView.getText().toString() +
                ", password: " + binding.SignUpPasswordView.getText().toString());

                String email = binding.emailEditView.getText().toString();
                String password = binding.SignUpPasswordView.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
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
        clearFields();
        Intent intent = new Intent(SignUpPage.this, MainActivity.class);
        startActivity(intent);

    }


    public void onClickPassword(View v) {
        if(isPasswordVisible){
            binding.SignUpPasswordView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isPasswordVisible = false;
        }else{
            binding.SignUpPasswordView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            isPasswordVisible = true;
        }

    }

}