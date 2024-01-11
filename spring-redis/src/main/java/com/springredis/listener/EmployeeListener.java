package com.springredis.listener;

import com.springredis.entity.Employee;
import jakarta.persistence.PostRemove;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeListener {

    @PostRemove
    public void postRemove(Employee employee) {
      log.info("Employee has equal {} removed from the database", employee.getId());
    }
}
