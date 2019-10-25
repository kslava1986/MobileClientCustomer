package com.example.pp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {
TextView textViewAddressDialog;
TextView textViewTelDialog;
Button btnCallDialog, btnOrderDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        String address = intent.getStringExtra("address");
        final String tel = intent.getStringExtra("tel");
        setContentView(R.layout.activity_dialog);

        textViewAddressDialog = (TextView) findViewById(R.id.address_dialog);
        textViewAddressDialog.setText(address);
        textViewTelDialog = (TextView) findViewById(R.id.telephone_dialog);
        textViewTelDialog.setText(tel);

        btnCallDialog = (Button) findViewById(R.id.button_call_dialog);
        btnOrderDialog = (Button) findViewById(R.id.button_order);
        btnCallDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial(tel);
            }
        });
        btnOrderDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProductsSelectionActivity.class);
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Замовлення On-Line в розробці...", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
    // виклик дзвінка
    public void dial(String tel) {
        Uri uri = Uri.parse(String.format("tel:%s", tel));
        startActivity(new Intent(Intent.ACTION_DIAL, uri));
    }
}
