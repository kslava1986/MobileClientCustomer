package com.example.pp.util;

import com.example.pp.models.Product;
import com.example.pp.models.to.ProductTO;

import java.util.ArrayList;
import java.util.List;

public class UtilProduct {
    public static List<ProductTO> convertListToProductTO(List<Product> products){
        List<ProductTO> productTOList = new ArrayList<>();
        for(Product product : products){
            productTOList.add(createFromProduct(product));
        }
        return productTOList;
    }

    public static ProductTO createFromProduct(Product product){
        return new ProductTO(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }
}
