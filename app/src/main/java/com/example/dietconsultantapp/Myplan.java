package com.example.dietconsultantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Myplan extends AppCompatActivity {

    public  BottomNavigationView bottomNavigationView;

    private RecyclerView chatsRV;
    private EditText userMsgEdt;
    private FloatingActionButton sendMsgFAB;
    private final  String BOT_KEY= "bot";
    private final String USER_KEY= "user";
    private ArrayList<ChatsModel>chatsModelArrayList;
    private ChatRVAdaptar chatRVAdaptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myplan);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        chatsRV = (RecyclerView) findViewById(R.id.recyclerView);
        userMsgEdt = (EditText) findViewById(R.id.idEdtMassage);
        sendMsgFAB = (FloatingActionButton) findViewById(R.id.idFABSend);
        chatsModelArrayList = new ArrayList<>();
        chatRVAdaptar = new ChatRVAdaptar(chatsModelArrayList,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(chatRVAdaptar);

        sendMsgFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userMsgEdt.getText().toString().isEmpty()){
                    Toast.makeText(Myplan.this, "Please enter a message", Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdt.getText().toString());
                userMsgEdt.setText("");

            }
        });


        bottomNavigationView.setSelectedItemId(R.id.myplan);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.myplan:

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

    private void getResponse(String message) {
        chatsModelArrayList.add(new ChatsModel(message,USER_KEY));
        chatRVAdaptar.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=167948&key=2VqUR5IkJJPE09RL&uid=uid&msg="+message;

       //String BASE_URL = "http://api.brainshop.ai/";

       RequestQueue queue = Volley.newRequestQueue(Myplan.this);

      /* Retrofit retrofit =new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
       Call<MsgModel> call =retrofitAPI.getMessage(url);*/

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String botResponse = response.getString("cnt");
                    chatsModelArrayList.add(new ChatsModel(botResponse, BOT_KEY));
                    chatRVAdaptar.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    chatsModelArrayList.add(new ChatsModel("NO RESPONSE", BOT_KEY));
                    chatRVAdaptar.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                chatsModelArrayList.add(new ChatsModel("Sorry no response found",BOT_KEY));
                Toast.makeText(Myplan.this,"No response from the bot", Toast.LENGTH_SHORT).show();
            }
    });
        queue.add(jsonObjectRequest);


        /*call.enqueue(new Callback<MsgModel>() {
            @Override
            public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                if (response.isSuccessful()){
                    MsgModel model = response.body();
                    chatsModelArrayList.add(new ChatsModel(model.getCnt(),BOT_KEY));
                    chatRVAdaptar.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<MsgModel> call, Throwable t) {
                chatsModelArrayList.add(new ChatsModel("Please rewrite your question",BOT_KEY));
                chatRVAdaptar.notifyDataSetChanged();
            }
        });*/

    }
}