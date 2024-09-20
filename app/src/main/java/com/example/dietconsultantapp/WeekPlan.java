package com.example.dietconsultantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class WeekPlan extends AppCompatActivity {

    RelativeLayout plan_man,plan_woman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_plan);

        plan_man = findViewById(R.id.planman);
        plan_woman = findViewById(R.id.planwoman);


        plan_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeekPlan.this,sevendays_manplan.class);
                startActivity(intent);
            }
        });
        plan_woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeekPlan.this,sevendays_womanplan.class);
                startActivity(intent);
            }
        });


    }
}