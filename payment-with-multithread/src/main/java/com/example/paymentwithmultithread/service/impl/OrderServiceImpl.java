package com.example.paymentwithmultithread.service.impl;

import com.example.paymentwithmultithread.entity.Order;
import com.example.paymentwithmultithread.entity.OrderDetail;
import com.example.paymentwithmultithread.entity.Product;
import com.example.paymentwithmultithread.model.dto.OrderDto;
import com.example.paymentwithmultithread.model.dto.PaymentDto;
import com.example.paymentwithmultithread.repository.OrderDetailRepository;
import com.example.paymentwithmultithread.repository.OrderRepository;
import com.example.paymentwithmultithread.repository.ProductRepository;
import com.example.paymentwithmultithread.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Boolean saveOrder(PaymentDto payments) throws Exception {

        Order order = Order.builder()
                .code(UUID.randomUUID().toString())
                .build();
        Order saveOrder = orderRepository.save(order);

        for(OrderDto payment: payments.getPayments()) {
            Optional<Product> product = productRepository.findById(payment.getProductId());
            if(product.isPresent()) {
                OrderDetail orderDetail = getOrderDetail(product, saveOrder);
                orderDetailRepository.save(orderDetail);
            } else {
                throw new Exception("Invalid");
            }
        }
        return true;
    }

    private OrderDetail getOrderDetail(Optional<Product> product, Order order) {
        return OrderDetail.builder()
                .productName(product.get().getName())
                .price(product.get().getPrice())
                .quantity(product.get().getQuantity())
                .product(product.get())
                .order(order)
                .build();
    }
}
