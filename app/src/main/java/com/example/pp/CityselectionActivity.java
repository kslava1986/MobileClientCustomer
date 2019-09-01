package com.example.pp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.pp.models.Shop;
import com.example.pp.models.Store;
import com.example.pp.models.UserInMemoryStore;

public class CityselectionActivity extends AppCompatActivity {

    String s1 = "Київська,88       (0988467236)";
    String s2 = "Бульвар Польський 13а (0983995114)";
    String s3 = "Хлібна,39/19          (0971146295)";
    String s4 = "М-н Станишівський,3/2 (0986408197)";
    String s5 = "пр-т Миру,61/2        (0971084756)";
    String s6 = "Вільський Шлях,14     (0985660818)";
    String s7 = "Івана Мазепи,5        (0985660818)";

    String[] shops = {s1,s2,s3,s4,s5,s6,s7};
    private final Store<Shop> store = new UserInMemoryStore();

    //String[] shops = { "Київська,88", "Бульвар Польський,13а", "Хлібна,39/19", "Станишівський,3/2", "пр.Миру,61/2", "Вільський Шлях,14"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cityselection);


// находим список
        ListView lvMain = (ListView) findViewById(R.id.shopsListView);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, shops);

        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);

        //додати onClickListener to adapter
        //call another screen

    }
    // у новий екран
    public void dial(View v) {
        String toDial="tel:0638237775";
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
    }
}
