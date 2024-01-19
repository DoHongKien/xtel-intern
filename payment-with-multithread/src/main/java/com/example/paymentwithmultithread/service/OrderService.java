package com.example.paymentwithmultithread.service;

import com.example.paymentwithmultithread.entity.Order;
import com.example.paymentwithmultithread.exception.IdNotFoundException;
import com.example.paymentwithmultithread.model.dto.PaymentDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface OrderService {

    List<Order> getOrderList() throws ExecutionException, InterruptedException;

    void saveOrder(PaymentDto payments) throws IdNotFoundException, ExecutionException, InterruptedException;
}
