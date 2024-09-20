package com.example.dietconsultantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    //Option list
    TextInputLayout  textInputLayout ;
   AutoCompleteTextView autoCompleteTextView;


    private FirebaseAuth mAuth;

    private TextView banner;
    private Button   btn_SignUp;
    private EditText editTextfullname, editTextemail,editTextcell, editTextpassword,editTextheight,editTextweight,editTextage;
    private RadioButton rmale, rfemale;
    private RadioGroup ugender;
    private RadioGroup gender;
    private ProgressBar progressBar;
    String Gender;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);




        textInputLayout = findViewById(R.id.activity_drop);
        autoCompleteTextView = findViewById(R.id.drop_items);

       String[] items = {"Low", "Medium", "High"};
       ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(Register.this, R.layout.items_list, items);
       autoCompleteTextView.setAdapter(itemAdapter);


        mAuth = FirebaseAuth.getInstance();

        banner =(TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        btn_SignUp = (Button) findViewById(R.id.btn_SignUp);
        btn_SignUp.setOnClickListener(this);

        editTextfullname = (EditText) findViewById(R.id.user_Fullname);
        editTextcell = (EditText) findViewById(R.id.user_Cell);
        editTextweight = (EditText) findViewById(R.id.user_Weight);
        editTextheight =(EditText) findViewById(R.id.user_Height) ;
        editTextage = (EditText) findViewById(R.id.user_Age);
        editTextemail = (EditText) findViewById(R.id.userEmailAdress);
        editTextpassword = (EditText) findViewById(R.id.user_Password);
        progressBar = (ProgressBar) findViewById(R.id.prograssBar);

        rmale = (RadioButton) findViewById(R.id.user_Male);
        rfemale = (RadioButton) findViewById(R.id.user_Female);
        ugender = (RadioGroup)  findViewById(R.id.user_Gender);

        ugender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                String male = rmale.getText().toString().trim();
                String female =rfemale.getText().toString().trim();

            //gender = ugender.findViewById(i);

                switch (i){
                    case R.id.user_Male:
                        Gender = rmale.getText().toString().trim();
                        break;
                    case R.id.user_Female:
                        Gender = rfemale.getText().toString().trim();
                        break;

                    default:


                }
            }
        });


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btn_SignUp:
                registerUser();
                break;
        }
    }

    private void registerUser(){


        String fullname =editTextfullname.getText().toString().trim();
        String email = editTextemail.getText().toString().trim();
        String cell = editTextcell.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        String height =  editTextheight.getText().toString().trim();
        String weight = editTextweight.getText().toString().trim();
        String age = editTextage.getText().toString().trim();

        String activity = autoCompleteTextView.getText().toString().trim();

       // String male = rmale.getText().toString().trim();
       // String female =rfemale.getText().toString().trim();

      ///  String gender = ugender.toString();


       /* if (rmale.isChecked()){
                ugender.getCheckedRadioButtonId();
        }else {
                ugender.getCheckedRadioButtonId();
        }*/



        if (fullname.isEmpty()){
            editTextfullname.setError(" A full name must be inserted");
            editTextfullname.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextemail.setError("Email cannot be empty");
            editTextemail.requestFocus();
            return;
        }
        if (cell.isEmpty()){
            editTextcell.setError("Cell Phone Cannot be empty");
            editTextcell.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextpassword.setError("Password cannot be empty");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextpassword.setError("PLEASE ENTER A STRONG PASSWORD");
            editTextpassword.requestFocus();
            return;
        }
        if (height.isEmpty()){
            editTextheight.setError("Height cannot be empty");
            editTextheight.requestFocus();
            return;
        }
        if (weight.isEmpty()){
            editTextweight.setError("User Weight cannot be empty");
            editTextweight.requestFocus();
            return;
        }
        if (age.isEmpty()){
            editTextage.setError("Age cannot be empty");
            editTextage.requestFocus();
            return;
        }


        progressBar.setVisibility(View.GONE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(fullname, email, cell, height, weight, age, activity, Gender);
                            FirebaseDatabase.getInstance().getReference().child("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Register.this, "User has been registered successfully, PRESS BACK TO LOGIN", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);

                                                //redirect to login layout
                                            } else {
                                                Toast.makeText(Register.this, "Failed to register, Try again", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);

                                            }
                                        }
                                    });


                        } else {
                            Toast.makeText(Register.this, "Failed to register", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                });

        //  bmi();

    }

  /*  private void bmi() {
        String height =  editTextheight.getText().toString().trim();
        String weight = editTextweight.getText().toString().trim();

        float weightValue = Float.parseFloat(weight);
        float heightValue = Float.parseFloat(height);

        float bmi = weightValue / (heightValue * heightValue);

        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.putExtra("key", bmi);
        startActivity(intent);


    }*/


}
