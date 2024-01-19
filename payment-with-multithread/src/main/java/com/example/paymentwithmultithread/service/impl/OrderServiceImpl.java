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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final ProductRepository productRepository;

    @Override
    public List<Order> getOrderList() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Order>> completableFuture = new CompletableFuture<>();
        completableFuture.completeAsync(orderRepository::findAll);
        return completableFuture.get();
    }

    @Transactional
    @Async
    @Override
    public void saveOrder(PaymentDto payment) throws IdNotFoundException, ExecutionException, InterruptedException {
        log.info("Saving order " + payment + " with thread " + Thread.currentThread().getName());

        CompletableFuture<Order> orderFuture = CompletableFuture.supplyAsync(() -> {
            Order order = Order.builder()
                    .code(UUID.randomUUID().toString())
                    .build();
            return orderRepository.save(order);
        });

        for (OrderDto pay : payment.getPayments()) {
            Product product = productRepository.findById(pay.getProductId())
                    .orElseThrow(() -> new IdNotFoundException("Product not found with id: " + pay.getProductId()));
            OrderDetail orderDetail = getOrderDetail(product, orderFuture.get());
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
