package com.republic.kumquat;

import com.republic.kumquat.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSearchImpl {


    List <Product> productList = new ArrayList<Product>();
    public ProductSearchImpl()
    {
        Product product = new Product();
        product.setBrand("Tomy");
        productList.add(product);

    }

    public List<Product> findByBrand(String brand)
    {

        List <Product> resultList = productList.stream()
                .filter(product->product.getBrand().equals(brand))
                .collect(Collectors.toList());

        System.out.println("*************************");
        resultList.forEach(System.out::println);
        return new ArrayList<Product>();
    }
}
