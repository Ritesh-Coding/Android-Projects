package com.example.sangeetsaregamapa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.karumi.dexter.Dexter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_DEVICE);
    }
}