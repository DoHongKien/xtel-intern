package com.example.paymentwithmultithread.repository;

import com.example.paymentwithmultithread.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
