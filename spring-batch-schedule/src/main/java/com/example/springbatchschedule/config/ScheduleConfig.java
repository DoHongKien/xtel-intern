package com.example.springbatchschedule.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
@Slf4j
public class ScheduleConfig {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    // Viết hàm lên lịch sau mỗi 60s đọc file thêm dữ liệu vào database 1 lần
    @Scheduled(fixedRate = 60, initialDelay = 0, timeUnit = TimeUnit.SECONDS)
    public void scheduleJob() {
        log.info("Start schedule job");
        try {
            jobLauncher.run(job, new JobParametersBuilder()
                            .addLong("uniqueness", System.nanoTime())
                            .toJobParameters());
        } catch (Exception e) {
            log.error("Error occur when execute schedule job {}", e.getMessage());
        }
        log.info("Finished schedule job");
    }
}
