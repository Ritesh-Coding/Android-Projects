package com.example.bottom_sheet_viewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.order);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //we have open this bottom sheet dialogue box

                Bottom_Sheet_Fragment bottom_sheet_fragment=new Bottom_Sheet_Fragment();
                bottom_sheet_fragment.show(getSupportFragmentManager(),bottom_sheet_fragment.getTag());
            }
        });
    }
}