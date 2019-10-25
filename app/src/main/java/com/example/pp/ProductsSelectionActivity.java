package com.example.pp;

import android.app.Notification;
import android.app.NotificationManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pp.models.Shop;
import com.example.pp.models.Store;
import com.example.pp.models.UserInMemoryStore;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsSelectionActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private ShopAdapter mAdapter;
    private final Store<Shop> store = new UserInMemoryStore();
    RecyclerView recyclerView;
    Button btn_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_selection);
        btn_order = (Button) findViewById(R.id.button_send);
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(ProductsSelectionActivity.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Полісся-Продукт")
                                .setContentText("Ваше замовлення відправлено на обробку. Дякуємо за замовлення!")
                                .setLargeIcon(bitmap);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Замовлення On-Line в розробці...", Toast.LENGTH_SHORT);
                toast.show();


            }
        });
    }
}
