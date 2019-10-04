package com.example.pp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       // btnStart = (Button) findViewById(R.id.button_start);
        ImageButton buttonCall = findViewById(R.id.callButton);
        //btnStart.setOnClickListener(this);
        buttonCall.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CityselectionActivity.class);
        switch (v.getId()){
            case R.id.callButton:
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
