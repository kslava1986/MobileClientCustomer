package com.example.pp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pp.models.to.ProductTO;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context mContext;
    private List<ProductTO> mProductsTO;

    public ProductAdapter(Context mContext, List<ProductTO> mProductsTO) {
        this.mContext = mContext;
        this.mProductsTO = mProductsTO;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.product_item, viewGroup,  false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, final int position) {
        ProductTO productTO = mProductsTO.get(position);

        productViewHolder.mName.setText(productTO.getName());
        productViewHolder.mDescription.setText(productTO.getDescription());
        productViewHolder.mPrice.setText(String.format("%n грн", productTO.getPrice()));
        productViewHolder.mCount.setText(String.valueOf(productTO.getCount()));

        productViewHolder.mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductTO temp = mProductsTO.get(position);
                temp.setCount(temp.getCount() + 1);

                notifyItemChanged(position);
            }
        });

        productViewHolder.mDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductTO temp = mProductsTO.get(position);
                temp.setCount(temp.getCount() - 1);

                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductsTO.size();
    }


    public  class  ProductViewHolder extends  RecyclerView.ViewHolder {
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
        }
    }
}
