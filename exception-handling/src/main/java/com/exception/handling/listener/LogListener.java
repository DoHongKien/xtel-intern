package com.exception.handling.listener;


import com.exception.handling.entity.Employee;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogListener {

    @PrePersist
    public void preUpdate(Employee employee) {
        log.info("Before update employee => Employee before change data: " + employee);
    }

    @PostPersist
    public void postPersist(Employee employee) {
        log.info("Save employee => Employee after change: " + employee);
    }

    @PostUpdate
    public void postUpdate(Employee employee) {
        log.info("After update employee => Employee after change data: " + employee);
    }
}
