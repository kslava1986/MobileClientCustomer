package com.example.pp;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pp.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context mContext;
    private List<Product> mProducts;

    public ProductAdapter(Context mContext, List<Product> mProducts) {
        this.mContext = mContext;
        this.mProducts = mProducts;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.product_item, viewGroup,  false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int position) {
        Product product = mProducts.get(position);
        productViewHolder.mName.setText(product.getName());
        productViewHolder.mDescription.setText(product.getDescription());
        productViewHolder.mPrice.setText(String.format("%n грн", product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }


    public  class  ProductViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mImage;
        private TextView mName;
        private TextView mDescription;
        private TextView mPrice;
        private TextView mCount;
        private Button mAdd;
        private Button mDel;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.item_order_image);
            mName = itemView.findViewById(R.id.item_order_name);
            mDescription = itemView.findViewById(R.id.item_order_description);
            mPrice = itemView.findViewById(R.id.item_order_price);
            mCount = itemView.findViewById(R.id.item_order_count);
            mAdd = itemView.findViewById(R.id.item_order_add);
            mDel = itemView.findViewById(R.id.item_order_del);

            mAdd.setOnClickListener(this);
            mDel.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.item_order_add:
                    onAdd();
                case R.id.item_order_del:
                    onDel();
            }
        }

        void onAdd(){

            String value = this.mCount.getText().toString();
            int num = getCorrectNum(value);
            num++;
            this.mCount.setText(num);
        }
        void  onDel(){
            String value = this.mCount.getText().toString();
            int num = getCorrectNum(value);
            num--;
            if(num<0) {
                this.mCount.setText(0);
            } else {
                this.mCount.setText(num);
            }
        }
        private int getCorrectNum(String value)
        {
            try {
                return  Integer.parseInt(value);
            }
            catch (NumberFormatException e)
            {
                return 0;
            }

        }
    }
}
