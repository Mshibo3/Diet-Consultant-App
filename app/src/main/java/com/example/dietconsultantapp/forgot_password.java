package com.example.dietconsultantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {

        private EditText emailText;
        private Button buttonreset;
        private ProgressBar progressBar;

        FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        emailText =(EditText) findViewById(R.id.emailEditText);
        buttonreset = (Button) findViewById(R.id.buttonreset);
        progressBar = (ProgressBar) findViewById(R.id.prograssBar);

        Auth = FirebaseAuth.getInstance();

        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    resetePassword();

            }
        });
    }

    private void resetePassword(){
        String email= emailText.getText().toString().trim();

        if (email.isEmpty()){
            emailText.setError("Email is required");
            emailText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Please provide a valid email address");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        Auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(forgot_password.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(forgot_password.this, "Try again, something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}