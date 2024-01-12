package com.example.paymentwithmultithread.repository;

import com.example.paymentwithmultithread.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
