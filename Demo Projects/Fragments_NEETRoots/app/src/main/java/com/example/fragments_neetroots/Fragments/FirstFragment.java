package com.example.fragments_neetroots.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragments_neetroots.R;
import com.example.fragments_neetroots.newActivity;

public class FirstFragment extends Fragment {
     TextView tv;
    public FirstFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false); //here we infalte the xml instead
                                                                                            //of find view by Id


        tv=view.findViewById(R.id.textview1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), newActivity.class);  //here we use getContext
                Toast.makeText(getContext(), "Here we also apply click list on First Fragment", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


        return view;
    }
}