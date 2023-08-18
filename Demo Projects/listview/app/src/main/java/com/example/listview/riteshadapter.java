package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class riteshadapter extends ArrayAdapter<String> {

    private String arr[];
    private Context context;
    public riteshadapter(@NonNull Context context,int resource, String[] arr) {
        super(context, resource,arr);
        this.context=context;
        this.arr=arr;
    }
    //in order to make custom adapter  this methods are going to be override
    @Nullable
    @Override
    public String getItem(int position)
    {
        return arr[position];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.myriteshlayout,parent,false);
        TextView t=convertView.findViewById(R.id.textView);
        t.setText(getItem(position));
        //method to show the listener on custom adapter and before returning the view , add Click listener means start the position as zero on mobile view

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked on "+position, Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }
}
