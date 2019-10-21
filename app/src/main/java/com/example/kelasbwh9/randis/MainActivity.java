package com.example.kelasbwh9.randis;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView iv;;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

           iv = findViewById(R.id.imageView);
       }

       @Override
    public void onClick(View view) {
           Intent intent = new Intent(MainActivity.this, CariActivity.class);
           startActivity(intent);

       }


    }




