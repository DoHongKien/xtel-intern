package com.exception.handling.repository;

import com.exception.handling.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggingRepository extends JpaRepository<Log, Long> {
}
