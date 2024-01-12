package com.example.paymentwithmultithread.service;

import com.example.paymentwithmultithread.entity.Product;

import java.util.List;

public interface ProductService {

    Product findProductById(Long productId);

    List<Product> findProductGreaterThanQuantity(Integer quantity);

    List<Product> findProductByQuantityAndPriceAllOf(Integer quantity, Double price);

    List<Product> findProductByQuantityAndPriceAnyOf(Integer quantity, Double price);

    Product saveProduct(Product product);
}
