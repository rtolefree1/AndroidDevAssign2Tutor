package com.tutsplus.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tutsplus.myapplication.databinding.ActivityMainBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (usernameEditText.getText().toString().isEmpty()) {
            emptyUserDialogBox();
            return;
        }

        if(!isValidEmail(usernameEditText.getText().toString())){
            // if invalid email format, then return
            inValidUserDialogBox();
            return;

        }
        if(passwordEditText.getText().toString().isEmpty()){
            emptyPasswordDialogBox();
            return;
        }

        if(!isValidPassword(passwordEditText.getText().toString())){
            // if invalid password, then return
//            Toast.makeText(this, "Please enter a valid password\n" +
//                    "Password must be at least 4 characters \n" +
//                    "long and contain at least one uppercase \n" +
//                    "letter, one digit, and one special character", Toast.LENGTH_LONG).show();
            inValidPasswordDialogBox();
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

    public boolean isValidPassword(String password) {

        //  ^: Start of the string.
        //  (?=.*[A-Z]): At least one uppercase letter.
        //  (?=.*\\d): At least one digit.
        //  (?=.*[@$!%*?&]): At least one special character.
        //  [A-Za-z\\d@$!%*?&]: Allowed characters.
        //  {4,}: Minimum length of 4 characters.
        //  $: End of the string.

        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,}$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);

        // Match the password with the pattern
        Matcher matcher = pattern.matcher(password);

        // Return true if the password matches the pattern, false otherwise
        return matcher.matches();


    }

    private void inValidPasswordDialogBox() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the title and message of the dialog
        builder.setTitle("Invalid Password");
        builder.setMessage("Please enter a valid password\n" +
                "Password must be at least 4 characters \n" +
                "long and contain at least one uppercase \n" +
                "letter, one digit, and one special character");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void inValidUserDialogBox() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the title and message of the dialog
        builder.setTitle("Invalid Email");
        builder.setMessage("Please enter a valid email address\n" +
                "Example format: john.catron@example-pet-store.com");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public boolean isValidEmail(String email){
//        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void emptyPasswordDialogBox() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the title and message of the dialog
        builder.setTitle("Invalid Password - Empty");
        builder.setMessage("Please enter a valid password\n" +
                "Password must be at least 4 characters \n" +
                "long and contain at least one uppercase \n" +
                "letter, one digit, and one special character");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void emptyUserDialogBox() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the title and message of the dialog
        builder.setTitle("Invalid Email - Empty");
        builder.setMessage("Please enter a valid email address\n" +
                "Example format: john.catron@example-pet-store.com");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}