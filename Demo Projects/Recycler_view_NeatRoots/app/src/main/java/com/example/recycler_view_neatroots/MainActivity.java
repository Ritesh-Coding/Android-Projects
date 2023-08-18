package com.example.recycler_view_neatroots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.recycler_view_neatroots.Adapter.ReciepeAdapter;
import com.example.recycler_view_neatroots.Models.ReceipieModel;
import com.example.recycler_view_neatroots.classes_.RecyclerItemClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.RECYCLER_VIEW);
        ArrayList<ReceipieModel> list=new ArrayList();

        list.add(new ReceipieModel(R.drawable.img,"Burger"));
        list.add(new ReceipieModel(R.drawable.img,"Khaman"));
        list.add(new ReceipieModel(R.drawable.img,"Dhokla"));
        list.add(new ReceipieModel(R.drawable.img,"Dabeli"));
        list.add(new ReceipieModel(R.drawable.img,"Vadapav"));
        list.add(new ReceipieModel(R.drawable.img,"Pizza"));
        list.add(new ReceipieModel(R.drawable.img,"Icecream"));
        list.add(new ReceipieModel(R.drawable.img,"Shikanji"));
        list.add(new ReceipieModel(R.drawable.img,"Pulav"));
        ReciepeAdapter adapter=new ReciepeAdapter(list,this);
        recyclerView.setAdapter(adapter); //by these we ser the image on the screene

        //in which format the image are to be visible are decided by Grid,Staggered or LinaerLayoutManager
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);//by default it is vertical
        recyclerView.setLayoutManager(layoutManager);

        //HERE WE AddONITEMTOUCHLISTNER   //here we use this in mainActivity hence it's priority increases and the code writeen in RecipieaDPATER WILL NOT RUN
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener
                (this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch(position)
                        {
                            case 0:
                                Intent intent=new Intent(MainActivity.this,ScrollingActivity.class);
                                startActivity(intent);
                              break;
                            case 1:
                                Toast.makeText(MainActivity.this, "Item 2 is Clicked", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "This is the default case ", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        switch(position)
                        {
                            case 0:
                                Toast.makeText(MainActivity.this, "Get 10 percent discount", Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                Toast.makeText(MainActivity.this, "This is the long item click listner ", Toast.LENGTH_SHORT).show();

                        }
                    }
                }));



//        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,2);  //span count shows the span in gallery to attch photos in cloumn
//        recyclerView.setLayoutManager(gridLayoutManager);

//        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);//we can also define different shape in staggeredLayoutManager
    }
}