package com.example.learn_image_picker_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    ImageView cover,Profile;
    FloatingActionButton fab,fab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cover=findViewById(R.id.cover_image);
        fab=findViewById(R.id.floatingActionButton);

        Profile=findViewById(R.id.profile_image);
        fab2=findViewById(R.id.floatingActionButton2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(MainActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        //you can visit the website for making more features like SquareCrop
                        .start(10);

            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(MainActivity.this)

                        .start(20);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10)
        {
            Uri uri =data.getData(); //by these we get the data into the URI from the location of the gallery
            cover.setImageURI(uri);  //here we set the image
        }
        else
        {
            Uri uri =data.getData(); //by these we get the data into the URI from the location of the gallery
            Profile.setImageURI(uri);  //here we set the image
        }

    }
}