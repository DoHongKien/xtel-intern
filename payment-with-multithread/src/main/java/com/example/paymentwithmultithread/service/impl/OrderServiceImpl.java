package com.example.paymentwithmultithread.service.impl;

import com.example.paymentwithmultithread.entity.Order;
import com.example.paymentwithmultithread.entity.OrderDetail;
import com.example.paymentwithmultithread.entity.Product;
import com.example.paymentwithmultithread.exception.IdNotFoundException;
import com.example.paymentwithmultithread.model.dto.OrderDto;
import com.example.paymentwithmultithread.model.dto.PaymentDto;
import com.example.paymentwithmultithread.repository.OrderDetailRepository;
import com.example.paymentwithmultithread.repository.OrderRepository;
import com.example.paymentwithmultithread.repository.ProductRepository;
import com.example.paymentwithmultithread.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final ProductRepository productRepository;

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @Transactional
    @Async
    @Override
    public synchronized void saveOrder(PaymentDto payments) throws IdNotFoundException {
        log.info("Saving order " + payments + " with thread " + Thread.currentThread().getName());
        Order order = Order.builder()
                .code(UUID.randomUUID().toString())
                .build();
        Order saveOrder = orderRepository.save(order);

        for (OrderDto payment : payments.getPayments()) {
            Product product = productRepository.findById(payment.getProductId())
                    .orElseThrow(() -> new IdNotFoundException("Product not found with id: " + payment.getProductId()));

            OrderDetail orderDetail = getOrderDetail(product, saveOrder);
            orderDetailRepository.save(orderDetail);
        }
    }

    private OrderDetail getOrderDetail(Product product, Order order) {
        return OrderDetail.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .product(product)
                .order(order)
                .build();
    }
}
