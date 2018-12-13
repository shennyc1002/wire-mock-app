package com.republic.kumquat.productsearchservice.controller;


import com.republic.kumquat.productsearchservice.entity.Product;
import com.republic.kumquat.productsearchservice.service.ProductSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class ProductSearchController {

    Logger logger = LoggerFactory.getLogger(ProductSearchController.class);
    ProductSearchImpl productService;

    @Autowired
    public ProductSearchController(ProductSearchImpl productService)
    {

        this.productService = productService;
    }


    @GetMapping(value="/brand/{brandname}")
    public List<Product> findByBrand(@PathVariable ("brandname") String brandname)
    {
        logger.error("++++++++++++++++++++++");
        return productService.findByBrand(brandname);

    }

    @GetMapping(value="/color/{color}")
    public List<Product> findByColor(@PathVariable ("color") String color)
    {
        logger.error("++++++++++++++++++++++");
        return productService.findByColor(color);

    }

}
