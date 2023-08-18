package com.example.recycler_view_neatroots.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler_view_neatroots.Models.ReceipieModel;
import com.example.recycler_view_neatroots.R;

import java.util.ArrayList;

public class ReciepeAdapter extends RecyclerView.Adapter<ReciepeAdapter.viewHolder> {
    ArrayList<ReceipieModel> list;   //RECIPEMODEL IS DATA TYPE
    Context context;

    public ReciepeAdapter(ArrayList<ReceipieModel> list, Context context) {  //in main activity it is used
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //WHAT THE LAYOUT WE MADE WE SIMPLY INFLUATE IT
        View view= LayoutInflater.from(context).inflate(R.layout.sample_recycler_view,parent,false);//here we write parent to maintain size constant in all the page

        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
              ReceipieModel model=list.get(position);          //HOW TO SET THE DATA on the scren i.e image should be placed according to the positiion 0,1,2
                holder.imageView.setImageResource(model.getPic());
                holder.textView.setText(model.getText());

//                holder.imageView.setOnClickListener(new View.OnClickListener() {    //By these we made the functionality that by clicking on all the images we show the text message
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(context, "Item is clicked", Toast.LENGTH_SHORT).show();
//                    }
//                });


        //now by the use of switch case according to the image we made functionality by clicing on the image
        //here we define combinely also with the help of the mainActivity.java
//        switch (position)
//        {
//            case 0:
//                holder.imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(context, "Imaeg 1 is clicked", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                holder.textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(context, "Text 1 is clicked ", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                break;
//            default:
//
//        }
    }


    @Override
    public int getItemCount() {
        return list.size();  //size of recycler view is upto the items on the list available on the screen

    }

    public class viewHolder extends RecyclerView.ViewHolder  //here we get the image
    {
        ImageView imageView;
        TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
        }

    }


}
