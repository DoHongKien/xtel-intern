package com.example.paymentwithmultithread.service.impl;

import com.example.paymentwithmultithread.entity.Product;
import com.example.paymentwithmultithread.model.dto.SearchRequest;
import com.example.paymentwithmultithread.model.dto.SearchSpecification;
import com.example.paymentwithmultithread.repository.ProductRepository;
import com.example.paymentwithmultithread.service.ProductService;
import com.example.paymentwithmultithread.service.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> searchProduct(SearchRequest request) {
        SearchSpecification<Product> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return productRepository.findAll(specification, pageable);
    }

    @Override
    public Product findProductById(Long productId) {
        Specification<Product> specification = Specification
                .where(ProductSpecification.findProductById(productId));
        return productRepository.findOne(specification).orElseThrow();
    }

    // Tim san pham lon hon so luong = ?
    @Override
    public List<Product> findProductGreaterThanQuantity(Integer quantity) {
        Specification<Product> specification = Specification
                .where(ProductSpecification.hasQuantityGreaterThan(quantity));
        return productRepository.findAll(specification);
    }

    // Thoa man ca 2 dieu kien quantity va price khi dung allOf()
    @Override
    public List<Product> findProductByQuantityAndPriceAllOf(Integer quantity, Double price) {
        Specification<Product> specification = Specification.allOf(
                        ProductSpecification.hasQuantityLessThanOrEqualTo(quantity),
                        ProductSpecification.hasPriceGreaterThanOrEqualTo(price));
        return productRepository.findAll(specification);
    }

    // Thoa man 1 trong 2 dieu kien quantity va price khi dung anyOf()
    @Override
    public List<Product> findProductByQuantityAndPriceAnyOf(Integer quantity, Double price) {
        Specification<Product> specification = Specification.anyOf(
                ProductSpecification.hasQuantityLessThanOrEqualTo(quantity),
                ProductSpecification.hasPriceGreaterThanOrEqualTo(price));
        return productRepository.findAll(specification);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
