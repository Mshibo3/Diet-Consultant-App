package com.example.dietconsultantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Calculator extends AppCompatActivity {

    Button calculatebmi;


    TextView bmi_height;
    TextView bmi_age,bmi_weight;
    ImageView incre_age,incre_weight,decre_age,decre_weight;
    SeekBar seekBar_height;
    SeekBar seekBar_weight;
    RelativeLayout bmi_male,bmi_female;

    int max = 500;

    int intWeight;
    int currentprogress;
    String mintprogress="1";
    String typeofuser="0";
    String weight2="1";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        getSupportActionBar().hide();
        calculatebmi = findViewById(R.id.calculatebmi);

       // bmi_age = findViewById(R.id.currentAge);
        bmi_height = findViewById(R.id.currentheight);
        bmi_weight = findViewById(R.id.currentWeight);
       // incre_age = findViewById(R.id.incrementage);
      //  decre_age = findViewById(R.id.decrementage);
        incre_weight = findViewById(R.id.incrementweight);
        decre_weight = findViewById(R.id.decrementweight);
        seekBar_height = findViewById(R.id.seekbarforheight);
        seekBar_weight = findViewById(R.id.seekbarforW);

        bmi_male = findViewById(R.id.male);
        bmi_female = findViewById(R.id.female);



        bmi_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmi_male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.focus));
                bmi_female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.notfocus));
                typeofuser="Male";


            }
        });

        bmi_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmi_female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.focus));
                bmi_male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.notfocus));
                typeofuser="Female";
                
            }
        });
        
        seekBar_height.setMax(300);
        seekBar_height.setProgress(0);
        seekBar_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    currentprogress=i;
                    mintprogress=String.valueOf(currentprogress);
                    bmi_height.setText(mintprogress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBar_weight.setMax(90);
        seekBar_weight.setProgress(0);
        seekBar_weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                intWeight=i;
                weight2=String.valueOf(intWeight);
                bmi_weight.setText(weight2);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        /* incre_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intage=intage+1;
                age2=String.valueOf(intage);
                bmi_age.setText(age2);
                }
        });*/
       /* decre_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intage=intage-1;
                age2=String.valueOf(intage);
                bmi_age.setText(age2);
            }
        });*/

        /*incre_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intWeight=intWeight+1;
                weight2=String.valueOf(intWeight);
                bmi_weight.setText(weight2);
            }
        });
        decre_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intWeight=intWeight-1;
                weight2=String.valueOf(intWeight);
                bmi_weight.setText(weight2);
            }
        });*/

        calculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(typeofuser.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "PLEASE SELECT YOUR GENDER", Toast.LENGTH_SHORT).show();
                }
                else if(mintprogress.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "PLEASE SELECT YOUR HEIGHT", Toast.LENGTH_SHORT).show();

                }

                else if (weight2.equals("0")){
                    Toast.makeText(getApplicationContext(), "PLEASE SELECT YOUR WEIGHT", Toast.LENGTH_SHORT).show();
                }

                else {
                    Intent intent = new Intent(Calculator.this,bmicalculator.class);
                    intent.putExtra("gender",typeofuser);
                    intent.putExtra("height",mintprogress);
                    intent.putExtra("weight",weight2);
                   // intent.putExtra("age",age2);
                    startActivity(intent);

                }

            }
        });





        
    }
}