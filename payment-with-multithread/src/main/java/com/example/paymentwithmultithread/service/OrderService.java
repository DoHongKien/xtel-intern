package com.example.paymentwithmultithread.service;

import com.example.paymentwithmultithread.entity.Order;
import com.example.paymentwithmultithread.exception.IdNotFoundException;
import com.example.paymentwithmultithread.model.dto.PaymentDto;

import java.util.List;

public interface OrderService {

    List<Order> getOrderList();

    void saveOrder(PaymentDto payments) throws IdNotFoundException;
}
