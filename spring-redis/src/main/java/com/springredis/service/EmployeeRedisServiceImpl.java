package com.springredis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springredis.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeRedisServiceImpl implements EmployeeRedisService{

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public void clear() {
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().flushAll();
    }

    @Override
    public List<Employee> getAllEmployee(int pageNumber, int pageSize) throws JsonProcessingException {
        String key = getKey(pageNumber, pageSize);
        String json = (String) redisTemplate.opsForValue().get(key);
        return json != null ? objectMapper.readValue(json, new TypeReference<>() {
        }) : null;
    }

    @Override
    public List<Employee> saveAllEmployee(List<Employee> employees, int pageNumber, int pageSize) throws JsonProcessingException {
        String key = getKey(pageNumber, pageSize);
        String json = objectMapper.writeValueAsString(employees);
        redisTemplate.opsForValue().set(key, json);
        return getAllEmployee(pageNumber, pageSize);
    }

    private String getKey(int pageNumber, int pageSize) {
        return String.format("all_employees:%d:%d", pageNumber, pageSize);
    }
}
