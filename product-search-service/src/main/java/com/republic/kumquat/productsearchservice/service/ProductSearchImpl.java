package com.republic.kumquat.productsearchservice.service;

import com.republic.kumquat.productsearchservice.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSearchImpl {

    Logger logger = LoggerFactory.getLogger(ProductSearchImpl.class);

    List <Product> productList = new ArrayList<Product>();
    public ProductSearchImpl()
    {
        Product product = new Product();
        product.setBrand("Tomy");
        product.setColor("Red");
        product.setPrice(100);
        productList.add(product);

        Product product1 = new Product();
        product1.setBrand("Crocodile");
        product1.setColor("Blue");
        product1.setPrice(150);
        productList.add(product1);

        /*Product product2 = new Product();
        product.setBrand("Levis");
        productList.add(product2);*/


    }

    public List<Product> findByBrand(String brand)
    {

        List <Product> resultList = productList.stream()
                .filter(product->product.getBrand().equals(brand))
                .collect(Collectors.toList());

        logger.error("*************************");
        resultList.forEach(result->{
            logger.error(result.getBrand());
                });

        return resultList;
    }

    public List<Product> findByColor(String color)
    {
        List <Product> resultList = productList.stream()
                .filter(product->product.getColor().equals(color))
                .collect(Collectors.toList());

        logger.error("*************************");
        resultList.forEach(result->{
            logger.error(result.getBrand());
        });

        return resultList;
    }

    public List<Product> findByPriceRange(int price)
    {
        List <Product> resultList = productList.stream()
                .filter(product->product.getPrice()<price && product.getPrice()>price)
                .collect(Collectors.toList());

        logger.error("*************************");
        resultList.forEach(result->{
            logger.error(result.getBrand());
        });

        return resultList;
    }
}
