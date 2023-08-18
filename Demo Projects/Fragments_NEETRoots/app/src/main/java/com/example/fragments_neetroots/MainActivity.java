package com.example.fragments_neetroots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.fragments_neetroots.Fragments.FirstFragment;
import com.example.fragments_neetroots.Fragments.SecondFragment;

public class MainActivity extends AppCompatActivity {
        Button first,second;
        LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first=findViewById(R.id.first);
        second=findViewById(R.id.second);
        layout=findViewById(R.id.linearLayout2);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstFragment firstFragment=new FirstFragment();
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.linearLayout2,firstFragment);
                fragmentTransaction.commit(); //to start the transaction
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondFragment Fragment=new SecondFragment();
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.linearLayout2,Fragment);
                fragmentTransaction.commit(); //to start the transaction
            }
        });
    }
}
