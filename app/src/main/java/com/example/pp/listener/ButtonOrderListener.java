package com.example.pp.listener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

import com.example.pp.models.to.ProductTO;
import com.example.pp.rest.NetworkService;

import java.util.List;

public class ButtonOrderListener implements View.OnClickListener {
    private Context context;
    private int shopId;
    private List<ProductTO> productTOList;
    private String telCustomer;

    public ButtonOrderListener(Context context, int shopId, List<ProductTO> productTOList, String telCustomer) {
        this.context = context;
        this.shopId = shopId;
        this.productTOList = productTOList;
        this.telCustomer = telCustomer;
    }

    @Override
    public void onClick(View v) {
        NetworkService.getInstance()
                .getJSONApi()
                .sendOrder(shopId,telCustomer,productTOList);


        /*BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(ProductsSelectionActivity.this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Полісся-Продукт")
                        .setContentText("Ваше замовлення відправлено на обробку. Дякуємо за замовлення!")
                        .setLargeIcon(bitmap);

         */

        Toast toast = Toast.makeText(context,
                "Замовлення On-Line в розробці...", Toast.LENGTH_SHORT);
        toast.show();
    }
}
