package com.lorenzotribuiani.tailorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        Intent intent = getIntent();
        boolean logged = intent.getBooleanExtra("isLogged", false);

//        TextView text = findViewById(R.id.textView);
//        if(logged){
//            text.setText("Logged");
//        } else {
//            text.setText("Not Logged");
//        }
    }
}
