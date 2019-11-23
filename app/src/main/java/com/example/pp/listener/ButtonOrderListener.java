package com.example.pp.listener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

import com.example.pp.models.to.ProductTO;
import com.example.pp.rest.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                .sendOrder(shopId,telCustomer,productTOList)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        String msg = response.body();
                        if(msg != null && msg.length() > 0){
                            Toast toast = Toast.makeText(context,
                                    msg, Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(context,
                                    "Щось пішло не так...", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        Toast toast = Toast.makeText(context,
                                "Замовлення було відправлене...", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
    }
}
