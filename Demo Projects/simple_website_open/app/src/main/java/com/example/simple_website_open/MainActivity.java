package com.example.simple_website_open;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void opening_website(View view) {
        url=findViewById(R.id.url);
        String urlText = url.getText().toString();
       //Toast.makeText(this, urlText, Toast.LENGTH_SHORT).show();
        // Implicit Intent to open a web page
        Uri webpage = Uri.parse(urlText);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

            startActivity(intent);



    }
}


