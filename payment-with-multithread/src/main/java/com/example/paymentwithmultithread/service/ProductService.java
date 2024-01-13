package com.example.paymentwithmultithread.service;

import com.example.paymentwithmultithread.entity.Product;
import com.example.paymentwithmultithread.model.dto.SearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Page<Product> searchProduct(SearchRequest request);

    Product findProductById(Long productId);

    List<Product> findProductGreaterThanQuantity(Integer quantity);

    List<Product> findProductByQuantityAndPriceAllOf(Integer quantity, Double price);

    List<Product> findProductByQuantityAndPriceAnyOf(Integer quantity, Double price);

    Product saveProduct(Product product);
}
