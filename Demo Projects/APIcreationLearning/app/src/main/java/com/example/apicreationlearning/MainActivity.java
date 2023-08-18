package com.example.apicreationlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    APIINTERFACE ApiInterface;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler_view);
        ApiInterface=RetrofitInstance.getRetrofit().create(APIINTERFACE.class);//variable define and declare

        //Function call
         ApiInterface.getposts().enqueue(new Callback<List<PostPOJO>>() {  //ENQUEUE USED TO GENERATE REQUEST
             @Override
             public void onResponse(Call<List<PostPOJO>> call, Response<List<PostPOJO>> response) {
                 if (response.body().size()>0)
                 {

                     List<PostPOJO> postList=response.body();
                     Adapter adapter1=new Adapter(MainActivity.this,postList);

                     LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
                     recyclerView.setLayoutManager(linearLayoutManager);
                     recyclerView.setAdapter(adapter1);//here adapter class and listview class is left.. copy it from the source code given by NeatROOTS IN WHAT IS API LEC



                     Toast.makeText(MainActivity.this, "List is not empty", Toast.LENGTH_SHORT).show();
                 }
                 else
                 {
                     Toast.makeText(MainActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(Call<List<PostPOJO>> call, Throwable t) {
                 Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
             }
         });
    }
}