package com.example.dietconsultantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dietconsultantapp.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    ActivityHomeBinding binding;
   public BottomNavigationView bottomNavigationView;

   TextView text_username ;

   RelativeLayout homeGrid;

    private FirebaseAuth mAuth;
    //FirebaseDatabase database;
   // DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        text_username = (TextView) findViewById(R.id.text_name);

        bottomNavigationView =  findViewById(R.id.bottomNavigationView);

        homeGrid = (RelativeLayout) findViewById(R.id.homeGrid);

        setSingleEvent(homeGrid);


        mAuth = FirebaseAuth.getInstance();


        FirebaseDatabase.getInstance().getReference().child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String fullname = snapshot.child("fullname").getValue(String.class);
                //assigning
                text_username.setText(fullname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.myplan:
                        startActivity(new Intent(getApplicationContext(),Myplan.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.support:
                        startActivity(new Intent(getApplicationContext(),Support.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });




    }

    private void setSingleEvent(RelativeLayout homeGrid) {
        //Loop
        for(int i = 0; i < homeGrid.getChildCount();i++){
            //Linearlayout Children
            LinearLayout linearLayout = (LinearLayout) homeGrid.getChildAt(i);
            final int finalI = i;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI == 0) //  Open The BMI calculator
                    {
                        Intent intent = new Intent(Home.this,WeekPlan.class);
                        startActivity(intent);

                    }
                    else if(finalI ==1) // Opens the meal plans for seven days
                    {

                        Intent intent = new Intent(Home.this, Tips.class);
                        startActivity(intent);
                        }
                    else if (finalI ==2) // opens the videos screen
                    {
                        Intent intent = new Intent(Home.this,Calculator.class);
                        startActivity(intent);
                    }
                    else if (finalI==3) // opens diet tips
                    {
                        Intent intent = new Intent(Home.this, Videos.class);
                        startActivity(intent);
                    }

                }
            });
        }

    }
}