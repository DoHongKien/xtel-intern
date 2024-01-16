package com.exception.handling.listener;


import com.exception.handling.config.AuditingAware;
import com.exception.handling.entity.Employee;
import com.exception.handling.entity.Log;
import com.exception.handling.repository.LoggingRepository;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogListener {

    @Autowired
    private ApplicationContext applicationContext;

    @PostPersist
    public void postPersist(Employee employee) {
        log.info("Save employee => Employee after change: " + employee);
        saveLog(employee, "Save");
    }

    @PostUpdate
    public void postUpdate(Employee employee) {
        log.info("After update employee => Employee after change data: " + employee);
        saveLog(employee, "Update");
    }

    @PostRemove
    public void postRemove(Employee employee) {
        saveLog(employee, "Remove");
    }

    private void saveLog(Employee employee, String action) {
        AuditingAware auditingAware = new AuditingAware();

        Log log = new Log();
        log.setEntityId(employee.getId());
        log.setAction(action);
        log.setCreatedBy(auditingAware.getCurrentAuditor().get());

        LoggingRepository loggingRepository = applicationContext.getBean(LoggingRepository.class);
        loggingRepository.save(log);
    }
}
