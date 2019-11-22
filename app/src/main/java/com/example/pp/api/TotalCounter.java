package com.example.pp.api;

import android.util.Log;
import android.widget.TextView;
import com.example.pp.models.to.ProductTO;

import java.util.List;

public class TotalCounter {
    private TextView mTotal;

    public TotalCounter(TextView mTotal) {
        this.mTotal = mTotal;
    }

    private float sum(List<ProductTO> productTOList){
        float total = 0;
        for(ProductTO productTO : productTOList){
         total = total + productTO.getPrice()*productTO.getCount();
        }
        return total;
    }
    public void refresh(List<ProductTO> productTOList){
        float t = sum(productTOList);
        Log.i("total", String.valueOf(t));
        mTotal.setText(String.format("%.2f грн", t));
        //mTotal.setText("55");
        Log.i("textView", mTotal.getText().toString());
    }
}
