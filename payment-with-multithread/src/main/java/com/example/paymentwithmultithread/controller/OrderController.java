package com.example.paymentwithmultithread.controller;

import com.example.paymentwithmultithread.entity.Order;
import com.example.paymentwithmultithread.exception.IdNotFoundException;
import com.example.paymentwithmultithread.model.dto.PaymentDto;
import com.example.paymentwithmultithread.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findAllOrders() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(orderService.getOrderList());
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOrder(@RequestBody PaymentDto payments) throws IdNotFoundException, ExecutionException, InterruptedException {
        orderService.saveOrder(payments);
        return ResponseEntity.ok("OK");
    }


}
