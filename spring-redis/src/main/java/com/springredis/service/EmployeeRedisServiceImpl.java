package com.springredis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springredis.entity.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeRedisServiceImpl implements EmployeeRedisService{

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public void clearAll() {
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().flushAll();
    }

    @Override
    public void clearByKey(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public String getKey(Long employeeId) {
        return getKeyById(employeeId);
    }

    @Override
    public List<Employee> getAllEmployee(int pageNumber, int pageSize) {
        try {
            String key = getKeyByPage(pageNumber, pageSize);
            String json = (String) redisTemplate.opsForValue().get(key);
            return json != null ? objectMapper.readValue(json, new TypeReference<>() {}) : null;
        } catch (JsonProcessingException e) {
            log.error("Error occurred while parsing JSON: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        try {
            String key = getKeyById(employeeId);
            String json = (String) redisTemplate.opsForValue().get(key);
            return json != null ? objectMapper.readValue(json, new TypeReference<>() {}) : null;
        } catch (JsonProcessingException e) {
            log.error("Error occurred while parsing JSON: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void saveAllEmployee(List<Employee> employees, int pageNumber, int pageSize) {
        try {
            String key = getKeyByPage(pageNumber, pageSize);
            String json = objectMapper.writeValueAsString(employees);
            redisTemplate.opsForValue().set(key, json);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while save list employee to redis: " + e.getMessage(), e);
        }
    }

    @Override
    public void saveEmployee(Employee employee) {
        try {
            String key = getKeyById(employee.getId());
            String json = objectMapper.writeValueAsString(employee);
            redisTemplate.opsForValue().set(key, json);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while save employee to redis: " + e.getMessage(), e);
        }
    }

    private String getKeyByPage(int pageNumber, int pageSize) {
        return String.format("all_employees:%d:%d", pageNumber, pageSize);
    }

    private String getKeyById(Long employeeId) {
        return String.format("employee:%d", employeeId);
    }
}
