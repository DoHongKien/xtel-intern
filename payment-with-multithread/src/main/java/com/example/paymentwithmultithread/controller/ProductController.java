package com.example.paymentwithmultithread.controller;

import com.example.paymentwithmultithread.entity.Product;
import com.example.paymentwithmultithread.model.dto.SearchRequest;
import com.example.paymentwithmultithread.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(productService.searchProduct(request));
    }

    @GetMapping("/get-product")
    public ResponseEntity<?> findProductById(@RequestParam Long productId) {
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @GetMapping("/get-quantity-greater-than")
    public ResponseEntity<?> findProductByQuantityGreaterThan(@RequestParam Integer quantity) {
        return ResponseEntity.ok(productService.findProductGreaterThanQuantity(quantity));
    }

    @GetMapping("/get-quantity-price-all-of")
    public ResponseEntity<?> findProductByQuantityAndPriceAllOf(@RequestParam Integer quantity, @RequestParam Double price) {
        return ResponseEntity.ok(productService.findProductByQuantityAndPriceAllOf(quantity, price));
    }

    @GetMapping("/get-quantity-price-any-of")
    public ResponseEntity<?> findProductByQuantityAndPriceAnyOf(@RequestParam Integer quantity, @RequestParam Double price) {
        return ResponseEntity.ok(productService.findProductByQuantityAndPriceAnyOf(quantity, price));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }
}
