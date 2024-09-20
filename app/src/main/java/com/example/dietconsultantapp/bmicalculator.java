package com.example.dietconsultantapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bmicalculator extends AppCompatActivity {


    Button mrecalculatebmi;

    TextView bmi_display, bmi_catagory,bmi_gender;
    Intent intent;
    ImageView imageView;
    String bmi;
    Float intbmi;
    String  weight,height;
    Float intheight, intweight;

    RelativeLayout mbackgroud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getSupportActionBar().setTitle("Results");
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        intent = getIntent();

        bmi_display = findViewById(R.id.bmidisplay);
        bmi_catagory = findViewById(R.id.bmicatagory);
        bmi_gender = findViewById(R.id.genderdisplay);
        mbackgroud = findViewById(R.id.contentlayout);
        imageView = findViewById(R.id.imageview);

        height=intent.getStringExtra("height");
        weight=intent.getStringExtra("weight");

        intheight= Float.parseFloat(height);
        intweight= Float.parseFloat(weight);

        intheight= intheight/100;

        intbmi=intweight/(intheight*intheight);

        bmi = Float.toString(intbmi);

        if (intbmi<16){
            bmi_catagory.setText("Too much Under Weight, Chat to the chatbot and make an appointment. You have to see the Doctor");
            mbackgroud.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.crosss);
        }
        else if (intbmi<16.9 && intbmi > 16){
            bmi_catagory.setText("Under Weight, You need to gain weight, Chat to the chatbot for more options");
            mbackgroud.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.warning);
        }
        else if (intbmi<18.4 && intbmi > 17){
            bmi_catagory.setText("Partially under Weight, check meal plans for gaining weight");
            mbackgroud.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.crosss);
        }
        else if (intbmi<25 && intbmi>18.4){
            bmi_catagory.setText("BMI Normal, Eat Healthy and Maintain your Body");
           // mbackgroud.setBackgroundColor(Color.YELLOW);
            imageView.setImageResource(R.drawable.ok);
        }
        else if (intbmi<29.4 && intbmi> 18.4){
            bmi_catagory.setText("Over Weight, You need to Exercise and loose weight. Normal BMI range is 18.50 - 24.99");
            mbackgroud.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.crosss);
        }
        else {
            bmi_catagory.setText("Your are obese You need to Start Diet Exercise and loose weight. Normal BMI range is 18.50 - 24.99");
            mbackgroud.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.crosss);
        }


        bmi_gender.setText(intent.getStringExtra("gender"));
        bmi_display.setText(bmi)
        ;





        mrecalculatebmi = findViewById(R.id.recalculatebmi);

        mrecalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bmicalculator.this,Calculator.class);
                startActivity(intent);
                finish();
            }
        });
    }
}