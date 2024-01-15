package com.example.paymentwithmultithread.service.specification;

import com.example.paymentwithmultithread.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> hasQuantityLessThanOrEqualTo(Integer quantity) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("quantity"), quantity);
    }

    public static Specification<Product> hasPriceGreaterThanOrEqualTo(Double price) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> hasQuantityGreaterThan(Integer quantity) {
        return Specification.where((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("quantity"), quantity));
    }

    public static Specification<Product> findProductById(Long productId) {
        return Specification.where((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), productId));
    }
}