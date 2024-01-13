package com.example.paymentwithmultithread.controller;

import com.example.paymentwithmultithread.model.dto.PaymentDto;
import com.example.paymentwithmultithread.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> findAllOrders() {
        return ResponseEntity.ok(orderService.getOrderList());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(@RequestBody PaymentDto payments) throws Exception {
        orderService.saveOrder(payments);
        return ResponseEntity.ok("OK");
    }


}
